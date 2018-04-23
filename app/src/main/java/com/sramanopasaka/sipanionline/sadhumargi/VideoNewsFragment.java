package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GathividhiVideoNewsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BaseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiVideoNews;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class VideoNewsFragment extends BaseFragment implements GUICallback {


    Context context;
    private String FEED_URL = "http://shriabsjainsangh.sipanionline.com/sramanopasaka/phpfiles/videosnews.php";
    JSONArray jsonarray = null;
    // private ArrayList<VideoNewsGetSetter> arraylist;
   // ListView listview;

    private ArrayList<GathividhiVideoNews> arraylist;
    // ProgressDialog mProgressDialog;
    // JSONObject jsonobject;
    VideoNewsAdapter adapter;
    private RecyclerView recyclerView;
    TextView emptyView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View vi = inflater.inflate(R.layout.fragment_video_news, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        recyclerView = (RecyclerView)vi.findViewById(R.id.video_recycler_view);
        emptyView=(TextView)vi.findViewById(R.id.emptyElement);
        // Inflate the style for this fragment
        boolean isConnected = ConnectivityReceiver.isConnected();

        //new Remote().execute();

        RequestProcessor processor=new RequestProcessor(this);
//        processor.getGathividhiVideoNewsList();
        showLoadingDialog();

        return vi;
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

        hideLoadingDialog();

        if(guiResponse!=null){

            if(requestStatus.equals(RequestStatus.SUCCESS)){

                GathividhiVideoNewsResponse response= (GathividhiVideoNewsResponse) guiResponse;
                if(response!=null){

                    if(response.getData()!=null && response.getData().size()>0){

                        arraylist=response.getData();

                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);

                        // Pass the results into ListViewAdapter.java
                        adapter = new VideoNewsAdapter(getActivity(),arraylist);
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

                        try {
                            // Locate the array name in JSON
                            jsonarray = jsonobject.getJSONArray("data");

                            for (int i = 0; i < jsonarray.length(); i++) {

                                jsonobject = jsonarray.getJSONObject(i);

                                VideoNewsGetSetter imgs=new VideoNewsGetSetter();
                                imgs.setVideo_id(jsonobject.getString("video_link"));
                                imgs.setVideo_title(jsonobject.getString("video_title"));

                                arraylist.add(imgs);
                            }
                            Log.e("list values>>>>>>","????????"+arraylist);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        emptyView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
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
            adapter = new VideoNewsAdapter(getActivity(),arraylist);
            // Set the adapter to the ListView
            recyclerView.setAdapter(adapter);


            if(mProgressDialog.isShowing()==true)
            {
                // Close the progressdialog
                mProgressDialog.dismiss();
            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mProgressDialog.isShowing()==true)
        {
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }*/

    public class VideoNewsAdapter  extends  RecyclerView.Adapter<VideoNewsAdapter.ViewHolder> {

        // Declare Variables
        // ArrayList<VideoNewsGetSetter> data;

        ArrayList<GathividhiVideoNews> arrayList;
        Context ctx;

        //Constructor
        public VideoNewsAdapter(Context context,
                                ArrayList<GathividhiVideoNews> arraylist) {
            this.ctx = context;
            arrayList = arraylist;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_video_news_adapter, parent, false);

            ViewHolder view=new ViewHolder(view1);
            return view;
        }

        @Override
        public void onBindViewHolder(final VideoNewsAdapter.ViewHolder holder, final int position)
        {

            //Calling a interface of thumbbail YouTubeThumbnailLoader, AND ITS method OnThumbnailLoadedListener
            final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
                @Override
                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                }
                @Override
                //Set the visibility to thumbnail(image of video) the thumbnail loaded
                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                    youTubeThumbnailView.setVisibility(View.VISIBLE);

                   /*Over thumbnail relativelauout placed to image view, this imageview containd a image
                    for a video. hence set the visibility for trelaytive layout */
                    holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
                }
            };


            /*iniatilize the yout tube thumbnail with a youtube key(AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw).
            Initialize method of Youtube Thumbnailview has params sting and a listener*/
            holder.youTubeThumbnailView.initialize("AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw", new YouTubeThumbnailView.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                    //Declared two arrayList. List1 for getvideo. List2 for to get title.
                    final List<String> allNames1 = new ArrayList<String>();
                    final List<String> allNames2 = new ArrayList<String>();

                    for(int i=0;i<arrayList.size();i++)
                    {
                      String s1=arrayList.get(i).getVideo_link().toString();
                        allNames1.add(s1);
                        String s2=arrayList.get(i).getVideo_title().toString();
                        allNames2.add(s2);

                    }
                    final String videoid[] =allNames1.toArray(new String [allNames1.size()]);
                    final String video_title[] =allNames2.toArray(new String [allNames2.size()]);

                    Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/KrutiDev010 .TTF");
                    holder.title.setTypeface(type);

                    //this is for video title
                    for(int i3=0;i3<video_title.length;i3++)
                    {
                        //Title of video are displyed on at the bottom in aviewgropu cardview
                        holder.title.setText(video_title[position]);
                    }
                    //this is for video thumbnail
                    for(int i1=0;i1<videoid.length;i1++)
                    {
                        //Video rendered on youtubethumbnail
                        youTubeThumbnailLoader.setVideo(videoid[position]);
                        youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                    }

                    //create video intent playing video
                    holder.playButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boolean isConnected = ConnectivityReceiver.isConnected();
                            if(isConnected)
                            {
                                //A Class
                                Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(), "AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw",videoid[position]);
                                ctx.startActivity(intent);
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

                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    //write something for failure
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
            YouTubeThumbnailView youTubeThumbnailView;
            protected ImageView playButton;
            private TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
                playButton.setOnClickListener(this);
                relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
                youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
                title=(TextView)itemView.findViewById(R.id.video_news_title);
            }

            @Override
            public void onClick(View v) {

//                Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(), "AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw",videoid[getLayoutPosition()]);
//                ctx.startActivity(intent);
            }
        }
    }

}
