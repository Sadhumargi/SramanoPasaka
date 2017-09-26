package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;

public class MainActivityCollectionview extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ConnectivityReceiver.ConnectivityReceiverListener{

    GridView gv;
    Context context;
    public String [] iconlist={"स्रामनोपसक","साहित्य","प्रवचन","विहार","पाठशाला","दान","सदस्य","गति विधि","कार्यसमिति"};
    public static int [] iconImages={R.drawable.circle1,R.drawable.circle2,R.drawable.circle3,R.drawable.circle4,R.drawable.pathsala,R.drawable.circle6,R.drawable.circle7,R.drawable.circle8,R.drawable.circle9,};
    private AdView mAdView;
    private NavigationView navigationView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_collectionview);


        gv = (GridView) findViewById(R.id.coll_grid);

        gv.setAdapter(new CollectionAdapter(MainActivityCollectionview.this, iconlist, iconImages));

        mAdView = (AdView) findViewById(R.id.adView4);
        AdRequest adRequest1 = new AdRequest.Builder()
                .build();

       mAdView.loadAd(adRequest1);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }

        getWindow().setBackgroundDrawable(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.syncState();

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>साधुमार्गी</font>"));

        // Set the ActionBar title font size
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkConnection();


        ///////////

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 160f);
        Log.e("-------","densityDpi"+densityDpi);
        Log.e("-------","density"+getResources().getDisplayMetrics().density);


    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
    // Showing the status in Snackbar
    public void showSnack(boolean isConnected) {
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
        if (mAdView != null) {
            mAdView.resume();
        }

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(MainActivityCollectionview.this);



        Menu menu = navigationView.getMenu();
        MenuItem loginItem = menu.findItem(R.id.nav_8);
        if(OfflineData.getLoginData()!=null){
            loginItem.setTitle("प्रोफाइल");
        }else{
            loginItem.setTitle("सदस्य लॉगिन / रजिस्ट्रेशन");
        }

    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
        // unregisterReceiver(networkReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean isConnected = ConnectivityReceiver.isConnected();
        Fragment frag = null;
        FragmentManager fm1 = MainActivityCollectionview.this
                .getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        if (id == R.id.nav_1) {

            Intent in=new Intent(this,MainActivityCollectionview.class);
            startActivity(in);

        } else if (id == R.id.nav_2) {
            frag = new PhotoGallery();
            //ft1.replace(R.id.drawer_layout, frag);
            // ft1.commit();
            if(isConnected)
            {
                Intent in=new Intent(this,GathividhiActivity.class);
                startActivity(in);
            }
            else
            {
                //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
                showSnack(isConnected);
            }

        } /*else if (id == R.id.nav_3) {

        } else if (id == R.id.nav_4) {

        }*/
        else if (id == R.id.nav_5) {

            if(isConnected)
            {
                Intent is=new Intent(this,WebViewHistory.class);
                startActivity(is);
            }
            else
            {
                //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
                showSnack(isConnected);
            }

        }
        else if (id == R.id.nav_6) {

            if(isConnected)
            {
                Intent is=new Intent(this,AboutApp.class);
                startActivity(is);
            }
            else
            {
                //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
                showSnack(isConnected);
            }

        }
        else if (id == R.id.nav_7) {

            if (isConnected) {
                Intent is = new Intent(this, webview.class);
                startActivity(is);
            } else {
                //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
                showSnack(isConnected);
            }

        }else if (id == R.id.nav_8) {

            Menu menu = navigationView.getMenu();
            MenuItem logoutItem = menu.findItem(R.id.nav_8);
            if(logoutItem.getTitle().toString().equalsIgnoreCase("सदस्य लॉगिन / रजिस्ट्रेशन")) {

                if (isConnected) {
                    Intent i = new Intent(MainActivityCollectionview.this, SigninActivity.class);
                    startActivity(i);
                } else {
                    //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
                    showSnack(isConnected);
                }

            }else{
                Intent i = new Intent(MainActivityCollectionview.this, ProfileActivity.class);
                startActivity(i);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
     /*   FragmentManager fragmentManager = getSupportFragmentManager();
        ft1.replace(R.id.drawer_layout, frag);
        ft1.commit();*/
        return true;
    }


}
