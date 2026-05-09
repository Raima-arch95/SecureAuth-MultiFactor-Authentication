package models;

import java.sql.Timestamp;

public class LoginAttempt {
    private int id;
    private int userId;
    private boolean success;
    private Timestamp attemptTime;

    public LoginAttempt(int userId, boolean success) {
        this.userId = userId;
        this.success = success;
    }

    public LoginAttempt(int id, int userId, boolean success, Timestamp attemptTime) {
        this.id = id;
        this.userId = userId;
        this.success = success;
        this.attemptTime = attemptTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public Timestamp getAttemptTime() { return attemptTime; }
    public void setAttemptTime(Timestamp attemptTime) { this.attemptTime = attemptTime; }

    @Override
    public String toString() {
        return "LoginAttempt{userId=" + userId + ", success=" + success + "}";
    }
}
