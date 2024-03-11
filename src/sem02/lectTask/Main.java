package sem02.lectTask;
//reflection API
//9 строка параметризированный класс.
// Вопрос, т.к. не знаем, какие параметры туда положим
// .forName позволяет нам найти класс по имени

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> car = Class.forName("sem02.lectTask.Car"); // Нашли класс
        Constructor<?>[] constructors = car.getConstructors(); // Нашли конструктор, положили в переменную constructors

        Object gaz = constructors[0].newInstance("GAZ"); // создаем объект этого класса
        System.out.println(gaz);

        // Хотим воздействовать на приватные поля класса car, для этого создаем массив fields
        // это массив тех полей, которые есть в нашем объекте.
        // теперь достаем класс, и из класса достаем массив нужных полей.
        Field[] fields = gaz.getClass().getFields();
        int tmp = fields[fields.length - 1].getInt(gaz);
        fields[fields.length - 1].setInt(gaz, tmp * 2);

        // можем работать и с методами класса

        Method[] methods = gaz.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }

        System.out.println(gaz);
    }


}
