package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;
import com.sramanopasaka.sipanionline.sadhumargi.model.SanghData;
import com.sramanopasaka.sipanionline.sadhumargi.model.Zone;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.OnClickListener;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by sipani001 on 29/8/17.
 */

public class SanghPickerAdapter extends Adapter<SanghPickerAdapter.SanghViewHolder> {

    private List<LocalSangh> zoneList;
    private SanghPickerAdapter.Callback mCallback;


    public SanghPickerAdapter(List<LocalSangh> list, SanghPickerAdapter.Callback  callback) {
        this.zoneList=list;
        this.mCallback=callback;

    }


    @Override
    public SanghViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rooview=inflater.inflate(R.layout.zone_list_iteam,viewGroup,false);
        return new SanghViewHolder(rooview);
    }

    @Override
    public void onBindViewHolder(SanghViewHolder holder, int i) {


        final LocalSangh zone = zoneList.get(i);

        holder.setZone(zone) ;

        holder.tvName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

              mCallback.onSanghSelected(zone.getBranch_name(),zone.getId());
            }
        });

    }





    @Override
    public int getItemCount() {
        return zoneList == null ? 0 : zoneList.size();
    }

    public interface Callback {

        void onSanghSelected(String zone, String id);
    }


    public class SanghViewHolder extends ViewHolder {

        public AppCompatTextView tvName;

        public SanghViewHolder(View view) {
            super(view);

            tvName = (AppCompatTextView) view.findViewById(R.id.zone_name_tv);
        }

        public void setZone(LocalSangh zone) {


            if (zone != null) {
                //viewDivider.setVisibility(View.GONE);

                tvName.setText(zone.getBranch_name());
                tvName.setVisibility(View.VISIBLE);

            } else {
                //viewDivider.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
            }
        }
    }
}
