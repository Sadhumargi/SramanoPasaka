package com.sramanopasaka.sipanionline.sadhumargi;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShivirFragment extends Fragment {

    RecyclerView recyclerview;
    ProgressDialog pg;
    ShivirAdapter adapter;
    Context context;

    public int [] arraylist={R.string.Upcomming_Shivir,R.string.Past_Shivir,R.string.Shivir_Registration};

    public ShivirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_shivir,container,false);
     recyclerview= (RecyclerView) view.findViewById(R.id.rv_shivir);

        //showtoast();
        new Remote().execute();
        return view;
    }
    /*void showtoast()
    {
        Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();
    }*/


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

            /*// Create an array
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
                }*/
            // });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            recyclerview.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerview.setLayoutManager(layoutManager);
            // Pass the results into ListViewAdapter.java
            adapter = new ShivirAdapter(getActivity(), arraylist);
            // Set the adapter to the ListView
            recyclerview.setAdapter(adapter);

            pg.dismiss();
        }
    }
}
