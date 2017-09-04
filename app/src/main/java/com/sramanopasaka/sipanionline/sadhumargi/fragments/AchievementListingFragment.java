package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.AchivementsActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.AchievementListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AchievementListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 */

public class AchievementListingFragment extends BaseFragment implements GUICallback,AchievementListAdapter.EditDeleteActionListener {

    private View view = null;

    @Bind(R.id.achievementRecycler)
    RecyclerView recyclerView;

    @Bind(R.id.txt_no_address)
    TextView txtNoAddress;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private ActionBarUpdator actionBarUpdator = null;

    public static AchievementListingFragment newInstance() {
        return new AchievementListingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actionBarUpdator = (ActionBarUpdator) getActivity();
        actionBarUpdator.onUpdateTitile(getString(R.string.Achievements));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AchivementsActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        loadAddressList();
    }

    private void loadAddressList(){
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(AchievementListingFragment.this);
            requestProcessor.getAchievementList(loginResponse.getId(), loginResponse.getAppToken());
        }
    }

    private void showAddressList(List<Achievements> list) {
        if (list != null && list.size() != 0) {
            txtNoAddress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            AchievementListAdapter adapter = new AchievementListAdapter(getActivity(), list, this);
            recyclerView.setAdapter(adapter);
        } else {
            AchievementListAdapter adapter = new AchievementListAdapter(getActivity(), null, this);
            recyclerView.setAdapter(adapter);
            txtNoAddress.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onRequestProcessed(GUIResponse guiResponse, GUICallback.RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(GUICallback.RequestStatus.SUCCESS)) {
                if (guiResponse instanceof AchievementListResponse) {
                    AchievementListResponse response = (AchievementListResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (response.getData() != null && response.getData().size() > 0) {
                                showAddressList(response.getData());
                            } else {
                                txtNoAddress.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }

                            Log.e("----", "success");
                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                new CustomToast().showErrorToast(getActivity(), view, response.getMessage());
                            } else {
                                new CustomToast().showErrorToast(getActivity(), view, "Some thing went wrong");
                            }
                        }
                    }
                }else if (guiResponse instanceof DeleteAchievementResponse) {
                    DeleteAchievementResponse response = (DeleteAchievementResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (!TextUtils.isEmpty(response.getMessage())) {
                                new CustomToast().showErrorToast(getActivity(), view, response.getMessage());
                            } else {
                                new CustomToast().showErrorToast(getActivity(), view, "Address removed");
                            }

                            loadAddressList();

                            Log.e("----", "success");
                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                new CustomToast().showErrorToast(getActivity(), view, response.getMessage());
                            } else {
                                new CustomToast().showErrorToast(getActivity(), view, "Some thing went wrong");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void edit(Achievements address) {

    }

    @Override
    public void delete(final Achievements address) {
        DialogueUtils.showDialogOKCancel(getActivity(), "Delete Achievement", "Are you sure to delete this achievement ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginModel loginResponse = OfflineData.getLoginData();
                if (loginResponse != null) {


                    RequestProcessor requestProcessor = new RequestProcessor(AchievementListingFragment.this);
                    requestProcessor.removeAchievements(loginResponse.getId(), loginResponse.getAppToken(),address);
                }

                dialogInterface.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }
}
