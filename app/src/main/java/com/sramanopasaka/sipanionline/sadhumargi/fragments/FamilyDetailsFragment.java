package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;

import butterknife.ButterKnife;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.view;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyDetailsFragment extends BaseFragment implements GUICallback{

    private View view = null;

    private RegistrationPojo registrationPojo = null;
    public static FamilyDetailsFragment newInstance() {
        return new FamilyDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.family_details_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            registrationPojo = getArguments().getParcelable("DATA");
            RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);

            requestProcessor.selectFamily(registrationPojo.getLocalSanghId(),registrationPojo.getFirstName(),registrationPojo.getLastName(),registrationPojo.getCity());
            Log.e("----", registrationPojo + "");
        } catch (Exception ex) {
        }



    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

    }
}

