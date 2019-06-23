package com.eternalflamelabs.minigames.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eternalflamelabs.minigames.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import static android.content.Context.VIBRATOR_SERVICE;

public class Utilities {

    public static ArrayList<Integer> randomGenerator(ArrayList<?> arrayList) {
        int max = arrayList.size();

        ArrayList<Integer> random_list = new ArrayList<>(max);

        Random randomGenerator = new Random();
        while (random_list.size() < max) {

            int random = randomGenerator .nextInt(max);
            if (!random_list.contains(random)) {
                random_list.add(random);
            }
        }
        return random_list;
    }

    public static void shakePhone(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        if (v != null) {
            if (v.hasVibrator()) {
                v.vibrate(200);
            }
        }
    }

    public static String getCurrentTime(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    private Utilities() {
        throw new AssertionError();
    }

    public static class ViewDialog {

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_custom);

            TextView text = dialog.findViewById(R.id.text_dialog);
            text.setText(msg);

            Button dialogButton = dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

}