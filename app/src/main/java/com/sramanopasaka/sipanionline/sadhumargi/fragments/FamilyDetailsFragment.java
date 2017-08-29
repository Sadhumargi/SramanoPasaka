package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;

import butterknife.ButterKnife;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.view;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyDetailsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.family_details_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}

