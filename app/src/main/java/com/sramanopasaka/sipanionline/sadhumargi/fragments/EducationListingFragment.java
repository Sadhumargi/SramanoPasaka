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
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.BusinessActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.BusinessListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BusinessListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteBusinessResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Business;
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

public class EducationListingFragment extends BaseFragment implements GUICallback,BusinessListAdapter.EditDeleteActionListener {

    private View view = null;

    @Bind(R.id.businessRecycler)
    RecyclerView recyclerView;

    @Bind(R.id.txt_no_address)
    TextView txtNoAddress;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private ActionBarUpdator actionBarUpdator = null;

    public static EducationListingFragment newInstance() {
        return new EducationListingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_businesslist, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actionBarUpdator = (ActionBarUpdator) getActivity();
        actionBarUpdator.onUpdateTitile(getString(R.string.Business));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), BusinessActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        loadBusinessList();
    }

    private void loadBusinessList(){
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(EducationListingFragment.this);
            requestProcessor.getBusnessList(loginResponse.getId(), loginResponse.getAppToken());
        }
    }

    private void showAddressList(List<Business> list) {
        if (list != null && list.size() != 0) {
            txtNoAddress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            BusinessListAdapter adapter = new BusinessListAdapter(getActivity(), list, this);
            recyclerView.setAdapter(adapter);
        } else {
            BusinessListAdapter adapter = new BusinessListAdapter(getActivity(), null, this);
            recyclerView.setAdapter(adapter);
            txtNoAddress.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof BusinessListResponse) {
                    BusinessListResponse response = (BusinessListResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (response.getData() != null && response.getData().size() > 0) {
                                showAddressList(response.getData());
                            } else {
                                txtNoAddress.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "some thing went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else if (guiResponse instanceof DeleteBusinessResponse) {
                    DeleteBusinessResponse response = (DeleteBusinessResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Business removed", Toast.LENGTH_SHORT).show();
                            }

                            loadBusinessList();
                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "some thing went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void edit(Business address) {

    }

    @Override
    public void delete(final Business address) {
        DialogueUtils.showDialogOKCancel(getActivity(), "Delete Achievement", "Are you sure to delete this achievement ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginModel loginResponse = OfflineData.getLoginData();
                if (loginResponse != null) {


                    RequestProcessor requestProcessor = new RequestProcessor(EducationListingFragment.this);
                    requestProcessor.removeBusiness(loginResponse.getId(), loginResponse.getAppToken(),address);
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
