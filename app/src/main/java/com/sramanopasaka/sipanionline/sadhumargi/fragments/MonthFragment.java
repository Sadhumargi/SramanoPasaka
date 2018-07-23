package com.sramanopasaka.sipanionline.sadhumargi.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.sramanopasaka.sipanionline.sadhumargi.CalenderActivity;
import com.sramanopasaka.sipanionline.sadhumargi.ConnectivityReceiver;
import com.sramanopasaka.sipanionline.sadhumargi.GpsStatusDetector;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;


public class MonthFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener
         {

    Context context;
    private TabselectionListner tabselectionListner = null;
    private String selectedDate;
    GpsStatusDetector gpsStatusDetector;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view  = inflater.inflate(R.layout.fragment_calndrmonth,container,false);

        CalendarView calendarView = view.findViewById(R.id.calender_view);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                if ((tabselectionListner != null)) {

                        selectedDate = (year+"-"+month+"-"+dayOfMonth);
                        Log.i(TAG, "onSelectedDayChange: "+selectedDate);

                    String TabOfFragmentB = ((CalenderActivity)getActivity()).getTabFragmentB();

//                    Toast.makeText(getActivity(),
//                            "text sent to Fragment B:\n " + TabOfFragmentB,
//                            Toast.LENGTH_LONG).show();

                        tabselectionListner.onSelectTab(1);

                    DayFragment fragmentB = (DayFragment)getActivity()
                            .getSupportFragmentManager()
                            .findFragmentByTag(TabOfFragmentB);
                    fragmentB.b_updateText(selectedDate);
                }
            }

        });

        return  view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabselectionListner = (TabselectionListner) getActivity();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

}
