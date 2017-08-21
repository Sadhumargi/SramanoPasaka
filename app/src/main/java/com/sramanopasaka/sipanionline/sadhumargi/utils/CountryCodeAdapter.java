package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CountryResponse;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.Country;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.text.BreakIterator;
import java.util.List;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.country;

/**
 * Created by hbb20 on 11/1/16. item
 *
 * Move all code unrelated with RecyclerView item to parent dialog.
 * Updated by joielechong on 6 June 2017
 */
public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.CountryCodeViewHolder> {

  private List<Country> mCountries;
  private List<City> mCity;
  private Callback mCallback;

  public interface Callback {
    void onItemSelected(Country country);

    void onItemSelected(City city);
  }

  public CountryCodeAdapter(List<City> city, Callback callback) {
    this.mCity = city;
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

   viewHolder.setCountry(mCountries.get(position));


    viewHolder.rlyMain.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        mCallback.onItemSelected(mCountries.get(position));
      }
    });
  }

  @Override
  public int getItemCount() {
    return mCountries.size();
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
      stateName= (TextView) itemView.findViewById(R.id.country_name_tv);
    }

    private void setCountry(Country country) {

      tvName.setText(country.getState_name());
      if (country != null) {
       viewDivider.setVisibility(View.GONE);

        tvName.setText(country.getState_name());
        tvName.setVisibility(View.VISIBLE);

      } else {
        viewDivider.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.GONE);
      }
    }
  }
}

