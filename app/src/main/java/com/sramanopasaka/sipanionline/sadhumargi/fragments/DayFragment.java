package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.CalenderActivity;
import com.sramanopasaka.sipanionline.sadhumargi.ConnectivityReceiver;
import com.sramanopasaka.sipanionline.sadhumargi.GPSTracker;
import com.sramanopasaka.sipanionline.sadhumargi.GpsStatusDetector;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.CalenderAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CalenderResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.CalenderModel;

import java.util.ArrayList;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public  class DayFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener, GpsStatusDetector.GpsStatusDetectorCallBack, GUICallback {

    TextView date;
    private GPSTracker gpsTracker;
    static String latitude, longitude;
    private ArrayList<CalenderModel> arraylist;
    String startDate;
    RecyclerView recyclerView;
    CalenderAdapter adapter;
    ScrollView scrollView;
    TextView dateText, sunsetTxt, sunriseTxt, navTxt, porsiTxt, sadhaporsiTxt, purimadhaTxt, avadhhaTxt;
    TextView udvegTxt, chalTxt, labhTxt, amritTxt, kaalTxt, shubhTxt, rogTxt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dayview, container, false);

        date = view.findViewById(R.id.monthfragment_text);
        scrollView = view.findViewById(R.id.scrollView_cal);

        dateText = view.findViewById(R.id.date_txt);
        sunsetTxt = view.findViewById(R.id.sunset_txt);
        sunriseTxt = view.findViewById(R.id.sunrise_txt);
        navTxt = view.findViewById(R.id.navkarsi_txt);
        sadhaporsiTxt = view.findViewById(R.id.sadhporsi_txt);
        purimadhaTxt = view.findViewById(R.id.purimaddha_txt);
        avadhhaTxt = view.findViewById(R.id.avadhha_txt);
        udvegTxt = view.findViewById(R.id.udveg_txt);
        chalTxt = view.findViewById(R.id.chal_txt);
        labhTxt = view.findViewById(R.id.labh_txt);
        amritTxt = view.findViewById(R.id.amrit_txt);
        kaalTxt = view.findViewById(R.id.kaal_txt);
        shubhTxt = view.findViewById(R.id.shubh_txt);
        rogTxt = view.findViewById(R.id.rog_txt);
        porsiTxt = view.findViewById(R.id.porsi_txt);


        String myTag = getTag();
        ((CalenderActivity) getActivity()).setTabFragmentB(myTag);
        getlocation();

            return view;
    }

    public void b_updateText(String t) {
        date.setText(t);
        startDate = t;
        Log.i(TAG, "SelectedDate in function: "+startDate);

        RequestProcessor processor=new RequestProcessor(DayFragment.this);
        processor.getCalenderList(latitude,longitude,startDate,startDate);
        showLoadingDialog();

        CalenderResponse response = new CalenderResponse();
        arraylist = response.getData();

    }

    void getlocation() {
        gpsTracker = new GPSTracker(getContext());

        if (gpsTracker.canGetLocation() == true) {
            if (gpsTracker.getLatitude() != 0 && gpsTracker.getLongitude() != 0) {
                latitude = String.valueOf(gpsTracker.getLatitude());
                longitude = String.valueOf(gpsTracker.getLongitude());
                Log.i(TAG, "getlocation: " + latitude);
                Log.i(TAG, "getlocation: " + longitude);

            }
        }
    }

    @Override
    public void onGpsSettingStatus(boolean enabled) {

        if (enabled == false) {
            // turnGPSOn();
            // showSettingsAlert();
        } else if (enabled == true) {
            getlocation();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

        hideLoadingDialog();

        try {

            if (guiResponse != null) {

                if (requestStatus.equals(RequestStatus.SUCCESS)) {

                    CalenderResponse calenderResponse = (CalenderResponse) guiResponse;
                    if (calenderResponse != null) {

                        if (calenderResponse.getData() != null && calenderResponse.getData().size() > 0) {

                            arraylist = calenderResponse.getData();

                            Log.i(TAG, "onRequestProcessed: "+calenderResponse.getData());

                            dateText.setText(arraylist.get(0).getDate());
                            sunriseTxt.setText(arraylist.get(0).getSunrise());
                            sunsetTxt.setText(arraylist.get(0).getSunset());
                            navTxt.setText(arraylist.get(0).getNavkarsi());
                            porsiTxt.setText(arraylist.get(0).getPorsi());
                            purimadhaTxt.setText(arraylist.get(0).getPurimaddha());
                            sadhaporsiTxt.setText(arraylist.get(0).getSadhporsi());
                            avadhhaTxt.setText(arraylist.get(0).getAvadhha());
                            udvegTxt.setText(arraylist.get(0).getUdveg());
                            chalTxt.setText(arraylist.get(0).getChal());
                            labhTxt.setText(arraylist.get(0).getLabh());
                            amritTxt.setText(arraylist.get(0).getAmrit());
                            kaalTxt.setText(arraylist.get(0).getKaal());
                            shubhTxt.setText(arraylist.get(0).getShubh());
                            rogTxt.setText(arraylist.get(0).getRog());


                        } else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (RuntimeException e) {

            Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
        }
    }
}

