package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sramanopasaka.sipanionline.sadhumargi.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name    :   pranavjdev
 * Date   : 8/29/17
 * Email : pranavjaydev@gmail.com
 */

public class GeneralDetailsFragment extends BaseFragment {

    @Bind(R.id.first_name)
    EditText firstName;

    @Bind(R.id.last_name)
    EditText lastName;



    private View view = null;

    public static GeneralDetailsFragment newInstance() {
        return new GeneralDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
