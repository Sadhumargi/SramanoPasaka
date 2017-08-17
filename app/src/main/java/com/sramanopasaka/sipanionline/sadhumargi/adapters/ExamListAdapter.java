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
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;

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

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.EducationListViewHolder> {
    private List<Exams> list = Collections.emptyList();
    private LayoutInflater inflater;
    private ExamListAdapter.EditDeleteActionListener listener;

    public ExamListAdapter(Context activity, List<Exams> list, ExamListAdapter.EditDeleteActionListener listener) {
        inflater = LayoutInflater.from(activity);
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ExamListAdapter.EducationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.education_list_item, parent, false);
        return new ExamListAdapter.EducationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EducationListViewHolder holder, int position) {
        final Exams model = list.get(position);
        holder.educationName.setText(model.getExam_name());
        holder.educationYear.setText(model.getExam_year());
        holder.instituteName.setText(model.getExam_institute_name());
        holder.description.setText("");
        holder.score.setText("");
    }



    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
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

        void delete(Exams exams);
    }

}
