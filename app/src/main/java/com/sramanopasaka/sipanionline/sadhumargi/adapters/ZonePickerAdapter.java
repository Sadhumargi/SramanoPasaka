package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Zone;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by sipani001 on 29/8/17.
 */

public class ZonePickerAdapter extends RecyclerView.Adapter<ZonePickerAdapter.ZoneViewHolder> {

    private List<Zone> zoneList;
    private ZonePickerAdapter.Callback mCallback;


    public ZonePickerAdapter(List<Zone> list, ZonePickerAdapter.Callback  callback) {
        this.zoneList=list;
        this.mCallback=callback;

    }


    @Override
    public ZoneViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rooview=inflater.inflate(R.layout.zone_list_iteam,viewGroup,false);
        return new ZoneViewHolder(rooview);
    }

    @Override
    public void onBindViewHolder(ZoneViewHolder holder, int i) {


        final int position= holder.getAdapterPosition();

        holder.setZone(zoneList.get(position)) ;

        holder.tvName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

              //mCallback.onZoneSelected(zoneList.,zoneList.get(position));
            }
        });

    }





    @Override
    public int getItemCount() {
        return zoneList == null ? 0 : zoneList.size();
    }

    public interface Callback {

        void onZoneSelected(String zone,String id);
    }


    public class ZoneViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView tvName;

        public ZoneViewHolder(View view) {
            super(view);

            tvName = (AppCompatTextView) view.findViewById(R.id.zone_name_tv);
        }

        public void setZone(Zone zone) {


            if (zone != null) {
                //viewDivider.setVisibility(View.GONE);

                tvName.setText(zone.getName());
                tvName.setVisibility(View.VISIBLE);

            } else {
                //viewDivider.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
            }
        }
    }
}
