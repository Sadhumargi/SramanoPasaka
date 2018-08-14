package com.sramanopasaka.sipanionline.sadhumargi;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShivirFragment extends Fragment {

    RecyclerView recyclerview;
    ProgressDialog pg;
    ShivirAdapter adapter;
    Context context;

    public int [] arraylist={R.string.Upcomming_Shivir,R.string.Past_Shivir,R.string.Shivir_Registration};

    public ShivirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_shivir, container, false);
        Toast.makeText(getActivity(), "Please wait will update it soon", Toast.LENGTH_SHORT).show();

        return view;
    }



}
