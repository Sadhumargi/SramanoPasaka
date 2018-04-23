package com.sramanopasaka.sipanionline.sadhumargi;

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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.model.SahityaFragmentOne;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by SipaniOnline on 07-07-2016.
 */
public class OneFragAdapter extends RecyclerView.Adapter<OneFragAdapter.ViewHolder> implements Filterable {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<OneFragGetSetter> data;
    ImageLoader imageLoader;
   // private ArrayList<OneFragGetSetter> androidlist;
    private ArrayList<OneFragGetSetter> android2;

    ArrayList<SahityaFragmentOne> androidlist;

    public OneFragAdapter(Context context,
                          ArrayList<SahityaFragmentOne> arraylist) {
        this.context = context;
        androidlist = arraylist;
        imageLoader = new ImageLoader(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.onefrag_adapter_layout, parent, false);

        ViewHolder view=new ViewHolder(view1);

        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.tv_date.setTypeface(type);
        viewHolder.tv_date.setTypeface(type, Typeface.BOLD);
        viewHolder.tv_date.setText(androidlist.get(i).getTitle());
        String url2=androidlist.get(i).getImg_link().toString();
        imageLoader.DisplayImage(url2, viewHolder.image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isConnected=ConnectivityReceiver.isConnected();

                if(isConnected)
                {
                    String encode=androidlist.get(i).getSang_book_id();
                    try {
                        String query = URLEncoder.encode(encode, "utf-8");
                        Log.e("Encode values","Success///"+query);
                        String a1=viewHolder.tv_date.getText().toString();
                        Intent i1 = new Intent(context, WebviewSahitya.class);
                        i1.putExtra("BookTitle",encode);
                        i1.putExtra("BookType","S");
                        context.startActivity(i1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_date;
        ImageView image;

        public ViewHolder(View view) {
            super(view);

            tv_date = (TextView)view.findViewById(R.id.onefrag_txt_date);
            image=(ImageView)view.findViewById(R.id.onefrag_image);

        }

    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
