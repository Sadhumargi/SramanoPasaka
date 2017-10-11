package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PatasalaName extends AppCompatActivity  implements ConnectivityReceiver.ConnectivityReceiverListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patasala_name);

        toolbar= (Toolbar) findViewById(R.id.toolpathsala_name);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
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
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>पाठशाला</font>"));

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));
    }
    private void setupViewPager(ViewPager viewPager) {

        PatasalaName.ViewPagerAdapter adapter=new  PatasalaName.ViewPagerAdapter (getSupportFragmentManager());
        adapter.addFragment(new PthsalaDetailsFragment(),getResources().getString(R.string.Pathsala_Details));
        adapter.addFragment(new PthsalaSyllabusFragment(),getResources().getString(R.string.Pathsala_Syllabus));
        adapter.addFragment(new PthsalaActivitiesFragment(),getResources().getString(R.string.Pathsala_Activities));
        adapter.addFragment(new PthsalaRulesFragment(),getResources().getString(R.string.Rules));

        viewPager.setAdapter(adapter);

    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.txt_msg), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);

    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener((ConnectivityReceiver.ConnectivityReceiverListener) this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
   /* class ViewPagerAdapter extends FragmentPagerAdapter {
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

        }*/



       class ViewPagerAdapter extends FragmentPagerAdapter{

           private final List<Fragment> mFragmentList = new ArrayList<>();
           private final List<String> mFragmentTitleList = new ArrayList<>();


           public ViewPagerAdapter(FragmentManager manager){
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


