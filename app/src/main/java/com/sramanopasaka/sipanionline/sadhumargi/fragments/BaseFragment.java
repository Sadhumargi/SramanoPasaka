package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class BaseFragment extends Fragment {
    private ProgressDialog mProgressDialog;
    //protected ActionBarUpdator actionBarUpdator = null;

    protected void showLoadingDialog() {
        if (getActivity() != null) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(getActivity()) {
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
    }


    protected void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }
}
