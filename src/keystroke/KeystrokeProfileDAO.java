package db;

import models.KeystrokeProfile;
import java.sql.*;

public class KeystrokeProfileDAO {

    public KeystrokeProfile getProfileByUserId(int userId) {

        String sql = "SELECT * FROM keystroke_profiles WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new KeystrokeProfile(
                        rs.getInt("user_id"),
                        rs.getString("timing_vector")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
