package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ViharResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Vihar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViharActivity extends BaseActivity implements
        ConnectivityReceiver.ConnectivityReceiverListener,GpsStatusDetector.GpsStatusDetectorCallBack,GUICallback {

    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;

    ViharListAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<Vihar> arraylist;

    protected Context context=this;
    static double distance;
    public static AlertDialog.Builder alertDialog;
    static double latitude,longitude;
    static ArrayAdapter a1;
    RecyclerView recyclerView;
    GPSTracker gps;

    static String GS_NO = "si.no";
    static String GS_NAME = "guru_name";
    static String GS_ASSIST_NAME = "guru_assist_name";
    static String GS_vihar_location = "guru_location";
    static String GS_ATTENDER_NAME = "guru_attender_name";
    static String GS_ATTENDER_PHONE = "guru_att_phone";
    static String GS_ATTENDER_CHIEF_NAME = "guru_chief_attender";
    static String GS_PHONE_NO = "guru_phone";
    static String GS_KM = "guru_dis";
    static String GS_LAT = "guru_lat";
    static String GS_LNG = "guru_lng";
    static String GS_MY_LAT = "guru_my_lat";
    static String GS_MY_LNG = "guru_my_lng";

    TextView emptyView;
    EditText search;
    private GpsStatusDetector mGpsStatusDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vihar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolvihar);
        setSupportActionBar(toolbar);

        emptyView=(TextView)findViewById(R.id.emptyElement);

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
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Vihar</font>"));
        recyclerView = (RecyclerView)findViewById(R.id.vihar_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViharActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);

        //ImageView imgSearch = (ImageView) findViewById(R.id.imgSearch);
        search=(EditText)findViewById(R.id.edit_srch_vihar);

        //arraylist = new ArrayList<TreeMap<String, String>>();
        mGpsStatusDetector = new GpsStatusDetector(this);
        mGpsStatusDetector.checkGpsStatus();
        getlocation();

        RequestProcessor processor=new RequestProcessor(ViharActivity.this);
        processor.getViharList();
        showLoadingDialog();
    }
    @Override
    public void onGpsSettingStatus(boolean enabled) {

        if(enabled==false)
        {
//            turnGPSOn();
            gps.showSettingsAlert();

        }
        else if(enabled==true)
        {
            getlocation();
        }
    }

    void getlocation()
    {
        gps=new GPSTracker(this);

        if(gps.canGetLocation()==true)
        {
            if(gps.getLatitude()!=0 && gps.getLongitude()!=0)
            {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                //new DownloadJSON().execute();

//                RequestProcessor processor=new RequestProcessor(ViharActivity.this);
//                processor.getViharList();
//                showLoadingDialog();
            }
        }
    }
    @Override

    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mGpsStatusDetector.checkOnActivityResult(requestCode, resultCode);
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();

        try {

            if (guiResponse != null) {

                if (requestStatus.equals(RequestStatus.SUCCESS)) {

                    ViharResponse response = (ViharResponse) guiResponse;
                    if (response != null) {

                        if (response.getData() != null && response.getData().size() > 0) {

                            arraylist = response.getData();

                            for (int i = 0; i < arraylist.size(); i++) {

                                Location locationA = new Location("point A");
                                locationA.setLatitude(latitude);
                                locationA.setLongitude(longitude);
                                Location locationB = new Location("point B");
                                locationB.setLatitude(Double.parseDouble(arraylist.get(i).getGuru_lat()));
                                locationB.setLongitude(Double.parseDouble(arraylist.get(i).getGuru_lng()));

                                distance = locationA.distanceTo(locationB) / 1000;   //in km
                                Log.e("Distance to", " kilometers " + distance);
                                double dis = Double.parseDouble(new DecimalFormat("#####.##").format(distance));
                                response.getData().get(i).setGuru_dis(String.valueOf(dis));
                            }

                            Comparator<Vihar> distanceComparator = new Comparator<Vihar>() {

                                @Override
                                public int compare(Vihar d1, Vihar d2) {
                                    // Get the distance and compare the distance.
                                    Double distance1 = Double.parseDouble(d1.getGuru_dis());
                                    Double distance2 = Double.parseDouble(d2.getGuru_dis());

                                    return distance1.compareTo(distance2);
                                }
                            };

// And then sort it using collections.sort().
                            Collections.sort(arraylist, distanceComparator);

                            adapter = new ViharListAdapter(ViharActivity.this, arraylist);
                            // Set the adapter to the ListView
                            recyclerView.setAdapter(adapter);

                            addTextListener();
                        } else {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            }catch(RuntimeException e){
                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();

            }
    }

    // DownloadJSON AsyncTask
   /* private class DownloadJSON extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ViharActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Please wait");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            // Show progressdialog
            //mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL("http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/gurus.php");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (jsonobject != null) {

                        try {
                            // Locate the array name in JSON
                            jsonarray = jsonobject.getJSONArray("data");

                            if (jsonarray != null) {
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    jsonobject = jsonarray.getJSONObject(i);

                                    TreeMap<String, String> map = new TreeMap<String, String>();
                                    jsonobject = jsonarray.getJSONObject(i);

                                    map.put("si.no", jsonobject.getString("si.no"));
                                    map.put("guru_name", jsonobject.getString("guru_name"));
                                    map.put("guru_assist_name", jsonobject.getString("guru_assist_name"));
                                    map.put("guru_att_phone", jsonobject.getString("guru_att_phone"));
                                    map.put("guru_location", jsonobject.getString("guru_location"));
                                    map.put("guru_attender_name", jsonobject.getString("guru_attender_name"));
                                    map.put("guru_chief_attender", jsonobject.getString("guru_chief_attender"));
                                    map.put("guru_phone", jsonobject.getString("guru_phone"));
                                    map.put("guru_my_lat", String.valueOf(latitude));
                                    map.put("guru_my_lng", String.valueOf(longitude));
                                    map.put("guru_lat", jsonobject.getString("guru_lat"));
                                    map.put("guru_lng", jsonobject.getString("guru_lng"));

                                    Location locationA = new Location("point A");
                                    locationA.setLatitude(latitude);
                                    locationA.setLongitude(longitude);
                                    Location locationB = new Location("point B");
                                    locationB.setLatitude(Double.parseDouble(jsonobject.getString("guru_lat")));
                                    locationB.setLongitude(Double.parseDouble(jsonobject.getString("guru_lng")));

                                    distance = locationA.distanceTo(locationB) / 1000;   //in km
                                    Log.e("Distance to", " kilometers " + distance);
                                    double dis = Double.parseDouble(new DecimalFormat("#####.##").format(distance));
                                    map.put("guru_dis", String.valueOf(dis));
                                    arraylist.add(map);

                                }

                                recyclerView.setVisibility(View.VISIBLE);
                                emptyView.setVisibility(View.GONE);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getBaseContext(), "No Records Found", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            Log.e("Error", e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

// Pass the results into ListViewAdapter.java

            Comparator<TreeMap<String, String>> distanceComparator = new Comparator<TreeMap<String, String>>() {

                @Override
                public int compare(TreeMap<String, String> d1, TreeMap<String, String> d2) {
                    // Get the distance and compare the distance.
                    Double distance1 = Double.parseDouble(d1.get("guru_dis"));
                    Double distance2 = Double.parseDouble(d2.get("guru_dis"));

                    return distance1.compareTo(distance2);
                }
            };

// And then sort it using collections.sort().
            Collections.sort(arraylist, distanceComparator);

            //  adapter = new ViharListAdapter(ViharActivity.this,arraylist);
            // Set the adapter to the ListView
            recyclerView.setAdapter(adapter);
            // Close the progressdialog
            // mProgressDialog.dismiss();

            addTextListener();
        }*/

        public void addTextListener() {

            search.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence query, int start, int before, int count) {

                    query = query.toString().toLowerCase();
                    Log.e("filterlist", "???????" + query);
                   // final ArrayList<TreeMap<String, String>> filteredList = new ArrayList<TreeMap<String, String>>();

                    ArrayList<Vihar> filteredList=new ArrayList<>();

                    for (int i = 0; i < arraylist.size(); i++) {

                        final String location = arraylist.get(i).getGuru_location().toLowerCase();
                        final String name = arraylist.get(i).getGuru_name().toLowerCase();
                        final String phone = arraylist.get(i).getGuru_phone().toLowerCase();
                        final String phone2 = arraylist.get(i).getGuru_att_phone().toLowerCase();


                        if (location.contains(query) || name.contains(query) || phone.contains(query) || phone2.contains(query) ) {

                            filteredList.add(arraylist.get(i));

                        }
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(ViharActivity.this));
                    adapter = new ViharListAdapter(ViharActivity.this,filteredList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();  // data set changed
                }
            });
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
            message = "Sorry! Not connected to Internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.txt_msg), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(ViharActivity.this);
    }

    public void showSettingsAlert(){
        alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);

            }
        });
        alertDialog.setCancelable(false);
        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface
                                        dialog, int which) {
                dialog.cancel();
//                finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        arraylist.clear();
//        turnGPSOff();
    }


}