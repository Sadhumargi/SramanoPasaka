package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;



public class ShivirAdapter extends RecyclerView.Adapter<ShivirAdapter.ViewHolder>{

    Context context;
    LayoutInflater inflater;
    int[] data;
    ImageLoader imageLoader;

    public ShivirAdapter(Context context, int[] arraylist) {

        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.shiviradapter,parent, false);

        ViewHolder view=new ViewHolder(view1);
        return view;

    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, final int i) {

    holder.tv_menu.setText(data[i]);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((i)==0)
                {

                    Intent in=new Intent(context, Upcoming_Shivir.class);
                    context.startActivity(in);
                }else
                if(i==1)
                {
                    Intent in=new Intent(context, Past_Shivir.class);
                    context.startActivity(in);
                }else
                if(i==2) {

                    Intent in=new Intent(context, Shivir_Registration.class);
                    context.startActivity(in);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu;
        public ViewHolder(View iView) {
            super(iView);

            tv_menu= (TextView) iView.findViewById(R.id.tv_shivir_menu);
        }
    }
}
