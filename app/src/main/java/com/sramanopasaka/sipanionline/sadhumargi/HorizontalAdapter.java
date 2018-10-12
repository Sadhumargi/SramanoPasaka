package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by apple on 09/01/18.
 */

class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

  Context context;
    ArrayList iconList;
    ArrayList nameList;

    public HorizontalAdapter(MainActivityCollectionview mainActivityCollectionview, ArrayList iconlist, ArrayList iconImages) {

        this.context = mainActivityCollectionview;
        this.iconList = iconlist;
        this.nameList = iconImages;
    }

    @Override
    public HorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_view,parent,false);
        MyViewHolder view1 = new MyViewHolder(view);
        return view1;
    }

    @Override
    public void onBindViewHolder(HorizontalAdapter.MyViewHolder holder, final int position) {


        Glide.with(context).load(iconList.get(position)).into(holder.imageView);

//      holder.imageView.setImageResource((Integer) iconList.get(position));
        holder.textView.setText((CharSequence) nameList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isConnected = ConnectivityReceiver.isConnected();

                if(isConnected)
                {

                    if(position==0)
                    {
                        Intent i1=new Intent(context,Pravachan.class);
                        context.startActivity(i1);
                    }
                    else if(position==1)
                    {
                        Intent i1=new Intent(context,ViharActivity.class);
                        context.startActivity(i1);
                    }
                    else if(position==2)
                    {
                        Intent i1=new Intent(context,Donations.class);
                        context.startActivity(i1);
                    }
                    else if(position==3)
                    {
                        Intent i1=new Intent(context,Pathsala.class);
                        context.startActivity(i1);

                    }
                    else if(position==4)
                    {
                        Intent i1=new Intent(context,Ebooks.class);
                        context.startActivity(i1);

                    }
                    else if(position==5)
                    {
                        Intent i1=new Intent(context,Sahitya.class);
                        context.startActivity(i1);

                    }
//                    else if(position==6)
//                    {
//                        Intent i1=new Intent(context,Sadharshya.class);
//                        context.startActivity(i1);
//                    }
                    /*else if(position==7)
                    {
                        Intent i1=new Intent(context,GathividhiActivity.class);
                        context.startActivity(i1);

                    }*/
                    else if(position==6)
                    {
                        Intent i1=new Intent(context,KariyaKarni.class);
                        context.startActivity(i1);
                    }
                    else if(position==7)
                    {
                        Intent i1=new Intent(context,CalenderActivity.class);
                        context.startActivity(i1);
                    }
                }
                else
                {
                    // showSnack(isConnected);
                    Snackbar snackbar1 = Snackbar.make(view, "Sorry! Not connected to internet", Snackbar.LENGTH_SHORT);
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
        return nameList == null ? 0 : nameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (RoundedImageView) itemView.findViewById(R.id.roundImageview);
            textView = (TextView) itemView.findViewById(R.id.textView40);

        }
    }
}
