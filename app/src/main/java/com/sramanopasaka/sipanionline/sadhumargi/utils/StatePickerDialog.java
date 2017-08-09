package com.sramanopasaka.sipanionline.sadhumargi.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mukesh.countrypicker.Country;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for selecting Country.
 * <p>
 * Created by Joielechong on 11 May 2017.
 */

public class StatePickerDialog extends Dialog implements CountryCodeAdapter.Callback {
    private RelativeLayout mRlyDialog = null;
    private AppCompatTextView mTvTitle = null;
    private AppCompatEditText mEdtSearch = null;
    private AppCompatTextView mTvNoResult  =null;
    private RecyclerView country_dialog_rv = null;
private StateChangeListner stateChangeListner = null;
    private List<Country> mFilteredCountries;
    private List<State> stateList = null;
    private Context context = null;

    public StatePickerDialog(Context context,StateChangeListner stateChangeListner) {
        super(context);
        this.context = context;
        this.stateChangeListner = stateChangeListner;
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
    }

    private void setupData() {

        InputStream is = context.getResources().openRawResource(R.raw.state);
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
        stateList = (List<State>) new Gson().fromJson(writer.toString(), listType);

        CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(stateList,this);
        country_dialog_rv.setAdapter(countryCodeAdapter);
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
                    if (stateList != null ) {
                        charSequence = charSequence.toString().trim().toLowerCase();
                        final List<State> filteredList = new ArrayList<>();
                        boolean itemFound = false;
                        for (int i = 0; i < stateList.size(); i++) {

                            final String text = stateList.get(i).getName().toLowerCase();
                            if (text.contains(charSequence)) {
                                itemFound = true;
                                filteredList.add(stateList.get(i));
                            }
                        }
                  /*  if (!itemFound) {
                        Industry college = new Industry();
                        college.setName(charSequence.toString());
                        college.setId("new");
                        selected = charSequence.toString();
                        filteredList.add(college);
                    }*/
                        CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(filteredList,StatePickerDialog.this);
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

        if (mFilteredCountries.size() == 0) {
            mTvNoResult.setVisibility(View.VISIBLE);
        }

      //  mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemSelected(State country) {
        stateChangeListner.onStateSelected(country.getName());
        dismiss();
    }
}
