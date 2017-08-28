package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.listener.SearchFamilyUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FindFamilyRecyclerAdapter extends RecyclerView.Adapter<FindFamilyRecyclerAdapter.MyViewHolder> implements Filterable {

    private String token = null;
    private String location = null;
    private Context mContext = null;
    private SearchFamilyUpdator searchFamilyUpdator = null;
    private List<Family> resultList = new ArrayList<Family>();

    public void setResultList(List<Family> resultList) {
        this.resultList = resultList;
    }

    public FindFamilyRecyclerAdapter(Context context, SearchFamilyUpdator searchFamilyUpdator) {
        mContext = context;
        this.searchFamilyUpdator = searchFamilyUpdator;
        this.token = token;
        this.location = location;
    }

    @Override
    public FindFamilyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_family_item, parent, false);
        return new FindFamilyRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindFamilyRecyclerAdapter.MyViewHolder holder, int position) {
       /* final FindSpecialityModel model = resultList.get(position);
        DoctorSearchPojo doctorSearchPojo = new DoctorSearchPojo(model.getName(), "", 0, location, 0, 0, "", "");
        holder.name.setText(model.getName());
        holder.keyType.setText(model.getKeyType());
        holder.countryCodeItem.setTag(doctorSearchPojo);
        holder.countryCodeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DoctorListActivity.class);
                intent.putExtra("data", (DoctorSearchPojo) view.getTag());
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.name)
        TextView name;

        @Bind(R.id.keyType)
        TextView keyType;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    //TODO API CALL HERE
                    searchFamilyUpdator.onQuerryChanged(constraint.toString());
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
        return filter;
    }
}