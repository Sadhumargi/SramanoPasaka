package com.sramanopasaka.sipanionline.sadhumargi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GathividhiResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiModel;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityCollectionview extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,ConnectivityReceiver.ConnectivityReceiverListener, GUICallback{

    GridView gv;

    Context context;
    public ArrayList namelist = new ArrayList<>(Arrays.asList("Pravachan","Vihar","Daan","Pathshala","Patrika","Sahitya","Karyasamiti","Thiti"));
    public ArrayList iconlist = new ArrayList<>(Arrays.asList (R.drawable.circle3,R.drawable.circle4,R.drawable.daan,R.drawable.pathsala,R.drawable.ebook,R.drawable.newsahitya,R.drawable.karyakarni,R.drawable.ic_launcher));

    private AdView mAdView;
    private NavigationView navigationView = null;

    RecyclerView horisontalRecyclerview;
    HorizontalAdapter horizontalAdapter;
    RecyclerView horizontalRecyclerViewNews;
    VerticalAdapter horizontalAdapterNews;

    ArrayList<GathividhiModel> gathiVidhiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_collectionview);

        horizontalAdapter = new HorizontalAdapter(MainActivityCollectionview.this,iconlist,namelist);
//      verticalAdapter = new VerticalAdapter(MainActivityCollectionview.this,gathiVidhiList);

//      gv = (GridView) findViewById(R.id.coll_grid);
//      gv.setAdapter(new CollectionAdapter(MainActivityCollectionview.this, iconlist, iconImages));

        horisontalRecyclerview = (RecyclerView) findViewById(R.id.hor_recyclerview);
        horizontalRecyclerViewNews = (RecyclerView) findViewById(R.id.ver_recyclerview);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivityCollectionview.this, LinearLayoutManager.HORIZONTAL, false);
        horisontalRecyclerview.setLayoutManager(horizontalLayoutManager);
        horisontalRecyclerview.setAdapter(horizontalAdapter);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        horizontalRecyclerViewNews.setLayoutManager(verticalLayoutManager);

        mAdView = (AdView) findViewById(R.id.adView4);
        AdRequest adRequest1 = new AdRequest.Builder()
                .build();

        mAdView.loadAd(adRequest1);

        RequestProcessor processor = new RequestProcessor(MainActivityCollectionview.this);
        processor.getGathividhiList();
        showLoadingDialog();

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
//        toggle.setHomeAsUpIndicator(R.drawable.menuimage);

        toggle.syncState();

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Sadhumargi</font>"));
        toolbar.setNavigationIcon(R.drawable.menuimage);

        // Set the ActionBar title font size
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkConnection();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 160f);
        Log.e("-------","densityDpi"+densityDpi);
        Log.e("-------","density"+getResources().getDisplayMetrics().density);

//        new MyFirebaseInstanceIdService();

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
            loginItem.setTitle("Login/Registration");
        }
    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        System.gc();
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

//        } else if (id == R.id.nav_2) {
//            frag = new PhotoGallery();
//            //ft1.replace(R.id.drawer_layout, frag);
//            // ft1.commit();
//            if(isConnected)
//            {
//                Intent in=new Intent(this,MainActivityCollectionview.class);
//                startActivity(in);
//            }
//            else
//            {
//                //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
//                showSnack(isConnected);
//            }

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
            if(logoutItem.getTitle().toString().equalsIgnoreCase("Login/Registration")) {

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
        } else if (id == R.id.nav_9){

            if (isConnected) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName() )));

                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/stor/apps/market://details?id="+getPackageName() )));
                }
            } else {
                //Toast.makeText(getBaseContext(),"No Internet",Toast.LENGTH_SHORT).show();
                showSnack(isConnected);
            }

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
     /*   FragmentManager fragmentManager = getSupportFragmentManager();
        ft1.replace(R.id.drawer_layout, frag);
        ft1.commit();*/
        return true;
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

           hideLoadingDialog();

        try{

            if(guiResponse!=null){

                if(requestStatus.equals(RequestStatus.SUCCESS)){

                    GathividhiResponse response= (GathividhiResponse) guiResponse;
                    if(response!=null){

                        if(response.getData()!=null && response.getData().size()>0){

                            gathiVidhiList=response.getData();

//                            Collections.sort(gathiVidhiList, new Comparator<GathividhiModel>() {
//
//                                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                                public int compare(GathividhiTextNews lhs, GathividhiTextNews rhs) {
//
//                                    try {
//                                        return df.parse(rhs.getDate()).compareTo(
//                                                df.parse(lhs.getDate()));
//
//                                    } catch (ParseException e) {
//                                        throw new IllegalArgumentException(e);
//                                    }
//                                }
//                            });

                            horizontalAdapterNews = new VerticalAdapter(MainActivityCollectionview.this,gathiVidhiList);
                            horizontalRecyclerViewNews.setAdapter(horizontalAdapterNews);
                        }else{
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

        }catch (RuntimeException e){
            Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
        }
        }
}


