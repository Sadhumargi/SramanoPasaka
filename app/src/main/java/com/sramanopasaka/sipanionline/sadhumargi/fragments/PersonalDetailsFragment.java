package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.model.people.Person;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileUpdateActivty;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.LoginRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.ClickToSelectEditText;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;
import com.sramanopasaka.sipanionline.sadhumargi.model.Gender;
import com.sramanopasaka.sipanionline.sadhumargi.model.ProfileCreatedBy;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;
import com.sramanopasaka.sipanionline.sadhumargi.model.Salutation;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sipani001 on 29/8/17.
 */

public class PersonalDetailsFragment extends BaseFragment implements GUICallback {


    @Bind(R.id.create_profile)
    Button btnCreateProfile;

    @Bind(R.id.birth_date)
    EditText bDate;

    @Bind(R.id.i_birthdate)
    TextInputLayout textinputlayoutbithdate;

    @Bind(R.id.i_country)
    TextInputLayout textinputlayoutcountry;

    @Bind(R.id.i_gender)
    TextInputLayout textinputlayoutgender;

    @Bind(R.id.i_state)
    TextInputLayout textinputlayoutstate;

    @Bind(R.id.emailid)
    TextInputLayout textinputlayoutemail;

    @Bind(R.id.i_age)
    TextInputLayout textinputlayoutage;

    @Bind(R.id.i_pincode)
    TextInputLayout textinputlayoutpincode;

    @Bind(R.id.mobnumber)
    TextInputLayout textinputlayoutmobilenumber;

    @Bind(R.id.i_post)
    TextInputLayout textinputlayoutpost;

    @Bind(R.id.profilecreateby)
    TextInputLayout textinputlayoutprofilecreatedby;

    @Bind(R.id.i_workercode)
    TextInputLayout textinputlayoutworkercode;

    @Bind(R.id.gender)
    ClickToSelectEditText<Gender> gender;

    @Bind(R.id.mobile_number)
    EditText pNumber;

    @Bind(R.id.email)
    EditText emailId;

    @Bind(R.id.country)
    EditText sCountry;

    @Bind(R.id.state)
    EditText sState;

    @Bind(R.id.pincode)
    EditText pinCode;

  /*  @Bind(R.id.password)
    EditText password;

    @Bind(R.id.retype_password)
    EditText reTypepassword;*/

    @Bind(R.id.termsCheckBox)
    CheckBox termsCheckBox;

    @Bind(R.id.countryCodeTxt)
    TextView countryCode;

    @Bind(R.id.profile_created_by)
    ClickToSelectEditText<ProfileCreatedBy> profileCreatedby;

    @Bind(R.id.work_code)
    EditText valunteerCode;

    @Bind(R.id.post)
    EditText post;

    @Bind(R.id.no_birth_date)
    CheckBox ageCheckBox;

    @Bind(R.id.age)
    EditText age;

    @Bind(R.id.mobileInfo)
    TextView mobileInfo;

    List<Gender> genderList = new ArrayList<>();
    List<ProfileCreatedBy> profileCreatedList = new ArrayList<>();

    String[] descriptionData = {"Basic", "Family", "Personal"};

    private View view = null;


    private RegistrationPojo registrationPojo = null;

    public static PersonalDetailsFragment newInstance() {
        return new PersonalDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.personal_details_fragment, container, false);
        ButterKnife.bind(this, view);
        StateProgressBar step_view = (StateProgressBar) view.findViewById(R.id.step_view);
        step_view.setStateDescriptionData(descriptionData);
        try {
            registrationPojo = getArguments().getParcelable("DATA");
        } catch (Exception ex) {

        }

