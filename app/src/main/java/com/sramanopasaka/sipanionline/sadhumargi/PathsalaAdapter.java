package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PathsalaAdapter extends RecyclerView.Adapter<PathsalaAdapter.ViewHolder> {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    int[] data;
    ImageLoader imageLoader;
    String [] result;

    public PathsalaAdapter(Context context,
                           int[] arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pathsala_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.tv_menu.setText(data[position]);

//        result[position]=(data[position]);

        // Capture ListView item click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                final boolean isConnected=ConnectivityReceiver.isConnected();
                if(isConnected)
                {


                if((position)==0)
                {
                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
//                    Intent in=new Intent(context, Pathsalalist.class);
//                    context.startActivity(in);
                }else
                if(position==1)
                {
                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
//                    Intent in=new Intent(context, Pathsala_Registration.class);
//                    context.startActivity(in);
                }
                /*else if(position==2)
                {
                    Intent in=new Intent(context, Pathsala_Rules.class);
                    context.startActivity(in);
                }
               *//* else if(position==3)
                {
                    Intent in=new Intent(context, ExamsActivity.class);
                    context.startActivity(in);

                }
                else if(position==4)
                {
                    Intent in=new Intent(context, ExamCentersActivity.class);
                    context.startActivity(in);

                }*//*
*/

                }
                else
                {
                    Snackbar snackbar1 = Snackbar.make(v, "Sorry! Not connected to internet", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar1.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar1.show();
                }

                //Toast.makeText(context, "Hi welcome to school list", Toast.LENGTH_SHORT).show();*/

                /*String a1=viewHolder.txt_path_name.getText().toString();
                Intent i1 = new Intent(context, Schoollist.class);
                context.startActivity(i1);*/

                /*String a1=viewHolder.txt_path_name.getText().toString();
                Intent intent=new Intent(context,Schoollist.class);
                context.startActivity(intent);*/


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_menu;
        ImageView image;
        TextView textView;

        public ViewHolder(View vi) {
            super(vi);
            tv_menu=(TextView)vi.findViewById(R.id.tv_pathasala_menu);
        }
    }
}
