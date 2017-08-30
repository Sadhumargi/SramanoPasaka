package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;

import java.util.Calendar;

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

    @Bind(R.id.gender)
    Spinner gender;

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

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.retype_password)
    EditText reTypepassword;

    @Bind(R.id.termsCheckBox)
    CheckBox termsCheckBox;

    @Bind(R.id.countryCodeTxt)
    TextView countryCode;

    @Bind(R.id.profile_created_by)
    Spinner profileCreatedby;

    @Bind(R.id.work_code)
    EditText valunteerCode;

    @Bind(R.id.post)
    EditText post;

    @Bind(R.id.age)
    EditText age;


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



        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear += 1;

                bDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }


        };

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean callAPi = true;

                if (profileCreatedby.getSelectedItem() == null && callAPi) {
                    Toast.makeText(getActivity(), "Profile Created by is required", Toast.LENGTH_SHORT).show();
                    profileCreatedby.requestFocus();
                    // Gender.requestFocusFromTouch();
                    callAPi = false;
                }


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

                if (post.getText().toString().length() == 0) {
                    post.setError("Password is required");
                    post.requestFocus();
                    callAPi = false;
                } else {

                    post.setError(null);
                }


                if (pinCode.getText().toString().length() == 0) {
                    pinCode.setError("Pincode is required");
                    pinCode.requestFocus();
                    callAPi = false;
                } else {

                    pinCode.setError(null);
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

                if (gender.getSelectedItem() == null && callAPi) {
                    Toast.makeText(getActivity(), "Gender is required", Toast.LENGTH_SHORT).show();
                    gender.requestFocus();
                    // Gender.requestFocusFromTouch();
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


                if (callAPi && !termsCheckBox.isChecked()) {
                    Toast.makeText(getActivity(), "Please accept the terms and condition", Toast.LENGTH_SHORT).show();
                    callAPi = false;
                }


            }
        });


        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.gender_type, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        genderAdapter,
                        R.layout.gender_type_selection,
                        getActivity()));

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

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


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.profile_created_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //achievementArea.setPrompt("Select your favorite Planet!");

        profileCreatedby.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.profile_created_by_selection,
                        getActivity()));

        profileCreatedby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
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

        });

        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




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
                }
            }

        }

    }
}