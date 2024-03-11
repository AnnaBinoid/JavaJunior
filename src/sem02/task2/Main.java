package sem02.task2;

import java.lang.reflect.Field;

/**
 * ������ 2: ���������� Reflection API � ����������
 *
 * ���������� ���������� �����, ������� ��������� ������
 * � ������� � ������� �������� ���� ��� ����������.
 * �������� ����� Car � ���������� ������ (��������, ������,
 * ����, ��� �������).
 * ��������� Reflection API ��� ������ �������� �����
 * ���������� ������� ������ Car � �������������� �����
 * ���������� ������
 */

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // ��� ���� ������
        Car car = new Car ("Toyota", "blue", 2022);
        task2(car);
    }
    //������� ���������� �����.

    private static  <T> void  task2 (T obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // ���� ������ � private ����
            System.out.printf("%s : %s\n", field.getName(), field.get(obj));
        }
    }

}
