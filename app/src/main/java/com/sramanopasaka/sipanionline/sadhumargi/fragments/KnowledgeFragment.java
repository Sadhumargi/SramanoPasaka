package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.DharmikActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DharmikDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateKnowledgeResponse;
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
import retrofit2.http.Field;

public class KnowledgeFragment extends BaseFragment implements GUICallback, DataUpdator {

    private View view = null;
    //private DharmikActivity dharmikActivity = null;

    @Bind(R.id.navkar_mantra)
    CheckBox navkar_mantra;

    @Bind(R.id.samayik)
    CheckBox samayik;

    @Bind(R.id.pratikraman)
    CheckBox pratikraman;

    @Bind(R.id.bol_thokde)
    CheckBox bol_thokde;

    @Bind(R.id.shastra_gyan)
    CheckBox shastra_gyan;

    @Bind(R.id.vishesh_gyan)
    CheckBox vishesh_gyan;



    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_gnan_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showDataUi();
    }
   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (getActivity() != null) {

                        showDataUi();
                       *//* dharmikActivity = (DharmikActivity) getActivity();
                        dharmikActivity.setDataUpdator(KnowledgeFragment.this);*//*

                    }
                }

            }, 100L);

        }
    }*/

    private void showDataUi() {
        DharmicData dharmicData = OfflineData.getDharmikData();
        if (dharmicData != null) {
            if (dharmicData.getKnowledge() != null) {

                if (!TextUtils.isEmpty(dharmicData.getKnowledge().getSamayik()) && dharmicData.getKnowledge().getSamayik().equalsIgnoreCase("1"))
                    samayik.setChecked(true);
                else
                    samayik.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getKnowledge().getNavkar_mantra()) && dharmicData.getKnowledge().getNavkar_mantra().equalsIgnoreCase("1"))
                    navkar_mantra.setChecked(true);
                else
                    navkar_mantra.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getKnowledge().getPratikraman()) && dharmicData.getKnowledge().getPratikraman().equalsIgnoreCase("1"))
                    pratikraman.setChecked(true);
                else
                    pratikraman.setChecked(false);

                if (!TextUtils.isEmpty(dharmicData.getKnowledge().getBol_thokde()) && dharmicData.getKnowledge().getBol_thokde().equalsIgnoreCase("1"))
                    bol_thokde.setChecked(true);
                else
                    bol_thokde.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getKnowledge().getShastra_gyan()) && dharmicData.getKnowledge().getShastra_gyan().equalsIgnoreCase("1"))
                    shastra_gyan.setChecked(true);
                else
                    shastra_gyan.setChecked(false);
                if (!TextUtils.isEmpty(dharmicData.getKnowledge().getVishesh_gyan()) && dharmicData.getKnowledge().getVishesh_gyan().equalsIgnoreCase("1"))
                    vishesh_gyan.setChecked(true);
                else
                    vishesh_gyan.setChecked(false);


            }
        }
    }

    @OnClick(R.id.updateKnowledge)
    public void updateKnowledge() {

        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {
            showLoadingDialog();

            RequestProcessor requestProcessor = new RequestProcessor(KnowledgeFragment.this);
            requestProcessor.updateKnowledge(loginResponse.getId(), loginResponse.getAppToken(), String.valueOf(navkar_mantra.isChecked()), String.valueOf(samayik.isChecked()),
                    String.valueOf(pratikraman.isChecked()), String.valueOf(bol_thokde.isChecked()), String.valueOf(shastra_gyan.isChecked()),
                    String.valueOf(vishesh_gyan.isChecked()));

        }


    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof UpdateKnowledgeResponse) {
                    UpdateKnowledgeResponse response = (UpdateKnowledgeResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                           /* if (dharmikActivity == null)
                                dharmikActivity = (DharmikActivity) getActivity();
                            dharmikActivity.loadDharmikData();*/
                            loadDharmikData();
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
                }else if (guiResponse instanceof DharmikDetailsResponse) {
                    DharmikDetailsResponse response = (DharmikDetailsResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        OfflineData.saveDharmicResponse(response.getData());
                    /*if (dataUpdator != null)
                        dataUpdator.onDataRefreshed();*/
                        showDataUi();
                    } else {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
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

    public void loadDharmikData() {
        showLoadingDialog();
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(KnowledgeFragment.this);
            requestProcessor.getDharmikDetails(loginResponse.getId(), loginResponse.getAppToken());
        }
    }
}
