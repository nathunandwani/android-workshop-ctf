package workshop.android.cspcert.challenge;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PremiumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        Pinview pinview = findViewById(R.id.pinView);
        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                new SendPostRequest().execute(pinview.getValue());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}
        protected String doInBackground(String... arg0) {
            try {
                URL url = new URL(APIv2.url);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write("pin=" + arg0[0]);

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    return "false : "+responseCode;
                }
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txtResult = findViewById(R.id.txtResult);
            txtResult.setText(result);
            txtResult.setGravity(Gravity.CENTER);

            Pinview pinview = findViewById(R.id.pinView);
            for (int i = 0; i < pinview.getChildCount() ; i++) {
                ((EditText)pinview.getChildAt(i)).setText("");
            }
        }
    }
}
