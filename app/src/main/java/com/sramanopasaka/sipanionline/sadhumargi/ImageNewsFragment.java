package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GathividhiImageNewsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BaseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiImageNews;

import java.util.ArrayList;


public class ImageNewsFragment extends BaseFragment implements GUICallback{

    Context context;
    private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/imagesnews.php";
    //JSONParser jParser1 = new JSONParser();
    //JSONArray jsonarray = null;
    //private ArrayList<ImageNewsGetSetter> arraylist;
    private ArrayList<GathividhiImageNews> arraylist;
    ListView listview;
    ProgressDialog mProgressDialog;
   // JSONObject jsonobject;
    ImageNewsAdapter adapter;
    private RecyclerView recyclerView;
    ImageLoader imageLoader;
    TextView emptyView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // View view =  inflater.inflate(R.style.fragment_image_news, container, false); //pass the correct style name for the fragment
        // Inflate the style for this fragment
        final View vi = inflater.inflate(R.layout.fragment_image_news, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        emptyView=(TextView)vi.findViewById(R.id.emptyElement);
        recyclerView = (RecyclerView)vi.findViewById(R.id.img_recycler_view);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //new Remote().execute();

        RequestProcessor processor=new RequestProcessor(this);
//        processor.getGathividhiImageNewsList();
        showLoadingDialog();
        return vi;

    }

  /*  @Override
    public void onDestroy() {
        super.onDestroy();

        if ( mProgressDialog!=null && mProgressDialog.isShowing() ){
            mProgressDialog.cancel();
        }
    }*/

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

        hideLoadingDialog();

        if(guiResponse!=null){

            if(requestStatus.equals(RequestStatus.SUCCESS)){

                GathividhiImageNewsResponse response= (GathividhiImageNewsResponse) guiResponse;
                if(response!=null){

                    if(response.getData()!=null && response.getData().size()>0){

                        arraylist=response.getData();

                        LinearLayoutManager llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(llm);
                        adapter = new ImageNewsAdapter(getContext(),arraylist);
                        recyclerView.setAdapter( adapter );
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

   /* private class Remote extends AsyncTask<Void,Void,Void>
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
        protected Void doInBackground(Void... params) {

            // Create an array
            arraylist = new ArrayList<>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(FEED_URL);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(jsonobject!=null)
                    {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    // Locate the array name in JSON
                                    jsonarray = jsonobject.getJSONArray("data");

                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        HashMap<String, String> map = new HashMap<String, String>();
                                        jsonobject = jsonarray.getJSONObject(i);

                                        ImageNewsGetSetter imgs=new ImageNewsGetSetter();
                                        imgs.setImgId(jsonobject.getString("img_id"));
                                        imgs.setImglink(jsonobject.getString("img_link"));
                                        imgs.setImgtitle(jsonobject.getString("img_text_title"));
                                        //imgs.setImgdetails(jsonobject.getString("img_text"));
                                        imgs.setImgdate(jsonobject.getString("date"));
                                        arraylist.add(imgs);

                                    }
                                    Log.e("list values>>>>>>","????????"+arraylist);

                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });

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

            // Pass the results into ListViewAdapter.java
            adapter = new ImageNewsAdapter(getContext(),arraylist);
            // Set the adapter to the ListView
            recyclerView.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();

        }

    }*/
}
