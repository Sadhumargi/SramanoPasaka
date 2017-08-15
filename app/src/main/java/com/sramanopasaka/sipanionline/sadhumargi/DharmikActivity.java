package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.view;

public class DharmikActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dharmik);


        toolbar= (Toolbar) findViewById(R.id.tool_dharmik);
        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>धार्मिक विवरण</font>"));

        viewPager= (ViewPager) findViewById(R.id.vp_dharmik);
        tabLayout= (TabLayout) findViewById(R.id.tab_dharmik);
        setUpViewpager( viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));


    }

    private void setUpViewpager(ViewPager viewPager) {

        DharmikActivity.ViewPagerAdapter adapter=new  DharmikActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RuleFragment(),"नियम/त्याग");
        adapter.addFragment(new ParikshaFragment(),"परीक्षाएं ");
        adapter.addFragment(new GnanFragment(),"ज्ञान");
        viewPager.setAdapter(adapter);

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public List<Fragment> mFragentList = new ArrayList<>();
        public List<String> mFragentTitleList = new ArrayList<>();

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

        public void addFragment(Fragment fragment, String title) {

            mFragentList.add(fragment);
            mFragentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragentTitleList.get(position);
        }

    }
}

