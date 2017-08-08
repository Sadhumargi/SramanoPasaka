package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TwoFragAdapter extends RecyclerView.Adapter<TwoFragAdapter.ViewHolder> implements Filterable {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<TwoFragGetSetter> data;
    ImageLoader imageLoader;
    private ArrayList<TwoFragGetSetter> androidlist;
    private ArrayList<TwoFragGetSetter> android2;

    public TwoFragAdapter(Context context,
                          ArrayList<TwoFragGetSetter> arraylist) {
        this.context = context;
        androidlist = arraylist;
        imageLoader = new ImageLoader(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_two_frag_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);

        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.tv_date.setTypeface(type);
        viewHolder.tv_date.setTypeface(type,Typeface.BOLD);
        viewHolder.tv_date.setText(androidlist.get(i).getDate());
        String url2=androidlist.get(i).getImglink().toString();
        imageLoader.DisplayImage(url2, viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final boolean isConnected=ConnectivityReceiver.isConnected();

                if(isConnected)
                {
                    String a1=viewHolder.tv_date.getText().toString();
                    Intent i1 = new Intent(context, WebviewSahitya.class);
                    i1.putExtra("BookTitle",androidlist.get(i).getId());
                    i1.putExtra("BookType","R");
                    context.startActivity(i1);
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
    public int getItemCount() {
        return androidlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_date;
        ImageView image;
        private Context context;

        public ViewHolder(View view) {
            super(view);

            tv_date = (TextView)view.findViewById(R.id.twofrag_txt_date);
            image=(ImageView)view.findViewById(R.id.twofrag_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
