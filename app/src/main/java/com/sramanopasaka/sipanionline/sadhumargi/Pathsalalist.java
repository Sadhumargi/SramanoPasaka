package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Pathsalalist extends AppCompatActivity  {

    TextView empty_element;
    RecyclerView recyclerview;
    ProgressDialog pDialog;
    ProgressDialog mProgressDialog;
    // private int ival = 0;
    private static int loadLimit = 99;
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    JSONObject jsonobject;
    JSONArray jsonarray;
    public int current_pages = 1;
    String FEED_URL = "http://pathshala.sabsjs.org/api/schools/list";
    ArrayList<PathsalaGetSetter> arraylist = new ArrayList<PathsalaGetSetter>();

    LinearLayoutManager mLayoutManager;

    PathsalalistAdapter adapter;

    /*//EditText search;
    SearchView search;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathsalalist);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolpathsala);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
       /* empty_element = (TextView) findViewById(R.id.emptyElement);
        //search= (EditText) findViewById(R.id.edit_srch_patasala);

        search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(this);*/

        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#FFFFFF'>पाठशाला लिस्ट</font>"));


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        recyclerview.setHasFixedSize(true);

        new Remote().execute();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mLayoutManager);


    }

    /*@Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        text = text.toString().toLowerCase();
        Log.e("filterlist", "???????" + text);
        final ArrayList<PathsalaGetSetter> filteredList = new ArrayList<>();

        for (int i = 0; i < arraylist.size(); i++) {

            final String Code = arraylist.get(i).getCode().toLowerCase();
            final String City = arraylist.get(i).getCity().toLowerCase();
            if (Code.contains(text) || City.contains(text)) {

                filteredList.add(arraylist.get(i));

            }
        }

        recyclerview.setLayoutManager(new LinearLayoutManager(Pathsalalist.this));
        adapter = new PathsalalistAdapter(Pathsalalist.this, filteredList, recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();  // data set changed
        return false;
    }*/

    class Remote extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Pathsalalist.this);
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
        protected Void doInBackground(Void... voids) {
            //
            final String sign2 = MD5(current_pages + "" + "" + "" + "1a2ea8119addab5f196b6c8cf5baeaa9");

            Log.e("Signature", "????????????" + sign2);

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("page", String.valueOf(current_pages)));
            nameValuePairs.add(new BasicNameValuePair("city", ""));
            nameValuePairs.add(new BasicNameValuePair("prof_query", ""));
            nameValuePairs.add(new BasicNameValuePair("edu_query", ""));
            nameValuePairs.add(new BasicNameValuePair("signature", sign2));

            Log.e("Signature", "????????????" + sign2);

            try {
                JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "POST", nameValuePairs)));


                if (json1 != null) {
                    cast1 = json1.getJSONArray("data");
                    for (int i = 0; i <= loadLimit; i++) {

                        json1 = cast1.getJSONObject(i);

                        PathsalaGetSetter imgs = new PathsalaGetSetter();
                        imgs.setId(json1.getString("id"));
                        imgs.setCode(json1.getString("code"));
                        imgs.setCity(json1.getString("city"));
                        imgs.setPlace(json1.getString("place"));
                        imgs.setType(json1.getString("type"));
                        imgs.setTimings(json1.getString("timing"));
                        imgs.setStrength(json1.getString("strength"));
                        imgs.setAddress(json1.getString("address"));
                        imgs.setSangh(json1.getString("local_sangh_name"));
                        imgs.setIn_charge_name(json1.getString("in_charge_name"));
                        imgs.setIn_charge_number(json1.getString("in_charge_number"));
                        imgs.setIn_charge_email(json1.getString("in_charge_email"));
                        imgs.setGrade(json1.getString("grade"));

                        arraylist.add(imgs);

                    }
                } else {
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //Toast.makeText(Pathsalalist.this, "data received", Toast.LENGTH_SHORT).show();

            // create an Object for Adapter
            adapter = new PathsalalistAdapter(Pathsalalist.this, arraylist, recyclerview);
            // set the adapter object to the Recyclerview
            recyclerview.setAdapter(adapter);

            mProgressDialog.dismiss();
            loadmore();

            // onQueryTextChange(String);


        }
    }


    /*public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString();
                Log.e("filterlist","???????"+query);
                final ArrayList<PathsalaGetSetter> filteredList = new ArrayList<>();

                for (int i = 0; i < arraylist.size(); i++) {

                    final String Code = arraylist.get(i).getCode().toLowerCase();
                    final String City = arraylist.get(i).getCity().toLowerCase();
                    if (Code.contains(query) || City.contains(query)) {

                        filteredList.add(arraylist.get(i));

                    }
                }

                recyclerview.setLayoutManager(new LinearLayoutManager(Pathsalalist.this));
                adapter = new PathsalalistAdapter(Pathsalalist.this,filteredList,recyclerview);
                recyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();  // data set changed
            }
        });
    }*/


    void loadmore() {
        recyclerview.setOnScrollListener(new EndlessRecyclerOnScrollListener(
                mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do somthing...

                arraylist.add(null);
                ///   adapter.notifyItemInserted(arraylist.size() - 1);
                current_pages += 1;

                String sign2 = MD5(current_pages + "" + "" + "" + "1a2ea8119addab5f196b6c8cf5baeaa9");
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("page", String.valueOf(current_pages)));
                nameValuePairs.add(new BasicNameValuePair("city", ""));
                nameValuePairs.add(new BasicNameValuePair("prof_query", ""));
                nameValuePairs.add(new BasicNameValuePair("edu_query", ""));
                nameValuePairs.add(new BasicNameValuePair("signature", sign2));

                try {
                    JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "POST", nameValuePairs)));

                    if (json1 != null) {

                        arraylist.remove(arraylist.size() - 1);
                        adapter.notifyItemRemoved(arraylist.size());

                        cast1 = json1.getJSONArray("data");
                        for (int i = 0; i <= loadLimit; i++) {

                            json1 = cast1.getJSONObject(i);

                            PathsalaGetSetter imgs = new PathsalaGetSetter();
                            imgs.setId(json1.getString("id"));
                            imgs.setCode(json1.getString("code"));
                            imgs.setCity(json1.getString("city"));
                            imgs.setPlace(json1.getString("place"));
                            imgs.setType(json1.getString("type"));
                            imgs.setTimings(json1.getString("timing"));
                            imgs.setStrength(json1.getString("strength"));
                            imgs.setAddress(json1.getString("address"));
                            imgs.setSangh(json1.getString("local_sangh_name"));
                            imgs.setIn_charge_name(json1.getString("in_charge_name"));
                            imgs.setIn_charge_number(json1.getString("in_charge_number"));
                            imgs.setIn_charge_email(json1.getString("in_charge_email"));
                            imgs.setGrade(json1.getString("grade"));

                            arraylist.add(imgs);
                            adapter.notifyItemInserted(arraylist.size());

                        }
                        adapter.setLoaded();

                        Log.e("Sampl size", ">>>>>>>" + arraylist.size());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    @Override
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


        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered

                String text = newText;
                text = text.toString().toLowerCase();
                Log.e("filterlist", "???????" + text);
                final ArrayList<PathsalaGetSetter> filteredList = new ArrayList<>();

                for (int i = 0; i < arraylist.size(); i++) {

                    final String Code = arraylist.get(i).getCode().toLowerCase();
                    final String City = arraylist.get(i).getCity().toLowerCase();
                    if (Code.contains(text) || City.contains(text)) {

                        filteredList.add(arraylist.get(i));

                    }
                }

                recyclerview.setLayoutManager(new LinearLayoutManager(Pathsalalist.this));
                adapter = new PathsalalistAdapter(Pathsalalist.this, filteredList, recyclerview);
                recyclerview.setAdapter(adapter);
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



    /*"id": 1,
            "code": "SABSJS/P/MW/A/01",
            "city": "Awarimata",
            "place": "आवरीमाता ",
            "type": "दैनिक ",
            "timing": "4:30 PM to 5:30 PM",
            "strength": 19,
            "address": "समता भवन, आसावरा",
            "local_sangh_name": "श्री साधुमार्गी जैन संघ ",
            "in_charge_name": "श्री हीरालाल जी लोढा",
            "in_charge_number": "9414777424",
            "in_charge_email": "",
            "grade": "उपलब्ध नहीं*/

