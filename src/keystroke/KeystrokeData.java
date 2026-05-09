package keystroke;

public class KeystrokeData {

    private long pressTime;
    private long releaseTime;

    public KeystrokeData(long pressTime, long releaseTime) {
        this.pressTime = pressTime;
        this.releaseTime = releaseTime;
    }

    public long getPressTime() {
        return pressTime;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    // Dwell time = how long key is pressed
    public long getDwellTime() {
        return releaseTime - pressTime;
    }
}
