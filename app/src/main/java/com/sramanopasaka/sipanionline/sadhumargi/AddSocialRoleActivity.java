package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddScocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DateTimeUtils;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddSocialRoleActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.toolbar_add_socialrole)
    Toolbar toolbar;

    @Bind(R.id.orgName)
    EditText orgName;


    @Bind(R.id.role)
    Spinner role;

    @Bind(R.id.level)
    Spinner level;

    @Bind(R.id.startyear)
    EditText startdate;

    @Bind(R.id.endyear)
    EditText enddate;

    @Bind(R.id.radiogrp)
    RadioGroup radiogrp;

    private  View view = null;
    private DatePickerDialog endDatePickerDialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_social_role);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>सामाजिक पद</font>"));


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.org_role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        role.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.org_role_selection,
                        this));

        ArrayAdapter<CharSequence> leverlAdapter = ArrayAdapter.createFromResource(this, R.array.org_level, android.R.layout.simple_spinner_item);
        leverlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        level.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        leverlAdapter,
                        R.layout.org_level_selection,
                        this));



        try {
            view =  findViewById(android.R.id.content);
        }catch (Exception ex){
            view =  getWindow().getDecorView().findViewById(android.R.id.content);
        }

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener startDateSelectionListner = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear+=1;

               long minTime =  DateTimeUtils.dateToMillisecs(dayOfMonth+" "+ monthOfYear+" "+year);
                startdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                endDatePickerDialog =  new DatePickerDialog(AddSocialRoleActivity.this, endDateSelectionListner, year, myCalendar.get(Calendar.MONTH),
                        monthOfYear);
                endDatePickerDialog.getDatePicker().setMinDate(minTime);
                endDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
               // endDatePickerDialog.getDatePicker().setMinDate();
            }



        };

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog picker =   new DatePickerDialog(AddSocialRoleActivity.this, startDateSelectionListner, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();

            }
        });


        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(startdate.getText().toString())){
                    new CustomToast().showErrorToast(AddSocialRoleActivity.this,view,"Please select the start date first");
                }else {

                    endDatePickerDialog.show();
                }

            }
        });

    }

    private DatePickerDialog.OnDateSetListener endDateSelectionListner = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear+=1;

            enddate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
        }



    };



    @OnClick(R.id.btn_Socialrole)
    public void addSocialRole() {

        boolean callApi = true;



        if (TextUtils.isEmpty(enddate.getText().toString())) {
            callApi = false;
            enddate.setError("Please enter the end year");
            enddate.requestFocus();
        }

        if (TextUtils.isEmpty(startdate.getText().toString())) {
            callApi = false;
            startdate.setError("Please enter the start year");
            startdate.requestFocus();
        }

        if (level.getSelectedItem() == null && callApi) {
            Toast.makeText(AddSocialRoleActivity.this, "Organisation level is required", Toast.LENGTH_SHORT).show();
            level.requestFocusFromTouch();
            level.requestFocus();
            callApi = false;
        }

        if (role.getSelectedItem() == null && callApi) {
            Toast.makeText(AddSocialRoleActivity.this, "Organisation role is required", Toast.LENGTH_SHORT).show();
            role.requestFocusFromTouch();
            role.requestFocus();
            callApi = false;
        }

        int selectedId = radiogrp.getCheckedRadioButtonId();
        if (selectedId == -1 && callApi) {
            radiogrp.requestFocus();
            radiogrp.requestFocusFromTouch();
            Toast.makeText(AddSocialRoleActivity.this, "Please select the salution", Toast.LENGTH_SHORT).show();
            callApi = false;
        }

        if (TextUtils.isEmpty(orgName.getText().toString())) {
            callApi = false;
            orgName.setError("Institute name is required");
            orgName.requestFocus();
        }




        if (callApi) {
            showLoadingDialog();

            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {
                RequestProcessor requestProcessor = new RequestProcessor(AddSocialRoleActivity.this);
                String active = "Past";
                if (selectedId == -1)
                    active = (((RadioButton) findViewById(selectedId)).getText().toString());
                if (active.equalsIgnoreCase("Past"))
                    active = "0";
                else active = "1";
                requestProcessor.addSocialRole(loginResponse.getId(), loginResponse.getAppToken(), startdate.getText().toString(),
                        enddate.getText().toString(), orgName.getText().toString(), active, role.getSelectedItem().toString(),
                        level.getSelectedItem().toString());


            }
        }

    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                AddScocialRoleResponse response = (AddScocialRoleResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(AddSocialRoleActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddSocialRoleActivity.this, "SocialRole added successfully!", Toast.LENGTH_SHORT).show();
                    }
                    //finish();
                    Intent intentMessage = new Intent();
                    if (getParent() == null) {
                        setResult(RESULT_OK, intentMessage);
                    } else {
                        getParent().setResult(RESULT_OK, intentMessage);
                    }
                    finish();
                } else {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(AddSocialRoleActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddSocialRoleActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
