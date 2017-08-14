package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.AddressListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name    :   pranavjdev
 * Date   : 8/10/17
 * Email : pranavjaydev@gmail.com
 */

public class ContactDetailsFragment extends BaseFragment implements AddressListAdapter.EditDeleteActionListener{

    private View view = null;

    @Bind(R.id.address_recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.txt_no_address)
    TextView txtNoAddress;

    @Bind(R.id.fab)
    FloatingActionButton fab;


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

       List<Address> addresses = new ArrayList<>();
        for (int i=0;i<10;i++)
        addresses.add(new Address("address1","address2","post","dist","city","pincode","state","country","type"));

        showAddressList(addresses);

    }

    private void showAddressList(List<Address> list){
        if (list != null && list.size() != 0) {

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            AddressListAdapter adapter = new AddressListAdapter(getActivity(), list, this);
            recyclerView.setAdapter(adapter);
        } else {
            txtNoAddress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void edit(Address address) {

    }

    @Override
    public void delete(Address address) {


    }
}
