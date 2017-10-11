package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.FamilyHeadPickerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.SanghPickerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.listener.FamilyHeadChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.listener.SanghChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;

import java.util.List;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyHeadPickerDialog extends Dialog implements FamilyHeadPickerAdapter.Callback {

    private FamilyHeadChangeListener sanghChangeListener;
    private List<String> mFilteredZone;
    private List<Family> zoneList = null;
    private Context context = null;
    private  RecyclerView zone_dialog_rv;
    private String titleTxt = null;

    public boolean isHasData() {
        return hasData;
    }

    private boolean hasData = false;

    public FamilyHeadPickerDialog(@NonNull Context context, List<Family> zoneList,String titleTxt) {
        super(context);
        this.context=context;
        this.zoneList=zoneList;
        this.titleTxt = titleTxt;
        if(zoneList!=null && zoneList.size()>0)
            hasData = true;
    }

    public void setFamilyHeadChangeListner(FamilyHeadChangeListener zoneChangeListner) {
        this.sanghChangeListener = zoneChangeListner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zone_picker_layout);
        setupUI();
        setupData();
        //setTextWatcher();
    }

    private void setupUI() {
        //mRlyDialog = (RelativeLayout) this.findViewById(R.id.dialog_rly);
        AppCompatTextView mTvTitle = (AppCompatTextView) this.findViewById(R.id.title_tv);
       // mEdtSearch = (AppCompatEditText) this.findViewById(R.id.search_edt);
       // mTvNoResult = (AppCompatTextView) this.findViewById(R.id.no_result_tv);
        zone_dialog_rv = (RecyclerView) this.findViewById(R.id.zone_dialog_rv);
        zone_dialog_rv.setLayoutManager(new LinearLayoutManager(context));

        mTvTitle.setText(titleTxt);
    }

    private void setupData() {



        FamilyHeadPickerAdapter zonePickerAdapter = new FamilyHeadPickerAdapter(zoneList,this);
        zone_dialog_rv.setAdapter(zonePickerAdapter);
    }



    @Override
    public void onFamilyHeadSelected(String family, String id) {
        sanghChangeListener.onFamilyHeadSelected(family,id);
    }






    /*private void setTextWatcher() {
        if () mEdtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (zoneList != null) {
                    charSequence = charSequence.toString().trim().toLowerCase();
                    final List<Zone> filteredList = new ArrayList<>();
                    boolean itemFound = false;
                    for (int i = 0; i < zoneList.size(); i++) {

                        final String text = zoneList.get(i).getName().toLowerCase();
                        if (text.contains(charSequence)) {
                            itemFound = true;
                            filteredList.add(zoneList.get(i));
                        }
                    }
                  *//*  if (!itemFound) {
                        Industry college = new Industry();
                        college.setName(charSequence.toString());
                        college.setId("new");
                        selected = charSequence.toString();
                        filteredList.add(college);
                    }*//*
                    ZonePickerAdapter zonePickerAdapter = new ZonePickerAdapter(filteredList, ZonePickerDialog.this);
                    zone_dialog_rv.setAdapter(zonePickerAdapter);
                }
            }
        });
    }*/

    /**
     * Filter country list for given keyWord / query.
     * Lists all countries that contains @param query in country's name, name code or phone code.
     *
     * @param query : text to match against country name, name code or phone code
     */
   /* private void applyQuery(String query) {
       // mTvNoResult.setVisibility(View.GONE);
        query = query.toLowerCase();

        //if query started from "+" ignore it
        if (query.length() > 0 && query.charAt(0) == '+') {
            query = query.substring(1);
        }

        // mFilteredCountries = getFilteredCountries(query);

        if (mFilteredZone.size() == 0) {
          //  mTvNoResult.setVisibility(View.VISIBLE);
        }

        //  mAdapter.notifyDataSetChanged();
    }*/





}
