package sem02.task1;
// используется reflection API в RAM-системах, тестировании и ряде других
// у рефлексии единственная цель - создание механизмов, помогающих в работе

import javax.naming.NameNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Задача 1: основы Reflection API
 * Получите информацию о классе Person с использованием reflection API:
 * выведите на экран все поля и методы класса.
 * Создайте экземпляр класса Person с использованием reflection API,
 * установите значения полей и вызовите методы.
 * Реализуйте обработку исключений для обеспечения корректного
 * взаимодействия с reflection API.
 */
public class Main {
    public static void main(String[] args) throws NameNotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
        // получили описатель калсса - это не экземпляр класса,
        // это класс, который описывает некоторый тип.
        // ? - предполагаем, что переменная person может принадлежать любому типу.
        Class<?> personalClass = Class.forName("sem02.task1.Person");
        System.out.println(personalClass + "\n");

        // получить и вывести список всех полей
        Field[] fields = personalClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("Поле: " + fields[i]);
        }
        System.out.printf("\n");

        //получаем все конструкторы класса
        Constructor[] constructors = personalClass.getConstructors();
        // метод выведет массив параметров
        constructors[0].getParameters();

        // а вот здесь уже создан объект - новый экземпляр класса Person
        Object personInstance = constructors[0].newInstance(null);


        // а здесь мы работаем с именем конкретного объекта, устанавливая ему имя
        Field nameField = personalClass.getDeclaredField("name");

        // для того, чтобы работать с закрытыми полями
        // , нужно установить разрешение setAccessible
        nameField.setAccessible(true);
        // только после этого мы можем менять значение закрытых полей
        nameField.set(personInstance, "Alice");

        Field ageFiels = personalClass.getDeclaredField("age");
        ageFiels.setAccessible(true);
        ageFiels.set(personInstance, 40);

        personalClass.getDeclaredMethod("displayInfo");
        Method displayInfoMethod = personalClass.getDeclaredMethod("displayInfo");
        // в invoke первый параметр - ссылка на объект, на котором мы вызываем этот метод
        // далее - параметры метода, если они есть.
        displayInfoMethod.invoke(personInstance);

    }
}
