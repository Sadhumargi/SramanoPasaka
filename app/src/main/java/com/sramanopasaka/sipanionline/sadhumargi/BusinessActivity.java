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
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddBusinessResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Business;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessActivity extends BaseActivity implements GUICallback {

    @Bind(R.id.businesstool)
    Toolbar toolbar;

    @Bind(R.id.businessType)
    Spinner businessType;

    @Bind(R.id.businessName)
    EditText businessName;

    @Bind(R.id.businessRole)
    EditText businessRole;

    @Bind(R.id.businessStartYear)
    EditText businessStartYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>व्यवसाय, नौकरी, विशेष दक्षता विवरण</font>"));

      /*  ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.business_type));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessType.setAdapter(aa);*/

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.business_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessType.setPrompt("Select your favorite Planet!");

        businessType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.business_type_selection,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));


    }

    @OnClick(R.id.addBusinessButton)
    public void addBusiness(){
        if(businessType.getSelectedItem()!=null) {
            showLoadingDialog();
            Business business = new Business(businessType.getSelectedItem().toString(), businessName.getText().toString(), businessRole.getText().toString(),
                    businessStartYear.getText().toString());
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {


                RequestProcessor requestProcessor = new RequestProcessor(BusinessActivity.this);
                requestProcessor.addBusiness(loginResponse.getId(), loginResponse.getAppToken(), business);
            }
        }else{
            Toast.makeText(BusinessActivity.this,"Please select your business type",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

        //TODO Validation

        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                AddBusinessResponse response = (AddBusinessResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(BusinessActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BusinessActivity.this, "Business added successfully!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }else{
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(BusinessActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BusinessActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
