package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView basicDetails;
    TextView password;
    TextView logOut;

    private TextView userNameTxt = null;
    private TextView locationTxt = null;


    Button next;
    Button EditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.profiletool);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>My Profile</font>"));

        basicDetails = (TextView) findViewById(R.id.basic_details);
        password = (TextView) findViewById(R.id.password);
        logOut = (TextView) findViewById(R.id.logout);
        EditProfile = (Button) findViewById(R.id.edit_profile);

        userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        locationTxt = (TextView) findViewById(R.id.locationTxt);

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, UploadPhoto.class);
                startActivity(i);
            }
        });


        basicDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, BasicDetailsActivity.class);
                startActivity(i);

            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, Password.class);
                startActivity(i);

            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OfflineData.deleteLoginResponse();
                Intent i = new Intent(ProfileActivity.this, SigninActivity.class);
                startActivity(i);
                finish();

            }
        });


        fillDataToUi();

    }

    private void fillDataToUi() {
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null ) {


            userNameTxt.setText(loginResponse.getFirstName());
            locationTxt.setText(loginResponse.getCity());
        }
    }
}
