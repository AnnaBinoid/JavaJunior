package sem02.task3;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {

    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        // добавление данных в некую таблицу в некие колонки
        StringBuilder query = new StringBuilder("INSERT INTO ");

        // содержит ли мой метод некую аннотацию?
        // теперь если класс содержит аннотацию Table, с ним можно работать.
        if (clazz.isAnnotationPresent(Table.class)) {

            // нужно получить описание аннотации
            Table tableAnnotation = clazz.getAnnotation(Table.class);

            // и, обратившись к ней уже получить доступ к полям,
            // указанным в рамках этой аннотации
            query
                    .append(tableAnnotation.name())
                    .append(" (");

            // теперь в рамках запроса пройдем по всем полям field
            // и уточним, содержат ли они нужную нам аннотацию для дальнейшей работы

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {

                // если аннотация доступна - будем работать.
                if(field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    query.append(columnAnnotation.name()).append(", ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(") VALUES (");

            // теперь пройдем по всем полям снова и вытащим значения.
            for (Field field : fields) {

                // нам нужно обязательно получить доступ, если вдруг поле закрыто
                if(field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);

                    // пробуем прочитать значение поля
                    query.append("'").append(field.get(obj)).append("', ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(")");
            return query.toString();
        }
        else {
            return null;
        }
    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey) {

        StringBuilder query = new StringBuilder("SELECT * FROM ");

        // теперь мы говорим о том, что для запроса нам объект не нужен
        if (clazz.isAnnotationPresent(Table.class)) {
            // получаем описание аннотации Table
            Table tableAnnotation = clazz.getAnnotation(Table.class);

            // на основе её форируем запрос (наименование таблицы + WHERE)
            query.append(tableAnnotation.name()).append(" WHERE ");
        }
        // получаем весь список полей нашего типа
        Field[] fields = clazz.getDeclaredFields();

        // нужно пройтись по всем, т.к. мы не знаем, какое наименование
        // содержит ключ нашего поля в таблице (откуда мы знаем, что наш
        // ключ называется id? Значит через механизм рефлексии мы найдем
        // поле с аннотацией Column и у которого значение primaryKey = екгу
        // взять наименование этой колонки в базе данных , и на основании
        // данных из колонки составить запрос.
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name())
                            .append(" = ").append(primaryKey);
                    break;
                }
            }
        }

        return query.toString();
    }

    // запрос для обновления данных
    public String buildUpdateQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" SET ");

            // мы сейчас пройдем по всем полям, и все поля будут обновляться.
            // Кроме ключа, потому что по ключу мы будем делать условия
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    // проверяю. если первичный ключ = то сразу завершаю работу
                    // текущей итерации цикла
                    if (columnAnnotation.primaryKey())
                        continue;
                    query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("', ");
                }
            }

                if (query.charAt(query.length() - 2) == ',') {
                    query.delete(query.length() - 2, query.length());
                }

                query.append(" WHERE ");

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        field.setAccessible(true);
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        if (columnAnnotation.primaryKey()) {
                            query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("'");
                            break;
                        }
                    }
                }
            return query.toString();
        }
        else {
            return null;
        }
    }

    /**
     * TODO: Доработать в рамках домашней работы
     * @param clazz
     * @param primaryKey
     * @return
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        StringBuilder query = new StringBuilder("DELETE FROM ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = ").append(primaryKey);
                        break;
                    }
                }
            }
        }

        return query.toString();
    }
}
