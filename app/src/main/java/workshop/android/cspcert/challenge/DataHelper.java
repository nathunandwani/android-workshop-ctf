package workshop.android.cspcert.challenge;

import android.app.Activity;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class DataHelper {

    public static void SaveData(Activity activity, String prefname, Map<String, String> dictionary,  boolean overwrite) {
        SharedPreferences pref = activity.getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (overwrite) {
            editor.clear();
        }
        for (Map.Entry<String, String> e : dictionary.entrySet()) {
            editor.putString(e.getKey(), e.getValue());
        }
        editor.commit();
    }

    public static void WriteToCache(Activity activity, String contents) {
        File outputDir = activity.getCacheDir();
        try {
            File outputFile = File.createTempFile(".cache", "", outputDir);
            FileOutputStream stream = new FileOutputStream(outputFile);
            try {
                stream.write(contents.getBytes());
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList A(int[] input) {
        ArrayList al = new ArrayList();
        for (int i = 0; i < input.length; i++) {
            al.add(input[i] - 1);
        }
        return al;
    }
}
