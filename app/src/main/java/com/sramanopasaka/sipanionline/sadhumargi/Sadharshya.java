package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Sadharshya extends AppCompatActivity  {


    private ProgressDialog pDialog;
    private String FEED_URL = "http://members.sabsjs.org/api/members";
    ArrayList<SadharshyaGetSetter> arraylist=new ArrayList<SadharshyaGetSetter>();

    private RecyclerView recyclerView;
    SadharshyaAdapter adapter;
    public  int current_pages = 1;
    private LinearLayoutManager mLayoutManager;
    private int ival = 0;
    private static int loadLimit = 99;
    Spinner city;
    HashMap<String, String> map = new HashMap<String, String>();
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    ProgressDialog mProgressDialog;
    JSONObject jsonobject;
    JSONArray jsonarray;
    TextView empty_element;
    android.widget.SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbarcolor));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmembers);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.memb_recycler_view);
        empty_element=(TextView)findViewById(R.id.emptyElement);
        /*search= (android.widget.SearchView) findViewById(R.id.search_memmbers);
        search.setOnQueryTextListener( this);*/




        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#000000'>सदस्य</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        recyclerView.setHasFixedSize(true);

        new LoadData().execute();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

    }


    class LoadData extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // Create a progressdialog
                mProgressDialog = new ProgressDialog(Sadharshya.this);
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
            protected Void doInBackground(Void... uparams) {


                //
                final String sign2=MD5(current_pages+""+""+""+"1a2ea8119addab5f196b6c8cf5baeaa9");

                Log.e("Signature","????????????"+sign2);

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("page", String.valueOf(current_pages)));
                nameValuePairs.add(new BasicNameValuePair("city", ""));
                nameValuePairs.add(new BasicNameValuePair("prof_query", ""));
                nameValuePairs.add(new BasicNameValuePair("edu_query", ""));
                nameValuePairs.add(new BasicNameValuePair("signature", sign2));

                Log.e("Signature","????????????"+sign2);

                try {
                    JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "POST", nameValuePairs)));



                    if(json1!=null) {
                        cast1 = json1.getJSONArray("data");
                        for (int i = ival; i <= loadLimit; i++) {

                            json1 = cast1.getJSONObject(i);

                            SadharshyaGetSetter imgs = new SadharshyaGetSetter();
                            imgs.setId(json1.getString("id"));
                            imgs.setFirst_name(json1.getString("first_name"));
                            imgs.setMiddle_name(json1.getString("middle_name"));
                            imgs.setLast_name(json1.getString("last_name"));
                            imgs.setCity(json1.getString("city"));
                            imgs.setState(json1.getString("state"));
                            imgs.setMobile(json1.getString("mobile"));
                            imgs.setProfile_pic(json1.getString("profile_pic"));
                            imgs.setEducation_name(json1.getString("education_name"));
                            imgs.setBusiness_name(json1.getString("business_name"));

                            arraylist.add(imgs);

                        }
                    }
                    else
                    {
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // create an Object for Adapter
                adapter = new SadharshyaAdapter(Sadharshya.this,arraylist,recyclerView);
                // set the adapter object to the Recyclerview
                recyclerView.setAdapter(adapter);

                mProgressDialog.dismiss();
                loadmore();
               // onCreateOptionsMenu(Menu menu);
            }
        }

        void loadmore()
        {
            recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(
                    mLayoutManager) {
                @Override
                public void onLoadMore(int current_page) {
                    // do somthing...

                    arraylist.add(null);
                    ///   adapter.notifyItemInserted(arraylist.size() - 1);
                    current_pages += 1;

                    String sign2=MD5(current_pages+""+""+""+"1a2ea8119addab5f196b6c8cf5baeaa9");
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("page", String.valueOf(current_pages)));
                    nameValuePairs.add(new BasicNameValuePair("city", ""));
                    nameValuePairs.add(new BasicNameValuePair("prof_query", ""));
                    nameValuePairs.add(new BasicNameValuePair("edu_query", ""));
                    nameValuePairs.add(new BasicNameValuePair("signature", sign2));

                    try {
                        JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "POST", nameValuePairs)));

                        if(json1!=null) {

                            arraylist.remove(arraylist.size() - 1);
                            adapter.notifyItemRemoved(arraylist.size());

                            cast1 = json1.getJSONArray("data");
                            for (int i = 0; i <=loadLimit; i++) {

                                json1 = cast1.getJSONObject(i);

                                SadharshyaGetSetter imgs = new SadharshyaGetSetter();
                                imgs.setId(json1.getString("id"));
                                imgs.setFirst_name(json1.getString("first_name"));
                                imgs.setMiddle_name(json1.getString("middle_name"));
                                imgs.setLast_name(json1.getString("last_name"));
                                imgs.setCity(json1.getString("city"));
                                imgs.setState(json1.getString("state"));
                                imgs.setMobile(json1.getString("mobile"));
                                imgs.setGender(json1.getString("gender"));
                                imgs.setProfile_pic(json1.getString("profile_pic"));
                                imgs.setEmail_address(json1.getString("email_address"));
                                imgs.setEducation_name(json1.getString("education_name"));
                                imgs.setBusiness_name(json1.getString("business_name"));

                                arraylist.add(imgs);
                                adapter.notifyItemInserted(arraylist.size());

                            }
                            adapter.setLoaded();

                            Log.e("Sampl size",">>>>>>>"+arraylist.size());

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            });

        }

    public String MD5(String md5)
    {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        searchView.setQueryHint("Search...");


        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered

                //Receive the searched query

                String text = newText;
                text = text.toString().toLowerCase();
                Log.e("filterlist", "???????" + text);

                //Create an array to store the searched result

                final ArrayList<SadharshyaGetSetter> filteredList = new ArrayList<>();

                //get the array elements those need to be searched for entered query

                for (int i = 0; i < arraylist.size(); i++) {

                    final String Firstname = arraylist.get(i).getFirst_name().toLowerCase();
                    final String Middlename = arraylist.get(i).getMiddle_name().toLowerCase();
                    final String Lastname = arraylist.get(i).getLast_name().toLowerCase();
                    final String City = arraylist.get(i).getCity().toLowerCase();
                    final String State = arraylist.get(i).getState().toLowerCase();
                    final String Mobile = arraylist.get(i).getMobile().toLowerCase();
                    final String Education = arraylist.get(i).getEducation_name().toLowerCase();

                    if (Firstname.contains(text) || Middlename.contains(text) || Lastname.contains(text) || City.contains(text) || State.contains(text)
                            || Mobile.contains(text) || Education.contains(text)) {

                        filteredList.add(arraylist.get(i));//searched result stored in an array


                    }
                }
                Log.e("filteredList",">>>>>>>"+filteredList.size());

                //set recyclerview layout , call adapter to display data set after the search
                recyclerView.setLayoutManager(new LinearLayoutManager(Sadharshya.this));
                adapter = new SadharshyaAdapter(Sadharshya.this, filteredList, recyclerView);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();  // data set changed
                return false;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                return false;

            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);


    }

}
