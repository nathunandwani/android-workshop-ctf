package workshop.android.cspcert.challenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "news_db";
    private static String tbl_authors = "authors";
    private static String tbl_news = "news";

    private static String tblAuthors =
            "CREATE TABLE " + tbl_authors + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
             ")";

    private static String tblNews =
            "CREATE TABLE " + tbl_news + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "premium INTEGER," +
                "author_id INTEGER," +
                "content TEXT," +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public long InsertAuthor(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        long ret = db.insert(tbl_authors, null, cv);
        db.close();
        return ret;
    }

    public long InsertNews(int premium, long author_id, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("premium", premium);
        cv.put("author_id", author_id);
        cv.put("content", content);
        long ret = db.insert(tbl_news, null, cv);
        db.close();
        return ret;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblAuthors);
        db.execSQL(tblNews);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
