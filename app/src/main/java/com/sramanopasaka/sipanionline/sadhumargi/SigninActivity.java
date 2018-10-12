package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.LoginRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.SignupActivity;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ValidationUtils;

public class SigninActivity extends BaseActivity implements GUICallback {
    
    Toolbar toolbar;
//    ViewPager viewPager;
//    TabLayout tabLayout;

    EditText edttxtEmail, edttxtPassword;
    TextView frgtPassword;
    Button btnLogin, btnSignup;
    private TabselectionListner tabselectionListner = null;
    ProgressDialog pg;
    private static Animation shakeAnimation;
    private View view = null;
    String sPassword, username;
    private LinearLayout loginLayout = null;
    Context context;

    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        
        toolbar= (Toolbar) findViewById(R.id.toollogin);
        setSupportActionBar(toolbar);

//        viewPager= (ViewPager) findViewById(R.id.viewpager);
//        tabLayout= (TabLayout) findViewById(R.id.tabs);
//        setUpViewPage(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));

        }
        
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SigninActivity.this, MainActivityCollectionview.class);
                startActivity(intent);
                
//                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        android.support.v7.app.ActionBar actionBar=this.getSupportActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Login</font>"));

//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#F05957"));
//        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
//        tabLayout.setTabTextColors(Color.parseColor("#2D1C03"), Color.parseColor("#2D1C03"));

        edttxtEmail = (EditText)findViewById(R.id.editTex_email);
        edttxtPassword = (EditText) findViewById(R.id.editText_password);
        loginLayout = (LinearLayout)findViewById(R.id.loginLayout);

        frgtPassword = findViewById(R.id.tv_frgtpass);

        btnLogin = (Button) findViewById(R.id.button_login);
        btnSignup = (Button)findViewById(R.id.button_create_profile);
        shakeAnimation = AnimationUtils.loadAnimation(this,
                R.anim.shake);


        // creating an shared Preference file for the information to be stored
// first argument is the name of file and second is the mode, 0 is private mode


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPassword = edttxtPassword.getText().toString();
                username = edttxtEmail.getText().toString();
                //String password = edttxtPassword.getText().toString();
                LoginRequest loginRequest = new LoginRequest();


                if (TextUtils.isEmpty(username)) {

//                    loginLayout.startAnimation(shakeAnimation);
                    Toast.makeText(SigninActivity.this, "Please enter a valid email/mobile number", Toast.LENGTH_LONG).show();
//                    new CustomToast().showErrorToast(getApplicationContext(), view,
//                            "Please enter a valid email/mobile number");

                } else {
                    if (ValidationUtils.isValidMail(username)) {

                        if (!TextUtils.isEmpty(sPassword) && sPassword.length() > 5) {
                            loginRequest.setEmail(username);
                            loginRequest.setPassword(sPassword);
                            loginRequest.setMobileNumber("");
                            initiateAPI(loginRequest);
                        } else {
//                            loginLayout.startAnimation(shakeAnimation);
                            Toast.makeText(SigninActivity.this, "Password should contain 5 - 12 characters", Toast.LENGTH_SHORT).show();
//                            new CustomToast().showErrorToast(context, view,
//                                    "Password should contain 5 - 12 characters");
                        }

                    } else if (ValidationUtils.isValidMobile(username)) {

                        if (!TextUtils.isEmpty(sPassword) && sPassword.length() > 5) {
                            loginRequest.setMobileNumber(username);
                            loginRequest.setPassword(sPassword);
                            loginRequest.setEmail("");
                            initiateAPI(loginRequest);
                        } else {

//                            loginLayout.startAnimation(shakeAnimation);
                            Toast.makeText(SigninActivity.this, "Password should contain 5 - 12 characters", Toast.LENGTH_SHORT).show();
//                            new CustomToast().showErrorToast(context, view,
//                                    "Password should contain 5 - 12 characters");
                        }

                    } else {
//                        loginLayout.startAnimation(shakeAnimation);
                        Toast.makeText(SigninActivity.this, "Please enter a valid email/mobile number", Toast.LENGTH_LONG).show();
//                        new CustomToast().showErrorToast(context, view,
//                                "Please enter a valid email/mobile and password to login ");
                    }
                }
            }
        });

        frgtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SigninActivity.this, ResetPasswordActivity.class);
                startActivity(i);
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(i);

//                if (tabselectionListner != null)
//                    tabselectionListner.onSelectTab(1);
            }
        });

    }

    private void initiateAPI(LoginRequest loginRequest) {
        if (loginRequest != null) {
            showLoadingDialog();
            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(loginRequest.getURLEncodedPostdata().toString());
            RequestProcessor requestProcessor = new RequestProcessor(this);
            requestProcessor.doLogin(gsonObject);
        }
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        tabselectionListner = (TabselectionListner) getActivity();
//    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {

                LoginResponse loginResponse = (LoginResponse) guiResponse;
                if (loginResponse != null) {
                    if (!TextUtils.isEmpty(loginResponse.getStatus()) && loginResponse.getStatus().equalsIgnoreCase("success")) {
                        OfflineData.saveLoginResponse(loginResponse.getData());

                        //"password":"5dc8e5500e207aa79ddd66a8f7e146df"

                        PreferenceUtils.setPassword(context, sPassword);
                        PreferenceUtils.setAppToken(context, loginResponse.getData().getAppToken());
                        PreferenceUtils.setUserId(context, Integer.parseInt(loginResponse.getData().getId()));
                        PreferenceUtils.setLastLoginTime(context, System.currentTimeMillis());
                        PreferenceUtils.setProfileImageUrl(context, loginResponse.getProfile_base());
                        PreferenceUtils.setProfileImageId(context, loginResponse.getData().getProfilePic());
                        Toast.makeText(context, "Login Successfuly", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, ProfileActivity.class);
                        startActivity(i);
//                        context.finish();

                    } else {
                        if (!TextUtils.isEmpty(loginResponse.getMessage())) {
//                            loginLayout.startAnimation(shakeAnimation);
//                            new CustomToast().showErrorToast(context, view,
//                                    loginResponse.getMessage());
                            Toast.makeText(SigninActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
//                            loginLayout.startAnimation(shakeAnimation);
//                            new CustomToast().showErrorToast(context, view,
//                                    "Invalid username/password");
                            Toast.makeText(SigninActivity.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

        }
    }
}

//    private void setUpViewPage(ViewPager viewPager) {
//
//        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new SignInFragment(), getResources().getString(R.string.Sign_in));
//        adapter.addFragment(new SignUpFragment(),getResources().getString(R.string.Create_Account));
//        viewPager.setAdapter(adapter);
//    }

    //To move from one fragment to another within the same tablayout
//    @Override
//    public void onSelectTab(int index) {
//        tabLayout.getTabAt(index).select();
//    }
//
//    @Override
//    public void onSelectNextTab() {
//
//    }
//
//    @Override
//    public void enableNestedScrolling(boolean status) {
//
//    }
//
//    static class ViewPagerAdapter extends FragmentPagerAdapter {
//
//        List<Fragment> mFragmentList=new ArrayList<>();
//        List<String> mFragmenttitleList=new ArrayList<>();
//
//
//        public ViewPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//
//        public void addFragment(Fragment fragment, String title){
//            mFragmentList.add(fragment);
//            mFragmenttitleList.add(title);
//
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmenttitleList.get(position);
//        }
//    }

