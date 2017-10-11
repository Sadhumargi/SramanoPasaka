package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

public class Shivir_Registration extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tablayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shivir__registration);


        toolbar= (Toolbar) findViewById(R.id.toolregi);
        toolbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>रेजिस्ट्रेशन</font>"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(R.drawable.left_arrow_patasala);

        viewPager = (ViewPager) findViewById(R.id.vpager);
        tablayout = (TabLayout)findViewById(R.id.tbl);
        setupViewPager(viewPager);
        tablayout.setupWithViewPager(viewPager);

        tablayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tablayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tablayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));


    }

    private void setupViewPager(ViewPager viewPager) {

        Shivir_Registration.ViewPagerAdapter adapter=new  Shivir_Registration.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ShivirRegistrationPathsalaFragment(),"पाठशाला");
        adapter.addFragment(new ShivirRegistrationTeacherFragment(),"अमला");
        adapter.addFragment(new ShivirRegistrationStudentFragment(),"छात्रों");
        viewPager.setAdapter(adapter);

    }


private class ViewPagerAdapter extends FragmentPagerAdapter {

    public List<Fragment> mFragentList=new ArrayList<>();
    public List<String> mFragentTitleList=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);


    }

    @Override
    public Fragment getItem(int position) {
        return mFragentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragentList.size();
    }

    public void addFragment(Fragment fragment, String title){

        mFragentList.add(fragment);
        mFragentTitleList.add(title);


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragentTitleList.get(position);
    }


}
}
