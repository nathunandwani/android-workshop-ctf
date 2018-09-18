package workshop.android.cspcert.challenge;

public class News {

    private int id;
    private int premium;
    private int author_id;
    private String content;
    private String timestamp;

    public News(int id, int premium, int author_id, String content, String timestamp) {
        this.id = id;
        this.premium = premium;
        this.author_id = author_id;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public int getAuthorID() {
        return author_id;
    }

    public void setAuthorID(int author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
