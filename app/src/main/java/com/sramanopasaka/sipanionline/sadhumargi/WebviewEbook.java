package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.EbookDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sipani001 on 1/10/16.
 */
public class WebviewEbook  extends BaseActivity implements GUICallback {

    private WebView WebView1;
    static int PageNo=0;
    public static String urlmain = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/bk1.php";
    JSONArray jsonarray = null;
    JSONParser jParser1 = new JSONParser();
    ListView listview;
    JSONObject jsonobject;
    ArrayList<String> arraylist;
    private ProgressBar progressBar;
    String a1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView1 = (WebView) findViewById(R.id.webView);

       // WebView1.setWebViewClient(new WebViewClient());
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
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
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>पत्रिका</font>"));

      /*  WebView1.clearCache(true);
        WebView1.clearHistory();*/
        /*WebView1.getSettings().setJavaScriptEnabled(true);
        WebView1.getSettings().setSupportZoom(true);
        WebView1.getSettings().setBuiltInZoomControls(true);
        WebView1.getSettings().setDisplayZoomControls(true);
        WebView1.getSettings().setLoadWithOverviewMode(true);
        WebView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        new Remote().execute();*/


        Intent in = getIntent();
        final String edition = in.getStringExtra("Edition");

        final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("Edition",edition));
        nameValuePairs.add(new BasicNameValuePair("PageNo",String.valueOf(PageNo)));

        RequestProcessor processor=new RequestProcessor(this);
        processor.getEbookDetails(edition,String.valueOf(PageNo));
        showLoadingDialog();

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if(guiResponse!=null){
            if(requestStatus.equals(RequestStatus.SUCCESS)){

                EbookDetailsResponse response= (EbookDetailsResponse) guiResponse;

                if(response!=null){

                    if(response.getPages()!=null && response.getPages().size()>0){

                        String a1=response.getPages().get(0).getTxt_file();

                        WebView1.getSettings().setJavaScriptEnabled(true);
                        WebView1.getSettings().setSupportZoom(true);
                        WebView1.getSettings().setBuiltInZoomControls(true);
                        WebView1.getSettings().setDisplayZoomControls(true);
                        WebView1.getSettings().setLoadWithOverviewMode(true);
                        WebView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

                        WebView1.setWebViewClient(new WebViewClient());
                        if (Build.VERSION.SDK_INT >= 19) {
                            WebView1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                        }
                        else {
                            WebView1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                        }
                        WebView1.setVerticalScrollBarEnabled(true);
                        WebView1.loadUrl(a1);

                        PreferenceManager.getDefaultSharedPreferences(WebviewEbook.this).edit().putString("CurrentPage", a1).commit();


                    }
                }else {
                    Toast.makeText(this, "RequestStatus Failed", Toast.LENGTH_SHORT).show();}
            }else {
                Toast.makeText(this, "RequestStatus Failed", Toast.LENGTH_SHORT).show();}
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();}
    }


   /* class Remote extends AsyncTask<Void,Void,Void>
    {

        ProgressDialog pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(WebviewEbook.this);
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

                        Intent in = getIntent();
                       final String edition = in.getStringExtra("Edition");

                        final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("Edition",edition));
                        nameValuePairs.add(new BasicNameValuePair("PageNo",String.valueOf(PageNo)));

                        Log.e("pageno","&&&&"+nameValuePairs);
                        Log.e("url","???????"+urlmain+PageNo);

                        JSONObject jsonobject = new JSONObject(String.valueOf(jParser1.makeHttpRequest(urlmain, "GET", nameValuePairs)));
                        if(jsonobject!=null)
                        {
                            try {

                                // Locate the array name in JSON
                                jsonarray = jsonobject.getJSONArray("pages");

                                for (int i = 0; i < jsonarray.length(); i++) {
                                  //  HashMap<String, String> map = new HashMap<String, String>();
                                    jsonobject = jsonarray.getJSONObject(i);
                                     a1=jsonobject.getString("txt_file");
                                    Log.e("Succes","&&&&"+a1);

                                     PreferenceManager.getDefaultSharedPreferences(WebviewEbook.this).edit().putString("CurrentPage", a1).commit();
                                }

                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                        }
                        else
                        {
                         Toast.makeText(getBaseContext(),"No Data",Toast.LENGTH_SHORT).show();
                           // WebView1.loadUrl(s2);
                        }

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

            WebView1.setWebViewClient(new WebViewClient());
            if (Build.VERSION.SDK_INT >= 19) {
                WebView1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
            else {
                WebView1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            WebView1.setVerticalScrollBarEnabled(true);
            WebView1.loadUrl(a1);
            pg.dismiss();

        }
    }*/

  /*  public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && WebView1.canGoBack()) {
            WebView1.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
