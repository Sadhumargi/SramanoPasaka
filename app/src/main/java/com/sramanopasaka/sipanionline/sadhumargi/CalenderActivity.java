package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sramanopasaka.sipanionline.sadhumargi.fragments.DayFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.HoroscopeFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.MonthFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

import java.util.ArrayList;
import java.util.List;

public class CalenderActivity extends AppCompatActivity implements TabselectionListner {


    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    String TabFragmentB;

    public void setTabFragmentB(String t){
        TabFragmentB = t;
    }

    public String getTabFragmentB(){
        return TabFragmentB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

//        if (Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

        toolbar = (Toolbar) findViewById(R.id.toolcal);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpagercal);
        tabLayout = (TabLayout) findViewById(R.id.tabcal);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.background));
        }

        ActionBar actionbar = this.getSupportActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Shri Jain Calender</font>"));

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#f05858"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));

    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MonthFragment(), getResources().getString(R.string.Month));
        adapter.addFragment(new DayFragment(),getResources().getString(R.string.DayView));
        adapter.addFragment(new HoroscopeFragment(),getResources().getString(R.string.Rashifal));

        viewPager.setAdapter(adapter);
    }

//    @Override
//    public void sendData(String message) {
//        DayFragment dayFragment = getSupportFragmentManager().findFragmentByTag();
//
//        String tag = "android:switcher:" + R.id.viewpagercal + ":" + 1;
//        DayFragment f = (DayFragment) getSupportFragmentManager().findFragmentByTag(tag);
//        f.displayReceivedData(message);
//    }

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
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
