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
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DialogueListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AchivementsActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.achievmenttool)
    Toolbar toolbar;

    @Bind(R.id.achievementArea)
    Spinner achievementArea;
    @Bind(R.id.achievementLevel)
    Spinner achievementLevel;
    @Bind(R.id.achievementType)
    Spinner achievementType;
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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>उल्लेखनीय उपलब्धियां</font>"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.achievement_sector, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        achievementArea.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.achievement_sector_selection,
                        this));


        ArrayAdapter<CharSequence> leveladapter = ArrayAdapter.createFromResource(this, R.array.achievement_level, android.R.layout.simple_spinner_item);
        leveladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //achievementArea.setPrompt("Select your favorite Planet!");

        achievementLevel.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        leveladapter,
                        R.layout.achievement_level_selection,
                        this));

        ArrayAdapter<CharSequence> typeadapter = ArrayAdapter.createFromResource(this, R.array.achievement_type, android.R.layout.simple_spinner_item);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //achievementArea.setPrompt("Select your favorite Planet!");

        achievementType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        typeadapter,
                        R.layout.achievement_type_selection,
                        this));

    }

    @OnClick(R.id.achievementYear)
    public void achievmentYear(){
        DialogueUtils.showYearPicker(AchivementsActivity.this, new DialogueListner() {
            @Override
            public void onYearSelected(String year) {
                achievementYear.setText(year);
            }
        });
    }

    @OnClick(R.id.addAchievements)
    public void createAchievements() {

        boolean callApi = true;
        if (achievementArea.getSelectedItem() == null) {
            callApi = false;
            Toast.makeText(AchivementsActivity.this,"Please select your achievement selector",Toast.LENGTH_SHORT).show();
        }
        else if (achievementLevel.getSelectedItem() == null) {
            callApi = false;
            Toast.makeText(AchivementsActivity.this,"Please select your achievement level",Toast.LENGTH_SHORT).show();
        }
        else if (achievementType.getSelectedItem() == null) {
            callApi = false;
            Toast.makeText(AchivementsActivity.this,"Please select your achievement type",Toast.LENGTH_SHORT).show();
        }
        if(callApi) {
            showLoadingDialog();
            Achievements address = new Achievements(achievementArea.getSelectedItem().toString(), achievementLevel.getSelectedItem().toString(), achievementType.getSelectedItem().toString(),
                    detailsAchievement.getText().toString(), achievementYear.getText().toString());
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {
                RequestProcessor requestProcessor = new RequestProcessor(AchivementsActivity.this);
                requestProcessor.addAchievemetns(loginResponse.getId(), loginResponse.getAppToken(), address);
            }
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
                } else {
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
