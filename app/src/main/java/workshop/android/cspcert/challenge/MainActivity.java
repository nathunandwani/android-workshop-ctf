package workshop.android.cspcert.challenge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
* Image credit: https://www.drupal.org/project/revision_log_default
*
* */

public class MainActivity extends AppCompatActivity {

    private static String PLEASE_EXCUSE_MY_SLOPPY_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityBackgroundColor(0);
        SaveData();
        ImageActions();
        new SendPostRequest().execute(APIv2.usernames);
        new SendPostRequest().execute(APIv2.news);
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
                ArrayList arr = DataHelper.A(imgAlertObj.enc0);
                String junk = "";
                String junk2 = imgAlertObj.A();
                for (int i = 0; i < arr.size(); i++) {
                    junk += (char)((int)arr.get(i) ^ (int)junk2.charAt(i % junk2.length()));
                }
                junk = APIv2.news2 + "?rand=" + junk;
                new SecureComms(junk).Contact();
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

    public class SendPostRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}
        protected String doInBackground(String... arg0) {
            try {
                URL url = new URL(arg0[0]);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    return "";
                }
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == "")
                return;
            DatabaseHelper db = new DatabaseHelper(MainActivity.this);
            if (result.contains("|")) {
                String[] splitter = result.split("\\|");
                for (int i = 0; i < splitter.length; i++) {
                    db.InsertAuthor(splitter[i]);
                }
            } else {
                try {
                    byte[] data = Base64.decode(result, Base64.DEFAULT);
                    String text = new String(data, StandardCharsets.US_ASCII);
                    if (text.contains("|")) {
                        String[] splitter = text.split("\n");
                        for (int i = 0; i < splitter.length; i++) {
                            String[] line_splitter = splitter[i].split("\\|");
                            String title = line_splitter[0];
                            int premium = Integer.parseInt(line_splitter[1]);
                            int author = Integer.parseInt(line_splitter[2]);
                            String contents = line_splitter[3];
                            db.InsertNews(title, premium, author, contents);
                        }
                    }
                } catch (Exception ex) {
                    Log.e("err", ex.getMessage());
                }
            }
        }
    }
}
