package com.sramanopasaka.sipanionline.sadhumargi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.sramanopasaka.sipanionline.sadhumargi.listener.CityChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.Country;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.CountryCodeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sipani001 on 18/8/17.
 */

public class CityPickerDialog extends Dialog implements CountryCodeAdapter.Callback, CityChangeListner {

    private RelativeLayout mRlyDialog = null;
    private AppCompatTextView mTvTitle = null;
    private AppCompatEditText mEdtSearch = null;
    private AppCompatTextView mTvNoResult  =null;
    private RecyclerView country_dialog_rv = null;
    private CityChangeListner cityChangeListner = null;
    private List<City> mFilteredCity;
    private List<City> cityList = null;
    private Context context = null;
    private boolean isCity = false;


    public CityPickerDialog(Context context, CityChangeListner cityChangeListner, List<City> cityResponse) {
        super(context);
        this.context = context;
        this.cityChangeListner = cityChangeListner;
        this.cityList=cityResponse;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.city_layout_picker_dialogue);
        setupUI();
        //setupData();
        setTextWatcher();
    }

    private void setupUI() {
        mRlyDialog = (RelativeLayout) this.findViewById(R.id.dialog_rly);
        mTvTitle = (AppCompatTextView) this.findViewById(R.id.title_tv);
        mEdtSearch = (AppCompatEditText) this.findViewById(R.id.search_edt);
        mTvNoResult = (AppCompatTextView) this.findViewById(R.id.no_result_tv);

        country_dialog_rv = (RecyclerView) this.findViewById(R.id.country_dialog_rv);
        country_dialog_rv.setLayoutManager(new LinearLayoutManager(context));
    }

    /*private void setupData() {
        InputStream is = context.getResources().openRawResource(R.raw.state);
        if(isCity)
             is = context.getResources().openRawResource(R.raw.city);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {

            }
        }

        Type listType = new TypeToken<List<State>>() {
        }.getType();
        stateList = (List<State>) new Gson().fromJson(writer.toString(), listType);*/

        /*CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(stateList,this);
        country_dialog_rv.setAdapter(countryCodeAdapter);*/

    /**
     * add textChangeListener, to apply new query each time editText get text changed.
     */
    private void setTextWatcher() {
        if (mEdtSearch != null) {
            mEdtSearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    if (cityList != null ) {
                        charSequence = charSequence.toString().trim().toLowerCase();
                        final List<City> filteredList = new ArrayList<>();
                        boolean itemFound = false;
                        for (int i = 0; i < cityList.size(); i++) {

                            final String text = cityList.get(i).getCity_name().toLowerCase();
                            if (text.contains(charSequence)) {
                                itemFound = true;
                                filteredList.add(cityList.get(i));
                            }
                        }
                  /*  if (!itemFound) {
                        Industry college = new Industry();
                        college.setName(charSequence.toString());
                        college.setId("new");
                        selected = charSequence.toString();
                        filteredList.add(college);
                    }*/
                        CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(filteredList, CityPickerDialog.this);
                        country_dialog_rv.setAdapter(countryCodeAdapter);
                    }
                }
            });


        }
    }

    /**
     * Filter country list for given keyWord / query.
     * Lists all countries that contains @param query in country's name, name code or phone code.
     *
     * @param query : text to match against country name, name code or phone code
     */
    private void applyQuery(String query) {
        mTvNoResult.setVisibility(View.GONE);
        query = query.toLowerCase();

        //if query started from "+" ignore it
        if (query.length() > 0 && query.charAt(0) == '+') {
            query = query.substring(1);
        }

        // mFilteredCountries = getFilteredCountries(query);

        if (mFilteredCity.size() == 0) {
            mTvNoResult.setVisibility(View.VISIBLE);
        }

        //  mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(Country country) {

    }

    @Override
    public void onItemSelected(City city) {

        cityChangeListner.onCitySelected(city.getCity_name());
        dismiss();
    }

    @Override
    public void onCitySelected(String city) {

    }
}

