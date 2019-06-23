package com.eternalflamelabs.minigames.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.constants.Constants;
import com.eternalflamelabs.minigames.util.SPTag;
import com.eternalflamelabs.minigames.util.SharedPreferencesManager;

import static android.view.View.VISIBLE;

public class CheckUpdate {

    public static void checkForUpdate(Context context, Activity activity) {
        SharedPreferencesManager shared = new SharedPreferencesManager(context);
        UpdateDialog dialog = new UpdateDialog();
        int updateAskingCap = 3;
        int updateAskedTime;

        boolean newSession = shared.retrieveBoolean(SPTag.NEW_SESSION, false);
        boolean updateNeeded = shared.retrieveBoolean(SPTag.NEEDS_UPDATE, false);
        updateAskedTime = shared.retrieveInt(SPTag.UPDATE_ASKED, 0);
        String update_message = context.getResources().getString(R.string.update_app_message);

        if (newSession) {
            if (updateAskedTime < updateAskingCap && updateNeeded) {
                dialog.updateDialog(activity, shared, update_message, updateAskedTime);
                shared.storeBoolean(SPTag.NEW_SESSION, false);
            }
        }
    }

    private static class UpdateDialog {

        private void updateDialog(final Activity activity, SharedPreferencesManager shared, String msg, int updateAskedTime) {
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
                    try {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_NO_PLAY_MGC)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_PLAY_MGC)));
                    }
                    dialog.dismiss();
                }
            });

            Button dialogCancelButton = dialog.findViewById(R.id.btn_dialog_cancel);
            dialogCancelButton.setVisibility(VISIBLE);
            dialogCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            updateAskedTime++;
            shared.storeInt(SPTag.UPDATE_ASKED, updateAskedTime);

            dialog.show();
        }
    }
}
