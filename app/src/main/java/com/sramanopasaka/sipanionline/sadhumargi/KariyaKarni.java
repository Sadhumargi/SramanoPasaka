package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.KaryakarniListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.KaryakarniList;

import java.util.ArrayList;
import java.util.Collections;

public class KariyaKarni extends BaseActivity implements ConnectivityReceiver.ConnectivityReceiverListener ,GUICallback {

   // private static final String TAG = KariyaKarni.class.getSimpleName();
    private GridView mGridView;
    private ProgressBar mProgressBar;
   // ProgressDialog pg;

    private GridViewAdapter mGridAdapter;
    private ArrayList<KaryakarniList> mGridData=null;
    private ArrayList<KaryakarniList> arrayList;
    Context context;

   // private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/karyakarnigroups.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kariya_karni);

        mGridView = (GridView) findViewById(R.id.gridView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkarikarni);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>कार्यसमिति</font>"));

        //Initialize with empty data
        mGridData = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, mGridData);
        mGridView.setAdapter(mGridAdapter);



        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

               // KaryakarniList item = (KaryakarniList) parent.getItemAtPosition(position);

                final boolean isConnected=ConnectivityReceiver.isConnected();

                if(isConnected)
                {
                    Intent intent = new Intent(KariyaKarni.this, DetailsActivity.class);
                    intent.putExtra("karyakarni_id",arrayList.get(position).getKaryakarni_id());
                    startActivity(intent);
                }
                else
                {
                    Snackbar snackbar1 = Snackbar.make(v, "Sorry! Not connected to internet", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar1.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar1.show();
                }

            }
        });

        //Start download
        //new AsyncHttpTask().execute();

        RequestProcessor processor=new RequestProcessor(this);
        processor.getKaryakarniList();
        showLoadingDialog();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {


        hideLoadingDialog();

        if(guiResponse!=null){

            if(requestStatus.equals(RequestStatus.SUCCESS)){

                KaryakarniListResponse response= (KaryakarniListResponse) guiResponse;
                if(response!=null){

                    if(response.getData()!=null && response.getData().size()>0){

                        arrayList=response.getData();

                        KaryakarniList item;
                        item = new KaryakarniList();

                        for (int i=0;i<response.getData().size();i++){

                            item.setKaryakarni_id(arrayList.get(i).getKaryakarni_id());
                            item.setGrp_karkarni_name(arrayList.get(i).getGrp_karkarni_name());
                            item.setGrp_karkarni_place(arrayList.get(i).getGrp_karkarni_place());
                            item.setGrp_karkarni_imglink(arrayList.get(i).getGrp_karkarni_imglink());
                            item.setLogo_id(arrayList.get(i).getLogo_id());
                            mGridData.add(i,item);
                          Collections.sort(mGridData, item);

                        }
                        mGridAdapter.setGridData(arrayList);

                    }else{
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
        }


    }

  /*  //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mProgressBar.setVisibility(View.VISIBLE);
            pg =new ProgressDialog(KariyaKarni.this);
            pg.setTitle("Please wait");
            pg.setMessage("Loading..");
            pg.setIndeterminate(true);
            pg.setCancelable(false);
            pg.show();

        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
// Retrieve JSON Objects from the given URL address
            JSONObject jsonobject = JSONfunctions
                    .getJSONfromURL(FEED_URL);
            try {
                JSONArray posts = jsonobject.optJSONArray("data");
                GridItem item;
                for (int i = 0; i < posts.length(); i++)
                {
                    JSONObject post = posts.optJSONObject(i);
                    String name = post.optString("grp_karkarni_name");
                    String city = post.optString("grp_karkarni_place");
                    String logoid = post.optString("logo_id");
                    int id=post.getInt("karyakarni_id");
                    item = new GridItem();
                    item.setKaryakarni_id(id);
                    item.setName(name);
                    item.setCity(city);
                    item.setImage(post.getString("grp_karkarni_imglink"));
                    item.setLogoid(logoid);
                    mGridData.add(item);

                    Collections.sort(mGridData,item);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI

            mGridAdapter.setGridData(mGridData);

           // mProgressBar.setVisibility(View.GONE);
            pg.dismiss();
        }
    }*/
}