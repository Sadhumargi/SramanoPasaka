package com.sramanopasaka.sipanionline.sadhumargi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.plus.model.people.Person;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.AchievementListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BusinessListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ChangePasswordFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ContactDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.DharmicFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.EducationListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.SanghFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.UploadPhotoFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class ProfileUpdateActivty extends AppCompatActivity implements TabselectionListner, ActionBarUpdator {

    private RelativeLayout basicDetaisView = null;
    private RelativeLayout uploadPhotoView = null;
    private RelativeLayout contactDetailsView = null;
    private RelativeLayout educationView = null;
    private RelativeLayout businessView = null;
    private RelativeLayout achievementDetailsView = null;
    private RelativeLayout dharmikView = null;
    private RelativeLayout sanghView = null;
    private RelativeLayout passwordView = null;

    private ImageView basicDetailsImg = null;
    private ImageView uploadPhotoImg = null;
    private ImageView contactDetailsImage = null;
    private ImageView educationImage = null;
    private ImageView businessImage = null;
    private ImageView achievementDetailsImage = null;
    private ImageView dharmikImage = null;
    private ImageView sanghImage = null;
    private ImageView passwordImage = null;


    private View basicRight = null;
    private View businessLeft = null;
    private View educationRight = null;
    private View educationLeft = null;
    private View contactRight = null;
    private View contactLeft = null;
    private View uploadRight = null;
    private View uploadLeft = null;
    private View achievementLeft = null;
    private View achievementRight = null;
    private View dharmikLeft = null;
    private View dharmikRight = null;
    private View businessRight = null;
    private View sanghLeft = null;
    private View sanghRight =  null;
    private View passwordLeft = null;

    private TextView titleTxt = null;
    private HorizontalScrollView horizondalScrollView = null;




    private int MAX_DETAILS = 8;

    private int currentTab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        horizondalScrollView = (HorizontalScrollView) findViewById(R.id.horizondalScrollView);

        basicDetaisView = (RelativeLayout) findViewById(R.id.basicDetaisView);
        uploadPhotoView = (RelativeLayout) findViewById(R.id.uploadPhotoView);
        contactDetailsView = (RelativeLayout) findViewById(R.id.contactDetailsView);
        educationView = (RelativeLayout) findViewById(R.id.educationView);
        businessView = (RelativeLayout) findViewById(R.id.businessView);
        achievementDetailsView = (RelativeLayout) findViewById(R.id.achievementDetailsView);
        dharmikView = (RelativeLayout) findViewById(R.id.dharmikView);
        sanghView = (RelativeLayout) findViewById(R.id.sanghView);
        passwordView = (RelativeLayout) findViewById(R.id.passwordView);


        basicDetailsImg = (ImageView) findViewById(R.id.basicDetailsImg);
        uploadPhotoImg = (ImageView) findViewById(R.id.uploadPhotoImg);
        contactDetailsImage = (ImageView) findViewById(R.id.contactDetailsImage);
        educationImage = (ImageView) findViewById(R.id.educationImage);
        businessImage = (ImageView) findViewById(R.id.businessImage);
        achievementDetailsImage = (ImageView) findViewById(R.id.achievementDetailsImage);
        dharmikImage  = (ImageView) findViewById(R.id.dharmikImage);
        sanghImage   = (ImageView) findViewById(R.id.sanghImage);
        passwordImage   = (ImageView) findViewById(R.id.passwordImage);

        uploadLeft = findViewById(R.id.uploadLeft);
        uploadRight = findViewById(R.id.uploadRight);
        contactLeft = findViewById(R.id.contactLeft);
        contactRight = findViewById(R.id.contactRight);
        educationLeft = findViewById(R.id.educationLeft);
        educationRight = findViewById(R.id.educationRight);
        businessLeft = findViewById(R.id.businessLeft);
        basicRight = findViewById(R.id.basicRight);
        achievementLeft = findViewById(R.id.achievementLeft);
        achievementRight = findViewById(R.id.achievementRight);
        dharmikLeft  = findViewById(R.id.dharmikLeft);
        dharmikRight  = findViewById(R.id.dharmikRight);
        businessRight  = findViewById(R.id.businessRight);
        sanghLeft  = findViewById(R.id.sanghLeft);
        sanghRight  = findViewById(R.id.sanghRight);
        passwordLeft = findViewById(R.id.passwordLeft);

        titleTxt = (TextView) findViewById(R.id.titleTxt);

        basicDetaisView.setTag(0);
        uploadPhotoView.setTag(1);
        contactDetailsView.setTag(2);
        achievementDetailsView.setTag(3);
        educationView.setTag(4);
        businessView.setTag(5);
        dharmikView.setTag(6);
        sanghView.setTag(7);
        passwordView.setTag(8);

        basicDetaisView.setOnClickListener(sectionSelecredListner);
        uploadPhotoView.setOnClickListener(sectionSelecredListner);
        contactDetailsView.setOnClickListener(sectionSelecredListner);
        educationView.setOnClickListener(sectionSelecredListner);
        businessView.setOnClickListener(sectionSelecredListner);
        achievementDetailsView.setOnClickListener(sectionSelecredListner);
        dharmikView.setOnClickListener(sectionSelecredListner);
        sanghView.setOnClickListener(sectionSelecredListner);
        passwordView.setOnClickListener(sectionSelecredListner);


        try {
            currentTab = getIntent().getExtras().getInt("position");
        } catch (Exception ex) {

        }


       /* switch (currentTab) {
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
                setFragment(AchievementListingFragment.newInstance(), "");
                break;
            case 5:
                setFragment(BusinessListingFragment.newInstance(), "");
                break;

            default:
                setFragment(BasicDetailsFragment.newInstance(), "");
                break;
        }
        if (currentTab == -1)
            setViewColor(0);
        else
            setViewColor(currentTab);*/
        if (currentTab == -1)
            showPages(0);
        else
            showPages(currentTab);

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTab + 1 < MAX_DETAILS) {
                    currentTab++;
                    showPages(currentTab);
                } else
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
                achievementDetailsImage.setImageResource(R.drawable.achievement);
                dharmikImage.setImageResource(R.drawable.dharmic);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));


                break;
            case 1:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail);
                educationImage.setImageResource(R.drawable.education_icon);
                businessImage.setImageResource(R.drawable.business);
                achievementDetailsImage.setImageResource(R.drawable.achievement);
                dharmikImage.setImageResource(R.drawable.dharmic);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 2:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                educationImage.setImageResource(R.drawable.education_icon);
                businessImage.setImageResource(R.drawable.business);
                achievementDetailsImage.setImageResource(R.drawable.achievement);
                dharmikImage.setImageResource(R.drawable.dharmic);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 3:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                achievementDetailsImage.setImageResource(R.drawable.achievement_b);
                educationImage.setImageResource(R.drawable.education_icon);
                businessImage.setImageResource(R.drawable.business);
                dharmikImage.setImageResource(R.drawable.dharmic);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 4:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                achievementDetailsImage.setImageResource(R.drawable.achievement_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business);
                dharmikImage.setImageResource(R.drawable.dharmic);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 5:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                achievementDetailsImage.setImageResource(R.drawable.achievement_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business_b);
                dharmikImage.setImageResource(R.drawable.dharmic);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;

            case 6:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                achievementDetailsImage.setImageResource(R.drawable.achievement_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business_b);
                dharmikImage.setImageResource(R.drawable.dharmic_b);
                sanghImage.setImageResource(R.drawable.sangh);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;

            case 7:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                achievementDetailsImage.setImageResource(R.drawable.achievement_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business_b);
                dharmikImage.setImageResource(R.drawable.dharmic_b);
                sanghImage.setImageResource(R.drawable.sangh_b);
                passwordImage.setImageResource(R.drawable.password_w);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.white));
                break;
            case 8:
                basicDetailsImg.setImageResource(R.drawable.basic_detail_b);
                uploadPhotoImg.setImageResource(R.drawable.upload_b);
                contactDetailsImage.setImageResource(R.drawable.contact_detail_b);
                achievementDetailsImage.setImageResource(R.drawable.achievement_b);
                educationImage.setImageResource(R.drawable.education_icon_b);
                businessImage.setImageResource(R.drawable.business_b);
                dharmikImage.setImageResource(R.drawable.dharmic_b);
                sanghImage.setImageResource(R.drawable.sangh_b);
                passwordImage.setImageResource(R.drawable.password_b);

                basicRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                uploadRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                contactRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                achievementRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                educationRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                businessRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                dharmikLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                dharmikRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                sanghLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                sanghRight.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
                passwordLeft.setBackgroundColor(ContextCompat.getColor(ProfileUpdateActivty.this, R.color.black));
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

    private void showPages(final int tag) {
        currentTab = tag;
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
                setFragment(AchievementListingFragment.newInstance(), "");

                break;
            case 4:
                setFragment(EducationListingFragment.newInstance(), "");
                break;
            case 5:
                setFragment(BusinessListingFragment.newInstance(), "");
                break;
            case 6:
                setFragment(DharmicFragment.newInstance(), "");
                break;
            case 7:
                setFragment(SanghFragment.newInstance(), "");
                break;
            case 8:
                setFragment(ChangePasswordFragment.newInstance(), "");
                break;




        }
        if (tag != -1) {
            setViewColor(tag);
            horizondalScrollView.post(new Runnable() {
                @Override
                public void run() {
                    horizondalScrollView.scrollTo(125 * tag, 0);
                }
            });
        }
    }

    protected void setFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();


        // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.contentPanel, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onSelectTab(int index) {
        if (index < MAX_DETAILS) {
            currentTab = index;
            showPages(currentTab);
        }
    }

    @Override
    public void onSelectNextTab() {
        if (currentTab + 1 < MAX_DETAILS) {
            currentTab++;
            showPages(currentTab);
        }
    }

    @Override
    public void enableNestedScrolling(boolean status) {
        // nestedScrollView.setNestedScrollingEnabled(status);
    }

    @Override
    public void onUpdateTitile(String title) {
        titleTxt.setText(title);
    }
}
