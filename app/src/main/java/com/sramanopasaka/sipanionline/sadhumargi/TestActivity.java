package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.DharmicFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ExamListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.KnowledgeFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.PromiseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 */

public class TestActivity extends AppCompatActivity implements TabselectionListner {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //setFragment(new PromiseFragment().newInstance(), "");


     /*   viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setUpViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));*/
    }


    private void setUpViewpager(ViewPager viewPager) {

        TestActivity.ViewPagerAdapter adapter = new TestActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PromiseFragment().newInstance(), "नियम/त्याग");
        adapter.addFragment(new ExamListingFragment().newInstance(), "परीक्षाएं ");

        //adapter.addFragment(new ParikshaFragment(), "परीक्षाएं ");
        adapter.addFragment(new KnowledgeFragment().newInstance(), "ज्ञान");
        viewPager.setAdapter(adapter);

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

    }

    @Override
    public void onSelectNextTab() {

    }

    @Override
    public void enableNestedScrolling(boolean status) {

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
