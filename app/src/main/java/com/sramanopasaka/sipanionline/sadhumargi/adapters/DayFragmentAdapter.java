package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.CalenderModel;

import java.util.ArrayList;

public class DayFragmentAdapter extends RecyclerView.Adapter<DayFragmentAdapter.DayFragmentListViewHolder> {

    Context context;
    ArrayList<CalenderModel> arrayList;
//      ArrayList<String> arrayList;


    public DayFragmentAdapter(ArrayList<CalenderModel> arraylist, FragmentActivity activity) {
        this.arrayList = arraylist;
        this.context = activity;
    }

    @Override
    public DayFragmentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.activity_day_fragment_adapter,parent,false);

        return new DayFragmentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DayFragmentListViewHolder holder, int position) {

        holder.Date.setText(arrayList.get(position).getDate());
        holder.Sunrise.setText(arrayList.get(position).getSunrise());
        holder.Sunset.setText(arrayList.get(position).getSunset());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DayFragmentListViewHolder extends RecyclerView.ViewHolder {

        TextView Date,Sunrise,Sunset;

        public DayFragmentListViewHolder(View itemView) {
            super(itemView);

            Date = itemView.findViewById(R.id.date_txt);
            Sunrise = itemView.findViewById(R.id.sunrise_txt);
            Sunset = itemView.findViewById(R.id.sunset_txt);
        }

    }
}
