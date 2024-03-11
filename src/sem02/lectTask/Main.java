package sem02.lectTask;
//reflection API
//9 ������ ������������������� �����.
// ������, �.�. �� �����, ����� ��������� ���� �������
// .forName ��������� ��� ����� ����� �� �����

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> car = Class.forName("sem02.lectTask.Car"); // ����� �����
        Constructor<?>[] constructors = car.getConstructors(); // ����� �����������, �������� � ���������� constructors

        Object gaz = constructors[0].newInstance("GAZ"); // ������� ������ ����� ������
        System.out.println(gaz);

        // ����� �������������� �� ��������� ���� ������ car, ��� ����� ������� ������ fields
        // ��� ������ ��� �����, ������� ���� � ����� �������.
        // ������ ������� �����, � �� ������ ������� ������ ������ �����.
        Field[] fields = gaz.getClass().getFields();
        int tmp = fields[fields.length - 1].getInt(gaz);
        fields[fields.length - 1].setInt(gaz, tmp * 2);

        // ����� �������� � � �������� ������

        Method[] methods = gaz.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }

        System.out.println(gaz);
    }


}
