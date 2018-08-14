
package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pathsala extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {


    int day;
    int month;
    int year;
    Context context;
    private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/pathsala.php";
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    ArrayList<HashMap<String, String>> arraylist;
    static String id = "pathsala_id";
    static String code = "pathsala_code";
    private RecyclerView recyclerView;
    PathsalaAdapter adapter;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathsala);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolpathsala);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
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
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }

        ActionBar actionbar = this.getSupportActionBar();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Pathshala</font>"));

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#f05957"));
        tabLayout.setSelectedTabIndicatorHeight((int) (4 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));

        // To set icon for fragment names

        tabLayout.getTabAt(0).setIcon(R.drawable.pathshala_1);
        tabLayout.getTabAt(1).setIcon(R.drawable.pathshala_2);
        tabLayout.getTabAt(2).setIcon(R.drawable.pathshala_3);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SchoolFragment(), getResources().getString(R.string.title_activity_pathsala));
        adapter.addFragment(new ShivirFragment(),getResources().getString(R.string.title_shivir));
        adapter.addFragment(new ExamsFragment(),getResources().getString(R.string.title_exams));

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
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
