package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Pravachan extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private RecyclerView recyclerView;
    private ArrayList<PravachanGetSetter> arraylist;
    private PravachanAdapter adapter;
    LayoutInflater inflater;
    Context context=this;
    ProgressDialog mProgressDialog;
    JSONObject jsonobject;
    JSONArray jsonarray;
    TextView emptyView;
    ImageLoader imageLoader;
    final String url = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/pravachan.php";
    private static int current_page = 1;
    private LinearLayoutManager mLayoutManager;
    private int ival = 0;
    private int loadLimit = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pravachan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolpravachan);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }
        emptyView=(TextView)findViewById(R.id.emptyElement);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);

        recyclerView = (RecyclerView)findViewById(R.id.pr_recycler_view);
        recyclerView.setHasFixedSize(true);

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>प्रवचन</font>"));

        new Remote().execute();

    }

    private class Remote extends AsyncTask<String,Void,Integer>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Pravachan.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Please wait");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Integer doInBackground(String... params) {


            // Create an array
            arraylist = new ArrayList<PravachanGetSetter>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(url);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {

                        if(jsonobject!=null)
                        {
                            jsonarray = jsonobject.getJSONArray("data");
                            if(jsonarray!=null)
                            {
                                for (int i = 0; i < jsonarray.length(); i++)
                                {
                                    //HashMap<String, String> map = new HashMap<String, String>();
                                    jsonobject = jsonarray.getJSONObject(i);

                                    PravachanGetSetter item = new PravachanGetSetter();
                                    item.setPravachan_id(jsonobject.optString("prv_id"));
                                    item.setTitle(jsonobject.optString("title"));
                                    item.setDate(jsonobject.optString("date"));
                                    arraylist.add(item);

                                }
                                Log.e("list values>>>>>>","????????"+arraylist);
                            }
                            else
                            {
                                Toast.makeText(getBaseContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            recyclerView.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                        }

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            return null; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            mLayoutManager = new LinearLayoutManager(context);
            // use a linear layout manager
            recyclerView.setLayoutManager(mLayoutManager);

              Collections.sort(arraylist, new Comparator<PravachanGetSetter>() {

                                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                        public int compare(PravachanGetSetter lhs, PravachanGetSetter rhs) {

                                            try {
                                                return df.parse(rhs.getDate()).compareTo(
                                                        df.parse(lhs.getDate()));

                                            } catch (ParseException e) {
                                                throw new IllegalArgumentException(e);
                                            }
                                        }
                                    });

            adapter = new PravachanAdapter(context,arraylist);
            recyclerView.setAdapter( adapter );
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( mProgressDialog!=null && mProgressDialog.isShowing() ){
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);

    }
    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected)
        {
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




}
