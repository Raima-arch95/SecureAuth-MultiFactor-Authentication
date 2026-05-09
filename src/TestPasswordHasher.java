import auth.PasswordHasher;

public class TestPasswordHasher {
    public static void main(String[] args) {

        PasswordHasher hasher = new PasswordHasher();

        String hash = hasher.hashPassword("MyPassword@1");

        System.out.println("Hash: " + hash);

        System.out.println("Correct password: " +
                hasher.verifyPassword("MyPassword@1", hash));

        System.out.println("Wrong password: " +
                hasher.verifyPassword("WrongPass", hash));
    }
}
