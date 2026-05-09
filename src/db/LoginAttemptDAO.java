package db;

import models.LoginAttempt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAttemptDAO {

    public void saveAttempt(LoginAttempt attempt) {
        String sql = "INSERT INTO login_attempts (user_id, success) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attempt.getUserId());
            stmt.setBoolean(2, attempt.isSuccess());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LoginAttempt> getRecentAttempts(int userId) {
        String sql = "SELECT * FROM login_attempts WHERE user_id = ? ORDER BY attempt_time DESC LIMIT 5";

        List<LoginAttempt> attempts = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                attempts.add(new LoginAttempt(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getBoolean("success"),
                        rs.getTimestamp("attempt_time")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attempts;
    }
}
