package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class ContactDetailsFragment extends BaseFragment {

    private View view = null;

    public static ContactDetailsFragment newInstance() {
        return new ContactDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragnment_contact_details, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
