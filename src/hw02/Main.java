package hw02;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        Animal[] animals = {new Cat("Bars", 3, "black")
                , new Dog("Ressi", 8, 100)
                , new Cat ("Garfild", 5, "white")};

        for (Animal animal : animals) {
            System.out.println(animal.toString());

            Field[] fields = animal.getClass().getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
                System.out.printf("%s: %s\n", field.getName()
                        , field.get(animal));
            }

            Method[] methods = animal.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].invoke(animal) != null) {
                    System.out.println(methods[i].invoke(animal));
                }
                System.out.println("\n");
            }
        }
    }
}
