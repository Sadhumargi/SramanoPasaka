package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    int[] data;
    Context context;

    public ExamAdapter(FragmentActivity activity, int[] arraylist) {

       context =activity;
        data=arraylist;

    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_adapter);
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exam_adapter,parent,false);
        ViewHolder view=new ViewHolder(view1);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_exam.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_exam;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_exam= (TextView) itemView.findViewById(R.id.tv_exam_menu);
        }
    }
}
