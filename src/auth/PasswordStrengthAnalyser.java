package auth;

import java.util.ArrayList;
import java.util.List;

public class PasswordStrengthAnalyser {

    public StrengthLevel analyse(String password) {
        int score = calculateScore(password);

        if (score <= 2) return StrengthLevel.VERY_WEAK;
        if (score == 3) return StrengthLevel.WEAK;
        if (score == 4) return StrengthLevel.MEDIUM;
        if (score == 5) return StrengthLevel.STRONG;
        return StrengthLevel.VERY_STRONG;
    }

    public int getStrengthPercentage(String password) {
        int score = calculateScore(password);
        return (score * 20); // max = 100%
    }

    public List<String> getSuggestions(String password) {
        List<String> suggestions = new ArrayList<>();

        if (password.length() < 8)
            suggestions.add("Use at least 8 characters");

        if (!password.matches(".*[A-Z].*"))
            suggestions.add("Add uppercase letters");

        if (!password.matches(".*[a-z].*"))
            suggestions.add("Add lowercase letters");

        if (!password.matches(".*\\d.*"))
            suggestions.add("Add numbers");

        if (!password.matches(".*[@#$%^&+=!].*"))
            suggestions.add("Add special characters");

        return suggestions;
    }

    public String getStrengthColour(String password) {
        StrengthLevel level = analyse(password);

        switch (level) {
            case VERY_WEAK: return "RED";
            case WEAK: return "ORANGE";
            case MEDIUM: return "YELLOW";
            case STRONG: return "LIGHT_GREEN";
            case VERY_STRONG: return "GREEN";
            default: return "GRAY";
        }
    }

    private int calculateScore(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[@#$%^&+=!].*")) score++;

        return score;
    }
}
