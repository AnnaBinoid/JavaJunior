package sem03Serialisation.task1;
// ������������ - ������� �������������� �������� � ������������������ ����
// ����� ������� ������, ���������� �� ����
// ���: ����� ������� �������, ��������� ��, ��������� ��������

import java.io.*;

/**
 * ������ 1
 *
 * �������� ����� UserData � ������ String name, int age, transient String password
 * ���� password ������ ���� �������� �������� ������ transient.
 * ���������� ��������� Serializable � ����� ������.
 * � ������ main �������� ��������� ������ UserData � ��������������� ��� �������.
 * ������������ ���� ������ � ����, ����������� ObjectOutputStream � ��������� c FileOutputStream
 *
 * ������ 2
 * �������������� ������ �� ����� ��������� ����� ������� � ������ Java,
 * ��������� ObjectInputStream � ��������� � FileInputStream.
 * ������� ������ ������������������ ������� UserData
 * �������� ������ �� ������������ � �����, �������� �������� ��������
 * �� ���� transient.
 * ��������, ������ ��� ���� �� ���� ��������� ����� ��������������.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UserData user = new UserData("A", 3, "12");
        // ������� �� ����������/�����, ��������� �������� �� ������ � ��������� � ��������
        // ���������� - ��� ������ � ����� � ����� ����� (�������� ������, ������ ����, ����� ������)
        // � ���������������� ��������

        // ������������
        try(FileOutputStream fileOutputStream = new FileOutputStream("user.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(user);
            System.out.println("������ UserData ������� ������������.");

        }
        // ��������������
        try(FileInputStream fileInputStream = new FileInputStream("user.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            user = (UserData) objectInputStream.readObject();
            System.out.println("������ UserData ������� ��������������.");
        }

        System.out.println("\n���: " + user.getName());
        System.out.println("�������: " + user.getAge());
        System.out.println("������ (������ ���� null, ��� ��� transient): " + user.getPassword());
    }
}
