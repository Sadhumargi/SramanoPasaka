package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.ZonePickerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ZoneChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.model.Zone;

import java.util.List;

/**
 * Created by sipani001 on 29/8/17.
 */

public class ZonePickerDialog extends Dialog implements ZonePickerAdapter.Callback {

    private ZoneChangeListener zonechangeListner;
    private List<String> mFilteredZone;
    private List<Zone> zoneList = null;
    private Context context = null;
    private  RecyclerView zone_dialog_rv;

    public ZonePickerDialog(@NonNull Context context, List<Zone> zoneList) {
        super(context);
        this.context=context;
        this.zoneList=zoneList;
    }

    public void setZoneChangeListner(ZoneChangeListener zoneChangeListner) {
        this.zonechangeListner = zoneChangeListner;
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
       // mTvTitle = (AppCompatTextView) this.findViewById(R.id.title_tv);
       // mEdtSearch = (AppCompatEditText) this.findViewById(R.id.search_edt);
       // mTvNoResult = (AppCompatTextView) this.findViewById(R.id.no_result_tv);
        zone_dialog_rv = (RecyclerView) this.findViewById(R.id.zone_dialog_rv);
        zone_dialog_rv.setLayoutManager(new LinearLayoutManager(context));
    }

    private void setupData() {



        ZonePickerAdapter zonePickerAdapter = new ZonePickerAdapter(zoneList,this);
        zone_dialog_rv.setAdapter(zonePickerAdapter);
    }

    @Override
    public void onZoneSelected(String zone,String id) {

        zonechangeListner.onZoneSelected(zone,id);


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