       // mobileInfo.setText(Html.fromHtml(getString(R.string.mobile_info)));

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear += 1;
//2017-08-16
                bDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }


        };

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean callAPi = true;

                if (profileCreatedby.getText().toString().length() == 0) {
                    textinputlayoutprofilecreatedby.setError("Profile Created by is required" );
                    profileCreatedby.requestFocus();
                    profileCreatedby.requestFocusFromTouch();
                    profileCreatedby.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
                    callAPi = false;
                }else{
                    profileCreatedby.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                    textinputlayoutprofilecreatedby.setError(null);
                }


             /*   if ((password.getText().toString().length()) < 8) {
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
                }*/

                if (post.getText().toString().length() == 0) {
                    textinputlayoutpost.setError("Post is required");
                    post.requestFocus();
                    callAPi = false;
                } else {

                    textinputlayoutpost.setError(null);
                }


                if (pinCode.getText().toString().length() == 0) {
                    textinputlayoutpincode.setError("Pincode is required");
                    pinCode.requestFocus();
                    callAPi = false;
                } else {

                    textinputlayoutpincode.setError(null);
                }

                if (sState.getText().toString().length() == 0) {
                    textinputlayoutstate.setError("State name is required");
                    sState.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
                    sState.requestFocus();
                    callAPi = false;
                } else {
                    sState.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                    textinputlayoutstate.setError(null);
                }


                if (sCountry.getText().toString().length() == 0) {
                    textinputlayoutcountry.setError("Country name is required");
                    sCountry.requestFocus();
                    sCountry.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
                    callAPi = false;
                } else {
                    sCountry.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                    textinputlayoutcountry.setError(null);
                }


                if (!ValidationUtils.isValidMail(emailId.getText().toString())) {
                    textinputlayoutemail.setError("Email id is not valid");
                    emailId.requestFocus();
                    callAPi = false;
                } else {

                    textinputlayoutemail.setError(null);
                }


                if (emailId.getText().toString().length() == 0) {
                    textinputlayoutemail.setError("Email id number is required");
                    emailId.requestFocus();
                    callAPi = false;
                }else {

                    textinputlayoutemail.setError(null);
                }

                if (!ValidationUtils.isValidMobile(pNumber.getText().toString()) || (pNumber.getText().toString().length() < 5)) {
                    textinputlayoutmobilenumber.setError("Mobile number is not valid");
                    pNumber.requestFocus();
                    callAPi = false;
                } else {

                    textinputlayoutmobilenumber.setError(null);
                }


                if (pNumber.getText().toString().length() == 0) {
                    textinputlayoutmobilenumber.setError("Mobile number is required");
                    pNumber.requestFocus();
                    callAPi = false;
                }else {

                    textinputlayoutmobilenumber.setError(null);
                }

                if (gender.getText().toString().length() == 0 ) {
                    textinputlayoutgender.setError("Gender is required");
                    gender.requestFocus();
                    gender.requestFocusFromTouch();
                    gender.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
                    callAPi = false;
                }else {
                    gender.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                    textinputlayoutgender.setError(null);
                    textinputlayoutgender.setError(null);
                }

                if (!ageCheckBox.isChecked() && bDate.getText().toString().length() == 0) {
                    textinputlayoutbithdate.setError("Birthdate is required");
                    bDate.requestFocus();
                    bDate.requestFocusFromTouch();
                    callAPi = false;
                } else {
                    textinputlayoutbithdate.setError(null);
                    textinputlayoutbithdate.setError(null);
                }
                if (ageCheckBox.isChecked() && age.getText().toString().length() == 0) {
                    textinputlayoutage.setError("age is required");
                    age.requestFocus();
                    age.requestFocusFromTouch();
                    callAPi = false;
                } else {
                    textinputlayoutage.setError(null);
                }

                if (callAPi && !termsCheckBox.isChecked()) {
                    new CustomToast().showErrorToast(getActivity(),view,"Please accept the terms and condtions");
                    callAPi = false;
                }

                if (callAPi) {
                    RequestProcessor requestProcessor = new RequestProcessor(PersonalDetailsFragment.this);

                    if (ageCheckBox.isChecked())
                        bDate.setText("");
                    else {
                        age.setText("0");
                    }

                    showLoadingDialog();
                    requestProcessor.doRegister(registrationPojo.getAnchalId(), registrationPojo.getLocalSanghId(), registrationPojo.getFamilyId(), registrationPojo.getRelationId(), registrationPojo.getSaluation(), registrationPojo.getFirstName(), registrationPojo.getLastName()
                            , post.getText().toString(), registrationPojo.getCity(), registrationPojo.getDistrict(), sState.getText().toString(), sCountry.getText().toString(), pNumber.getText().toString(), bDate.getText().toString(), Integer.parseInt(age.getText().toString()), gender.getText().toString(),
                            emailId.getText().toString(), pinCode.getText().toString(), profileCreatedby.getText().toString(), valunteerCode.getText().toString());
                }

            }
        });


        /*ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.gender_type, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/

        /*gender.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        genderAdapter,
                        R.layout.gender_type_selection,
                        getActivity()));*/

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        /*gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

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

        sState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StateListResponse stateListResponse = OfflineData.getStateList();
                if (stateListResponse != null) {
                    final StatePickerDialog statePickerDialog = new StatePickerDialog(getActivity(), stateListResponse.getStateList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String state) {
                            sState.setText(state);
                            sState.setError(null);
                            statePickerDialog.dismiss();
                            sState.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                            textinputlayoutstate.setError(null);
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor((GUICallback) PersonalDetailsFragment.this);
                    processor.getStateList();
                }
            }
        });


       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.profile_created_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/


        //achievementArea.setPrompt("Select your favorite Planet!");

        /*profileCreatedby.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.profile_created_by_selection,
                        getActivity()));*/


       // valunteerCode.setVisibility(View.GONE);

       /* profileCreatedby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    if (item.toString().equalsIgnoreCase("volunteer"))
                        valunteerCode.setVisibility(View.VISIBLE);
                    else
                        valunteerCode.setVisibility(View.GONE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });*/

       // age.setVisibility(View.GONE);

        ageCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    textinputlayoutage.setError(null);
                    textinputlayoutage.setVisibility(View.VISIBLE);
                } else {
                    textinputlayoutage.setVisibility(View.GONE);
                }
            }
        });

        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       profileCreatedList.add(new ProfileCreatedBy("self"));
       profileCreatedList.add(new ProfileCreatedBy("volunteer"));


        profileCreatedby.setItems(profileCreatedList);
        profileCreatedby.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<ProfileCreatedBy>() {
            @Override
            public void onItemSelectedListener(ProfileCreatedBy item, int selectedIndex) {
                profileCreatedby.setText(item.getLabel());
                profileCreatedby.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                textinputlayoutprofilecreatedby.setError(null);

                if (item != null) {
                    if (item.getLabel
                            ().equalsIgnoreCase("volunteer"))
                        textinputlayoutworkercode.setVisibility(View.VISIBLE);
                    else
                        textinputlayoutworkercode.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNoDataSet() {

            }
        });

        genderList.add(new Gender("Male"));
        genderList.add(new Gender("Female"));


        gender.setItems(genderList);
        gender.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Gender>() {
            @Override
            public void onItemSelectedListener(Gender item, int selectedIndex) {
                gender.setText(item.getLabel());
                gender.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                textinputlayoutgender.setError(null);
            }

            @Override
            public void onNoDataSet() {

            }
        });


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
        picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, GUICallback.RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(GUICallback.RequestStatus.SUCCESS)) {

                if (guiResponse instanceof StateListResponse) {
                    StateListResponse stateListResponse = (StateListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveStateResponse(stateListResponse);
                        final StatePickerDialog statePickerDialog = new StatePickerDialog(getActivity(), stateListResponse.getStateList());
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
                } else if (guiResponse instanceof RegisterResponse) {
                    RegisterResponse loginResponse = (RegisterResponse) guiResponse;
                    if (loginResponse != null) {
                        if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                            PreferenceUtils.setAppToken(getActivity(), loginResponse.getApp_token());
                            PreferenceUtils.setUserId(getActivity(), loginResponse.getId());
                          /*  OfflineData.saveLoginResponse(loginResponse.getData());
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getActivity(), ProfileActivity.class);
                            startActivity(i);
                            getActivity().finish();*/


                            LoginRequest loginRequest = new LoginRequest();
                            loginRequest.setToken(loginResponse.getApp_token());
                            loginRequest.setId(String.valueOf(loginResponse.getId()));
                            JsonParser jsonParser = new JsonParser();
                            JsonObject gsonObject = (JsonObject) jsonParser.parse(loginRequest.getURLEncodedPostdata().toString());

                            RequestProcessor requestProcessor = new RequestProcessor(PersonalDetailsFragment.this);
                            requestProcessor.doLoginWithToken(gsonObject);

                        } else {
                            if (!TextUtils.isEmpty(loginResponse.getMessage())) {
                                new CustomToast().showErrorToast(getActivity(),view,loginResponse.getMessage());
                            } else {
                                new CustomToast().showErrorToast(getActivity(),view,"Invalid username/password");
                            }
                        }
                    }
                } else if (guiResponse instanceof LoginResponse) {
                    LoginResponse loginResponse = (LoginResponse) guiResponse;
                    if (loginResponse != null) {
                        if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                            OfflineData.saveLoginResponse(loginResponse.getData());

                            //"password":"5dc8e5500e207aa79ddd66a8f7e146df"

                            PreferenceUtils.setLastLoginTime(getActivity(), System.currentTimeMillis());
                            new CustomToast().showInformationToast(getActivity(),view,"Success");

                            Intent i = new Intent(getActivity(), ProfileActivity.class);
                            i.putExtra("position", 8);
                            startActivity(i);
                            getActivity().finish();
                        } else {
                            if (!TextUtils.isEmpty(loginResponse.getMessage())) {
                                new CustomToast().showErrorToast(getActivity(),view,loginResponse.getMessage());
                            } else {
                                new CustomToast().showErrorToast(getActivity(),view,"Invalid username/password");
                            }
                        }
                    }
                }
            }

        }

    }
}