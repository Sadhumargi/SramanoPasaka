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
import android.widget.TextView;

import java.util.ArrayList;

public class PravachanAdapter extends RecyclerView.Adapter<PravachanAdapter.ViewHolder> {


   // private ArrayList<PravachanGetSetter> androidlst;

    private ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.Pravachan> arraylist;
    Context context;
    public PravachanAdapter(Context context, ArrayList<com.sramanopasaka.sipanionline.sadhumargi.model.Pravachan> android) {
        this.arraylist = android;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pravachan_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");

        viewHolder.tv_title.setTypeface(type);
        viewHolder.tv_title.setTypeface(type,Typeface.BOLD);
        viewHolder.tv_title.setText(arraylist.get(i).getTitle());
        viewHolder.tv_date.setText(arraylist.get(i).getDate());

        Log.e("success","Demo"+arraylist.get(0).getTitle().toString());



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isConnected = ConnectivityReceiver.isConnected();

                if(isConnected)
                {
                     Intent i1 = new Intent(context, WebviewPravachan.class);
                     i1.putExtra("BookTitle",arraylist.get(i).getDate());
                     i1.putExtra("BookType", "P");
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
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_details,tv_date;
        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView)view.findViewById(R.id.txt_title_text);
            //tv_details = (TextView)view.findViewById(R.id.txt_details);
            tv_date = (TextView)view.findViewById(R.id.txt_date);

        }
    }

}
