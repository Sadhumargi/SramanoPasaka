package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.mukesh.countrypicker.Country;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.CityPickerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.StatePickerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for selecting Country.
 * <p>
 * Created by Joielechong on 11 May 2017.
 */

public class CityPickerDialog extends Dialog implements CityPickerAdapter.Callback {
    private RelativeLayout mRlyDialog = null;
    private AppCompatTextView mTvTitle = null;
    private AppCompatEditText mEdtSearch = null;
    private AppCompatTextView mTvNoResult  =null;
    private RecyclerView country_dialog_rv = null;

    public void setStateChangeListner(StateChangeListner stateChangeListner) {
        this.stateChangeListner = stateChangeListner;
    }

    private StateChangeListner stateChangeListner = null;
    private List<City> cityList = null;
    private Context context = null;

    public CityPickerDialog(Context context, List<City> cityList) {
        super(context);
        this.context = context;
        this.cityList = cityList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_picker_dialogue);
        setupUI();
        setupData();
        setTextWatcher();
    }

    private void setupUI() {
        mRlyDialog = (RelativeLayout) this.findViewById(R.id.dialog_rly);
        mTvTitle = (AppCompatTextView) this.findViewById(R.id.title_tv);
        mEdtSearch = (AppCompatEditText) this.findViewById(R.id.search_edt);
        mTvNoResult = (AppCompatTextView) this.findViewById(R.id.no_result_tv);
        country_dialog_rv = (RecyclerView) this.findViewById(R.id.country_dialog_rv);
        country_dialog_rv.setLayoutManager(new LinearLayoutManager(context));

        mTvTitle.setText(context.getString(R.string.selectcity));
        mEdtSearch.setHint("Search your city here");
    }

    private void setupData() {



        CityPickerAdapter statePickerAdapter = new CityPickerAdapter(cityList,this);
        country_dialog_rv.setAdapter(statePickerAdapter);
    }

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
                    if (!itemFound) {
                        City college = new City();
                        college.setCity_name(charSequence.toString());
                        filteredList.add(college);
                    }
                        CityPickerAdapter countryCodeAdapter = new CityPickerAdapter(filteredList,CityPickerDialog.this);
                        country_dialog_rv.setAdapter(countryCodeAdapter);
                    }
                }
            });


        }
    }




    @Override
    public void onItemSelected(City country) {
        stateChangeListner.onStateSelected(country.getCity_name());
        dismiss();
    }
}
