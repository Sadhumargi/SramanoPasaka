package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/oldcurrentkarni.php";
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    static String curr_karni=null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting details screen style
        setContentView(R.layout.activity_details_view);

        toolbar = (Toolbar) findViewById(R.id.toolgriddetails);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>कार्यसमिति</font>"));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }

        Intent mIntent = getIntent();
        String sValue = mIntent.getStringExtra("karyakarni_id");
        Log.e("karyakarni_id","///////"+sValue);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("karyakarni_id", sValue);
        editor.putString("karyakarni_id1", sValue);
        editor.commit();

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#fd5708"));
        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentKaryakarni(), "वर्तमान  कार्यसमिति");
        adapter.addFragment(new OldKaryakarni(), "पूर्वा अध्यक्ष");
      //  new Remote().execute();

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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

    final class Remote extends AsyncTask<Void, Void, Void> {
        ProgressDialog pg = null;

        String old_karni=null;
        FragmentTransaction transaction = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(DetailsActivity.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please wait....");
            pg.show();
            pg.setCancelable(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

          /*  Intent mIntent = getIntent();
            int intValue = Integer.parseInt(mIntent.getStringExtra("karyakarni_id"));
            Log.e("karyakarni_id","///////"+intValue);

            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("karyakarni_id", intValue);
            editor.putInt("karyakarni_id1", intValue);
            editor.commit();*/
/*
            try {


                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("karyakarni_id",intValue));

                final List<String> allNames1 = new ArrayList<String>();
                JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "GET", nameValuePairs)));
                if (json1 != null) {
                    try {
                        
                        cast1 = json1.getJSONArray("data");

                        for (int i = 0; i < cast1.length(); i++) {
                            json1 = cast1.getJSONObject(i);
                              curr_karni = json1.getString("currentkaryakarni_groups");
                              old_karni = json1.getString("oldkaryakarni_groups");
                        }
                      //  Log.e("curr_karni values","********"+curr_karni);
                        Log.e("old_karni values","********"+old_karni);



                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("curr_karni values","********"+curr_karni);
            pg.dismiss();
        }
    }

    public String getMyData() {
        return curr_karni;
    }

}
