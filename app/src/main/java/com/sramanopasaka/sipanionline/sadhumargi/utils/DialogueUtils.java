package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.sramanopasaka.sipanionline.sadhumargi.helpers.SadhuMargiDialogue;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class DialogueUtils {

    public static void showSingleChoiceDialog(Activity activity, int titleId, String[] items,
                                                 int selectedId, DialogInterface.OnClickListener listener) {
        final SadhuMargiDialogue dialog =
                new SadhuMargiDialogue(activity);
        dialog.setTitle(titleId);
        dialog.setSingleChoiceItems(items, selectedId, null);
        dialog.setPositiveButtonClickListener(listener);
        dialog.setNegativeButtonWithListener();



        AlertDialog alertDialog = dialog.show();
      /*  alertDialog.getWindow().getDecorView().setSystemUiVisibility(8);

        alertDialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        //  | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);*/

    }
}
