package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.listener.RegisterProgressUpdator;

import butterknife.ButterKnife;

/**
 * Name    :   pranavjdev
 * Date   : 8/29/17
 * Email : pranavjaydev@gmail.com
 */

public class SignUpFragment extends BaseFragment implements RegisterProgressUpdator {

    //signUpContainer

    private View view = null;

    String[] descriptionData = {"Basic", "Family", "Personal"};

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StateProgressBar step_view = (StateProgressBar) view.findViewById(R.id.step_view);
        step_view.setStateDescriptionData(descriptionData);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.signUpContainer,GeneralDetailsFragment.newInstance());
        //fragmentTransaction.addToBackStack("GeneralDetailsFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onProgressUpdate(int progress) {

        Log.e("----",""+progress);
    }
}
