package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.LoginRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.RegisterRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamilyResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ZoneChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.utils.CityPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ZonePickerDialog;

import java.util.Calendar;

public class CreateAccountActivity extends BaseActivity implements GUICallback {

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
    CheckBox termsCheckBox;
    Button btnCreateProfile;
    TextView countryCode;
    RadioGroup radiogrp;
    EditText anchal;
    Spinner localSanghName;
    CheckBox headOfFamily;
    Spinner relation;
    Spinner profileCreatedby;
    AutoCompleteTextView familyMember;
    Context context;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        fName = (EditText) findViewById(R.id.first_name);
        mName = (EditText) findViewById(R.id.middle_name);
        lName = (EditText) findViewById(R.id.last_name);
        bDate = (EditText) findViewById(R.id.birth_date);
        pNumber = (EditText) findViewById(R.id.mobile_number);
        emailId = (EditText)findViewById(R.id.email);
        sCountry = (EditText) findViewById(R.id.country);
        sState = (EditText) findViewById(R.id.state);
        sCity = (EditText) findViewById(R.id.city);
        pinCode = (EditText) findViewById(R.id.pincode);
        password = (EditText) findViewById(R.id.password);
        reTypepassword = (EditText)findViewById(R.id.retype_password);
        termsCheckBox = (CheckBox) findViewById(R.id.termsCheckBox);
        btnCreateProfile = (Button) findViewById(R.id.create_profile);
        countryCode = (TextView) findViewById(R.id.countryCodeTxt);
        radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
        familyMember = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        relation = (Spinner) findViewById(R.id.relation);
        profileCreatedby = (Spinner)findViewById(R.id.profile_created_by);
        anchal= (EditText) findViewById(R.id.Zone);
        localSanghName= (Spinner) findViewById(R.id.local_sangh_name);
        // selectFamily= (Spinner) view.findViewById(R.id.family);

        toolbar= (Toolbar) findViewById(R.id.toolCreateAccount);
        setSupportActionBar(toolbar);

//        viewPager= (ViewPager) findViewById(R.id.viewpager);
//        tabLayout= (TabLayout) findViewById(R.id.tabs);
//        setUpViewPage(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        android.support.v7.app.ActionBar actionBar=this.getSupportActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Create An Account</font>"));


        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear += 1;

                bDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }


        };

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        ArrayAdapter<CharSequence> typeadapter = ArrayAdapter.createFromResource(this, R.array.head_of_family_relation, android.R.layout.simple_spinner_item);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //achievementArea.setPrompt("Select your favorite Planet!");

        relation.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        typeadapter,
                        R.layout.head_of_family_relation_selection,
                        this));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.profile_created_by, android.R.layout.simple_spinner_item);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //achievementArea.setPrompt("Select your favorite Planet!");

        profileCreatedby.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.profile_created_by_selection,
                        this));


        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean callAPi = true;

                if ((password.getText().toString().length()) < 8) {
                    password.setError("Password should be atleast of 8 characters");
                    password.requestFocus();
                    callAPi = false;
                } else {

                    password.setError(null);
                }

                if (!password.getText().toString().equals(reTypepassword.getText().toString())) {
                    reTypepassword.setError("Password not matched");
                    reTypepassword.requestFocus();
                    callAPi = false;
                } else {

                    reTypepassword.setError(null);
                }

                if (reTypepassword.getText().toString().length() == 0) {
                    reTypepassword.setError("Please confirm password");
                    reTypepassword.requestFocus();
                    callAPi = false;
                } else {

                    reTypepassword.setError(null);
                }

                if (password.getText().toString().length() == 0) {
                    password.setError("Password is required");
                    password.requestFocus();
                    callAPi = false;
                } else {

                    password.setError(null);
                }


                if (pinCode.getText().toString().length() == 0) {
                    pinCode.setError("Pincode is required");
                    pinCode.requestFocus();
                    callAPi = false;
                } else {

                    pinCode.setError(null);
                }


                if (sCity.getText().toString().length() == 0) {
                    sCity.setError("City name is required");
                    sCity.requestFocus();
                    callAPi = false;
                } else {

                    sCity.setError(null);
                }


                if (sState.getText().toString().length() == 0) {
                    sState.setError("State name is required");
                    sState.requestFocus();
                    callAPi = false;
                } else {

                    sState.setError(null);
                }


                if (sCountry.getText().toString().length() == 0) {
                    sCountry.setError("Country name is required");
                    sCountry.requestFocus();
                    callAPi = false;
                } else {

                    sCountry.setError(null);
                }


                if (!ValidationUtils.isValidMail(emailId.getText().toString())) {
                    emailId.setError("Email id is not valid");
                    emailId.requestFocus();
                    callAPi = false;
                } else {

                    emailId.setError(null);
                }


                if (emailId.getText().toString().length() == 0) {
                    emailId.setError("Email id number is required");
                    emailId.requestFocus();
                    callAPi = false;
                }

                if (!ValidationUtils.isValidMobile(pNumber.getText().toString()) || (pNumber.getText().toString().length() < 5)) {
                    pNumber.setError("Mobile number is not valid");
                    pNumber.requestFocus();
                    callAPi = false;
                } else {

                    pNumber.setError(null);
                }


                if (pNumber.getText().toString().length() == 0) {
                    pNumber.setError("Mobile number is required");
                    pNumber.requestFocus();
                    callAPi = false;
                }

                if (bDate.getText().toString().length() == 0) {
                    bDate.setError("Birthdate is required");
                    bDate.requestFocus();
                    callAPi = false;
                } else {
                    bDate.setError(null);
                    bDate.setError(null);
                }


                if (lName.getText().toString().length() == 0) {
                    lName.setError("Last name is required");
                    lName.requestFocus();
                    callAPi = false;
                } else {

                    lName.setError(null);
                    lName.clearFocus();
                }

                if (mName.getText().toString().length() == 0) {
                    mName.setError("Middle name is required");
                    mName.requestFocus();
                    callAPi = false;
                } else {

                    mName.setError(null);
                    mName.clearFocus();
                }

                if (fName.getText().toString().length() == 0) {
                    fName.setError("First name is required");
                    fName.requestFocus();
                    callAPi = false;
                } else {

                    fName.setError(null);
                    fName.clearFocus();
                }

                if (callAPi && !termsCheckBox.isChecked()) {
                    Toast.makeText(context, "Please accept the terms and condition", Toast.LENGTH_SHORT).show();
                    callAPi = false;
                }


                if (callAPi) {
                    showLoadingDialog();
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

                    int selectedId = radiogrp.getCheckedRadioButtonId();

                    registerRequest.setSalution(((RadioButton) findViewById(selectedId)).getText().toString());
/*
                    JsonParser jsonParser = new JsonParser();
                    JsonObject gsonObject = (JsonObject) jsonParser.parse(registerRequest.getURLEncodedPostdata().toString());
                    RequestProcessor requestProcessor = new RequestProcessor(CreateAccountFragment.this);
                    requestProcessor.doRegister(gsonObject);*/


                }


               /* Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), Profile.class);
                startActivity(i);*/
            }
        });

        sCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCountryPicker();
            }
        });

        countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCountryPicker();
            }
        });
        sCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityListResponse cityListResponse = OfflineData.getCityList();
                if (cityListResponse != null) {
                    final CityPickerDialog statePickerDialog = new CityPickerDialog(context, cityListResponse.getCityList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String state) {
                            sCity.setText(state);
                            sCity.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(CreateAccountActivity.this);
                    processor.getCityList();
                }
            }
        });
        sState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StateListResponse stateListResponse = OfflineData.getStateList();
                if (stateListResponse != null) {
                    final StatePickerDialog statePickerDialog = new StatePickerDialog(context, stateListResponse.getStateList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String state) {
                            sState.setText(state);
                            sState.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(CreateAccountActivity.this);
                    processor.getStateList();
                }
            }
        });

        anchal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ZoneListResponse zoneListResponse = new ZoneListResponse();
                if (zoneListResponse != null) {
                    final ZonePickerDialog zonePickerDialog;
                    zonePickerDialog = new ZonePickerDialog(context, zoneListResponse.getZoneList(),"");
                    zonePickerDialog.setZoneChangeListner(new ZoneChangeListener() {
                        @Override
                        public void onZoneSelected(String name,String id) {
                            anchal.setText(name);
                            anchal.setError(null);
                            zonePickerDialog.dismiss();
                        }
                    });
                    zonePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(CreateAccountActivity.this);
                    processor.selectZoneList();
                }
            }
        });

        RequestProcessor processor = new RequestProcessor(CreateAccountActivity.this);
        processor.selectZoneList();
    }

    private void showCountryPicker() {
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
        picker.show(this.getSupportFragmentManager(), "COUNTRY_PICKER");

    }

    private void showZonePicker() {
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
        picker.show(this.getSupportFragmentManager(), "COUNTRY_PICKER");
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {

                if (guiResponse instanceof RegisterResponse) {
                    RegisterResponse loginResponse = (RegisterResponse) guiResponse;
                    if (loginResponse != null) {
                        if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                            PreferenceUtils.setPassword(context, password.getText().toString());
                          /*  OfflineData.saveLoginResponse(loginResponse.getData());
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getActivity(), ProfileActivity.class);
                            startActivity(i);
                            getActivity().finish();*/
                            LoginRequest loginRequest = new LoginRequest();
                            loginRequest.setMobileNumber(pNumber.getText().toString());
                            loginRequest.setPassword(password.getText().toString());
                            showLoadingDialog();
                            JsonParser jsonParser = new JsonParser();
                            JsonObject gsonObject = (JsonObject) jsonParser.parse(loginRequest.getURLEncodedPostdata().toString());
                            RequestProcessor requestProcessor = new RequestProcessor(CreateAccountActivity.this);
                            requestProcessor.doLogin(gsonObject);

                        } else {
                            if (!TextUtils.isEmpty(loginResponse.getMessage())) {
                                Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Invalid username/password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else if (guiResponse instanceof StateListResponse) {
                    StateListResponse stateListResponse = (StateListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveStateResponse(stateListResponse);
                        final StatePickerDialog statePickerDialog = new StatePickerDialog(context, stateListResponse.getStateList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String state) {
                                sState.setText(state);
                                sState.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                } else if (guiResponse instanceof CityListResponse) {
                    CityListResponse stateListResponse = (CityListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveCityResponse(stateListResponse);
                        final CityPickerDialog statePickerDialog = new CityPickerDialog(context, stateListResponse.getCityList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String state) {
                                sCity.setText(state);
                                sCity.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                } else if (guiResponse instanceof LoginResponse) {
                    LoginResponse loginResponse = (LoginResponse) guiResponse;
                    if (loginResponse != null) {
                        if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                            OfflineData.saveLoginResponse(loginResponse.getData());

                            //"password":"5dc8e5500e207aa79ddd66a8f7e146df"


                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(context, ProfileActivity.class);
                            startActivity(i);
//                            context.finish();
                        } else {
                            if (!TextUtils.isEmpty(loginResponse.getMessage())) {
                                Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Invalid username/password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    if (guiResponse instanceof FamilyResponse) {

                        FamilyResponse response = (FamilyResponse) guiResponse;
                        if (response != null && response.getFamilies() != null && response.getFamilies().size() > 0) {

                            String[] datas = new String[response.getFamilies().size()];

                            for (int i = 0; i < response.getFamilies().size(); i++) {
                                datas[i] = response.getFamilies().get(i).getLast_name();
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (context, android.R.layout.select_dialog_item, datas);

                            familyMember.setThreshold(1);//will start working from first character
                            familyMember.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            //familyMember.setTextColor(Color.RED);
                        }
                    } else if (guiResponse instanceof ZoneListResponse) {

                        ZoneListResponse response = (ZoneListResponse) guiResponse;
                        if (response != null && response.getZoneList() != null && response.getZoneList().size() > 0) {


                            final ZonePickerDialog zonePickerDialog = new ZonePickerDialog(context,response.getZoneList(),"");
                            zonePickerDialog.setZoneChangeListner(new ZoneChangeListener() {
                                @Override
                                public void onZoneSelected(String zone,String id) {
                                    anchal.setText(zone);
                                    anchal.setError(null);
                                    zonePickerDialog.dismiss();
                                }
                            });
                            zonePickerDialog.show();

                           /* String[] datas = new String[response.getZoneList().size()];

                            for (int i = 0; i < response.getZoneList().size(); i++) {
                                datas[i] = response.getZoneList().get(i).getName();
                            }


                            *//*ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (getActivity(), android.R.layout.simple_spinner_dropdown_item, datas);
                            anchal.setAdapter(adapter);*/


                        }
                    }

                    else if (guiResponse instanceof LocalSanghResponse) {

                        LocalSanghResponse response = (LocalSanghResponse) guiResponse;
                        if (response != null && response.getData() != null && response.getData().size() > 0) {

                            String[] datas = new String[response.getData().size()];

                            for (int i = 0; i < response.getData().size(); i++) {
                                datas[i] = response.getData().get(i).getBranch_name();
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (context, android.R.layout.simple_spinner_dropdown_item, datas);

                            localSanghName.setAdapter(adapter);


                        }
                    }

                }

            }
        }
    }

}