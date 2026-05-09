import auth.PasswordStrengthAnalyser;

public class TestPasswordStrength {
    public static void main(String[] args) {

        PasswordStrengthAnalyser analyser = new PasswordStrengthAnalyser();

        String password = "Hello@123!";

        System.out.println("Strength: " + analyser.analyse(password));
        System.out.println("Percentage: " + analyser.getStrengthPercentage(password));
        System.out.println("Suggestions: " + analyser.getSuggestions(password));
        System.out.println("Color: " + analyser.getStrengthColour(password));
    }
}
