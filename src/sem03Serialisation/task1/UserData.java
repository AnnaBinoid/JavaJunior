package sem03Serialisation.task1;

import java.io.Serializable;
// об€зательно имплементируем интерфейс Serializable -
// даем пон€ть Java, что можно его сереализовать
public class UserData implements Serializable {

    private String name;

    private int age;
    // transient - говоорит о том, что мы не собираемс€
    // серилизовывать это поле
    private transient String password;

    public UserData(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
