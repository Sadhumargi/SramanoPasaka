package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Donations extends AppCompatActivity {

    Context context;
    private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/donations.php";
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    ArrayList<HashMap<String, String>> arraylist;
    static String KR_NO = "Donate_id";
    static String KAR_NAME = "Types_donations";
    private RecyclerView recyclerView;
    DonationAdapter adapter;

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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);

        new Remote().execute();

        recyclerView = (RecyclerView)findViewById(R.id.donate_recycler_view);
        recyclerView.setHasFixedSize(true);

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>दान</font>"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Donations.this);
        recyclerView.setLayoutManager(layoutManager);

    }

    final class Remote extends AsyncTask<Void, Void, Void> {
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

    }

}
