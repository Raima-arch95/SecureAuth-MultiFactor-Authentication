import db.KeystrokeDAO;
import models.KeystrokeProfile;

public class TestKeystrokeDAO {
    public static void main(String[] args) {

        KeystrokeDAO dao = new KeystrokeDAO();

        // assuming user_id = 1 exists
        KeystrokeProfile profile =
                new KeystrokeProfile(1, "[100,120,110,90]");

        dao.saveProfile(profile);

        KeystrokeProfile fetched =
                dao.getProfileByUserId(1);

        System.out.println("Fetched: " + fetched);
    }
}
