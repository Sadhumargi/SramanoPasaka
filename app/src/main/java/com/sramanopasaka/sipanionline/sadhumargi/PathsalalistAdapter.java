package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.listener.OnLoadMoreListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

import static com.sramanopasaka.sipanionline.sadhumargi.Vihar.a1;

public class PathsalalistAdapter extends RecyclerView.Adapter<PathsalalistAdapter.ViewHolder> {

    /*Context context;
    ArrayList<PathsalaGetSetter> data;
    List<PathsalaGetSetter> androidlist;*/


    Context context;
    List<PathsalaGetSetter> data;
    private ArrayList<PathsalaGetSetter> filterlist = new ArrayList<PathsalaGetSetter>();

    private boolean loading;

    private int lastVisibleItem, totalItemCount;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 5;
    private OnLoadMoreListener onLoadMoreListener=null;
    public String Name;

    private ArrayList<PathsalaGetSetter> resultp= new ArrayList<PathsalaGetSetter>();

    public PathsalalistAdapter() {
    }

    public PathsalalistAdapter(Context context, ArrayList<PathsalaGetSetter> arraylist, RecyclerView recyclerview) {


        this.context=context;
        this.data=arraylist;
        this.filterlist=arraylist;


        if (recyclerview.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerview
                    .getLayoutManager();


            recyclerview
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //Inflate the xml file
        View view1= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_pathsalalist_adapter,parent,false);

        //Link between viewHolder class and view1
        RecyclerView.ViewHolder view=new ViewHolder(view1);
        return (ViewHolder) view;

    }

    @Override
    public void onBindViewHolder(final PathsalalistAdapter.ViewHolder holder, final int position) {

       /* Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        holder.tv_code.setTypeface(type);*/
       // holder.tv_city.setTypeface(type, Typeface.BOLD);



        holder.tv_code.setText(filterlist.get(position).getCode());
        holder.tv_city.setText(filterlist.get(position).getCity());
        /*holder.tv_place.setText(filterlist.get(position).getPlace());
        holder.tv_timings.setText(filterlist.get(position).getTimings());
        holder.tv_address.setText(filterlist.get(position).getAddress());
        holder.tv_strength.setText(filterlist.get(position).getStrength());
        holder.tv_grade.setText(filterlist.get(position).getGrade());
        holder.tv_In_Charge_name.setText(filterlist.get(position).getIn_charge_name());
        holder.tv_In_Charge_number.setText(filterlist.get(position).getIn_charge_number());
        holder.tv_In_Charge_email.setText(filterlist.get(position).getIn_charge_email());
        holder.tv_sanghname.setText(filterlist.get(position).getSangh());
        holder.tv_type.setText(filterlist.get(position).getType());*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id=String.valueOf(filterlist.get(position).getId());
                int a1=Integer.parseInt(id);
                Intent i = new Intent(context, PatasalaName.class);
                Bundle b = new Bundle();
                b.putInt("pathsala_id",a1);
                i.putExtra("id",a1);
                context.startActivity(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        return filterlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tv_code;
        public TextView tv_city;
        public TextView tv_place;
        public TextView tv_type;
        public TextView tv_address;
        public TextView tv_strength;
        public TextView tv_grade;
        public TextView tv_timings;
        public TextView tv_sanghname;
        public TextView tv_In_Charge_name ;
        public TextView tv_In_Charge_number ;
        public TextView tv_In_Charge_email ;
        public TextView tv_state ;


        public ViewHolder(View v) {

            super(v);

            tv_code= (TextView) v.findViewById(R.id.txtcode);
            tv_city= (TextView) v.findViewById(R.id.txtcity);
           // tv_state= (TextView) v.findViewById(R.id.txtstate);
            /*tv_place= (TextView) v.findViewById(R.id.txtplace);
            tv_type= (TextView) v.findViewById(R.id.txttype);
            tv_address= (TextView) v.findViewById(R.id.txtaddress);
            tv_grade= (TextView) v.findViewById(R.id.txtgrade);
            tv_strength= (TextView) v.findViewById(R.id.txtstrength);
            tv_timings= (TextView) v.findViewById(R.id.txttimings);
            tv_sanghname= (TextView) v.findViewById(R.id.txtlocal_sangh_name);
            tv_In_Charge_name= (TextView) v.findViewById(R.id.txtin_charge_name);
            tv_In_Charge_number= (TextView) v.findViewById(R.id.txtin_charge_number);
            tv_In_Charge_email= (TextView) v.findViewById(R.id.txtin_charge_email);*/





        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }


    /*public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(filterlist);
        } else {
            for (int i=0;i<filterlist.size();i++) {

                PathsalaGetSetter wp= filterlist.get(i);

                if (wp.getCode().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    setContentView(R.layout.activity_pathsalalist_adapter);*/
}
