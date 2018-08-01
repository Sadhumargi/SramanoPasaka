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



public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder> {

    Context context;
    ArrayList<CalenderModel> data;

    public CalenderAdapter(FragmentActivity activity, ArrayList<CalenderModel> arraylist) {

        this.context = activity;
        this.data = arraylist;
    }

    @Override
    public CalenderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_calender_adapter, parent, false);

        ViewHolder view = new ViewHolder(view1);

        return view;
    }

    @Override
    public void onBindViewHolder(CalenderAdapter.ViewHolder holder, int i) {

        holder.dateText.setText(data.get(i).getDate());
        holder.dateText.setText(data.get(i).getDate());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateText, sunsetTxt, sunriseTxt, navTxt, porsiTxt, sadhaporsiTxt, purimadhaTxt, avadhhaTxt;
        TextView udvegTxt, chalTxt, labhTxt, amritTxt, kaalTxt, shubhTxt, rogTxt;

        public ViewHolder(View itemView) {
            super(itemView);

            dateText = (TextView) itemView.findViewById(R.id.date_txt);
            sunsetTxt = (TextView) itemView.findViewById(R.id.sunset_txt);
            sunriseTxt = (TextView) itemView.findViewById(R.id.sunrise_txt);
            navTxt = (TextView) itemView.findViewById(R.id.navkarsi_txt);
            sadhaporsiTxt = (TextView) itemView.findViewById(R.id.sadhporsi_txt);
            purimadhaTxt = (TextView) itemView.findViewById(R.id.purimaddha_txt);
            avadhhaTxt = (TextView) itemView.findViewById(R.id.avadhha_txt);
//            udvegTxt = (TextView) itemView.findViewById(R.id.udveg_txt);
//            chalTxt = (TextView) itemView.findViewById(R.id.chal_txt);
//            labhTxt = (TextView) itemView.findViewById(R.id.labh_txt);
//            amritTxt = (TextView) itemView.findViewById(R.id.amrit_txt);
//            kaalTxt = (TextView) itemView.findViewById(R.id.kaal_txt);
//            shubhTxt = (TextView) itemView.findViewById(R.id.shubh_txt);
//            rogTxt = (TextView) itemView.findViewById(R.id.rog_txt);
            porsiTxt = (TextView) itemView.findViewById(R.id.porsi_txt);

        }
    }
}
