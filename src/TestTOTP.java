import auth.TOTPGenerator;

public class TestTOTP {
    public static void main(String[] args) throws Exception {

        TOTPGenerator t = new TOTPGenerator();

        String secret = t.generateSecretKey();
        System.out.println("Secret: " + secret);

        while (true) {
            String code = t.generateCurrentCode(secret);
            System.out.println("Current OTP: " + code);

            System.out.println("Valid check: " + t.verifyCode(secret, code));
            System.out.println("----------------------");

            Thread.sleep(5000); // refresh every 5 seconds
        }
    }
}
