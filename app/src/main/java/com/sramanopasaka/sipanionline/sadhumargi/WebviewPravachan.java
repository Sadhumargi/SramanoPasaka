package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.PravachanDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.PravachanDetails;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sipani001 on 3/10/16.
 */
public class WebviewPravachan extends BaseActivity implements GUICallback {

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

    List<PravachanDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView1 = (WebView) findViewById(R.id.webView);

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
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>प्रवचन</font>"));

        Intent in = getIntent();
        String title = in.getStringExtra("BookTitle");
        String type=  in.getStringExtra("BookType");

        final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("BookTitle", title));
        nameValuePairs.add(new BasicNameValuePair("BookType", type));

        RequestProcessor processor=new RequestProcessor(WebviewPravachan.this);
        processor.getPravachanDetails(title,type);
        showLoadingDialog();

       // new Remote().execute();


    }

        @Override
        public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

            hideLoadingDialog();

        if(guiResponse!=null){

            if(requestStatus.equals(RequestStatus.SUCCESS)){

                PravachanDetailsResponse response= (PravachanDetailsResponse) guiResponse;
                if(response!=null){

                    if(response.getPages()!=null && response.getPages().size()>0){

                        String a1=response.getPages().get(0).getTxt_file();

                        WebView1.setWebViewClient(new WebViewClient());
                        WebView1.getSettings().setJavaScriptEnabled(true);
                        WebView1.getSettings().setBuiltInZoomControls(true);
                        WebView1.getSettings().setDisplayZoomControls(true);
                        WebView1.getSettings().setLoadWithOverviewMode(false);
                        WebView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                        WebView1.loadUrl(a1);

                    }else {

                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Response failed", Toast.LENGTH_SHORT).show();
        }

    }

/*
    class Remote extends AsyncTask<Void,Void,Void>
    {

        ProgressDialog pg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(WebviewPravachan.this);
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
            WebView1.loadUrl(a1);
            pg.dismiss();

        }
    }
*/

}
