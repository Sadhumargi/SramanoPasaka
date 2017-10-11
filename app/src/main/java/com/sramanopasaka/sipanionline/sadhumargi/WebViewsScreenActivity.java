package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewsScreenActivity extends Activity {

    private WebView mwebview;
    int i =0;
    private WebViewsScreenActivity _activity;
    ProgressDialog _dilog;
    private String[] Urls = {"http://www.google.com","http://www.gmail.com","http://www.yahoo.com"};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        mwebview = new WebView(this);
        setContentView(mwebview);
        _activity = this;
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        if(checkInternetConnection(_activity)){
            if(savedInstanceState==null)
                mwebview.loadUrl(Urls[i]);
            else
                mwebview.restoreState(savedInstanceState);
        }
        else{
            //showAlert  "Unable to Connect Server"
        }
        mwebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if(mwebview.getVisibility()== View.VISIBLE)
                {
                    WebViewsScreenActivity.this.setProgress(progress * 100);

                }
            }
        });
        mwebview.setWebViewClient(new HelloWebViewClient());
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            mwebview.goBack();
            return true;
        }
        else
            return super.onKeyUp(keyCode, event);
    }


    //To check whether network connection is available on device or not
    private boolean checkInternetConnection(Activity _activity) {
        ConnectivityManager conMgr = (ConnectivityManager) _activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }//checkInternetConnection()



    //HelloWebViewClient class for webview
    private class HelloWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            Toast.makeText(getApplicationContext(), "Loading started...!"+Urls[i], Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);

        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            Toast.makeText(getApplicationContext(), "Loading done...!"+Urls[i], Toast.LENGTH_SHORT).show();
            i++;
            if(i<Urls.length)
                view.loadUrl(Urls[i]);
        }
    } //HelloWebViewClient-class
}//AccountsScreenActivity-class