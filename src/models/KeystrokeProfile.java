package models;

public class KeystrokeProfile {
    private int id;
    private int userId;
    private String timingVector; // JSON string

    public KeystrokeProfile(int userId, String timingVector) {
        this.userId = userId;
        this.timingVector = timingVector;
    }

    public KeystrokeProfile(int id, int userId, String timingVector) {
        this.id = id;
        this.userId = userId;
        this.timingVector = timingVector;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTimingVector() { return timingVector; }
    public void setTimingVector(String timingVector) { this.timingVector = timingVector; }

    @Override
    public String toString() {
        return "KeystrokeProfile{userId=" + userId + "}";
    }
}
