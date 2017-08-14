package com.sramanopasaka.sipanionline.sadhumargi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ContactDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.UploadPhotoFragment;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class ProfileUpdateActivty extends AppCompatActivity {

    private RelativeLayout basicDetaisView = null;
    private RelativeLayout uploadPhotoView = null;
    private RelativeLayout contactDetailsView = null;
    private RelativeLayout educationView = null;
    private RelativeLayout businessView = null;
    private FloatingActionButton fab = null;

    private ImageView basicDetailsImg = null;
    private ImageView uploadPhotoImg = null;
    private ImageView contactDetailsImage = null;
    private ImageView educationImage = null;
    private ImageView businessImage = null;

    private View basicRight = null;
    private View businessLeft = null;
    private View educationRight = null;
    private View educationLeft = null;
    private View contactRight = null;
    private View contactLeft = null;
    private View uploadRight = null;
    private View uploadLeft = null;

    private int MAX_DETAILS = 5;

    private int currentTab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        basicDetaisView = (RelativeLayout) findViewById(R.id.basicDetaisView);
        uploadPhotoView = (RelativeLayout) findViewById(R.id.uploadPhotoView);
        contactDetailsView = (RelativeLayout) findViewById(R.id.contactDetailsView);
        educationView = (RelativeLayout) findViewById(R.id.educationView);
        businessView = (RelativeLayout) findViewById(R.id.businessView);

        basicDetailsImg = (ImageView) findViewById(R.id.basicDetailsImg);
        uploadPhotoImg = (ImageView) findViewById(R.id.uploadPhotoImg);
        contactDetailsImage = (ImageView) findViewById(R.id.contactDetailsImage);
        educationImage = (ImageView) findViewById(R.id.educationImage);
        businessImage = (ImageView) findViewById(R.id.businessImage);

        uploadLeft = findViewById(R.id.uploadLeft);
        uploadRight = findViewById(R.id.uploadRight);
        contactLeft = findViewById(R.id.contactLeft);
        contactRight = findViewById(R.id.contactRight);
        educationLeft = findViewById(R.id.educationLeft);
        educationRight = findViewById(R.id.educationRight);
        businessLeft = findViewById(R.id.businessLeft);
        basicRight = findViewById(R.id.basicRight);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        basicDetaisView.setTag(0);
        uploadPhotoView.setTag(1);
        contactDetailsView.setTag(2);
        educationView.setTag(3);
        businessView.setTag(4);

        basicDetaisView.setOnClickListener(sectionSelecredListner);
        uploadPhotoView.setOnClickListener(sectionSelecredListner);
        contactDetailsView.setOnClickListener(sectionSelecredListner);
        educationView.setOnClickListener(sectionSelecredListner);
        businessView.setOnClickListener(sectionSelecredListner);


        try {
            currentTab = getIntent().getExtras().getInt("position");
        } catch (Exception ex) {

        }


        switch (currentTab) {
            case 0:
                setFragment(BasicDetailsFragment.newInstance(), "");

                break;
            case 1:
                setFragment(UploadPhotoFragment.newInstance(), "");
                break;
//
            default:
                setFragment(BasicDetailsFragment.newInstance(), "");
                break;
        }
        if (currentTab == -1)
            setViewColor(0);
        else
            setViewColor(currentTab);

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTab+1<MAX_DETAILS){
                    currentTab++;
                    showPages(currentTab);
                }else
                    findViewById(R.id.nextButton).setVisibility(View.GONE);
            }
        });


    }

    private void setViewColor(int index) {
        switch (index) {
            case 0:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload);
                contactDetailsImage.setImageResource(R.drawable.contact_detail);
                educationImage.setImageResource(R.drawable.education_icon);
                businessImage.setImageResource(R.drawable.business);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 1:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail);
                educationImage.setImageResource(R.drawable.education_icon);
                businessImage.setImageResource(R.drawable.business);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 2:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                educationImage.setImageResource(R.drawable.education_icon);
                businessImage.setImageResource(R.drawable.business);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 3:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 4:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business_b);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                break;

        }
    }

    private View.OnClickListener sectionSelecredListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            showPages(tag);
        }
    };

    private void showPages(int tag){
        switch (tag) {
            case 0:
                setFragment(BasicDetailsFragment.newInstance(), "");
                break;
            case 1:
                setFragment(UploadPhotoFragment.newInstance(), "");
                break;
            case 2:
                setFragment(ContactDetailsFragment.newInstance(), "");

                break;
            case 3:

                break;
            case 4:

                break;

        }
        if (tag != -1)
            setViewColor(tag);
    }

    protected void setFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();


        // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.contentPanel, fragment, tag);
        fragmentTransaction.commit();
    }
}