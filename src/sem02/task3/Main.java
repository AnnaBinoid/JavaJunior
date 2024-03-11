package sem02.task3;

import java.util.UUID;

/**
 * Задача 3: реализовать простой фреймворк для создания SQL-запроса
 * на основе Java объектов.
 *
 *  Необходимо разработать простой фреймворк. Он должен позволять аннотировать
 *  классы и поля для связывания их с таблицами и столбцами в базе данных.
 *
 * 1. Аннотация для маппинга:
 *      Создайте анностации, такие как @Entity, @Table, @Column
 *      для маппинка классов, таблиц и полей в базе данных.
 *
 *  2. Механизм генерации SQL-запросов:
 *      Реализуйте класс QueryBuilder, который может принимать объект
 *      и генерировать SQL-запросы для выполнения операций CRUD
 *      (create, read, update, delete) на основе аннотаций.
 *      Используйте Reflection API для получения метаданных класса,
 *      аннотированного объекта, чтобы построить соответствующий
 *      SQL-запрос.
 *
 *  3. Пример использования:
 *      Создайте простой класс, аннотированный для маппинга с базой данных.
 *      Используйте ваш фреймворк для генерации SQL-запросов для
 *      различных операций, таких как вставка, выборка, обновление, удаление.
 */

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Anna", "anna@mail.ru");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        // нагенерируем запросы, выдаст GUUID
        QueryBuilder queryBuilder = new QueryBuilder();
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("Insert Query: %s.\n", insertQuery);

        // Здесь объект нам не нужен, передаем только тип
        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.printf("Select Query: %s.\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("Update Query: %s.\n", updateQuery);
    }
}
