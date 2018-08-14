package com.sramanopasaka.sipanionline.sadhumargi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExamsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*showtoast();*/
        View view = inflater.inflate(R.layout.fragment_exams, container, false);
        Toast.makeText(getActivity(), "Please wait will update it soon", Toast.LENGTH_SHORT).show();

        return view;

    }
}

