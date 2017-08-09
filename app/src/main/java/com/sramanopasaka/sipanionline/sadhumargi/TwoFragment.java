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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TwoFragment extends Fragment {


    Context context;
    public static String url="http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/ramsahitya.php";
    JSONArray jsonarray = null;
    private ArrayList<TwoFragGetSetter> arraylist;
    ListView listview;
    JSONObject jsonobject;
    private RecyclerView recyclerView;
    TwoFragAdapter adapter;
    ImageLoader imageLoader;
    TextView emptyView;
    public TwoFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the style for this fragment
        View view =  inflater.inflate(R.layout.fragment_two, container, false); //pass the correct style name for the fragment

        new Remote().execute();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recyclerView = (RecyclerView)view.findViewById(R.id.twofrag_recycler_view);
        emptyView = (TextView)view.findViewById(R.id.emptyElement);
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

                            for (int i = 0; i < jsonarray.length(); i++) {
                                HashMap<String, String> map = new HashMap<String, String>();
                                jsonobject = jsonarray.getJSONObject(i);

                                TwoFragGetSetter imgs=new TwoFragGetSetter();
                                imgs.setId(jsonobject.getString("ram_sah_id"));
                                imgs.setImglink(jsonobject.getString("img_link"));
                                imgs.setDate(jsonobject.getString("title"));
                                arraylist.add(imgs);
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
            adapter = new TwoFragAdapter(getActivity(),arraylist);
            // Set the adapter to the ListView
            recyclerView.setAdapter(adapter);


            pg.dismiss();

        }
    }

}