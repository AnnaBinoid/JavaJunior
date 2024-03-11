package hw02;

public class Cat extends Animal{
    private String tailColor;

    public void toPur () {
        System.out.println("Pur-pur-pur");
    }

    public Cat(String name, int age, String tailColor) {
        super(name, age);
        this.tailColor = tailColor;
    }

}
