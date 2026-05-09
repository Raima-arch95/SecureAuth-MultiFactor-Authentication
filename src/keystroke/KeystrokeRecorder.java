package keystroke;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeystrokeRecorder extends KeyAdapter {

    private List<Long> pressTimes = new ArrayList<>();
    private List<Double> timingVector = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent e) {
        pressTimes.add(System.nanoTime());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (pressTimes.size() >= 2) {
            int last = pressTimes.size() - 1;
            long diff = pressTimes.get(last) - pressTimes.get(last - 1);
            timingVector.add(diff / 1_000_000.0);
        }
    }

    public List<Double> getTimingVector() {
        return timingVector;
    }

    public void reset() {
        pressTimes.clear();
        timingVector.clear();
    }
}
