package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.DharmikActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdatePromiseResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DataUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.DharmicData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PromiseFragment extends BaseFragment implements GUICallback, DataUpdator {


    @Bind(R.id.samayik)
    CheckBox samayik;

    @Bind(R.id.navkar_mantra)
    CheckBox navkar_mantra;

    @Bind(R.id.chovihar)
    CheckBox chovihar;

    @Bind(R.id.swadhyay)
    CheckBox swadhyay;

    @Bind(R.id.navkarsi)
    CheckBox navkarsi;

    @Bind(R.id.others)
    CheckBox others;

    @Bind(R.id.sant_darshan)
    CheckBox sant_darshan;

    @Bind(R.id.pratikraman)
    CheckBox pratikraman;

    @Bind(R.id.special)
    EditText special;


    private View view = null;
    private DharmikActivity dharmikActivity = null;

    public static PromiseFragment newInstance() {
        return new PromiseFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_rule_fragment, container, false);

        ButterKnife.bind(this, view);
        return view;


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (getActivity() != null) {

                        showDataUi();
                        dharmikActivity = (DharmikActivity) getActivity();
                        dharmikActivity.setDataUpdator(PromiseFragment.this);

                    }
                }

            }, 100L);

        }
    }

    private void showDataUi() {
        DharmicData dharmicData = OfflineData.getDharmikData();
        if (dharmicData != null) {
            if (dharmicData.getPromises() != null) {

                if (!TextUtils.isEmpty(dharmicData.getPromises().getChovihar()) && dharmicData.getPromises().getChovihar().equalsIgnoreCase("1"))
                    chovihar.setChecked(true);
                else
                    chovihar.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getNavkar_mantra()) && dharmicData.getPromises().getNavkar_mantra().equalsIgnoreCase("1"))
                    navkar_mantra.setChecked(true);
                else
                    navkar_mantra.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getNavkarsi()) && dharmicData.getPromises().getNavkarsi().equalsIgnoreCase("1"))
                    navkarsi.setChecked(true);
                else
                    navkarsi.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getPratikraman()) && dharmicData.getPromises().getPratikraman().equalsIgnoreCase("1"))
                    pratikraman.setChecked(true);
                else
                    pratikraman.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getSamayik()) && dharmicData.getPromises().getSamayik().equalsIgnoreCase("1"))
                    samayik.setChecked(true);
                else
                    samayik.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getSant_darshan()) && dharmicData.getPromises().getSant_darshan().equalsIgnoreCase("1"))
                    sant_darshan.setChecked(true);
                else
                    sant_darshan.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getSwadhyay()) && dharmicData.getPromises().getSwadhyay().equalsIgnoreCase("1"))
                    swadhyay.setChecked(true);
                else
                    swadhyay.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getChovihar()) && dharmicData.getPromises().getChovihar().equalsIgnoreCase("1"))
                    chovihar.setChecked(true);
                else
                    chovihar.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getPromises().getOthers()) && dharmicData.getPromises().getOthers().equalsIgnoreCase("1"))
                    others.setChecked(true);
                else
                    others.setChecked(false);

            }
        }
    }

    @OnClick(R.id.updatePromises)
    public void updatePromises() {

        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {
            showLoadingDialog();

            RequestProcessor requestProcessor = new RequestProcessor(PromiseFragment.this);
            requestProcessor.updatePromises(loginResponse.getId(), loginResponse.getAppToken(), String.valueOf(navkar_mantra.isChecked()), String.valueOf(swadhyay.isChecked()),
                    String.valueOf(sant_darshan.isChecked()), String.valueOf(samayik.isChecked()), String.valueOf(navkarsi.isChecked()),
                    String.valueOf(pratikraman.isChecked()), String.valueOf(chovihar.isChecked()), String.valueOf(others.isChecked()), special.getText().toString());
        }


    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof UpdatePromiseResponse) {
                    UpdatePromiseResponse response = (UpdatePromiseResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (dharmikActivity == null)
                                dharmikActivity = (DharmikActivity) getActivity();
                            dharmikActivity.loadDharmikData();

                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Updated successfully", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDataRefreshed() {
        showDataUi();
    }
}
