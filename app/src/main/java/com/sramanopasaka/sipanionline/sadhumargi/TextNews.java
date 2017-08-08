package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class TextNews extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<TextGetSetter> data;
    private TextNewsAdapter adapter;
    LayoutInflater inflater;
    Context context;
    ProgressDialog mProgressDialog;
    JSONObject jsonobject;
    JSONArray jsonarray;
    TextView emptyView;
    final String url = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/textnews.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the style for this fragment
     //   inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View vi = inflater.inflate(R.layout.fragment_text_news, container, false);

        recyclerView = (RecyclerView)vi.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        emptyView=(TextView)vi.findViewById(R.id.emptyElement);
        new Remote().execute();
        boolean isConnected = ConnectivityReceiver.isConnected();
        if(isConnected)
        {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

        else
        {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        return vi;
    }

    private class Remote extends AsyncTask<String,Void,Integer>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getContext());
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
        protected Integer doInBackground(String... params) {

            // Create an array
            data = new ArrayList<>();
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

                                TextGetSetter item = new TextGetSetter();
                                item.setTextId(jsonobject.optString("text_id"));
                                item.setTitle(jsonobject.optString("text_news_title"));
                                item.setDetails(jsonobject.optString("text_news_details"));
                                item.setDate(jsonobject.optString("date"));
                                data.add(item);
                            }
                            Log.e("list values>>>>>>","????????"+data);

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

            return null; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);


            Collections.sort(data, new Comparator<TextGetSetter>() {

                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                public int compare(TextGetSetter lhs, TextGetSetter rhs) {

                    try {
                        return df.parse(rhs.getDate()).compareTo(
                                df.parse(lhs.getDate()));

                    } catch (ParseException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            adapter = new TextNewsAdapter(getContext(),data);
            recyclerView.setAdapter( adapter );

            mProgressDialog.dismiss();
        }

    }

}
