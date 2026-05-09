import db.UserDAO;
import models.User;

public class TestUserDAO {
    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        User user = new User("testuser", "testhash", "testsecret");

        int id = dao.saveUser(user);
        System.out.println("Saved ID: " + id);

        User fetched = dao.getUserByUsername("testuser");
        System.out.println("Fetched: " + fetched);

        System.out.println("Exists: " + dao.usernameExists("testuser"));
    }
}
