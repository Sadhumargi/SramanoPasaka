package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.GUIRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.RegisterRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateAccountFragment extends BaseFragment implements StateChangeListner, GUICallback {

    EditText fName;
    EditText mName;
    EditText lName;
    EditText bDate;
    EditText pNumber;
    EditText emailId;
    EditText sCountry;
    EditText sState;
    EditText sCity;
    EditText pinCode;
    EditText password;
    EditText reTypepassword;
    CheckBox chkbox;
    Button btnCreateProfile;
    TextView countryCode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_create_account_fragment, container, false);


        fName = (EditText) view.findViewById(R.id.first_name);
        mName = (EditText) view.findViewById(R.id.middle_name);
        lName = (EditText) view.findViewById(R.id.last_name);
        bDate = (EditText) view.findViewById(R.id.birth_date);
        pNumber = (EditText) view.findViewById(R.id.mobile_number);
        emailId = (EditText) view.findViewById(R.id.email);
        sCountry = (EditText) view.findViewById(R.id.country);
        sState = (EditText) view.findViewById(R.id.state);
        sCity = (EditText) view.findViewById(R.id.city);
        pinCode = (EditText) view.findViewById(R.id.pincode);
        password = (EditText) view.findViewById(R.id.password);
        reTypepassword = (EditText) view.findViewById(R.id.retype_password);
        chkbox = (CheckBox) view.findViewById(R.id.chkbox);
        btnCreateProfile = (Button) view.findViewById(R.id.create_profile);
        countryCode = (TextView) view.findViewById(R.id.countryCodeTxt);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }


            public void updateLabel() {
                String myFormat = "dd/mm/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                bDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean callAPi = true;

                if ((password.getText().toString().length()) < 8) {
                    password.setError("Password should be atleast of 8 charactors");
                    password.requestFocus();
                    callAPi = false;
                }

                if (!password.getText().toString().equals(reTypepassword.getText().toString())) {
                    reTypepassword.setError("Password Not matched");
                    reTypepassword.requestFocus();
                    callAPi = false;
                }

                if (reTypepassword.getText().toString().length() == 0) {
                    reTypepassword.setError("Please confirm password");
                    reTypepassword.requestFocus();
                    callAPi = false;
                }

                if (password.getText().toString().length() == 0) {
                    password.setError("Password is required");
                    password.requestFocus();
                    callAPi = false;
                }
                if (pinCode.getText().toString().length() == 0) {
                    pinCode.setError("Pincode is required");
                    pinCode.requestFocus();
                    callAPi = false;
                }
                if (sCity.getText().toString().length() == 0) {
                    sCity.setError("City name is required");
                    sCity.requestFocus();
                    callAPi = false;
                }
                if (sState.getText().toString().length() == 0) {
                    sState.setError("State name is required");
                    sState.requestFocus();
                    callAPi = false;
                }
                if (sCountry.getText().toString().length() == 0) {
                    sCountry.setError("Coutry name is required");
                    sCountry.requestFocus();
                    callAPi = false;
                }
                if (!ValidationUtils.isValidMail(emailId.getText().toString())) {
                    emailId.setError("Email id is required");
                    emailId.requestFocus();
                    callAPi = false;
                }
                if (!ValidationUtils.isValidMobile(pNumber.getText().toString())) {
                    pNumber.setError("Mobile number is required");
                    pNumber.requestFocus();
                    callAPi = false;
                }
                if (bDate.getText().toString().length() == 0) {
                    bDate.setError("Birthdate is required");
                    bDate.requestFocus();
                    callAPi = false;
                }
                if (lName.getText().toString().length() == 0) {
                    lName.setError("Last name is required");
                    lName.requestFocus();
                    callAPi = false;
                }
                if (mName.getText().toString().length() == 0) {
                    mName.setError("Middle name is required");
                    mName.requestFocus();
                    callAPi = false;
                }
                if (fName.getText().toString().length() == 0) {
                    fName.setError("First name is required");
                    fName.requestFocus();
                    callAPi = false;
                }


                if (callAPi) {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setFirst_name(fName.getText().toString());
                    registerRequest.setMiddle_name(mName.getText().toString());
                    registerRequest.setLast_name(lName.getText().toString());
                    registerRequest.setBirth_day(bDate.getText().toString());
                    registerRequest.setMobile(pNumber.getText().toString());
                    registerRequest.setEmail_address(emailId.getText().toString());
                    registerRequest.setCountry(sCountry.getText().toString());
                    registerRequest.setCity(sCity.getText().toString());
                    registerRequest.setState(sState.getText().toString());
                    registerRequest.setPincode(pinCode.getText().toString());
                    registerRequest.setPassword(password.getText().toString());

                    JsonParser jsonParser = new JsonParser();
                    JsonObject gsonObject = (JsonObject) jsonParser.parse(registerRequest.getURLEncodedPostdata().toString());
                    RequestProcessor requestProcessor = new RequestProcessor(CreateAccountFragment.this);
                    requestProcessor.doRegister(gsonObject);

                }


               /* Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), Profile.class);
                startActivity(i);*/
            }
        });

        sCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });

        countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker();
            }
        });
        sState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatePickerDialog statePickerDialog = new StatePickerDialog(getActivity(), CreateAccountFragment.this,false);
                statePickerDialog.show();
            }
        });

        return view;
    }


    private void showPicker() {
        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                sCountry.setText(name);
                sCountry.setError(null);
                countryCode.setText(dialCode);
                picker.dismiss();
            }
        });
        picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    @Override
    public void onStateSelected(String state) {
        sState.setText(state);
        sState.setError(null);
    }

    @Override
    public void onCitySelected(String city) {

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {

                RegisterResponse loginResponse = (RegisterResponse) guiResponse;
                if (loginResponse != null) {
                    if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {

                        OfflineData.saveLoginResponse(loginResponse.getData());
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
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



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_fragment);
    }*/
}
