package com.sramanopasaka.sipanionline.sadhumargi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddEducationResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DialogueListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Education;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EducationActivity extends BaseActivity implements GUICallback{

    @Bind(R.id.educationName)
    Spinner educationName;

    @Bind(R.id.educationtool)
    Toolbar toolbar;

    @Bind(R.id.educationDescription)
    EditText educationDescription;

    @Bind(R.id.educationScore)
    EditText educationScore;

    @Bind(R.id.educationInstitute)
    EditText educationInstitute;

    @Bind(R.id.educationyear)
    EditText educationyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>शिक्षा विवरण</font>"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.education_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        educationName.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.education_name_selection,
                        this));


    }

    @OnClick(R.id.educationyear)
    public void educationyear(){
        DialogueUtils.showYearPicker(EducationActivity.this, new DialogueListner() {
            @Override
            public void onYearSelected(String year) {
                educationyear.setText(year);
            }
        });
    }
    @OnClick(R.id.addEducation)
    public void addEducation(){
        boolean callApi = true;
        if (educationName.getSelectedItem() == null) {
            callApi = false;
            Toast.makeText(EducationActivity.this,"Please select your education name",Toast.LENGTH_SHORT).show();
        }

        if(callApi) {
            showLoadingDialog();
            Education education = new Education(educationName.getSelectedItem().toString(), educationDescription.getText().toString(), educationScore.getText().toString(),
                    educationInstitute.getText().toString(), educationyear.getText().toString());
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {
                RequestProcessor requestProcessor = new RequestProcessor(EducationActivity.this);
                requestProcessor.addEducation(loginResponse.getId(), loginResponse.getAppToken(), education);
            }
        }
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                AddEducationResponse response = (AddEducationResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(EducationActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EducationActivity.this, "Education added successfully!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(EducationActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EducationActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(EducationActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(EducationActivity.this, "Network error", Toast.LENGTH_SHORT).show();
        }
    }
}
