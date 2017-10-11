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

import com.sramanopasaka.sipanionline.sadhumargi.PostalAddressActivity;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.AddressListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddressListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class ContactDetailsFragment extends BaseFragment implements AddressListAdapter.EditDeleteActionListener, GUICallback {

    private View view = null;

    @Bind(R.id.address_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.txt_no_address)
    TextView txtNoAddress;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private TabselectionListner tabselectionListner = null;
    private ActionBarUpdator actionBarUpdator = null;


    public static ContactDetailsFragment newInstance() {
        return new ContactDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragnment_contact_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabselectionListner = (TabselectionListner) getActivity();
        tabselectionListner.enableNestedScrolling(false);
        actionBarUpdator = (ActionBarUpdator) getActivity();
        actionBarUpdator.onUpdateTitile(getString(R.string.postal_address));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PostalAddressActivity.class);
                startActivity(i);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 ||dy<0 && fab.isShown())
                {
                    fab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
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


            RequestProcessor requestProcessor = new RequestProcessor(ContactDetailsFragment.this);
            requestProcessor.getAddressList(loginResponse.getId(), loginResponse.getAppToken());
        }
    }

    private void showAddressList(List<Address> list) {
        if (list != null && list.size() != 0) {
            txtNoAddress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            AddressListAdapter adapter = new AddressListAdapter(getActivity(), list, this);
            recyclerView.setAdapter(adapter);
        } else {
            AddressListAdapter adapter = new AddressListAdapter(getActivity(), null, this);
            recyclerView.setAdapter(adapter);
            txtNoAddress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void edit(Address address) {

    }

    @Override
    public void delete(final Address address) {


        DialogueUtils.showDialogOKCancel(getActivity(), "Delete Address", "Are you sure to delete this address?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginModel loginResponse = OfflineData.getLoginData();
                if (loginResponse != null) {


                    RequestProcessor requestProcessor = new RequestProcessor(ContactDetailsFragment.this);
                    requestProcessor.removeAddress(loginResponse.getId(), loginResponse.getAppToken(),address);
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
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof AddressListResponse) {
                    AddressListResponse response = (AddressListResponse) guiResponse;
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
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "some thing went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else if (guiResponse instanceof DeleteAddressResponse) {
                    DeleteAddressResponse response = (DeleteAddressResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            if (!TextUtils.isEmpty(response.getMessage())) {
                                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Address removed", Toast.LENGTH_SHORT).show();
                            }

                            loadAddressList();

                            Log.e("----", "success");
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
}
