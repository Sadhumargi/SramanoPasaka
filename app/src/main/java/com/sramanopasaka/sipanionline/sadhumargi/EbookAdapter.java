package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.ViewHolder> implements Filterable {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<EbookGetSetter> data;
    ImageLoader imageLoader;
    private List<EbookGetSetter> androidlist;

    Activity a;


    public EbookAdapter(Context context,
                        ArrayList<EbookGetSetter> arraylist) {
        this.context = context;
        androidlist = arraylist;
        imageLoader = new ImageLoader(context);

        this.data = new ArrayList<EbookGetSetter>();
        this.data.addAll(androidlist);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {


        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ebook_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);

        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        viewHolder.tv_date.setText(androidlist.get(i).getDate());
        final String id = String.valueOf(androidlist.get(i).getId());
        String url2=androidlist.get(i).getImglink();
        imageLoader.DisplayImage(url2, viewHolder.image);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isConnected=ConnectivityReceiver.isConnected();
                if(isConnected)
                {
                    String a1=viewHolder.tv_date.getText().toString();
                    Intent i1 = new Intent(context, WebviewEbook.class);
                    i1.putExtra("Edition",id);
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

    @Override
    public Filter getFilter() {
        return null;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_date;
        CircleImageView image;


        public ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);

            tv_date = (TextView)view.findViewById(R.id.ebook_txt_date);
            image=(CircleImageView)view.findViewById(R.id.ebook_image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);

            }


    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        androidlist.clear();
        if (charText.length() == 0) {
            androidlist.addAll(data);
        }
        else
        {
            for (EbookGetSetter wp : data)
            {
                if (wp.getDate().contains(charText))
                {
                    androidlist.add(wp);
                }
                else
                {
                    Toast.makeText(context,"Item's Not Found",Toast.LENGTH_SHORT).show();
                }
            }
        }
        notifyDataSetChanged();
    }

    }

