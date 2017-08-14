package com.sramanopasaka.sipanionline.sadhumargi.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.sramanopasaka.sipanionline.sadhumargi.R;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class SadhuMargiDialogue extends AlertDialog.Builder {


    public SadhuMargiDialogue(Context context) {
        //Here's the magic..dialog
        super(context, R.style.AlertDialogStyle);

//        super.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
    }

    public void setPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        super.setPositiveButton("Select", listener);

    }


    public void setNegativeButtonClickListener(DialogInterface.OnClickListener listener) {
        super.setNegativeButton("Cancel", listener);

    }


    public void setNegativeButtonWithListener() {
        super.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

}
