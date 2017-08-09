package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OldKaryakarni extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
Context context;
    private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/old_karyakarni_mem.php";
    JSONParser jParser1 = new JSONParser();
    JSONArray cast1 = null;
    ArrayList<HashMap<String, String>> arraylist;
    static String KR_NO = "old_mem_id";
    static String KAR_NAME = "mem_name";
    static String KR_IMG_LINK = "img_link";
    static String KR_DESIGNATION = "desgn";
    static String KR_REGION = "region";
    static String KR_CITY = "city";
    static String KR_PHONE = "phone";
    HashMap<String, String> resultp= new HashMap<String, String>();
   // ListView listview;
    private RecyclerView recyclerView;
    ImageLoader imageLoader;
    oldkarniadapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the style for this fragment
        View view =  inflater.inflate(R.layout.fragment_old_karyakarni, container, false); //pass the correct style name for the fragment
        // Locate the listview in listview_main.xml
        recyclerView = (RecyclerView) view.findViewById(R.id.oldkar_recycler_view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new Remote().execute();

        return view;
    }

    final class Remote extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pg = new ProgressDialog(getActivity());
            pg.setIndeterminate(true);
            pg.setMessage("Please Wait While Loading....");
            pg.show();
            pg.setCancelable(false);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                arraylist = new ArrayList<HashMap<String, String>>();

               /* SharedPreferences sharedPreferences = getActivity().getPreferences(getActivity().MODE_PRIVATE);
                int savedPref = sharedPreferences.getString().getInt("karyakarni_id1", -1);*/

                Intent in = getActivity().getIntent();
                final int karyakarniname = in.getIntExtra("karyakarni_id", -1);

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("karyakarni_id", String.valueOf(karyakarniname)));

                final List<String> allNames1 = new ArrayList<String>();
                JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "GET", nameValuePairs)));
                if (json1 != null) {
                    try {
                        cast1 = json1.getJSONArray("pages");
                        for (int i = 0; i < cast1.length(); i++) {

                            HashMap<String, String> map = new HashMap<String, String>();
                            json1 = cast1.getJSONObject(i);

                            map.put("old_mem_id", json1.getString("old_mem_id"));
                            map.put("img_link", json1.getString("img_link"));
                            map.put("mem_name", json1.getString("mem_name"));
                            map.put("desgn", json1.getString("desgn"));
                            map.put("region", json1.getString("region"));
                            map.put("city", json1.getString("city"));
                            map.put("phone", json1.getString("phone"));
                            arraylist.add(map);

                        }

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            adapter=new oldkarniadapter(getContext(),arraylist);
            recyclerView.setAdapter(adapter);
            pg.dismiss();
        }
    }


    static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_kr_name,txt_kr_designation,txt_kr_region,txt_kr_city,txt_kr_phone;
        ImageView image;
        public UserViewHolder(View vi) {
            super(vi);
            txt_kr_name=(TextView)vi.findViewById(R.id.karni_name);
            txt_kr_designation=(TextView)vi.findViewById(R.id.karni_desigantion);
            txt_kr_region=(TextView)vi.findViewById(R.id.karni_region);
            txt_kr_city=(TextView)vi.findViewById(R.id.karni_city);
            txt_kr_phone=(TextView)vi.findViewById(R.id.karni_phone);
            image=(ImageView)vi.findViewById(R.id.kr_img);
        }

        @Override
        public void onClick(View v) {

        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
    /*class oldkarniadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;

        private OnLoadMoreListener mOnLoadMoreListener;

        private boolean isLoading;
        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;

        public oldkarniadapter() {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }

        public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
            this.mOnLoadMoreListener = mOnLoadMoreListener;
        }

        @Override
        public int getItemViewType(int position) {
            return arraylist.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_oldkarniadapter, parent, false);
                return new UserViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof UserViewHolder) {

                imageLoader = new ImageLoader(context);
                resultp = arraylist.get(position);
                UserViewHolder userViewHolder = (UserViewHolder) holder;



                // Capture position and set results to the TextViews
                userViewHolder.txt_kr_name.setText(resultp.get(OldKaryakarni.KAR_NAME));
                userViewHolder.txt_kr_designation.setText(resultp.get(OldKaryakarni.KR_DESIGNATION));
                userViewHolder.txt_kr_region.setText(resultp.get(OldKaryakarni.KR_REGION));
                userViewHolder.txt_kr_city.setText(resultp.get(OldKaryakarni.KR_CITY));
                userViewHolder.txt_kr_phone.setText(resultp.get(OldKaryakarni.KR_PHONE));

                imageLoader.DisplayImage(resultp.get(OldKaryakarni.KR_IMG_LINK), userViewHolder.image);
                // Capture ListView item click
                userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {


                    }
                });

            } else if (holder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }

        }

        @Override
        public int getItemCount() {
            return arraylist == null ? 0 : arraylist.size();
        }

        public void setLoaded() {
            isLoading = false;
        }

     *//*   @Override
        public Filter getFilter() {
            return null;
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            arraylist.clear();
            if (charText.length() == 0) {
                arraylist.addAll(arraylist);
            }
            else
            {
                for (TextGetSetter wp : data)
                {
                    if (wp.getDate().contains(charText))
                    {
                        data.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }*//*
    }*/

}
