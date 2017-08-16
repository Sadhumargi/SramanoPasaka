package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddressListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ContactDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

public class PasswordChangeActivity extends AppCompatActivity implements GUICallback {

    Toolbar toolbar;
    Button btnReset;
    EditText curPassword;
    EditText newPassword;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        curPassword = (EditText) findViewById(R.id.cur_password);
        newPassword = (EditText) findViewById(R.id.new_password);
        btnReset = (Button) findViewById(R.id.button_reset);

        toolbar = (Toolbar) findViewById(R.id.tool_password);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle("पासवर्ड");

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cPassword = curPassword.getText().toString();
                String nPassword = newPassword.getText().toString();

                Boolean callAPi = true;

                if (TextUtils.isEmpty(cPassword)) {
                    curPassword.setError("Please your the current password");
                    curPassword.requestFocus();
                    callAPi = false;
                }
                if (TextUtils.isEmpty(nPassword)) {
                    newPassword.setError("Please enter a new password");
                    newPassword.requestFocus();
                    callAPi = false;
                }
                if(callAPi){
                    if ((curPassword.getText().toString().length()) < 8) {
                        curPassword.setError("Current Password should be atleast of 8 charactors");
                        curPassword.requestFocus();
                        callAPi = false;
                    }
                    if (curPassword.getText().toString().equals(newPassword.getText().toString())) {
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
                if(callAPi){
                    if(!cPassword.equalsIgnoreCase(SHAREDPREFERENCE PASSWORD)){
                        curPassword.setError("current password is wrong");
                        curPassword.requestFocus();
                        callAPi = false;
                    }
                }






                if (callAPi) {

                    passwordChange();
                }


            }


        });


    }


    private void passwordChange() {
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(PasswordChangeActivity.this);
            requestProcessor.passwordChange(loginResponse.getId(), loginResponse.getAppToken(),
                    loginResponse.getPassword(), loginResponse.getAppToken());
        }
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {


        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof PasswordChangeResponse) {
                    PasswordChangeResponse response = (PasswordChangeResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}

