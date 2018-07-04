package com.sramanopasaka.sipanionline.sadhumargi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.view;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment {

    //public TextView text_school,text_staff,text_students,text_exams,text_exam_centers;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_school, container, false); //pass the correct style name for the fragment

        /*View view=inflater.inflate(R.layout.fragment_school,container,false);
        text_school=(TextView)view.findViewById(R.id.txt_schools);
        text_staff=(TextView)view.findViewById(R.id.txt_staffs);
        text_students=(TextView)view.findViewById(R.id.txt_students);
        text_exams=(TextView)view.findViewById(R.id.txt_exams);
        text_exam_centers=(TextView)view.findViewById(R.id.txt_centers);*/

        return view;
    }
    }

    /*@Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.txt_schools:

                Intent i = new Intent(getActivity(),Schoollist.class);
                startActivity(i);
                (getActivity()).overridePendingTransition(0,0);

                break;

            case R.id.txt_staffs:
                Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();

                break;

            case R.id.txt_students:
                Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();

                break;

            case R.id.txt_exams:
                Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();

                break;

            case R.id.txt_centers:
                Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();
                
                break;
        }
   }
    void showtoast()
    {
        Toast.makeText(getContext(),"Please Wiat we are Updating Soon",Toast.LENGTH_SHORT).show();
    }
*/