package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DonationsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;

import java.util.ArrayList;
import java.util.Arrays;

public class Donations extends BaseActivity implements GUICallback {

    Context context;
   // private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/donations.php";
   // JSONParser jParser1 = new JSONParser();
    //JSONArray cast1 = null;
    //ArrayList<HashMap<String, String>> arraylist;

    ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.Donations> arrayList=null;

   public int [] imageId = { R.drawable.newdaanimage,
                             R.drawable.newdaanimage2,
                             R.drawable.newdaanimage3,
                             R.drawable.newdaanimage4,
                             R.drawable.newdaanimage5,
                             R.drawable.newdaanimage6 };

    //static String KR_NO = "Donate_id";
    //static String KAR_NAME = "Types_donations";
    //private RecyclerView recyclerView;
    DonationAdapter adapter;
    public GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tooldonations);
        setSupportActionBar(toolbar);

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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);

       // new Remote().execute();

        RequestProcessor processor=new RequestProcessor(this);
        processor.getDonationsList();
        showLoadingDialog();

        gridView = findViewById(R.id.donate_grid_view);

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Daan</font>"));
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

        hideLoadingDialog();

        try{

            if(guiResponse!=null) {

                if (requestStatus.equals(RequestStatus.SUCCESS)) {

                    DonationsResponse response = (DonationsResponse) guiResponse;
                    if (response != null) {

                        if (response.getData() != null && response.getData().size() > 0) {

                            arrayList = response.getData();

                            MyAdapter myAdapter = new MyAdapter(this, R.layout.grid_item_layout, arrayList, imageId);
                            gridView.setAdapter((ListAdapter) myAdapter);

//                        recyclerView.setHasFixedSize(true);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//                        recyclerView.setLayoutManager(layoutManager);
                            // Pass the results into ListViewAdapter.java
//                        adapter = new DonationAdapter(Donations.this, araaylist);
                            // Set the adapter to the ListView
                            //recyclerView.setAdapter(adapter);
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

        }catch (RuntimeException e){

                Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
        }
    }

    /*final class Remote extends AsyncTask<Void, Void, Void> {
        ProgressDialog pg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pg = new ProgressDialog(Donations.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                JSONObject json1;
                arraylist = new ArrayList<HashMap<String, String>>();
                json1 = JSONfunctions
                        .getJSONfromURL(FEED_URL);

                if (json1 != null) {
                    try {
                        cast1 = json1.getJSONArray("data");
                        for (int i = 0; i < cast1.length(); i++) {

                            HashMap<String, String> map = new HashMap<String, String>();
                            json1 = cast1.getJSONObject(i);

                            map.put("Donate_id", json1.getString("Donate_id"));
                            map.put("Types_donations", json1.getString("Types_donations"));
                            arraylist.add(map);

                        }

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapter=new DonationAdapter(Donations.this,arraylist);
            recyclerView.setAdapter(adapter);
            pg.dismiss();
        }

    }*/

}
