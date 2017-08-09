package com.sramanopasaka.sipanionline.sadhumargi;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExamsFragment extends Fragment {

    RecyclerView recyclerview;
    ProgressDialog pg;
    Context context;
    RecyclerView.Adapter adapter;

    public int[] arraylist={R.string.Upcomming_Exam ,R.string.PastExam_Result ,R.string.Registration};

    public ExamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*showtoast();*/
        View view=inflater.inflate(R.layout.fragment_exams, container, false);

        recyclerview= (RecyclerView) view.findViewById(R.id.rv_exam);
        new Remote().execute();
        return view;

    }
   /* void showtoast()
    {
        Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();
    }*/

   class Remote extends AsyncTask<Void, Void, Void>{

       ProgressDialog pg=null;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pg=new ProgressDialog(getActivity());
           pg.setMessage("Please wait loading");
           pg.setIndeterminate(true);
           pg.setCancelable(false);
           pg.show();

       }

       @Override
       protected void onPostExecute(Void aVoid) {
           super.onPostExecute(aVoid);

           RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
           recyclerview.setLayoutManager(layoutManager);

           adapter=new ExamAdapter(getActivity(),arraylist);

           recyclerview.setAdapter(adapter);

           pg.dismiss();
       }

       @Override
       protected Void doInBackground(Void... params) {
           return null;
       }
   }
}
