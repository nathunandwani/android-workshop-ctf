package workshop.android.cspcert.challenge;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ImageAlert {

    private List<Character> list;
    private Activity activity;
    private int counter = 0;
    private String password = "this_is_not_the_flag";
    private String log = "";

    private int[] enc1 = {61, 72, 2, 22, 58, 25, 83, 45, 11, 14, 16, 54, 26, 15, 69, 43, 14, 5, 18, 71, 4, 9, 25, 22, 45, 73, 17, 42, 26, 79, 29, 43, 84, 12, 10, 58, 21, 2, 70, 19, 84, 5, 8, 24, 58, 73, 0, 58, 0, 28, 17, 126};
    private int[] enc2 = {60, 7, 30, 83, 59, 6, 22, 44, 78, 0, 26, 58, 84, 29, 11, 59, 3, 30, 18, 19, 21, 6, 13, 83, 43, 1, 26, 44, 81};
    private int[] enc3 = {39, 0, 6, 6, 51, 13, 83, 22, 78, 3, 27, 48, 31, 72, 4, 43, 70, 24, 9, 2, 84, 27, 6, 6, 45, 10, 22, 96};
    private int[] enc4 = {59, 26, 73, 0, 55, 6, 6, 51, 10, 79, 61, 127, 6, 13, 9, 38, 70, 3, 15, 71, 7, 7, 4, 22, 127, 15, 18, 52, 11, 79, 26, 58, 3, 27, 90};
    private int[] enc5 = {6, 13, 8, 23, 54, 7, 20, 0, 25, 6, 24, 51, 43, 15, 0, 43, 57, 21, 14, 18, 43, 9, 7, 10, 40, 1, 22, 45, 11};
    private int[] enc6 = {50, 36, 40, 52, 36, 15, 26, 49, 10, 6, 26, 56, 43, 0, 12, 59, 2, 9, 15, 56, 19, 13, 4, 0, 0, 8, 1, 58, 49, 1, 27, 43, 43, 0, 4, 45, 2, 17};
    private int[] enc7 = {18, 19, 0, 27, 28, 9, 10, 17, 29, 15, 11, 12, 8, 78, 93, 23, 23, 23, 1, 2, 4, 49};
    private int[] enc8 = {49, 5, 1, 2, 23, 22, 90, 91, 92, 76, 54, 3, 2, 0, 2, 2, 19, 18, 10, 11, 23, 24, 28, 36, 16};
    private int[] enc9 = {59, 26, 73, 0, 55, 6, 6, 22, 90, 91, 92, 76, 54, 3, 2, 0, 2, 2, 19, 22, 90, 91, 92, 76, 54, 3, 2, 0, 2, 2, 19};
    private int[] enc10 = {51, 10, 79, 61, 127, 6, 13, 9, 38, 70, 3, 15, 71, 7, 7, 4, 22, 127, 15, 10};

    public ImageAlert(Activity activity) {
        this.activity = activity;
        list = new ArrayList<Character>();
        for (int i = 0x41; i < 0x5b; i++) {
            list.add((char) i);
        }
    }

    public void ToastIt() {
        String message = "";
        String message2 = "";
        String message3 = "";
        switch (counter) {
            case 0:
                for (int i = 97; i < 123; i++) {
                    list.add((char) i);
                }
                for (int i = 0; i < enc1.length; i++) {
                    log = list.get(18).toString() + list.get(7).toString() + "";
                    message += (char)(enc1[i] ^ (int)password.charAt(i % password.length()));
                    log = list.get(5).toString() + list.get(11).toString() + list.get(0).toString() + list.get(6).toString();
                }
                Log.i("challenge", "Case 2");
                break;
            case 1:
                for (int i = 0; i < enc6.length; i++) {
                    message3 += (char)(enc6[i] ^ (int)password.charAt(i % password.length()));
                }
                list.add((char)123);
                for (int i = 0; i < enc2.length; i++) {
                    message += (char) (enc2[i] ^ (int) password.charAt(i % password.length()));
                }
                for (int i = 0; i < enc5.length; i++) {
                    message2 += (char)(enc5[i] ^ (int)password.charAt(i % password.length()));
                }
                log += list.get(52).toString() + message2;
                Log.i("challenge", "Case 3");
                DataHelper.WriteToCache(activity, message3);
                break;
            case 2:
                list.add((char)125);
                for (int i = 0; i < enc3.length; i++) {
                    message += (char)(enc3[i] ^ (int)password.charAt(i % password.length()));
                }
                log += list.get(53).toString();
                Log.i("challenge", "Case 5");
                break;
            case 3:
                for (int i = 0; i < enc4.length; i++) {
                    message += (char)(enc4[i] ^ (int)password.charAt(i % password.length()));
                }
                Log.i("challenge", "Case 4");
                Log.i("challenge", log);
                break;
        }
        counter++;
        if (counter == 4) {
            counter = 0;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
