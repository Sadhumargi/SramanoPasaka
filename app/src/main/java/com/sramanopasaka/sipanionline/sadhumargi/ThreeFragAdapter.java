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

import com.sramanopasaka.sipanionline.sadhumargi.model.NanenshSahitya;

import java.util.ArrayList;

public class ThreeFragAdapter extends RecyclerView.Adapter<ThreeFragAdapter.ViewHolder> implements Filterable {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<ThreeFragGetSetter> data;
    ImageLoader imageLoader;
    private  ArrayList<NanenshSahitya> arrayList;

    public ThreeFragAdapter(Context context,
                            ArrayList<NanenshSahitya> arraylist) {
        this.context = context;
        arrayList = arraylist;
        imageLoader = new ImageLoader(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_three_frag_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);

        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.tv_date.setTypeface(type);
        viewHolder.tv_date.setTypeface(type,Typeface.BOLD);
        viewHolder.tv_date.setText(arrayList.get(i).getTitle());

        if(arrayList.get(i)!=null){
            String url2=arrayList.get(i).getImg_link();
            imageLoader.DisplayImage(url2, viewHolder.image);
        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final boolean isConnected=ConnectivityReceiver.isConnected();

                if(isConnected)
                {
                    String a1=viewHolder.tv_date.getText().toString();
                    Intent i1 = new Intent(context, WebviewSahitya.class);
                    i1.putExtra("BookTitle",arrayList.get(i).getNane_sah_id());
                    i1.putExtra("BookType","N");
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
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_date;
        ImageView image;
        private Context context;

        public ViewHolder(View view) {
            super(view);

            tv_date = (TextView)view.findViewById(R.id.threefrag_txt_date);
            image=(ImageView)view.findViewById(R.id.threefrag_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          /*  itemListener.recyclerViewListClicked(v, this.getPosition());
            String a1=itemListener.recyclerViewListClicked(v,this.get);
            Intent i1 = new Intent(context, FlipActivity.class);
            i1.putExtra("book_name",a1);
            context.startActivity(i1);*/
        }
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
