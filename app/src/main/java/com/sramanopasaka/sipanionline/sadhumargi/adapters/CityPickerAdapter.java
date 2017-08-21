package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.util.List;

/**
 * Created by hbb20 on 11/1/16. item
 * <p>
 * Move all code unrelated with RecyclerView item to parent dialog.
 * Updated by joielechong on 6 June 2017
 */
public class CityPickerAdapter extends RecyclerView.Adapter<CityPickerAdapter.CountryCodeViewHolder> {

    private List<City> cityList;
    private Callback mCallback;

    public interface Callback {

        void onItemSelected(City city);
    }

    public CityPickerAdapter(List<City> cityList, Callback callback) {
        this.cityList = cityList;
        this.mCallback = callback;
    }

    @Override
    public CountryCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = inflater.inflate(R.layout.state_list_item, viewGroup, false);
        return new CountryCodeViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CountryCodeViewHolder viewHolder, final int i) {

        final int position = viewHolder.getAdapterPosition();

        viewHolder.setCity(cityList.get(position));


        viewHolder.rlyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onItemSelected(cityList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList == null ? 0 : cityList.size();
    }

    public class CountryCodeViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlyMain;
        public AppCompatTextView tvName;
        TextView stateName;
        View viewDivider;

        CountryCodeViewHolder(View itemView) {
            super(itemView);
            rlyMain = (RelativeLayout) itemView;
            tvName = (AppCompatTextView) rlyMain.findViewById(R.id.country_name_tv);
            viewDivider = rlyMain.findViewById(R.id.preference_divider_view);
            stateName = (TextView) itemView.findViewById(R.id.country_name_tv);
        }

        private void setCity(City city) {

           // tvName.setText(state.getName());
            if (city != null) {
                viewDivider.setVisibility(View.GONE);

                tvName.setText(city.getCity_name());
                tvName.setVisibility(View.VISIBLE);

            } else {
                viewDivider.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
            }
        }
    }
}

