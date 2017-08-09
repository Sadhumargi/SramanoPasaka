package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class ViharListAdapter extends RecyclerView.Adapter<ViharListAdapter.ViewHolder> implements Filterable{

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<TreeMap<String, String>> data;
    ArrayList<TreeMap<String, String>> data1;
    List<ViharGetSetter> data2;
    List<ViharGetSetter> data3;
    ImageLoader imageLoader;
    TreeMap<String, String> resultp= new TreeMap<String, String>();
    HashMap<String, String> resultp1= new HashMap<String, String>();

    public ViharListAdapter(Context context,
                            ArrayList<TreeMap<String, String>> arraylist) {
        this.context = context;
        data = new ArrayList<TreeMap<String, String>>(arraylist);
        this.data1 = new ArrayList<>();
        this.data1.addAll(data);
        imageLoader = new ImageLoader(context);
    }
   /* public ViharListAdapter(Context context,
                            ArrayList<ViharGetSetter> arraylist) {
        this.context = context;
        data2 = arraylist;
        this.data3 = new ArrayList<>();
        this.data3.addAll(data2);
        imageLoader = new ImageLoader(context);
    }
*/
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vihar_list_adapter, parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        resultp = data.get(i);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/KrutiDev010 .TTF");
        viewHolder.txt_gsname.setTypeface(type,Typeface.BOLD);
        viewHolder.txt_assist_name.setTypeface(type,Typeface.BOLD);
        //viewHolder.txt_assist_name1.setTypeface(type,Typeface.BOLD);
        //viewHolder.txt_assist_name2.setTypeface(type,Typeface.BOLD);
        //viewHolder.txt_assist_name3.setTypeface(type,Typeface.BOLD);
        //viewHolder.txt_vihar_location.setTypeface(type,Typeface.BOLD);
        viewHolder.txt_attender.setTypeface(type,Typeface.BOLD);
        viewHolder.txt_attender_chief.setTypeface(type,Typeface.BOLD);

        viewHolder.txt_gsname.setTextSize(22);
        viewHolder.txt_assist_name.setTextSize(22);
        //viewHolder.txt_assist_name1.setTextSize(22);
        //viewHolder.txt_assist_name2.setTextSize(22);
       // viewHolder.txt_assist_name3.setTextSize(22);
       
        viewHolder.txt_gsname.setTextSize(22);
        viewHolder.txt_attender.setTextSize(22);
        viewHolder.txt_attender_chief.setTextSize(22);


        //  txt_gsno.setText(resultp.get(Vihar.GS_NO));
        viewHolder.txt_gsname.setText(Html.fromHtml(resultp.get(Vihar.GS_NAME)));
        viewHolder.txt_vihar_location.setText(resultp.get(Vihar.GS_vihar_location));
        viewHolder.txt_attender.setText(Html.fromHtml(resultp.get(Vihar.GS_ATTENDER_NAME)));
        viewHolder.txt_attender_chief.setText(Html.fromHtml(resultp.get(Vihar.GS_ATTENDER_CHIEF_NAME)));
        viewHolder.txt_attender_phono.setText(resultp.get(Vihar.GS_ATTENDER_PHONE));
        viewHolder.txt_phone_no.setText(resultp.get(Vihar.GS_PHONE_NO));
        viewHolder.txt_km.setText(resultp.get(Vihar.GS_KM)+"km's");


        viewHolder.txt_assist_name.setText(resultp.get(Vihar.GS_ASSIST_NAME));

        String assistname[]=resultp.get(Vihar.GS_ASSIST_NAME).split(",");

        int lnth= assistname.length;
        String names="";
        if(lnth>1)
        {
            for(int in=0;in<lnth;in++)
            {
                 names=assistname[in];
                viewHolder.txt_assist_name.setText(names+"\n");
            }

        }


       /* if(lnth==1)
        {
            viewHolder.txt_assist_name.setText(assistname[0]);
        }
        if(lnth==2)
        {
            viewHolder.txt_assist_name.setText(assistname[0]);
            viewHolder.txt_assist_name1.setText(assistname[1]);
            viewHolder.tab_assist4.setVisibility(View.GONE);
        }
        if(lnth==3)
        {
            viewHolder.txt_assist_name.setText(assistname[0]);
            viewHolder.txt_assist_name1.setText(assistname[1]);
            viewHolder.txt_assist_name2.setText(assistname[2]);
            viewHolder.tab_assist4.setVisibility(View.GONE);
        }
        else if(lnth==4)
        {
            viewHolder.txt_assist_name.setText(assistname[0]);
            viewHolder.txt_assist_name1.setText(assistname[1]);
            viewHolder.txt_assist_name2.setText(assistname[2]);
            viewHolder.txt_assist_name3.setText(assistname[3]);
        }*/

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(i);

                String sra1=resultp.get(Vihar.GS_MY_LAT);
                String srb1=resultp.get(Vihar.GS_MY_LNG);

                String desa1=resultp.get(Vihar.GS_LAT);
                String desb1=resultp.get(Vihar.GS_LNG);

                double src1=Double.parseDouble(sra1);
                double src2=Double.parseDouble(srb1);

                double des1=Double.parseDouble(desa1);
                double des2=Double.parseDouble(desb1);

                Intent intent = new Intent(context, PathGoogleMapActivity.class);
                Bundle b = new Bundle();
                b.putDouble("my_latitude", src1);
                b.putDouble("my_longitude",src2);
                b.putDouble("des_latitude", des1);
                b.putDouble("des_longitude", des2);
                intent.putExtras(b);
                context.startActivity(intent);

                Log.e("dest lattidue"," a1a1a1a1a1a1 " +des1);
                Log.e("dest longitude"," b1b1b1bb1b1 " +des2);
                Log.e("a1 lattidue"," a1a1a1a1a1a1 " +src1);
                Log.e("b1 longitude"," b1b1b1bb1b1 " +src2);

            }
        });
       /* viewHolder.txt_gsname.setText(data2.get(i).getGsName());
        viewHolder.txt_vihar_location.setText(data2.get(i).getGS_vihar_location());
        viewHolder.txt_attender.setText(data2.get(i).getGsAttenderName());
        viewHolder.txt_attender_chief.setText(data2.get(i).getGsAttenderChiefName());
        viewHolder.txt_attender_phono.setText(data2.get(i).getGsAttenderPhone());
        viewHolder.txt_phone_no.setText(data2.get(i).getGsPhone());

        Location locationA = new Location("point A");
        locationA.setLatitude(data2.get(i).getGsMyLat());
        locationA.setLongitude(data2.get(i).getGsMyLng());
        Location locationB = new Location("point B");
        locationB.setLatitude(data2.get(i).getGsLat());
        locationB.setLongitude(data2.get(i).getGsLng());

        double distance = locationA.distanceTo(locationB)/1000;   //in km
        Log.e("Distance to"," kilometers " +distance);
        double dis= Double.parseDouble(new DecimalFormat("#####.##").format(distance));


      //  viewHolder.txt_km.setText(dis+"km's");
        String assistname[]=data2.get(i).getGsAssistName().split(",");

        int lnth= assistname.length;
        if(lnth==3)
        {
            viewHolder.txt_assist_name.setText(assistname[0]);
            viewHolder.txt_assist_name1.setText(assistname[1]);
            viewHolder.txt_assist_name2.setText(assistname[2]);
            viewHolder.tab_assist4.setVisibility(View.GONE);
        }
        else if(lnth==4)
        {
            viewHolder.txt_assist_name.setText(assistname[0]);
            viewHolder.txt_assist_name1.setText(assistname[1]);
            viewHolder.txt_assist_name2.setText(assistname[2]);
            viewHolder.txt_assist_name3.setText(assistname[3]);
        }

*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ArrayList<TreeMap<String, String>> filteredArrayVenues = new ArrayList<TreeMap<String, String>>();
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data1);
        } else {

            for (int index = 0; index < data.size(); index++) {
                TreeMap<String, String> dataVenues = data.get(index);

                if (dataVenues.get(Vihar.GS_NAME).toString().contains(charText.toString())) {
                    filteredArrayVenues.add(dataVenues);
                } else if (dataVenues.get(Vihar.GS_vihar_location).toString().contains(charText.toString())) {
                    filteredArrayVenues.add(dataVenues);
                }

            }
            notifyDataSetChanged();
        }

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_google,txt_gsname,txt_assist_name,txt_assist_name1,txt_assist_name2,txt_assist_name3,txt_address,txt_fromto,txt_vihar_location,txt_attender,txt_attender_chief,txt_phone_no,txt_km,txt_attender_phono;
        TableRow tab_assist4;
        public ViewHolder(View vi) {
            super(vi);
            //txt_gsno=(TextView)vi.findViewById(R.id.txt_sno);
            txt_gsname=(TextView)vi.findViewById(R.id.txt_guru_name);
            txt_assist_name=(TextView)vi.findViewById(R.id.txt_asist_guru);
            txt_assist_name1=(TextView)vi.findViewById(R.id.txt_assist_guru1);
            txt_assist_name2=(TextView)vi.findViewById(R.id.txt_assist_guru2);
            txt_assist_name3=(TextView)vi.findViewById(R.id.txt_assist_guru3);
            txt_address=(TextView)vi.findViewById(R.id.txt_addrs);
            txt_fromto=(TextView)vi.findViewById(R.id.txt_from_to);
            txt_vihar_location=(TextView)vi.findViewById(R.id.txt_vihar_location);
            txt_attender=(TextView)vi.findViewById(R.id.txt_atender_name);
            txt_attender_chief=(TextView)vi.findViewById(R.id.txt_chief_attender);
            txt_phone_no=(TextView)vi.findViewById(R.id.txt_phoneno);
            txt_km=(TextView)vi.findViewById(R.id.txt_km);
            tab_assist4=(TableRow)vi.findViewById(R.id.tab_assist4);
            txt_google=(TextView)vi.findViewById(R.id.txt_google);
            txt_attender_phono=(TextView)vi.findViewById(R.id.txt_phoneno_attender);

        }
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<TreeMap<String, String>> filteredArrayVenues = new ArrayList<TreeMap<String, String>>();

                if (constraint == null || constraint.length() == 0) {
                    results.count = data.size();
                    results.values = data;
                } else {
                    constraint = constraint.toString();
                    for (int index = 0; index < data.size(); index++) {
                        TreeMap<String, String> dataVenues = data.get(index);

                        if(dataVenues.get(Vihar.GS_vihar_location).toString().contains(constraint.toString()))
                        {
                            filteredArrayVenues.add(dataVenues);
                        }
                        else if(dataVenues.get(Vihar.GS_NAME).toString().contains(constraint.toString()))
                        {
                            filteredArrayVenues.add(dataVenues);
                        }

                    }

                    results.count = filteredArrayVenues.size();
                    System.out.println(results.count);
                    results.values = filteredArrayVenues;
                    Log.e("VALUES", results.values.toString());
                }

                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {

                data = (ArrayList<TreeMap<String, String>>) results.values;
                if (results.count == 0)
                    notifyDataSetChanged();
            }
        };
        return filter ;
    }

}
