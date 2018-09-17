package workshop.android.cspcert.challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/*
* Image credit: https://www.drupal.org/project/revision_log_default
*
* */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityBackgroundColor(0);
        SaveData();
        ImageActions();
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    public void ImageActions() {
        Log.i("challenge", "Welcome humans!");
        final ImageAlert imgAlertObj = new ImageAlert(MainActivity.this);
        Log.i("challenge"," Reading is important. Failure to read means failure to see important information.");
        ImageView imgRead = findViewById(R.id.imgRead);
        imgRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("challenge","Image has been clicked!");
                imgAlertObj.ToastIt();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        MenuInflater menuINF= getMenuInflater();
        menuINF.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Login:
                startActivity(new Intent(this, PremiumActivity.class));
                break;
            case R.id.menu_News:
                startActivity(new Intent(this, NewsActivity.class));
                break;
            case R.id.menu_About:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
        return true;
    }
    public void SaveData() {
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("Period", "16th Century");
        dictionary.put("Type", "Curious Painting");
        dictionary.put("SecretZ", "V29LRFAwc02pKVE5LRYbUNOkpYekE3eDOU2pKVE5OkpYekE3eDLRFBORFhtOkpYekAEE3eD5LR5LRFBOFBOQ1h6QTdTa5LRFBOkpYekE3eDBzeR9=");
        dictionary.put("M3", "Do you need eye glasses?");
        dictionary.put("SecretY", "VE5LRFAwc02pKVE5LRYbUNOkpYekE3eDOU2pKVE5OkpYekE3eDLRFBORFhtOkpYekAEE3eD5LR5LRFBOFBOQ1h6QTdTa5LRFBOkpYekE3eDBzeA==");
        dictionary.put("M2", "Sir, is it hard to read? or is it hard to identify?");
        dictionary.put("SecretX", "V293ISBZb3Uga25vdyB0aGF0IHRoaXMgd2FzIEJBU0U2NCEgSGVyZSdzIGFub3RoZXIgZmxhZyBmb3IgeW91OiBGTEFHe2lfa25vd19iYXNlNjR9=");
        dictionary.put("SecretW", "327a6c4304ad5938eaf0efb6cc3e53dc");
        dictionary.put("M1", "Hello? Is that even the correct format?");
        DataHelper.SaveData(MainActivity.this, "challenges", dictionary, false);
    }
}
