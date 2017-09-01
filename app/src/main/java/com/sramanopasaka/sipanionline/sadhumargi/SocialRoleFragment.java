package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.adapters.ExamListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.SocialRoleListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteSocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DharmikDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.SanghDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BaseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ExamListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DataUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.DharmicData;
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.model.SanghData;
import com.sramanopasaka.sipanionline.sadhumargi.model.SocialRole;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.fab;

public class SocialRoleFragment extends BaseFragment implements GUICallback, DataUpdator,SocialRoleListAdapter.EditDeleteActionListener {

    private View view = null;

    private int ADD_SOCIALROLE_REQUEST_CODE = 112;

    @Bind(R.id.businessRecycler)
    RecyclerView socialRoleListView;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.txt_no_address)
    TextView txtNoAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_businesslist, container, false);

        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddSocialRoleActivity.class);
                startActivityForResult(intent, ADD_SOCIALROLE_REQUEST_CODE);
            }
        });
        loadSocialRoleList();

    }

    private void loadSocialRoleList() {
        SanghData sanghData = OfflineData.getSanghData();
        if (sanghData != null) {
            if (sanghData.getSocialRole() != null) {
                showEducationList(sanghData.getSocialRole());
            } else {
                SocialRoleListAdapter adapter = new SocialRoleListAdapter(getActivity(), null, this);
                socialRoleListView.setAdapter(adapter);
                txtNoAddress.setVisibility(View.VISIBLE);
            }
        } else {
            SocialRoleListAdapter adapter = new SocialRoleListAdapter(getActivity(), null, this);
            socialRoleListView.setAdapter(adapter);
            txtNoAddress.setVisibility(View.VISIBLE);
        }
    }

    private void showEducationList(List<SocialRole> list) {
        if (list != null && list.size() != 0) {
            txtNoAddress.setVisibility(View.GONE);
            socialRoleListView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            socialRoleListView.setLayoutManager(layoutManager);
            SocialRoleListAdapter adapter = new SocialRoleListAdapter(getActivity(), list, this);
            socialRoleListView.setAdapter(adapter);
        } else {
            SocialRoleListAdapter adapter = new SocialRoleListAdapter(getActivity(), null, this);
            socialRoleListView.setAdapter(adapter);
            txtNoAddress.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof DeleteSocialRoleResponse) {
                    DeleteSocialRoleResponse response = (DeleteSocialRoleResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Social Role removed", Toast.LENGTH_SHORT).show();
                            }
                            loadSanghData();
                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else if (guiResponse instanceof SanghDetailsResponse) {
                    SanghDetailsResponse response = (SanghDetailsResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        OfflineData.saveSanghResponse(response.getData());
                    /*if (dataUpdator != null)
                        dataUpdator.onDataRefreshed();*/
                        loadSocialRoleList();
                    } else {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    public void loadSanghData() {
        showLoadingDialog();
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(SocialRoleFragment.this);
            requestProcessor.getSanghDetails(loginResponse.getId(), loginResponse.getAppToken());
        }
    }


    @Override
    public void delete(final SocialRole exams) {
        DialogueUtils.showDialogOKCancel(getActivity(), "Delete Social Role", "Are you sure to delete this social role ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginModel loginResponse = OfflineData.getLoginData();
                if (loginResponse != null) {


                    RequestProcessor requestProcessor = new RequestProcessor(SocialRoleFragment.this);
                    requestProcessor.removeSocialRole(loginResponse.getId(), loginResponse.getAppToken(), exams);
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

    @Override
    public void onDataRefreshed() {
        loadSocialRoleList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_SOCIALROLE_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                 /*if (dharmikActivity == null)
                     dharmikActivity = (DharmikActivity) getActivity();
                 dharmikActivity.loadSanghData();*/
                loadSanghData();
            }
        }
    }

    public static SocialRoleFragment newInstance() {

        return new SocialRoleFragment();
    }



}
