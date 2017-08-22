package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DialogueListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExamActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.achievmenttool)
    Toolbar toolbar;

    @Bind(R.id.examName)
    EditText examName;
    @Bind(R.id.instituteName)
    EditText instituteName;

    @Bind(R.id.year)
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pariksha_fragment);
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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>उल्लेखनीय उपलब्धियां</font>"));



    }

    @OnClick(R.id.year)
    public void selectYear(){
        DialogueUtils.showYearPicker(AddExamActivity.this, new DialogueListner() {
            @Override
            public void onYearSelected(String yearString) {
                year.setText(yearString);
            }
        });
    }

    @OnClick(R.id.addExam)
    public void addExam() {

        boolean callApi = true;
        if (TextUtils.isEmpty(instituteName.getText().toString())) {
            callApi = false;
            instituteName.setError("Please enter the institute name");
            instituteName.requestFocus();
        }
        if (TextUtils.isEmpty(examName.getText().toString())) {
            callApi = false;
            examName.setError("Please enter the exam name");
            examName.requestFocus();
        }

        if(callApi) {
            showLoadingDialog();
            Exams exams = new Exams(examName.getText().toString(), instituteName.getText().toString(), year.getText().toString());
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {
                RequestProcessor requestProcessor = new RequestProcessor(AddExamActivity.this);
                requestProcessor.addExams(loginResponse.getId(), loginResponse.getAppToken(), exams );
            }
        }

    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                AddExamResponse response = (AddExamResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(AddExamActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddExamActivity.this, "Exam added successfully!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AddExamActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddExamActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
