package sem03Serialisation.task1;
// сериализация - процесс преобразования объектов в последовательность байт
// чтобы хранить данные, передавать по сети
// ещё: можно хранить конфиги, локальные бд, коллекции объектов

import java.io.*;

/**
 * Задача 1
 *
 * Создайте класс UserData с полями String name, int age, transient String password
 * Поле password должно быть отмечено ключевым словом transient.
 * Реализуйте интерфейс Serializable в вашем классе.
 * В методе main создайте экземпляр класса UserData и инициализируйте его данными.
 * Сериализуйте этот объект в файл, использовав ObjectOutputStream в сочетании c FileOutputStream
 *
 * Задача 2
 * Десереализуйте объект из ранее соданного файла обратно в объект Java,
 * используя ObjectInputStream в сочетании с FileInputStream.
 * Введите данные десериализованного объекта UserData
 * Сравните данные до сериализации и после, особенно обратить внимание
 * на поле transient.
 * Обсудите, почему это поле не было сохранено после десериализации.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UserData user = new UserData("A", 3, "12");
        // отличие от файлрайтер/редер, названные заточены на работу с символами и строками
        // файлоутпут - это работа с общим и целым доком (бинарные данные, массив байт, поток данных)
        // с соответствующими методами

        // сериализация
        try(FileOutputStream fileOutputStream = new FileOutputStream("user.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(user);
            System.out.println("Объект UserData успешно сериализован.");

        }
        // десереализация
        try(FileInputStream fileInputStream = new FileInputStream("user.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            user = (UserData) objectInputStream.readObject();
            System.out.println("Объект UserData успешно десериализован.");
        }

        System.out.println("\nИмя: " + user.getName());
        System.out.println("Возраст: " + user.getAge());
        System.out.println("Пароль (должет быть null, так как transient): " + user.getPassword());
    }
}
