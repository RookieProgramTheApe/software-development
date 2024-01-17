package online.niehong._002.user;

import lombok.Data;

@Data
public class User {
    private String name;
    private int age;

    public User() {
        System.out.println("User no args constructor");
    }
}
