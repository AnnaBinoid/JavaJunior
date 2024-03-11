package sem02.task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // обязательная служебная аннотация
// теперь аннотация связывает нас с конкретным полем
// может быть использована только для полей.
// сможем указать, с какой колонкой отождествляет данное поле
// второй - является ли поле primary key
@Target(ElementType.FIELD)

public @interface Column {
    String name();

    boolean primaryKey() default false;
}
