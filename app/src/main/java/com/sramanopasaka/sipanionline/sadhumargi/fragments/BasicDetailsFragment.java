package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.BasicDetailsRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.BasicDetailsData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

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
    @Bind(R.id.father)
    EditText father;
    @Bind(R.id.adress)
    EditText adress;

    @Bind(R.id.pincode)
    EditText pincode;
    @Bind(R.id.birth_date)
    EditText birth_date;
    @Bind(R.id.addressLine)
    EditText addressLine;
    @Bind(R.id.edt_altnum)
    EditText alternateNumber;
    @Bind(R.id.bloodgrp)
    EditText bloodgrp;
    @Bind(R.id.city)
    EditText city;
    @Bind(R.id.country)
    EditText country;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.whatsupnumber)
    EditText whatsupnumber;
    @Bind(R.id.mobile_number)
    EditText mobileNumber;

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
        tabselectionListner = (TabselectionListner) getActivity();
        actionBarUpdator = (ActionBarUpdator) getActivity();
        actionBarUpdator.onUpdateTitile(getString(R.string.Basic_Details));

        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(BasicDetailsFragment.this);
            requestProcessor.getBasicDetails(loginResponse.getId(), loginResponse.getAppToken());
        }

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
                                father.setText(response.getData().getGuardianName());
                                adress.setText(response.getData().getAddress());
                                country.setText(response.getData().getCountry());
                                pincode.setText(response.getData().getPincode());
                                birth_date.setText(response.getData().getBirthDay());
                                alternateNumber.setText(response.getData().getAlternateNumber());
                                birth_date.setText(response.getData().getBirthDay());
                                bloodgrp.setText(response.getData().getBloodGroup());
                                city.setText(response.getData().getCity());
                                country.setText(response.getData().getCountry());
                                email.setText(response.getData().getEmailAddress());
                                whatsupnumber.setText(response.getData().getWhatsappNumber());
                                mobileNumber.setText(response.getData().getMobile());
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
                }else{
                    UpdateBasicDetailsResponse response = (UpdateBasicDetailsResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        }
                        //Moving to the next page.
                        tabselectionListner.onSelectNextTab();
                    }else{
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
        //TODO validations here and everything is okay then call the API Please set the filed values to Basic Details Data class.

        showLoadingDialog();
        BasicDetailsData basicDetailsData = new BasicDetailsData();
        basicDetailsData.setAddress(addressLine.getText().toString());
        basicDetailsData.setAlternateNumber(alternateNumber.getText().toString());
        basicDetailsData.setBirthDay(birth_date.getText().toString());
        basicDetailsData.setBloodGroup(bloodgrp.getText().toString());
        basicDetailsData.setCity(city.getText().toString());
        basicDetailsData.setCountry(country.getText().toString());
        basicDetailsData.setEmailAddress(email.getText().toString());
        basicDetailsData.setFirstName(firstName.getText().toString());
        basicDetailsData.setWhatsappNumber(whatsupnumber.getText().toString());
        basicDetailsData.setMobile(mobileNumber.getText().toString());
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(BasicDetailsFragment.this);
            requestProcessor.updateBasicDetails(loginResponse.getId(), loginResponse.getAppToken(), basicDetailsData);
        }
    }
}
