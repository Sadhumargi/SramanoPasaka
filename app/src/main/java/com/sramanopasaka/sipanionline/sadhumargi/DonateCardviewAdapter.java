package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DonateCardviewAdapter extends  RecyclerView.Adapter<DonateCardviewAdapter.ViewHolder>  {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp= new HashMap<String, String>();

    public DonateCardviewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public long getItemId(int position) {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_donation_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        resultp = data.get(i);
        // Capture position and set results to the TextViews
        viewHolder.txt_path_name.setText(resultp.get(Donations.KAR_NAME));

        // Capture ListView item click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                resultp = data.get(i);

                int a1= Integer.parseInt(resultp.get(Donations.KR_NO));
                Intent i1 = new Intent(context, DonationsDetails.class);
                Bundle b = new Bundle();
                b.putInt("donate_id",a1);
                b.putString("dhan_name1",resultp.get(Donations.KAR_NAME));
                i1.putExtras(b);
                context.startActivity(i1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_path_name;
        ImageView image;
        public ViewHolder(View vi) {
            super(vi);

            txt_path_name=(TextView)vi.findViewById(R.id.txt_donate_name);

        }
    }
}
