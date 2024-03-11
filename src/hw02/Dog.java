package hw02;

public class Dog extends Animal{
    private int tailWaggingSpeed;

    public Dog(String name, int age, int tailWaggingSpeed) {
        super(name, age);
        this.tailWaggingSpeed = tailWaggingSpeed;
    }

    public void bark () {
        System.out.println("Woof-woof!");
    }

}
