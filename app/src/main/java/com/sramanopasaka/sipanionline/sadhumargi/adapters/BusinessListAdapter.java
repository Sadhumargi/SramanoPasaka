package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Business;

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

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.AddressListViewHolder> {
    private List<Business> list = Collections.emptyList();
    private Context context;
    private EditDeleteActionListener listener;

    public BusinessListAdapter(Context activity, List<Business> list, EditDeleteActionListener listener) {
        context = (activity);
        this.list = list;
        this.listener = listener;
    }

    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false);
       // View view = inflater.inflate(R.layout.address_list_item, parent, false);
        return new AddressListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AddressListViewHolder holder, int position) {
        final Business model = list.get(position);
        holder.txtAddressTag.setText(model.getBusiness_type());
        String businessName = TextUtils.isEmpty(model.getBusiness_name()) ? " " : model.getBusiness_name() + ", " ;
        String businessRole = TextUtils.isEmpty(model.getBusiness_role()) ? " " : model.getBusiness_role() + ", " ;
        holder.txtAddress.setText( businessName + businessRole + model.getBusiness_start_year() /*+ ", " + model.getAchievement_year() */);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
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
        void edit(Business address);

        void delete(Business address);
    }

}
//member_id=11&app_token=0e4e7debeb748efb343129884296075e