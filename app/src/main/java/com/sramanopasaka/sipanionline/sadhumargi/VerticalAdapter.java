package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.view;

/**
 * Created by apple on 10/01/18.
 */

class VerticalAdapter extends RecyclerView.Adapter {

    ArrayList<GathividhiModel> arrayList;
    Context context;
    private final int Text = 0, Image = 1, Video = 2;
    ImageLoader imageLoader;


    public VerticalAdapter(MainActivityCollectionview mainActivityCollectionview, ArrayList arrayList) {
        this.context = mainActivityCollectionview;
        this.arrayList = arrayList;
    }

    /*
     * This method creates different RecyclerView.ViewHolder objects based on the item view type.
     * @param viewGroup ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            switch (viewType) {
                case Text:
                    View v1 = inflater.inflate(R.layout.activity_text_news_adapter, viewGroup, false);
                    viewHolder = new ViewHolder1(v1);
                    break;

                case Image:
                    View v2 = inflater.inflate(R.layout.activity_image_news_adapter, viewGroup, false);
                    viewHolder = new ViewHolder2(v2);
                    break;

                case Video:
                    View v3 = inflater.inflate(R.layout.activity_video_news_adapter, viewGroup, false);
                    viewHolder = new ViewHolder3(v3);
                    break;

                default:
                    break;
            }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case Text:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
                break;

            case Image:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2, position);
                break;

            case Video:
                ViewHolder3 vh3 = (ViewHolder3) holder;
                configureViewHolder3(vh3, position);
                break;

            default:
                break;
        }

    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        vh1.tv_title.setTypeface(type);
        vh1.tv_title.setTypeface(type,Typeface.BOLD);

        vh1.tv_details.setTypeface(type);
        vh1.tv_details.setTypeface(type,Typeface.BOLD);

        vh1.tv_title.setText(arrayList.get(position).getText_news_title());
        vh1.tv_details.setText(arrayList.get(position).getText_news_details());
        vh1.tv_date.setText(arrayList.get(position).getDate());
        String url1 = arrayList.get(position).getText_news_details().toString();
        try {
            // Create a URL for the desired page
            URL url = new URL(url1);
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
                Log.e("Success***","???????????"+str);
                //  viewHolder.tv_details.setText(str);
            }

            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

    }

    private void configureViewHolder2(ViewHolder2 vh2, int position) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        vh2.tv_title.setTypeface(type);
        vh2.tv_title.setTypeface(type,Typeface.BOLD);

        imageLoader = new ImageLoader(context);

        vh2.tv_title.setText(arrayList.get(position).getImg_text_title());
        vh2.tv_date.setText(arrayList.get(position).getDate());

        String url1 = arrayList.get(position).getImg_link().toString();

        imageLoader.DisplayImage(url1, vh2.image);

    }

    private void configureViewHolder3(final ViewHolder3 vh3, final int position) {


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
                vh3.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };


            /*iniatilize the yout tube thumbnail with a youtube key(AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw).
            Initialize method of Youtube Thumbnailview has params sting and a listener*/
        vh3.youTubeThumbnailView.initialize("AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw", new YouTubeThumbnailView.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                //Declared two arrayList. List1 for getvideo. List2 for to get title.
                final List<String> allNames1 = new ArrayList<String>();
                final List<String> allNames2 = new ArrayList<String>();

 try{

//     for(int i=0;i<arrayList.size();i++)
//     {
//         String s1 = arrayList.get(i).getVideo_link().toString();
//         allNames1.add(s1);
////                    String s1 = arrayList.get(i).getVideo_link().toString();
////                    allNames1.add(s1);
//         String s2=arrayList.get(i).getVideo_title().toString();
//         allNames2.add(s2);
//
//     }
//
//     final String videoid[] =allNames1.toArray(new String [allNames1.size()]);
//     final String video_title[] =allNames2.toArray(new String [allNames2.size()]);

     Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
     vh3.title.setTypeface(type);

//     //this is for video title
//     for(int i3=0;i3<video_title.length;i3++)
//     {

         //Title of video are displyed on at the bottom in aviewgropu cardview
         vh3.title.setText(arrayList.get(position).getVideo_title());

//     }
//     //this is for video thumbnail
//     for(int i1=0;i1<videoid.length;i1++)
//     {
         //Video rendered on youtubethumbnail
         youTubeThumbnailLoader.setVideo(arrayList.get(position).getVideo_link());
         youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
//     }

     //create video intent playing video
     vh3.playButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             boolean isConnected = ConnectivityReceiver.isConnected();
             if(isConnected)
             {
                 //A Class
                 Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, "AIzaSyBclrjpOToA9wC_xyqKtUrbU2dl7NagQtw",arrayList.get(position).getVideo_link());
                 context.startActivity(intent);
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

 }catch (RuntimeException e){

     Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
 }
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).getType() == 1) {
            return Text;
        }else if(arrayList.get(position).getType() == 2){

                return Image;
        }else if (arrayList.get(position).getType() == 3) {

                    return Video;
                }

        return super.getItemViewType(position);
    }


    private class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView tv_title,tv_details,tv_date;
        public ViewHolder1(View v1) {
            super(v1);

            tv_title = (TextView)v1.findViewById(R.id.txt_title_text);
            tv_details = (TextView)v1.findViewById(R.id.txt_details);
            tv_date = (TextView)v1.findViewById(R.id.txt_date);
        }
    }

    private class ViewHolder2 extends RecyclerView.ViewHolder {

        private TextView tv_title,tv_details,tv_date;
        ImageView image,image2,image3,image4;

        public ViewHolder2(View v2) {
            super(v2);

            tv_title = (TextView)v2.findViewById(R.id.img_txt_title);
            tv_details = (TextView)v2.findViewById(R.id.img_txt_details);
            tv_date = (TextView)v2.findViewById(R.id.img_txt_date);
            image=(ImageView)v2.findViewById(R.id.img_news);
        }
    }

    private class ViewHolder3 extends RecyclerView.ViewHolder {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;
        private TextView title;

        public ViewHolder3(View v3) {
            super(v3);

            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
//            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            title=(TextView)itemView.findViewById(R.id.video_news_title);
        }
    }

//    private void bindDataToAdapter() {
//        // Bind adapter to recycler view object
//        recyclerView.setAdapter(new VerticalAdapter(arrayList()));
//    }
}
