package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CurrentKaryakarniResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BaseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.OnLoadMoreListener;
import com.sramanopasaka.sipanionline.sadhumargi.model.CurrentKaryakarniModel;

import java.util.ArrayList;

public class CurrentKaryakarni extends BaseFragment implements GUICallback {

    Context context;
   // private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/current_karyakarni_mem.php";
   // JSONParser jParser1 = new JSONParser();
    //JSONArray cast1 = null;
    //ArrayList<HashMap<String, String>> arraylist;

    ArrayList<CurrentKaryakarniModel> arraylist;
    static String KR_NO = "new_mem_id";
    static String KAR_NAME = "mem_name";
    static String KR_IMG_LINK = "img_link";
    static String KR_DESIGNATION = "desgn";
    static String KR_REGION = "region";
    static String KR_CITY = "city";
    static String KR_PHONE = "phone";
    ListView listview;
    private RecyclerView recyclerView;
   // HashMap<String, String> resultp= new HashMap<String, String>();

   //HashSet<CurrentKaryakarni> resultp= new HashSet<CurrentKaryakarni>();

    CurrentKaryakarniModel resultp=null;


    ImageLoader imageLoader;
    currentkarniadapter adapter;
    //private OnFragmentInteractionListener mListener;
    public CurrentKaryakarni() {
        // Required empty public constructors
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the style for this fragment
        View view =  inflater.inflate(R.layout.fragment_current_karyakarni, container, false); //pass the correct style name for the fragment
        // Locate the listview in listview_main.xml
        recyclerView = (RecyclerView) view.findViewById(R.id.curkar_recycler_view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

     //  new Remote().execute();

        Intent in = getActivity().getIntent();
        final String karyakarniId = in.getStringExtra("karyakarni_id");
        RequestProcessor processor=new RequestProcessor(CurrentKaryakarni.this);
        processor.getCurrentKaryakarniList(karyakarniId);
        return view;
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

        hideLoadingDialog();

        if(guiResponse!=null){

            if(requestStatus.equals(RequestStatus.SUCCESS)){

                CurrentKaryakarniResponse response= (CurrentKaryakarniResponse) guiResponse;
                if(response!=null){

                    if(response.getPages()!=null && response.getPages().size()>0){

                        arraylist=response.getPages();

                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);
                        // Pass the results into ListViewAdapter.java
                        adapter = new currentkarniadapter(getActivity(), arraylist);
                        // Set the adapter to the ListView
                        recyclerView.setAdapter(adapter);
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

    /*final class Remote extends AsyncTask<Void, Void, Void> {
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

                    Intent in = getActivity().getIntent();
                    final String karyakarId = in.getStringExtra("karyakarni_id");

                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("karyakarni_id", karyakarniname));

                    final List<String> allNames1 = new ArrayList<String>();
                    JSONObject json1 = new JSONObject(String.valueOf(jParser1.makeHttpRequest(FEED_URL, "GET", nameValuePairs)));
                    if (json1 != null) {
                        try {
                            cast1 = json1.getJSONArray("pages");
                            for (int i = 0; i < cast1.length(); i++) {

                                HashMap<String, String> map = new HashMap<String, String>();
                                json1 = cast1.getJSONObject(i);

                                map.put("new_mem_id", json1.getString("new_mem_id"));
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

                adapter=new currentkarniadapter(getContext(),arraylist);
                recyclerView.setAdapter(adapter);
                pg.dismiss();
            }

        }
*/

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
    class oldkarniadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

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
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_currentkarniadapter, parent, false);
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
                userViewHolder.txt_kr_name.setText(resultp.getMem_name());
                userViewHolder.txt_kr_designation.setText(resultp.getDesgn());
                userViewHolder.txt_kr_region.setText(resultp.getRegion());
                userViewHolder.txt_kr_city.setText(resultp.getCity());
                userViewHolder.txt_kr_phone.setText(resultp.getPhone());

//                imageLoader.DisplayImage(resultp.getImg_link(), userViewHolder.image);

                Glide.with(context).load(resultp.getImg_link()).into(userViewHolder.image);
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

     /*   @Override
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
        }*/
    }

}
