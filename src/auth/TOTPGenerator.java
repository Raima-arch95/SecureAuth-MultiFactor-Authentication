package auth;

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TOTPGenerator {

    // Generate secret key
    public String generateSecretKey() {
        return Base32.random();
    }

    // Generate current OTP
    public String generateCurrentCode(String secret) {
        Totp totp = new Totp(secret);
        return totp.now();
    }

    // Verify OTP
    public boolean verifyCode(String secret, String code) {
        Totp totp = new Totp(secret);
        return totp.verify(code);
    }

    // Generate QR code image
    public void generateQRCode(String username, String secret) {
        try {
            String otpAuthURL = "otpauth://totp/SecureAuth:" + username +
                    "?secret=" + secret + "&issuer=SecureAuth";

            BitMatrix matrix = new MultiFormatWriter()
                    .encode(otpAuthURL, BarcodeFormat.QR_CODE, 200, 200);

            Path path = FileSystems.getDefault().getPath("qrcode.png");
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);

            System.out.println("QR Code generated: qrcode.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
