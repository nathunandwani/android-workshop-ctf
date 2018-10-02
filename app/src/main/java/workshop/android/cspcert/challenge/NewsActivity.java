package workshop.android.cspcert.challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        DatabaseHelper db = new DatabaseHelper(this);

        News news = db.getNews(1);

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(news.getTitle());
        txtTitle.setGravity(Gravity.CENTER);

        TextView txtContents = findViewById(R.id.txtContents);
        txtContents.setText(news.getContent());
        txtContents.setGravity(Gravity.CENTER);
        txtContents.setMovementMethod(new ScrollingMovementMethod());
    }
}
