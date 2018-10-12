package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sramanopasaka.sipanionline.sadhumargi.model.KaryakarniList;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<KaryakarniList> {

    private Context mContext;
    private int layoutResourceId;
   // private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();
    private ArrayList<KaryakarniList> mGridData = new ArrayList<KaryakarniList>();

    public GridViewAdapter(Context mContext, int layoutResourceId, ArrayList<KaryakarniList> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */
    public void setGridData(ArrayList<KaryakarniList> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) row.findViewById(R.id.grid_item_name);
            holder.cityTextView = (TextView) row.findViewById(R.id.grid_item_city);
            holder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        KaryakarniList item = mGridData.get(position);

        Typeface type = Typeface.createFromAsset(mContext.getAssets(),"fonts/KrutiDev010 .TTF");

        holder.nameTextView.setTypeface(type);
        holder.cityTextView.setTypeface(type);
        holder.cityTextView.setTypeface(type);
        holder.nameTextView.setText(Html.fromHtml(item.getGrp_karkarni_name()));
        holder.nameTextView.setTypeface(type);
        holder.cityTextView.setText(Html.fromHtml(item.getGrp_karkarni_place()));

        Glide.with(mContext).load(item.getGrp_karkarni_imglink()).into(holder.imageView);
        return row;
    }

    static class ViewHolder {
        TextView nameTextView,cityTextView;
        ImageView imageView;
    }
}