package com.sramanopasaka.sipanionline.sadhumargi.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.CalenderActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;


public class MonthFragment extends BaseFragment  {

    private TabselectionListner tabselectionListner = null;
    private String selectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_calndrmonth, container, false);

        Toast.makeText(getActivity(), "Please select a date in MonthView", Toast.LENGTH_SHORT).show();

        CalendarView calendarView = view.findViewById(R.id.calender_view);

//        if(tabselectionListner != null){
//
//            Calendar calendar = Calendar.getInstance();
//            String currebtDate = DateFormat.getDateInstance().format(calendar.getTime());
//            Log.i(TAG, "CurrebtDate: "+currebtDate);
//
//            dayFragment.b_updateText(currebtDate);
//
//            tabselectionListner.onSelectTab(1);
//        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                if ((tabselectionListner != null)) {

                    int correctMonth = month+1;

                    selectedDate = (year + "-" + correctMonth + "-" + dayOfMonth);
                    Log.i(TAG, "onSelectedDayChange: " + selectedDate);

                    String TabOfFragmentB = ((CalenderActivity) getActivity()).getTabFragmentB();

                    final DayFragment dayFragment = (DayFragment) getActivity()
                            .getSupportFragmentManager()
                            .findFragmentByTag(TabOfFragmentB);

                        dayFragment.b_updateText(selectedDate);

                    tabselectionListner.onSelectTab(1);
                }
            }

        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabselectionListner = (TabselectionListner) getActivity();
    }
}




