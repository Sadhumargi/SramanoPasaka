package com.sramanopasaka.sipanionline.sadhumargi;

/**
 * Created by sipani001 on 24/10/16.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sipani001 on 1/10/16.
 */
public class WebviewGathividhi extends AppCompatActivity {

    private WebView WebView1;
    static int PageNo=0;
    public static String urlmain = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/sk.php";
    JSONArray jsonarray = null;
    JSONParser jParser1 = new JSONParser();
    ListView listview;
    JSONObject jsonobject;
    ArrayList<String> arraylist;
    private ProgressDialog progressBar;
    String a1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView1 = (WebView) findViewById(R.id.webView);

        WebView1.setWebViewClient(new WebViewClient());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolcontact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>गति विधि</font>"));

        //Load url in WebView1
        new Remote().execute();


    }

    class Remote extends AsyncTask<Void,Void,Void>
    {

        ProgressDialog pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(WebviewGathividhi.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {

                Intent in = getIntent();
                String title = in.getStringExtra("BookTitle");
                String type=  in.getStringExtra("BookType");

                final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("BookTitle", title));
                nameValuePairs.add(new BasicNameValuePair("BookType", type));

                JSONObject jsonobject = new JSONObject(String.valueOf(jParser1.makeHttpRequest(urlmain, "GET", nameValuePairs)));
                if (jsonobject != null)
                {
                    try {

                        // Locate the array name in JSON
                        jsonarray = jsonobject.getJSONArray("pages");

                        for (int i = 0; i < jsonarray.length(); i++) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            jsonobject = jsonarray.getJSONObject(i);
                            a1 = jsonobject.getString("txt_file");
                            Log.e("Succes", "&&&&" + a1);

                        }

                    } catch (JSONException e) {

                    }
                }
            }
            catch (JSONException e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            WebView1.setWebViewClient(new WebViewClient());

            WebView1.getSettings().setJavaScriptEnabled(true);

            WebView1.getSettings().setBuiltInZoomControls(false);
            WebView1.getSettings().setDisplayZoomControls(false);
            WebView1.getSettings().setLoadWithOverviewMode(false);
            WebView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            WebView1.clearCache(true);
            WebView1.loadUrl(a1);

            pg.dismiss();

        }
    }

}
