package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.model.GathividhiTextNews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TextNewsAdapter extends  RecyclerView.Adapter<TextNewsAdapter.ViewHolder> {


    //private ArrayList<TextGetSetter> android;

    private ArrayList<GathividhiTextNews> android;
    Context context;
    public TextNewsAdapter(Context context, ArrayList<GathividhiTextNews> android) {
        this.android = android;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_text_news_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.tv_title.setTypeface(type);
        viewHolder.tv_title.setTypeface(type,Typeface.BOLD);

        viewHolder.tv_details.setTypeface(type);
        viewHolder.tv_details.setTypeface(type,Typeface.BOLD);

        viewHolder.tv_title.setText(android.get(i).getText_news_title());
        viewHolder.tv_details.setText(android.get(i).getText_news_details());
        viewHolder.tv_date.setText(android.get(i).getDate());
        String url1 = android.get(i).getText_news_details().toString();
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

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title,tv_details,tv_date;
        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView)view.findViewById(R.id.txt_title_text);
            tv_details = (TextView)view.findViewById(R.id.txt_details);
            tv_date = (TextView)view.findViewById(R.id.txt_date);

        }
    }

}
