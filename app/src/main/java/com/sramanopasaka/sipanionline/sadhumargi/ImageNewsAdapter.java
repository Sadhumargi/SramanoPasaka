package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageNewsAdapter extends  RecyclerView.Adapter<ImageNewsAdapter.ViewHolder> {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
   // ArrayList<ImageNewsGetSetter> data;
    ImageLoader imageLoader;
    private ArrayList<ImageNewsGetSetter> android;

    public ImageNewsAdapter(Context context,
                            ArrayList<ImageNewsGetSetter> arraylist) {
        this.context = context;
        android = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image_news_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.tv_title.setTypeface(type);
        viewHolder.tv_title.setTypeface(type,Typeface.BOLD);

        imageLoader = new ImageLoader(context);

        viewHolder.tv_title.setText(android.get(i).getImgtitle());
        viewHolder.tv_date.setText(android.get(i).getImgdate());

        String url1 = android.get(i).getImglink().toString();

        imageLoader.DisplayImage(url1, viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_details,tv_date;
        ImageView image,image2,image3,image4;
        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView)view.findViewById(R.id.img_txt_title);
            tv_details = (TextView)view.findViewById(R.id.img_txt_details);
            tv_date = (TextView)view.findViewById(R.id.img_txt_date);
            image=(ImageView)view.findViewById(R.id.img_news);

        }
    }
}
