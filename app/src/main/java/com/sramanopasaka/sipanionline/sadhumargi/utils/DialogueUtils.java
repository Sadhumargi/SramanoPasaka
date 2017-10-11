package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.SadhuMargiDialogue;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DialogueListner;

import java.util.Calendar;

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

    public static void showDialogOKCancel(Activity activity, String title, String msg, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        final SadhuMargiDialogue dialog =
                new SadhuMargiDialogue(activity);
        dialog.setTitle(title);
        dialog.setMessage(msg);
//        dialog.setNegativeButtonWithListener();
//        dialog.setOnCancelListener(cancelListener);
        dialog.setPositiveButton("Ok", okListener);
        dialog.setNegativeButton("Cancel", cancelListener);
        AlertDialog alertDialog = dialog.show();
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(8);

        alertDialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        //  | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }


    public static void showYearPicker(Activity activity, final DialogueListner dialogueListner) {
        final Dialog d = new Dialog(activity);
        d.setTitle("Year Picker");
        d.setContentView(R.layout.year_dialogue);

        Button set = (Button) d.findViewById(R.id.button1);
        Button cancel = (Button) d.findViewById(R.id.button2);
        final NumberPicker nopicker = (NumberPicker) d.findViewById(R.id.numberPicker1);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        nopicker.setMaxValue(year );
        nopicker.setMinValue(year - 50);
        nopicker.setWrapSelectorWheel(false);
        nopicker.setValue(year);
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogueListner.onYearSelected(String.valueOf(nopicker.getValue()));
                d.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }
}
