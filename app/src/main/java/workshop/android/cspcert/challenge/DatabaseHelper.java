package workshop.android.cspcert.challenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public News getNews(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tbl_news, new String[] {"id", "premium", "author_id", "content", "timestamp"}, "id=?", new String[] {String.valueOf(id)}, null, null, null, null);
        News news = null;
        if (cursor != null) {
            cursor.moveToFirst();
            news = new News(cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getInt(cursor.getColumnIndex("premium")),
                            cursor.getInt(cursor.getColumnIndex("author_id")),
                            cursor.getString(cursor.getColumnIndex("content")),
                            cursor.getString(cursor.getColumnIndex("timestamp"))
            );
        }
        cursor.close();
        return news;
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
