package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pathsala_Registration extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tablayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathsala__registration);

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

        Pathsala_Registration.ViewPagerAdapter adapter=new  Pathsala_Registration.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RegistrationPathsalaFragment(),"पाठशाला");
        adapter.addFragment(new RegistrationTeacherFragment(),"अमला");
        adapter.addFragment(new RegistrationStudentFragment(),"छात्रों");
        viewPager.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager)Pathsala_Registration.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(Pathsala_Registration.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
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
