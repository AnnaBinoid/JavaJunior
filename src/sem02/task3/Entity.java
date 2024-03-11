package sem02.task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// показывает, что если данная аннотация присутствует у нашего типа,
// значит мы можем  отождествлять этот тип с базой данных.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface Entity {


}
