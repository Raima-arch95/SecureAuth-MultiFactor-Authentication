package auth;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    // Hash password
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    // Verify password
    public boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
