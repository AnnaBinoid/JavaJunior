package sem02.task2;

import java.lang.reflect.Field;

/**
 * Задача 2: Применение Reflection API в разработке
 *
 * Реализуйте обобщенный метод, который принимает объект
 * и выводит в консоль значение всех его переменных.
 * Создайте класс Car с различными полями (например, модель,
 * цвет, год выпуска).
 * Примените Reflection API для вывода значений полей
 * созданного объекта класса Car с использованием ранее
 * созданного метода
 */

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // это один способ
        Car car = new Car ("Toyota", "blue", 2022);
        task2(car);
    }
    //создаем обобщенный метод.

    private static  <T> void  task2 (T obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // даем доступ к private полю
            System.out.printf("%s : %s\n", field.getName(), field.get(obj));
        }
    }

}
