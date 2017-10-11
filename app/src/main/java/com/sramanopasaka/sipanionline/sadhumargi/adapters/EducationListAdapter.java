package com.sramanopasaka.sipanionline.sadhumargi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.model.Education;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name    :   pranavjdev
 * Date   : 8/16/17
 * Email : pranavjaydev@gmail.com
 */

public class EducationListAdapter extends RecyclerView.Adapter<EducationListAdapter.EducationListViewHolder> {

    private List<Education> list = Collections.emptyList();
    private Context context;
    private EducationListAdapter.EditDeleteActionListener listener;

    public EducationListAdapter(Context activity, List<Education> list, EducationListAdapter.EditDeleteActionListener listener) {
        context =activity;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public EducationListAdapter.EducationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.education_list_item, parent, false);
        //View view = inflater.inflate(R.layout.education_list_item, parent, false);
        return new EducationListAdapter.EducationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EducationListViewHolder holder, int position) {
        final Education model = list.get(position);
        holder.educationName.setText(model.getEducationName());
        holder.description.setText(model.getDescription());
        holder.score.setText(model.getScore());
        holder.educationYear.setText(model.getYear());
        holder.instituteName.setText(model.getIstitute());

    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class EducationListViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.educationName)
        TextView educationName;
        @Bind(R.id.educationYear)
        TextView educationYear;
        @Bind(R.id.instituteName)
        TextView instituteName;
        @Bind(R.id.description)
        TextView description;
        @Bind(R.id.score)
        TextView score;
        @Bind(R.id.delete)
        ImageView delete;

        public EducationListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.delete)
        public void delete() {
            listener.delete(list.get(getLayoutPosition()));
        }
    }

    public interface EditDeleteActionListener {

        void delete(Education education);
    }

}
