package sem02.task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// добавляем две служебные аннотации
// Аннотация - некий атрибут (маркер), которую можно назначить какому-
// либо полю или классу и далее, через механизм рефлексии взаимодействовать
// с ними, т.к. я смогу получить к ним доступ через аннотацию.

@Retention(RetentionPolicy.RUNTIME)

// говорит о том, что данную аннотацию можно применять только к конкретному типу.
@Target(ElementType.TYPE)

public @interface Table {

    // Здесь name - имя таблицы
    String name();

}
