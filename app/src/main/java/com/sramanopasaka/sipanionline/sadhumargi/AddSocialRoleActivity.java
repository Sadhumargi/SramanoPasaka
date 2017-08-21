package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.wallet.LineItem;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddScocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DialogueListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.model.SocialRole;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.examName;
import static com.sramanopasaka.sipanionline.sadhumargi.R.id.instituteName;
import static com.sramanopasaka.sipanionline.sadhumargi.R.id.year;


public class AddSocialRoleActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.toolbar_add_socialrole)
    Toolbar toolbar;

    @Bind(R.id.institute_name)
    EditText instName;

    @Bind(R.id.edt_address)
    EditText address;

    @Bind(R.id.edt_role)
    EditText role;

    @Bind(R.id.level)
    EditText level;

    @Bind(R.id.startyear)
    EditText startdate;

    @Bind(R.id.endyear)
    EditText enddate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_social_role);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>संघ सम्बन्धी रुचि</font>"));

    }


      /*  @OnClick(R.id.year)
        public void selectYear(){
            DialogueUtils.showYearPicker(AddSocialRoleActivity.this, new DialogueListner() {
                @Override
                public void onYearSelected(String yearString) {
                    year.setText(yearString);
                }
            });
        }*/

@OnClick(R.id.btn_Socialrole)
        public void addSocialRole() {

            boolean callApi = true;
            if (TextUtils.isEmpty(instName.getText().toString())) {
                callApi = false;
                instName.setError("Please enter the institute name");
                instName.requestFocus();
            }
            if (TextUtils.isEmpty(address.getText().toString())) {
                callApi = false;
                address.setError("Please enter the address");
                address.requestFocus();
            }
            if (TextUtils.isEmpty(startdate.getText().toString())) {
                callApi = false;
                startdate.setError("Please enter the start year");
                startdate.requestFocus();
            }
            if (TextUtils.isEmpty(level.getText().toString())) {
                callApi = false;
                level.setError("Please enter level");
                level.requestFocus();
            }
            if (TextUtils.isEmpty(enddate.getText().toString())) {
                callApi = false;
                enddate.setError("Please enter the end year");
                enddate.requestFocus();
            }

            if (TextUtils.isEmpty(role.getText().toString())) {
                callApi = false;
                role.setError("Please enter the end year");
                role.requestFocus();
            }

            if(callApi) {
                showLoadingDialog();

                LoginModel loginResponse = OfflineData.getLoginData();
                if (loginResponse != null) {
                    RequestProcessor requestProcessor = new RequestProcessor(AddSocialRoleActivity.this);
                    requestProcessor.AddSocialRole(loginResponse.getId(),loginResponse.getAppToken(),startdate.getText().toString(),
                            enddate.getText().toString(),instName.getText().toString(),address.getText().toString(),role.getText().toString(),
                            level.getText().toString() );
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
