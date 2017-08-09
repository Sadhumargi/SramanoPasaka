package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    Toolbar toolbar;
    TextView basicDetails;
    TextView password;
    TextView logOut;

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
        EditProfile= (Button) findViewById(R.id.edit_profile);

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Profile.this,UploadPhoto.class);
                startActivity(i);
            }
        });




        basicDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Profile.this,BasicDetails.class);
                startActivity(i);

            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Profile.this,Password.class);
                startActivity(i);

            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Profile.this,Signin.class);
                startActivity(i);

            }
        });

    }
}
