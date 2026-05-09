import db.LoginAttemptDAO;
import models.LoginAttempt;
import java.util.List;

public class TestLoginAttemptDAO {
    public static void main(String[] args) {

        LoginAttemptDAO dao = new LoginAttemptDAO();

        // save attempts
        dao.saveAttempt(new LoginAttempt(1, true));
        dao.saveAttempt(new LoginAttempt(1, false));
        dao.saveAttempt(new LoginAttempt(1, true));

        // fetch recent
        List<LoginAttempt> attempts = dao.getRecentAttempts(1);

        for (LoginAttempt a : attempts) {
            System.out.println(a);
        }
    }
}
