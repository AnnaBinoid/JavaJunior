package sem02.task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // ������������ ��������� ���������
// ������ ��������� ��������� ��� � ���������� �����
// ����� ���� ������������ ������ ��� �����.
// ������ �������, � ����� �������� ������������� ������ ����
// ������ - �������� �� ���� primary key
@Target(ElementType.FIELD)

public @interface Column {
    String name();

    boolean primaryKey() default false;
}
