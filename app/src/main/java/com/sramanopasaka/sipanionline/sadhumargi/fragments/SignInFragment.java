package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.ResetPasswordActivity;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.LoginRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;

public class SignInFragment extends BaseFragment implements GUICallback {


    EditText edttxtEmail, edttxtPassword;
    TextView frgtPassword;
    Button btnLogin, btnSignup;
    private TabselectionListner tabselectionListner = null;
    ProgressDialog pg;


    String sPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_sign_in_fragment, container, false);

        edttxtEmail = (EditText) view.findViewById(R.id.editTex_email);
        edttxtPassword = (EditText) view.findViewById(R.id.editText_password);

        frgtPassword = (TextView) view.findViewById(R.id.tv_frgtpass);

        btnLogin = (Button) view.findViewById(R.id.button_login);
        btnSignup = (Button) view.findViewById(R.id.button_create_profile);

        // creating an shared Preference file for the information to be stored
// first argument is the name of file and second is the mode, 0 is private mode


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPassword = edttxtPassword.getText().toString();
                String username = edttxtEmail.getText().toString();
                //String password = edttxtPassword.getText().toString();
                LoginRequest loginRequest = new LoginRequest();


                if (TextUtils.isEmpty(username)) {

                    Toast.makeText(getActivity(), "Please enter a valid email/mobile number", Toast.LENGTH_LONG).show();

                } else if (ValidationUtils.isValidMail(username)) {

                    if (!TextUtils.isEmpty(sPassword) && sPassword.length() > 5) {
                        loginRequest.setEmail(username);
                        loginRequest.setPassword(sPassword);
                        initiateAPI(loginRequest);
                    } else {
                        Toast.makeText(getActivity(), "Password should contain 5 - 12 characters ", Toast.LENGTH_SHORT).show();
                    }

                } else if (ValidationUtils.isValidMobile(username)) {

                    if (!TextUtils.isEmpty(sPassword) && sPassword.length() > 5) {
                        loginRequest.setMobileNumber(username);
                        loginRequest.setPassword(sPassword);
                        initiateAPI(loginRequest);
                    } else {
                        Toast.makeText(getActivity(), "Password should contain 5 - 12 characters ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Please enter a valid email/mobile and password to login ", Toast.LENGTH_SHORT).show();
                }


            }


        });

        frgtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), ResetPasswordActivity.class);
                startActivity(i);

            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tabselectionListner != null)
                    tabselectionListner.onSelectTab(1);


            }
        });


        return view;
    }


    private void initiateAPI(LoginRequest loginRequest) {
        if (loginRequest != null) {
            showLoadingDialog();
            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(loginRequest.getURLEncodedPostdata().toString());
            RequestProcessor requestProcessor = new RequestProcessor(SignInFragment.this);
            requestProcessor.doLogin(gsonObject);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabselectionListner = (TabselectionListner) getActivity();
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {

                LoginResponse loginResponse = (LoginResponse) guiResponse;
                if (loginResponse != null) {
                    if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                        OfflineData.saveLoginResponse(loginResponse.getData());

                        //"password":"5dc8e5500e207aa79ddd66a8f7e146df"

                        PreferenceUtils.setPassword(getActivity(), sPassword);

                        Toast.makeText(getActivity(), "Login Successfuly", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), ProfileActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    } else {
                        if (!TextUtils.isEmpty(loginResponse.getMessage())) {
                            Toast.makeText(getActivity(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Invalid username/password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

        }
    }
}




