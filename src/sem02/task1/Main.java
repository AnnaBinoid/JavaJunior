package sem02.task1;
// ������������ reflection API � RAM-��������, ������������ � ���� ������
// � ��������� ������������ ���� - �������� ����������, ���������� � ������

import javax.naming.NameNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ������ 1: ������ Reflection API
 * �������� ���������� � ������ Person � �������������� reflection API:
 * �������� �� ����� ��� ���� � ������ ������.
 * �������� ��������� ������ Person � �������������� reflection API,
 * ���������� �������� ����� � �������� ������.
 * ���������� ��������� ���������� ��� ����������� �����������
 * �������������� � reflection API.
 */
public class Main {
    public static void main(String[] args) throws NameNotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
        // �������� ��������� ������ - ��� �� ��������� ������,
        // ��� �����, ������� ��������� ��������� ���.
        // ? - ������������, ��� ���������� person ����� ������������ ������ ����.
        Class<?> personalClass = Class.forName("sem02.task1.Person");
        System.out.println(personalClass + "\n");

        // �������� � ������� ������ ���� �����
        Field[] fields = personalClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("����: " + fields[i]);
        }
        System.out.printf("\n");

        //�������� ��� ������������ ������
        Constructor[] constructors = personalClass.getConstructors();
        // ����� ������� ������ ����������
        constructors[0].getParameters();

        // � ��� ����� ��� ������ ������ - ����� ��������� ������ Person
        Object personInstance = constructors[0].newInstance(null);


        // � ����� �� �������� � ������ ����������� �������, ������������ ��� ���
        Field nameField = personalClass.getDeclaredField("name");

        // ��� ����, ����� �������� � ��������� ������
        // , ����� ���������� ���������� setAccessible
        nameField.setAccessible(true);
        // ������ ����� ����� �� ����� ������ �������� �������� �����
        nameField.set(personInstance, "Alice");

        Field ageFiels = personalClass.getDeclaredField("age");
        ageFiels.setAccessible(true);
        ageFiels.set(personInstance, 40);

        personalClass.getDeclaredMethod("displayInfo");
        Method displayInfoMethod = personalClass.getDeclaredMethod("displayInfo");
        // � invoke ������ �������� - ������ �� ������, �� ������� �� �������� ���� �����
        // ����� - ��������� ������, ���� ��� ����.
        displayInfoMethod.invoke(personInstance);

    }
}
