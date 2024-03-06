package hw01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
    Напишите программу, которая использует Stream API для обработки списка чисел.
    Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Main {
    public static void main(String[] args) {

        Random rand = new Random();

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            numbers.add(rand.nextInt(50));
        }
        System.out.println("Our list is: " + numbers);

        List<Integer> evenNumbers = numbers.stream().filter(number -> number % 2 == 0).toList();
        System.out.println("Our even numbers list is :   " + evenNumbers);

        int average = evenNumbers.stream().mapToInt(i -> i).sum() / evenNumbers.size();
        System.out.println("Result is: " + average);
    }
}
