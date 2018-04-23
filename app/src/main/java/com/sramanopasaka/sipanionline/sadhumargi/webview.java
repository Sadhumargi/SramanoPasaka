package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class webview extends AppCompatActivity {

    private WebView WebView1;

    String url="http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/Contactus/ebook/contact.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView1=(WebView)findViewById(R.id.webView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolcontact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
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
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Contact Us</font>"));

        WebView1.clearCache(true);
        WebView1.clearHistory();
        WebView1.setWebViewClient(new WebViewClient());

        WebView1.getSettings().setJavaScriptEnabled(true);

        WebView1.getSettings().setBuiltInZoomControls(false);
        WebView1.getSettings().setDisplayZoomControls(false);
        WebView1.getSettings().setLoadWithOverviewMode(false);
        WebView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //Load url in webview
        WebView1.loadUrl(url);


        //new Remote().execute();
    }
    class Remote extends AsyncTask<Void,Void,Void>
    {

        ProgressDialog pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(webview.this);
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);
        }


        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        HttpClient client = new DefaultHttpClient();
                        HttpGet request = new HttpGet(url);
                        HttpResponse response = client.execute(request);

                        String html = "";
                        InputStream in = response.getEntity().getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder str = new StringBuilder();
                        String line = null;
                        while((line = reader.readLine()) != null)
                        {
                            str.append(line);
                        }
                        in.close();
                        html = str.toString();
                        String text="<html><head><style type=\\\"text/css\\\">@font-face {font-family: MyFont;src: url(\\\"file:///android_asset/font/BMitra.ttf\\\")}body {font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>";

                        WebView1.loadData("file:///android_asset/",html,"text/html; charset=UTF-8");
                    }
                    catch(Exception e)
                    {

                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pg.dismiss();

        }
    }


    private void loadHtmlPage(String url)
    {
        WebView1.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(webview.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

    }

   /* @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(WebView1.canGoBack()) {
            WebView1.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }*/
}
