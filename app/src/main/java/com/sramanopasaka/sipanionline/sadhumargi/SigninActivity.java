package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;

import com.sramanopasaka.sipanionline.sadhumargi.fragments.CreateAccountFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.SignInFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

import java.util.ArrayList;
import java.util.List;

public class SigninActivity extends AppCompatActivity implements TabselectionListner {
    
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        
        toolbar= (Toolbar) findViewById(R.id.toollogin);
        setSupportActionBar(toolbar);

        viewPager= (ViewPager) findViewById(R.id.viewpager);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        setUpViewPage(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        android.support.v7.app.ActionBar actionBar=this.getSupportActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Sign in</font>"));


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#2D1C03"), Color.parseColor("#2D1C03"));
        
        
    }

    private void setUpViewPage(ViewPager viewPager) {

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SignInFragment(), getResources().getString(R.string.Sign_in));
        adapter.addFragment(new CreateAccountFragment(),getResources().getString(R.string.Create_Account));
        viewPager.setAdapter(adapter);

    }

    //To move from one fragment to another within the same tablayout
    @Override
    public void onSelectTab(int index) {
        tabLayout.getTabAt(index).select();
    }

    @Override
    public void onSelectNextTab() {

    }

    @Override
    public void enableNestedScrolling(boolean status) {

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> mFragmentList=new ArrayList<>();
        List<String> mFragmenttitleList=new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmenttitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmenttitleList.get(position);
        }


    }
}
