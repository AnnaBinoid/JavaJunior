package hw03;

import java.io.*;

public class Main {    /**
 * Задача 1
 * ========
 Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
 Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными.
 Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла.
 Выведите все поля объекта, включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
 */
public static void main(String[] args) throws IOException, ClassNotFoundException {
    Student userA = new Student("Ann", 10, 5.0);
    Student userB = new Student("Bob", 11, 3.5);

    try(FileOutputStream fileOutputStream = new FileOutputStream("hw03.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
        objectOutputStream.writeObject(userA);
        System.out.printf("\nObject %s serialized successfully.", userA.getName());
        objectOutputStream.writeObject(userB);
        System.out.printf("\nObject %s serialized successfully.", userB.getName());
    }

    try(FileInputStream fileInputStream = new FileInputStream("hw03.bin");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
        userA = (Student)objectInputStream.readObject();
        System.out.printf("\nObject %s deserialized successfully.", userA.getName());
        userB = (Student)objectInputStream.readObject();
        System.out.printf("\nObject %s deserialized successfully.", userB.getName());
    }

    System.out.println("\nUserA:");
    System.out.println("Name: " + userA.getName());
    System.out.println("Age: " + userA.getAge());
    System.out.println("GPA: " + userA.getGPA());

    System.out.println("\nUserB:");
    System.out.println("Name: " + userB.getName());
    System.out.println("Age: " + userB.getAge());
    System.out.println("GPA: " + userB.getGPA());

    System.out.printf("\nAnn's GPA (is null): %s\nBob's GPA (is null): %s.It is because of transient. " +
            "It tell's us that this parameter should not be serialized.", userA.getGPA(), userB.getGPA());

}
}
