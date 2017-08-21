package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name    :   pranavjdev
 * Date   : 8/14/17
 * Email : pranavjaydev@gmail.com
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> {
    private List<Address> list = Collections.emptyList();
    private Context context;
    private EditDeleteActionListener listener;

    public AddressListAdapter(Context activity, List<Address> list, EditDeleteActionListener listener) {
        context = (activity);
        this.list = list;
        this.listener = listener;
    }

    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = inflater.inflate(R.layout.address_list_item, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false);
        return new AddressListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AddressListViewHolder holder, int position) {
        final Address model = list.get(position);
        holder.txtAddressTag.setText(model.getAddress_type());
        holder.txtAddress.setText(model.getAddress1() + ", " + model.getAddress2() + ", " + model.getCity() + ", " + model.getState() + ", " + model.getPincode());

    }

    @Override
    public int getItemCount() {
        return list==null? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.address)
        TextView txtAddressTag;
        @Bind(R.id.address_text)
        TextView txtAddress;
        @Bind(R.id.delete)
        ImageView imgDelete;

        public AddressListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.delete)
        public void delete() {
            listener.delete(list.get(getLayoutPosition()));
        }
    }

    public interface EditDeleteActionListener {
        void edit(Address address);

        void delete(Address address);
    }

}
