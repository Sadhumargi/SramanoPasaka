package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class oldkarniadapter extends RecyclerView.Adapter<oldkarniadapter.ViewHolder> {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp= new HashMap<String, String>();



    public oldkarniadapter(Context context,
                               ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_oldkarniadapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        resultp = data.get(i);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.txt_kr_name.setTypeface(type);
        viewHolder.txt_kr_designation.setTypeface(type);
        viewHolder.txt_kr_region.setTypeface(type);
        viewHolder.txt_kr_city.setTypeface(type);

        // Capture position and set results to the TextViews
        viewHolder.txt_kr_name.setText(resultp.get(OldKaryakarni.KAR_NAME));
        viewHolder.txt_kr_designation.setText(resultp.get(OldKaryakarni.KR_DESIGNATION));
        viewHolder.txt_kr_region.setText(resultp.get(OldKaryakarni.KR_REGION));
        viewHolder.txt_kr_city.setText(resultp.get(OldKaryakarni.KR_CITY));
        viewHolder.txt_kr_phone.setText(resultp.get(OldKaryakarni.KR_PHONE));

        imageLoader.DisplayImage(resultp.get(OldKaryakarni.KR_IMG_LINK), viewHolder.image);
        // Capture ListView item click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_kr_name,txt_kr_designation,txt_kr_region,txt_kr_city,txt_kr_phone;
        ImageView image;
        public ViewHolder(View vi) {
            super(vi);

            txt_kr_name=(TextView)vi.findViewById(R.id.karni_name);
            txt_kr_designation=(TextView)vi.findViewById(R.id.karni_desigantion);
            txt_kr_region=(TextView)vi.findViewById(R.id.karni_region);
            txt_kr_city=(TextView)vi.findViewById(R.id.karni_city);
            txt_kr_phone=(TextView)vi.findViewById(R.id.karni_phone);
            image=(ImageView)vi.findViewById(R.id.kr_img);
        }
    }


}
