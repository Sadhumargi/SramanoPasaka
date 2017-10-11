package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EbookWebview extends AppCompatActivity
{

    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    public static String url2 = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/bk1.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_webview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolwebview);
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
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>श्रमणोपासक</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);
    }

    final class Remote extends AsyncTask<Void, Void, Void> {
        ProgressDialog pg = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(EbookWebview.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);

        }
        @Override
        protected Void doInBackground(Void... params) {

            try {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent in = getIntent();
                        String date = in.getStringExtra("book_date");

                        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("book_date",date));

                        try
                        {
                            JSONObject jsonobject = new JSONObject(String.valueOf(jParser1.makeHttpRequest(url2, "GET", nameValuePairs)));
                            if (jsonobject != null)
                            {
                                cast1 = jsonobject.getJSONArray("data");
                                for (int i = 0; i < cast1.length(); i++) {

                                    jsonobject = cast1.getJSONObject(i);
                                    HashMap<String, String> post_map = new HashMap<String, String>();

                                    final String flip_id=jsonobject.getString("ebk_id");


                                }
                            }
                        }
                        catch (JSONException ex)
                        {
                            ex.printStackTrace();
                        }

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pg.dismiss();

        }

    }
}
