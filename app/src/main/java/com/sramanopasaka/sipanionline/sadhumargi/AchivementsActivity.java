package com.sramanopasaka.sipanionline.sadhumargi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AchivementsActivity extends BaseActivity implements GUICallback{

    @Bind(R.id.achievmenttool)
    Toolbar toolbar;

    @Bind(R.id.achievementArea)
    EditText achievementArea;
    @Bind(R.id.achievementLevel)
    EditText achievementLevel;
    @Bind(R.id.achievementType)
    EditText achievementType;
    @Bind(R.id.detailsAchievement)
    EditText detailsAchievement;
    @Bind(R.id.achievementYear)
    EditText achievementYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);
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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>उल्लेखनीय उपलब्धियां</font>"));

    }

    @OnClick(R.id.addAchievements)
    public void createAchievements(){
        showLoadingDialog();
        Achievements address = new Achievements(achievementArea.getText().toString(), achievementLevel.getText().toString(), achievementType.getText().toString(),
                detailsAchievement.getText().toString(), achievementYear.getText().toString());
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(AchivementsActivity.this);
            requestProcessor.addAchievemetns(loginResponse.getId(), loginResponse.getAppToken(), address);
        }
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                AddAchievementResponse response = (AddAchievementResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(AchivementsActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AchivementsActivity.this, "Address added successfully!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }else{
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(AchivementsActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AchivementsActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
