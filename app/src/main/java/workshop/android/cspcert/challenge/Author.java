package workshop.android.cspcert.challenge;

public class Author {

    private int id;
    private String username;
    private String timestamp;

    public Author(int id, String username, String timestamp) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
