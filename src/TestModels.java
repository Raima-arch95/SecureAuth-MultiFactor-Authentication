import models.User;

public class TestModels {
    public static void main(String[] args) {
        User u = new User("john", "hash123", "secretXYZ");
        System.out.println(u.getUsername());
    }
}
