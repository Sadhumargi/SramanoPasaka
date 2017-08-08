package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SadharshyaAdapter extends RecyclerView.Adapter<SadharshyaAdapter.ViewHolder>{

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    List<SadharshyaGetSetter> data;
    ImageLoader imageLoader;
    private ArrayList<SadharshyaGetSetter> filterlist = null;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener=null;
    public String Name;

    @Override
    public long getItemId(int position) {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_member_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }


 /*   public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(filterlist);
        } else {
            for (SadharshyaGetSetter wp : filterlist) {
                if (wp.getFirst_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {


        if(data.get(i).getFirst_name()!=null && data.get(i).getMiddle_name()!=null && data.get(i).getLast_name()!=null)
        {
            Name=data.get(i).getFirst_name().trim().concat(data.get(i).getMiddle_name().trim().concat(data.get(i).getLast_name()).trim());
        }

        if(data.get(i).getBusiness_name().equals("null")==true || data.get(i).getBusiness_name().isEmpty()==true)
        {
            viewHolder.txt_kr_bussiness.setText("");
        }
        else
        {
            viewHolder.txt_kr_bussiness.setText(data.get(i).getBusiness_name());
        }

        if(data.get(i).getEducation_name().equals("null")==true || data.get(i).getEducation_name().isEmpty()==true)
        {
            viewHolder.txt_kr_education.setText("");
        }
        else
        {
            viewHolder.txt_kr_education.setText(data.get(i).getEducation_name());
        }

        viewHolder.txt_kr_name.setText(Name);
        viewHolder.txt_kr_city.setText(data.get(i).getCity());
        viewHolder.txt_kr_state.setText(data.get(i).getState());
        viewHolder.txt_kr_phone.setText(data.get(i).getMobile());
        viewHolder.txt_kr_bussiness.setText(data.get(i).getBusiness_name());
        viewHolder.txt_kr_education.setText(data.get(i).getEducation_name());
        imageLoader.DisplayImage(data.get(i).getProfile_pic().toString(), viewHolder.image);
        // Capture ListView item click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_kr_name,txt_kr_city,txt_kr_state,txt_kr_phone,txt_kr_bussiness,txt_kr_education;
        ImageView image;
        public ViewHolder(View vi) {
            super(vi);

            txt_kr_name=(TextView)vi.findViewById(R.id.karni_name);
            txt_kr_city=(TextView)vi.findViewById(R.id.karni_citi);
            txt_kr_state=(TextView)vi.findViewById(R.id.karni_state);
            txt_kr_phone=(TextView)vi.findViewById(R.id.karni_phone);
            txt_kr_bussiness=(TextView)vi.findViewById(R.id.karni_buissness);
            txt_kr_education=(TextView)vi.findViewById(R.id.karni_edu_name);
            image=(ImageView)vi.findViewById(R.id.kr_img);
        }
    }


    public SadharshyaAdapter(Context context,ArrayList<SadharshyaGetSetter> data, RecyclerView recyclerView) {

        this.context=context;
        imageLoader = new ImageLoader(context);
        this.data = data;
        this.filterlist = data;

        /*mapIndex = new LinkedHashMap<>();
        for (int x = 0; x < data.size(); x++) {
            String fruit = data.get(x).getEducation_name();
            String ch = fruit.substring(0, 1);
            ch = ch.toUpperCase(Locale.US);
            if (!mapIndex.containsKey(ch)) {
                mapIndex.put(ch, x);
            }
            // HashMap will prevent duplicates

        }*/

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setLoaded() {
        loading = false;
    }


}
