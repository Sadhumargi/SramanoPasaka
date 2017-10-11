package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog = null;
    protected void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(BaseActivity.this) {
                @Override
                public void onDetachedFromWindow() {
                    mProgressDialog = null;
                }
            };
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("Please wait");
            mProgressDialog.show();
        } else {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }


    protected void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }

}
