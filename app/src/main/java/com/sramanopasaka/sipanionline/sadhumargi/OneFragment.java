package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class OneFragment extends Fragment  {


    Context context;
    public static String url="http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/sangsahitya.php";
    // public static String url2= "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/EbookFilter.php";
    JSONArray jsonarray = null;
    private ArrayList<OneFragGetSetter> arraylist;
    ListView listview;
    JSONObject jsonobject;
    private RecyclerView recyclerView;
    OneFragAdapter adapter;
    private TextView emptyView;
    ConnectivityReceiver.ConnectivityReceiverListener ctx;
    public OneFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the style for this fragment
        View view =  inflater.inflate(R.layout.fragment_one, container, false); //pass the correct style name for the fragment
        recyclerView = (RecyclerView)view.findViewById(R.id.onefrag_recycler_view);

        emptyView=(TextView)view.findViewById(R.id.emptyElement);

        boolean isConnected = ConnectivityReceiver.isConnected();

        if(isConnected)
        {
            new Remote().execute();
        }

        else
        {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        return view;
    }

    class Remote extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pg=null;
        private AdapterView.OnItemClickListener mListener=null;
        GestureDetector mGestureDetector;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg=new ProgressDialog(getActivity());
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            // Create an array
            arraylist = new ArrayList<>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(url);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(jsonobject!=null)
                    {
                        try {
                            // Locate the array name in JSON
                            jsonarray = jsonobject.getJSONArray("data");

                            if(jsonarray!=null)
                            {
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    jsonobject = jsonarray.getJSONObject(i);

                                    OneFragGetSetter imgs=new OneFragGetSetter();
                                    imgs.setId(jsonobject.getString("sang_book_id"));
                                    imgs.setImglink(jsonobject.getString("img_link"));
                                    imgs.setDate(jsonobject.getString("title"));
                                    arraylist.add(imgs);
                                }
                            }
                            else
                            {
                                recyclerView.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                                Toast.makeText(getContext(),"No Data",Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);

                    }
                }
            });

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            // Pass the results into ListViewAdapter.java
            adapter = new OneFragAdapter(getActivity(), arraylist);
            // Set the adapter to the ListView
            recyclerView.setAdapter(adapter);

            pg.dismiss();

        }
    }
}