package workshop.android.cspcert.challenge;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecureComms {

    private String url;

    public SecureComms(String url) {
        this.url = url;
    }

    public void Contact() {
        new SendRequest().execute(url);
    }

    private class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}
        protected String doInBackground(String... arg) {
            CertificatePinner certPinner = new CertificatePinner.Builder()
                    .add("nandtech.co", "sha256/fi6ngL1Rfod1fJ60Htn9CNmnoY67oVU+Fv/8IXCnIpU=")
                    .add("nandtech.co", "sha256/GI75anSEdkuHj05mreE0Sd9jE6dVqUIzzXRHHlZBVbI=")
                    .build();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().certificatePinner(certPinner).build();
            Request request = new Request.Builder().url(arg[0]).get().build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(final String result) {
            Log.i("secure", result);
        }
    }
}