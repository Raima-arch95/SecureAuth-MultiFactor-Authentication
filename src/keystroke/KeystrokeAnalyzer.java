package keystroke;

import java.util.List;

public class KeystrokeAnalyzer {

    public boolean isUserLegit(List<Double> stored, List<Double> current) {

        if (stored.size() != current.size()) return false;

        double totalDiff = 0;

        for (int i = 0; i < stored.size(); i++) {
            totalDiff += Math.abs(stored.get(i) - current.get(i));
        }

        double avgDiff = totalDiff / stored.size();

        return avgDiff < 200; // slightly relaxed threshold
    }
}
