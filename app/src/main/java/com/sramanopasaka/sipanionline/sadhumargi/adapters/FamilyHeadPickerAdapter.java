package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.OnClickListener;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyHeadPickerAdapter extends Adapter<FamilyHeadPickerAdapter.FamilyHeadViewHolder> {

    private List<Family> zoneList;
    private FamilyHeadPickerAdapter.Callback mCallback;


    public FamilyHeadPickerAdapter(List<Family> list, FamilyHeadPickerAdapter.Callback  callback) {
        this.zoneList=list;
        this.mCallback=callback;

    }


    @Override
    public FamilyHeadViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rooview=inflater.inflate(R.layout.zone_list_iteam,viewGroup,false);
        return new FamilyHeadViewHolder(rooview);
    }

    @Override
    public void onBindViewHolder(FamilyHeadViewHolder holder, int i) {


        final Family zone = zoneList.get(i);

        holder.setZone(zone) ;

        holder.tvName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

              mCallback.onFamilyHeadSelected(String.format("%s %s", zone.getCode(),zone.getFirst_name()),zone.getId());
            }
        });

    }





    @Override
    public int getItemCount() {
        return zoneList == null ? 0 : zoneList.size();
    }

    public interface Callback {

        void onFamilyHeadSelected(String family, String id);
    }


    public class FamilyHeadViewHolder extends ViewHolder {

        public AppCompatTextView tvName;

        public FamilyHeadViewHolder(View view) {
            super(view);

            tvName = (AppCompatTextView) view.findViewById(R.id.zone_name_tv);
        }

        public void setZone(Family zone) {


            if (zone != null) {
                //viewDivider.setVisibility(View.GONE);
                tvName.setText(String.format("%s %s", zone.getCode(),zone.getFirst_name()));
                tvName.setVisibility(View.VISIBLE);

            } else {
                //viewDivider.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
            }
        }
    }
}
