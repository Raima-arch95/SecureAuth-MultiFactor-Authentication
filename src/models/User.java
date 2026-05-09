package models;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String secretKey;

    // Constructor without ID (for new users)
    public User(String username, String passwordHash, String secretKey) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.secretKey = secretKey;
    }

    // Constructor with ID (for fetched users)
    public User(int id, String username, String passwordHash, String secretKey) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.secretKey = secretKey;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}
