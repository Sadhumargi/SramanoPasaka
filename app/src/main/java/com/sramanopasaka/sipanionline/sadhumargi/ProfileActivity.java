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

public class  ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView basicDetails;
    TextView password;
    TextView logOut;
    TextView contactDetails;
    TextView education;
    TextView postalAddress;
    TextView sangh;
    TextView achievements;
    TextView sadhasya;
    TextView vidheshiYatra;
    TextView dharmik;
    TextView business;

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
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>प्रोफाइल</font>"));

        basicDetails = (TextView) findViewById(R.id.basic_details);
        password = (TextView) findViewById(R.id.password);
        logOut = (TextView) findViewById(R.id.logout);
        //contactDetails= (TextView) findViewById(R.id.contact_details);
        postalAddress= (TextView) findViewById(R.id.postal_address);
        education= (TextView) findViewById(R.id.education);
        sadhasya= (TextView) findViewById(R.id.sadasya);
        sangh= (TextView) findViewById(R.id.sangh);
        dharmik= (TextView) findViewById(R.id.dharmik);
        achievements= (TextView) findViewById(R.id.achievments);
       // vidheshiYatra= (TextView) findViewById(R.id.Vidhesiyatra);
        business= (TextView) findViewById(R.id.business);


        EditProfile = (Button) findViewById(R.id.edit_profile);


        userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        locationTxt = (TextView) findViewById(R.id.locationTxt);

        sadhasya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(ProfileActivity.this,ProfileSadhasyaActivity.class);
                startActivity(i);

            }
        });

        dharmik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",6);
                startActivity(i);

            }
        });

        sangh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",7);
                startActivity(i);
            }
        });

        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",3);
                startActivity(i);
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",5);
                startActivity(i);
            }
        });


        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",4);
                startActivity(i);
            }
        });

        postalAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",2);
                startActivity(i);


            }
        });

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",1);
                startActivity(i);
            }
        });


        basicDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",0);
                startActivity(i);

            }
        });


        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",8);
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

        findViewById(R.id.buttonNxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, ProfileUpdateActivty.class);
                i.putExtra("position",0);
                startActivity(i);
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
