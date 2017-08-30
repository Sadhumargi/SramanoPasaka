package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.PasswordChangeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name    :   pranavjdev
 * Date   : 8/18/17
 * Email : pranavjaydev@gmail.com
 */

public class ChangePasswordFragment extends BaseFragment implements GUICallback {

    @Bind(R.id.cur_password)
    EditText currentPassword;

    @Bind(R.id.new_password)
    EditText newPassword;

    private View view = null;
    private ActionBarUpdator actionBarUpdator = null;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            actionBarUpdator = (ActionBarUpdator) getActivity();
            actionBarUpdator.onUpdateTitile(getString(R.string.password));
        }catch (Exception ex){

        }


    }

    @OnClick(R.id.button_reset)
    public void changePassword(){
        final String sPassword = PreferenceUtils.getPassword(getActivity());
        String cPassword = currentPassword.getText().toString();
        String nPassword = newPassword.getText().toString();

        Boolean callAPi = true;

        if (TextUtils.isEmpty(cPassword)) {
            currentPassword.setError("Please your the current password");
            currentPassword.requestFocus();
            callAPi = false;
        }
        if (TextUtils.isEmpty(nPassword)) {
            newPassword.setError("Please enter a new password");
            newPassword.requestFocus();
            callAPi = false;
        }
        if (callAPi) {
            if ((currentPassword.getText().toString().length()) < 8) {
                currentPassword.setError("Current Password should be atleast of 8 charactors");
                currentPassword.requestFocus();
                callAPi = false;
            }
            if (currentPassword.getText().toString().equals(newPassword.getText().toString())) {
                newPassword.setError("Current and New Passwords sholuldn't be same");
                newPassword.requestFocus();
                callAPi = false;
            }

            if ((newPassword.getText().toString().length()) < 8) {
                newPassword.setError("New Password should be atleast of 8 charactors");
                newPassword.requestFocus();
                callAPi = false;
            }
        }
        if (callAPi) {

            if(!TextUtils.isEmpty(sPassword)) {
                if (!cPassword.equalsIgnoreCase(sPassword)) {
                    currentPassword.setError("current password is wrong");
                    currentPassword.requestFocus();
                    callAPi = false;
                }

            }
        }


        if (callAPi) {

            passwordChange();
        }

    }

    private void passwordChange() {
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {
            showLoadingDialog();

            RequestProcessor requestProcessor = new RequestProcessor(ChangePasswordFragment.this);
            requestProcessor.passwordChange(loginResponse.getId(), loginResponse.getAppToken(),
                    PreferenceUtils.getPassword(getActivity()), newPassword.getText().toString());
        }
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, GUICallback.RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(GUICallback.RequestStatus.SUCCESS)) {
                if (guiResponse instanceof PasswordChangeResponse) {
                    PasswordChangeResponse response = (PasswordChangeResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}
