package db;

import models.KeystrokeProfile;
import java.sql.*;

public class KeystrokeDAO {

    public void saveProfile(KeystrokeProfile profile) {
        String sql = "INSERT INTO keystroke_profiles (user_id, timing_vector) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, profile.getUserId());
            stmt.setString(2, profile.getTimingVector());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KeystrokeProfile getProfileByUserId(int userId) {
        String sql = "SELECT * FROM keystroke_profiles WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new KeystrokeProfile(
                        rs.getInt("id"),
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
