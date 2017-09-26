package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CollectionAdapter extends BaseAdapter {

    String [] result;
    Context ctx;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public CollectionAdapter(Context mainActivityview, String[] iclist, int[] icimages) {
        // TODO Auto-generated constructor stub
        result=iclist;
        ctx=mainActivityview;
        imageId=icimages;
        inflater = ( LayoutInflater )ctx.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.activity_collection_adapter, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.circleview);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean isConnected = ConnectivityReceiver.isConnected();

                if(isConnected)
                {

                    if(position==0)
                    {
                        Intent i1=new Intent(ctx,Ebooks.class);
                        ctx.startActivity(i1);
                    }
                    else if(position==1)
                    {
                        Intent i1=new Intent(ctx,Sahitya.class);
                        ctx.startActivity(i1);
                    }
                    else if(position==2)
                    {
                        Intent i1=new Intent(ctx,Pravachan.class);
                        ctx.startActivity(i1);
                    }
                    else if(position==3)
                    {
                        Intent i1=new Intent(ctx,ViharActivity.class);
                        ctx.startActivity(i1);

                    }
                    else if(position==4)
                    {
                        Intent i1=new Intent(ctx,Pathsala.class);
                        ctx.startActivity(i1);

                    }
                    else if(position==5)
                    {
                         Intent i1=new Intent(ctx,Donations.class);
                          ctx.startActivity(i1);

                    }
                    else if(position==6)
                    {
                        Intent i1=new Intent(ctx,Sadharshya.class);
                        ctx.startActivity(i1);
                    }
                    else if(position==7)
                    {
                        Intent i1=new Intent(ctx,GathividhiActivity.class);
                        ctx.startActivity(i1);

                    }
                    else if(position==8)
                    {
                        Intent i1=new Intent(ctx,KariyaKarni.class);
                        ctx.startActivity(i1);
                    }
                }
                else
                {
                   // showSnack(isConnected);
                    Snackbar snackbar1 = Snackbar.make(v, "Sorry! Not connected to internet", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar1.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar1.show();
                }

            }
        });

        return rowView;
    }
}
