package sem02.task3;

import java.util.UUID;

/**
 * ������ 3: ����������� ������� ��������� ��� �������� SQL-�������
 * �� ������ Java ��������.
 *
 *  ���������� ����������� ������� ���������. �� ������ ��������� ������������
 *  ������ � ���� ��� ���������� �� � ��������� � ��������� � ���� ������.
 *
 * 1. ��������� ��� ��������:
 *      �������� ����������, ����� ��� @Entity, @Table, @Column
 *      ��� �������� �������, ������ � ����� � ���� ������.
 *
 *  2. �������� ��������� SQL-��������:
 *      ���������� ����� QueryBuilder, ������� ����� ��������� ������
 *      � ������������ SQL-������� ��� ���������� �������� CRUD
 *      (create, read, update, delete) �� ������ ���������.
 *      ����������� Reflection API ��� ��������� ���������� ������,
 *      ��������������� �������, ����� ��������� ���������������
 *      SQL-������.
 *
 *  3. ������ �������������:
 *      �������� ������� �����, �������������� ��� �������� � ����� ������.
 *      ����������� ��� ��������� ��� ��������� SQL-�������� ���
 *      ��������� ��������, ����� ��� �������, �������, ����������, ��������.
 */

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Anna", "anna@mail.ru");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        // ������������ �������, ������ GUUID
        QueryBuilder queryBuilder = new QueryBuilder();
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("Insert Query: %s.\n", insertQuery);

        // ����� ������ ��� �� �����, �������� ������ ���
        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.printf("Select Query: %s.\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("Update Query: %s.\n", updateQuery);
    }
}
