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
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SanghActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sangh);


        toolbar = (Toolbar) findViewById(R.id.profilesanghtool);
        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>संघ सम्बन्धी रुचि</font>"));


        viewPager= (ViewPager) findViewById(R.id.vp_sangh);
        tabLayout= (TabLayout) findViewById(R.id.tab_sangh);
        setUpViewpager( viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));


    }

    private void setUpViewpager(ViewPager viewPager) {

        SanghActivity.ViewPagerAdapter adapter=new  SanghActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RuchiFragment(),"रूचि");
        adapter.addFragment(new ServicesFragment(),"श्रीसंघ सेवए");
        adapter.addFragment(new SocialRoleFragment(),"सामाजिक पद");
        viewPager.setAdapter(adapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

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


