package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;

import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.OnClickListener;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by sipani001 on 29/8/17.
 */

public class RelationPickerAdapter extends Adapter<RelationPickerAdapter.FamilyHeadViewHolder> {

    private List<Relations> zoneList;
    private RelationPickerAdapter.Callback mCallback;


    public RelationPickerAdapter(List<Relations> list, RelationPickerAdapter.Callback  callback) {
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


        final Relations zone = zoneList.get(i);

        holder.setZone(zone) ;

        holder.tvName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

              mCallback.onRelationSelected(zone.getRelation(),zone.getId());
            }
        });

    }





    @Override
    public int getItemCount() {
        return zoneList == null ? 0 : zoneList.size();
    }

    public interface Callback {

        void onRelationSelected(String relation, String id);
    }


    public class FamilyHeadViewHolder extends ViewHolder {

        public AppCompatTextView tvName;

        public FamilyHeadViewHolder(View view) {
            super(view);

            tvName = (AppCompatTextView) view.findViewById(R.id.zone_name_tv);
        }

        public void setZone(Relations zone) {


            if (zone != null) {
                //viewDivider.setVisibility(View.GONE);
                tvName.setText(String.format("%s", zone.getRelation()));
                tvName.setVisibility(View.VISIBLE);

            } else {
                //viewDivider.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
            }
        }
    }
}
