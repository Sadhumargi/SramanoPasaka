package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.PasswordChangeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
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

    @Bind(R.id.currentpassword)
    TextInputLayout inputCurrentPassword;

    @Bind(R.id.newpassword)
    TextInputLayout inputNewPassword;


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
        } catch (Exception ex) {

        }


    }

    @OnClick(R.id.button_reset)
    public void changePassword() {
        final String sPassword = PreferenceUtils.getPassword(getActivity());
        String cPassword = currentPassword.getText().toString();
        String nPassword = newPassword.getText().toString();

        Boolean callAPi = true;

        if (TextUtils.isEmpty(cPassword)) {
            inputCurrentPassword.setError("Current password is required");
            currentPassword.requestFocus();
            callAPi = false;
        }else{

            inputCurrentPassword.setError(null);
        }

        if (callAPi) {

            if ((currentPassword.getText().toString().length()) < 5) {
                inputCurrentPassword.setError("Current password should be atleast of 5 characters");
                currentPassword.requestFocus();
                callAPi = false;
            }


            if ((newPassword.getText().toString().length()) < 5) {
                inputNewPassword.setError("New password should be atleast of 5 characters");
                newPassword.requestFocus();
                callAPi = false;
            }

            if (TextUtils.isEmpty(nPassword)) {
                inputNewPassword.setError("New password is required");
                newPassword.requestFocus();
                callAPi = false;
            }
            else {

                inputNewPassword.setError(null);
            }

            if (currentPassword.getText().toString().equals(newPassword.getText().toString())) {
                inputNewPassword.setError("Current and new password should not be same");
                newPassword.requestFocus();
                callAPi = false;
            }


        }
        if (callAPi) {

            if (!TextUtils.isEmpty(sPassword)) {
                if (!cPassword.equalsIgnoreCase(sPassword)) {
                    inputCurrentPassword.setError("Current password is wrong");
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
                            PreferenceUtils.setPassword(getActivity(), newPassword.getText().toString());
                            new CustomToast().showErrorToast(getActivity(), view, response.getMessage());
                            getActivity().finish();
                        }
                    }
                }
            }else{
                new CustomToast().showErrorToast(getActivity(), view,
                        "Please check your internet connection.");
            }
        }
    }
}
