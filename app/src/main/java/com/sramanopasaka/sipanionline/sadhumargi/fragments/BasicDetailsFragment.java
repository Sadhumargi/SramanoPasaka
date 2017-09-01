package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.ObjectConstructor;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.BasicDetailsRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.BasicDetailsData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.CityPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class BasicDetailsFragment extends BaseFragment implements GUICallback {

    private View view = null;
    @Bind(R.id.isFamilyHead)
    CheckBox isFamilyHead;
    @Bind(R.id.radiogrp)
    RadioGroup radiogrp;
    @Bind(R.id.first_name)
    EditText firstName;

    @Bind(R.id.last_name)
    EditText lastName;

    @Bind(R.id.adress)
    EditText adress;

    @Bind(R.id.birth_date)
    EditText birth_date;
    @Bind(R.id.edt_altnum)
    EditText alternateNumber;
    @Bind(R.id.bloodgrp)
    Spinner bloodgrp;
    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.state)
    EditText state;
    @Bind(R.id.country)
    EditText country;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.whatsupnumber)
    EditText whatsupnumber;
    @Bind(R.id.mobile_number)
    EditText mobileNumber;
    @Bind(R.id.maritual_status)
    Spinner maritual_status;
    @Bind(R.id.Gender)
    Spinner Gender;
    /* @Bind(R.id.Age)
     EditText Age;*/
    @Bind(R.id.pincode)
    EditText pincode;

    @Bind(R.id.guardianName)
    EditText guardianName;

    @Bind(R.id.relation)
    Spinner guardianType;

    @Bind(R.id.marriedDate)
    EditText marriedDate;


    private TabselectionListner tabselectionListner = null;
    private ActionBarUpdator actionBarUpdator = null;

    public static BasicDetailsFragment newInstance() {
        return new BasicDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basic_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            tabselectionListner = (TabselectionListner) getActivity();
            actionBarUpdator = (ActionBarUpdator) getActivity();
            actionBarUpdator.onUpdateTitile(getString(R.string.Basic_Details));
        } catch (Exception ex) {

        }

        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(BasicDetailsFragment.this);
            requestProcessor.getBasicDetails(loginResponse.getId(), loginResponse.getAppToken());
        }


        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCountryPicker();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityListResponse cityListResponse = OfflineData.getCityList();
                if (cityListResponse != null) {
                    final CityPickerDialog statePickerDialog = new CityPickerDialog(getActivity(), cityListResponse.getCityList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String state) {
                            city.setText(state);
                            city.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(BasicDetailsFragment.this);
                    processor.getCityList();
                }
            }
        });
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StateListResponse stateListResponse = OfflineData.getStateList();
                if (stateListResponse != null) {
                    final StatePickerDialog statePickerDialog = new StatePickerDialog(getActivity(), stateListResponse.getStateList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String stateString) {
                            state.setText(stateString);
                            state.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(BasicDetailsFragment.this);
                    processor.getStateList();
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.guardian_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        guardianType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.guardian_type_selection,
                        getActivity()));

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.gender_type, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Gender.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        genderAdapter,
                        R.layout.gender_type_selection,
                        getActivity()));

        ArrayAdapter<CharSequence> bloodGroupAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.blood_group, android.R.layout.simple_spinner_item);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bloodgrp.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        bloodGroupAdapter,
                        R.layout.blood_group_selection,
                        getActivity()));

        ArrayAdapter<CharSequence> maritualStatusAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.maretial_status, android.R.layout.simple_spinner_item);
        maritualStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        maritual_status.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        maritualStatusAdapter,
                        R.layout.maritual_status_selection,
                        getActivity()));

        maritual_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    if (item.toString().equalsIgnoreCase("Married"))
                        marriedDate.setVisibility(View.VISIBLE);
                    else
                        marriedDate.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear += 1;

                marriedDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                marriedDate.setError(null);
            }


        };

        marriedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        final DatePickerDialog.OnDateSetListener dateSelected = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear += 1;

                birth_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }


        };

        birth_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), dateSelected, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


    }

    private void showCountryPicker() {
        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                country.setText(name);
                country.setError(null);
                picker.dismiss();
            }
        });
        picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof BasicDetailsResponse) {
                    BasicDetailsResponse response = (BasicDetailsResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (response.getData() != null) {
                                firstName.setText(response.getData().getFirstName());
                                lastName.setText(response.getData().getLastName());
                                guardianName.setText(response.getData().getGuardianName());
                                adress.setText(response.getData().getAddress());
                                country.setText(response.getData().getCountry());
                                pincode.setText(response.getData().getPincode());
                                birth_date.setText(response.getData().getBirthDay());
                                state.setText(response.getData().getState());
                                alternateNumber.setText(response.getData().getAlternateNumber());
                                //bloodgrp.setText(response.getData().getBloodGroup());
                                city.setText(response.getData().getCity());
                                email.setText(response.getData().getEmailAddress());
                                whatsupnumber.setText(response.getData().getWhatsappNumber());
                                mobileNumber.setText(response.getData().getMobile());
                                selectSpinnerItemByValue(guardianType, response.getData().getGuardianType());
                                selectSpinnerItemByValue(Gender, response.getData().getGender());
                                selectSpinnerItemByValue(bloodgrp, response.getData().getBloodGroup());
                                if (!TextUtils.isEmpty(response.getData().getMaritalStatus()) && response.getData().getMaritalStatus().equalsIgnoreCase("0"))
                                    selectSpinnerItemByValue(maritual_status, "Married");
                                else
                                    selectSpinnerItemByValue(maritual_status, "UnMarried");
                                if (!TextUtils.isEmpty(response.getData().getMaritalStatus()) && response.getData().getMaritalStatus().equalsIgnoreCase("Married")) {
                                    marriedDate.setVisibility(View.VISIBLE);
                                    marriedDate.setText(response.getData().getMarriageDate());
                                }
                                if (!TextUtils.isEmpty(response.getData().getIsHeadOfFamily()) && response.getData().getIsHeadOfFamily().equalsIgnoreCase("1")) {
                                    isFamilyHead.setChecked(true);
                                } else
                                    isFamilyHead.setChecked(false);

                            }

                            Log.e("----", "success");
                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "some thing went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else if (guiResponse instanceof StateListResponse) {
                    StateListResponse stateListResponse = (StateListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveStateResponse(stateListResponse);
                        final StatePickerDialog statePickerDialog = new StatePickerDialog(getActivity(), stateListResponse.getStateList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String stateString) {
                                state.setText(stateString);
                                state.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                } else if (guiResponse instanceof CityListResponse) {
                    CityListResponse stateListResponse = (CityListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveCityResponse(stateListResponse);
                        final CityPickerDialog statePickerDialog = new CityPickerDialog(getActivity(), stateListResponse.getCityList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String state) {
                                city.setText(state);
                                city.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                } else if (guiResponse instanceof UpdateBasicDetailsResponse) {
                    UpdateBasicDetailsResponse response = (UpdateBasicDetailsResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        }
                        //Moving to the next page.
                        tabselectionListner.onSelectNextTab();
                    } else {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    @OnClick(R.id.create_profile)
    public void updateProfile() {

        boolean callAPi = true;
        if (alternateNumber.getText().toString().length() == 0) {
            alternateNumber.setError("Alternative number is required");
            alternateNumber.requestFocus();
            callAPi = false;
        }

        if (whatsupnumber.getText().toString().length() == 0) {
            whatsupnumber.setError("Whatsapp number is required");
            whatsupnumber.requestFocus();
            callAPi = false;
        }
        if (!ValidationUtils.isValidMail(email.getText().toString())) {
            email.setError("Email id is required");
            email.requestFocus();
            callAPi = false;
        }
        if (!ValidationUtils.isValidMobile(mobileNumber.getText().toString())) {
            mobileNumber.setError("Mobile number is required");
            mobileNumber.requestFocus();
            callAPi = false;
        }

        int selectedId = radiogrp.getCheckedRadioButtonId();
        if (selectedId == -1 && callAPi) {
            radiogrp.requestFocus();
            radiogrp.requestFocusFromTouch();
            Toast.makeText(getActivity(), "Please select the salution", Toast.LENGTH_SHORT).show();
            callAPi = false;
        }

        if (guardianType.getSelectedItem() == null && callAPi) {
            Toast.makeText(getActivity(), "Guardian Type is required", Toast.LENGTH_SHORT).show();
            guardianType.requestFocus();
            guardianType.requestFocusFromTouch();
            guardianType.requestFocus();
            callAPi = false;
        }
        if (Gender.getSelectedItem() == null && callAPi) {
            Toast.makeText(getActivity(), "Gender is required", Toast.LENGTH_SHORT).show();
            Gender.requestFocus();
            // Gender.requestFocusFromTouch();
            callAPi = false;
        }
        if (bloodgrp.getSelectedItem() == null && callAPi) {
            Toast.makeText(getActivity(), "Blood group is required", Toast.LENGTH_SHORT).show();
            bloodgrp.requestFocus();
            bloodgrp.requestFocusFromTouch();
            callAPi = false;
        }
        if (maritual_status.getSelectedItem() == null && callAPi) {
            Toast.makeText(getActivity(), "Marital status is required", Toast.LENGTH_SHORT).show();
            maritual_status.requestFocus();
            maritual_status.requestFocusFromTouch();
            callAPi = false;
        }




      /*  if (Age.getText().toString().length() == 0) {
            Age.setError("Age is required");
            Age.requestFocus();
            callAPi = false;
        }*/
        if (birth_date.getText().toString().length() == 0) {
            birth_date.setError("birth date is required");
            birth_date.requestFocus();
            callAPi = false;
        }
        if (pincode.getText().toString().length() == 0) {
            pincode.setError("Pin code is required");
            pincode.requestFocus();
            callAPi = false;
        }
        if (pincode.getText().toString().length() == 0) {
            pincode.setError("Pin code is required");
            pincode.requestFocus();
            callAPi = false;
        }
        if (city.getText().toString().length() == 0) {
            city.setError("City is required");
            city.requestFocus();
            callAPi = false;
        }
        if (state.getText().toString().length() == 0) {
            state.setError("State is required");
            state.requestFocus();
            callAPi = false;
        }
        if (country.getText().toString().length() == 0) {
            country.setError("Country is required");
            country.requestFocus();
            callAPi = false;
        }

        if (country.getText().toString().length() == 0) {
            country.setError("Country is required");
            country.requestFocus();
            callAPi = false;
        }

        if (adress.getText().toString().length() == 0) {
            adress.setError("Address is required");
            adress.requestFocus();
            callAPi = false;
        }
        if (guardianName.getText().toString().length() == 0) {
            guardianName.setError("Guardian name is required");
            guardianName.requestFocus();
            callAPi = false;
        }

        if (firstName.getText().toString().length() == 0) {
            firstName.setError("first name is required");
            firstName.requestFocus();
            callAPi = false;
        }
        if (lastName.getText().toString().length() == 0) {
            lastName.setError("last name is required");
            lastName.requestFocus();
            callAPi = false;
        }


        if (maritual_status.getSelectedItem() != null && callAPi) {
            if (maritual_status.getSelectedItem().toString().equalsIgnoreCase("Married") && TextUtils.isEmpty(marriedDate.getText().toString())) {
                marriedDate.requestFocus();
                marriedDate.setError("Please select your married date");
                callAPi = false;
            }
        }


        if (callAPi) {
            showLoadingDialog();
            BasicDetailsData basicDetailsData = new BasicDetailsData();
            basicDetailsData.setIsHeadOfFamily(String.valueOf(isFamilyHead.isChecked()));
            if (selectedId == -1)
                basicDetailsData.setSalution(((RadioButton) view.findViewById(selectedId)).getText().toString());
            basicDetailsData.setFirstName(firstName.getText().toString());
            basicDetailsData.setChildCount("0");
            basicDetailsData.setLastName(lastName.getText().toString());
            basicDetailsData.setGuardianType(guardianType.getSelectedItem().toString());
            basicDetailsData.setGuardianName(guardianName.getText().toString());
            basicDetailsData.setAddress(adress.getText().toString());
            basicDetailsData.setCountry(country.getText().toString());
            basicDetailsData.setCity(city.getText().toString());
            basicDetailsData.setState(state.getText().toString());
            basicDetailsData.setPincode(pincode.getText().toString());
            basicDetailsData.setBirthDay(birth_date.getText().toString());
            basicDetailsData.setGender(Gender.getSelectedItem().toString());
            basicDetailsData.setBloodGroup(bloodgrp.getSelectedItem().toString());
            if (maritual_status.getSelectedItem().toString().equalsIgnoreCase("Married"))
                basicDetailsData.setMaritalStatus("0");
            else
                basicDetailsData.setMaritalStatus("1");
            basicDetailsData.setMobile(mobileNumber.getText().toString());
            basicDetailsData.setEmailAddress(email.getText().toString());
            basicDetailsData.setWhatsappNumber(whatsupnumber.getText().toString());
            basicDetailsData.setAlternateNumber(alternateNumber.getText().toString());

            basicDetailsData.setMarriageDate(marriedDate.getText().toString());
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {


                RequestProcessor requestProcessor = new RequestProcessor(BasicDetailsFragment.this);
                requestProcessor.updateBasicDetails(loginResponse.getId(), loginResponse.getAppToken(), basicDetailsData);
            }
        }
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        NothingSelectedSpinnerAdapter adapter = (NothingSelectedSpinnerAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            Object object = adapter.getItem(position);
            if (object != null) {
                if (adapter.getItem(position).toString().equalsIgnoreCase(value)) {
                    spnr.setSelection(position);
                    return;
                }
            }
        }
    }
}
