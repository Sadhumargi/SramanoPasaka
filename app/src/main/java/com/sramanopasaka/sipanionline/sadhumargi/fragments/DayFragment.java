package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.sramanopasaka.sipanionline.sadhumargi.adapters.DayFragmentAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CalenderResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.CalenderModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public  class DayFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener, GUICallback, GpsStatusDetector.GpsStatusDetectorCallBack {


    private GPSTracker gpsTracker;
    static String latitude, longitude;
    private List<CalenderModel> list;
    RecyclerView recyclerView;
    DayFragmentAdapter adapter;
    ScrollView scrollView;
    public static AlertDialog.Builder alertDialog;
    TextView tv[] = new TextView[15];
    TextView dateofApiText, sunsetTxt, sunriseTxt, navTxt, porsiTxt, sadhaporsiTxt, purimadhaTxt, avadhhaTxt;
    TextView udvegTxt1, chalTxt1, labhTxt1, amritTxt1, kaalTxt1, shubhTxt1, rogTxt1, udvegTxt2, chalTxt2, labhTxt2, amritTxt2, kaalTxt2, shubhTxt2, rogTxt2;

    GUIResponse guiResponse;
    RequestStatus requestStatus;
    private GpsStatusDetector mGpsStatusDetector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dayview, container, false);

//        dateTxt = view.findViewById(R.id.monthfragment_text);
//        dayTxt = view.findViewById(R.id.txt_days);
//        recyclerView = view.findViewById(R.id.recyclerview_dayfragment);
//        recyclerView.setHasFixedSize(true);
//        LayoutManager = new LinearLayoutManager(context);
        // use a linear layout manager
//        recyclerView.setLayoutManager(LayoutManager);

        scrollView = view.findViewById(R.id.scrollView_cal);
        dateofApiText = view.findViewById(R.id.date_txt);
        sunsetTxt = view.findViewById(R.id.sunset_txt);
        sunriseTxt = view.findViewById(R.id.sunrise_txt);
        navTxt = view.findViewById(R.id.navkarsi_txt);
        sadhaporsiTxt = view.findViewById(R.id.sadhporsi_txt);
        purimadhaTxt = view.findViewById(R.id.purimaddha_txt);
        avadhhaTxt= view.findViewById(R.id.avadhha_txt);
        udvegTxt1 = view.findViewById(R.id.udveg1_txt);
        udvegTxt2 = view.findViewById(R.id.udveg2_txt);
        chalTxt1 = view.findViewById(R.id.chal1_txt);
        chalTxt2 = view.findViewById(R.id.chal2_txt);
        labhTxt1 = view.findViewById(R.id.labh_txt1);
        labhTxt2 = view.findViewById(R.id.labh_txt2);
        amritTxt1 = view.findViewById(R.id.amrit_txt1);
        amritTxt2 = view.findViewById(R.id.amrit_txt2);
        kaalTxt1 = view.findViewById(R.id.kaal_txt1);
        kaalTxt2 = view.findViewById(R.id.kaal_txt2);
        shubhTxt1 = view.findViewById(R.id.shubh_txt1);
        shubhTxt2 = view.findViewById(R.id.shubh_txt2);
        rogTxt1 = view.findViewById(R.id.rog_txt1);
        rogTxt2 = view.findViewById(R.id.rog_txt2);
        porsiTxt = view.findViewById(R.id.porsi_txt);

//        currentDate = "2018-08-06";
        mGpsStatusDetector = new GpsStatusDetector(this);
        mGpsStatusDetector.checkGpsStatus();



        try {
            getlocation();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String myTag = getTag();
        ((CalenderActivity) getActivity()).setTabFragmentB(myTag);
    }

    public void b_updateText(String selectedDate) {

            getlocation();

            RequestProcessor processor = new RequestProcessor(DayFragment.this);
            processor.getCalenderList(latitude, longitude, selectedDate, selectedDate);
            showLoadingDialog();
    }

    @Override
    public void onGpsSettingStatus(boolean enabled) {

        if (enabled == false) {
            // turnGPSOn();
             gpsTracker.showSettingsAlert();
        } else if(enabled==true)
        {
            getlocation();
        }
    }

    void getlocation() {

        gpsTracker = new GPSTracker(getContext());

        if(gpsTracker.canGetLocation()==true)
        {
            if(gpsTracker.getLatitude()!=0 && gpsTracker.getLongitude()!=0)
            {
                latitude = String.valueOf(gpsTracker.getLocation().getLatitude());
                longitude = String.valueOf(gpsTracker.getLocation().getLongitude());
                //new DownloadJSON().execute();

                java.util.Date todayDate = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = formatter.format(todayDate);

                RequestProcessor processor = new RequestProcessor(DayFragment.this);
                processor.getCalenderList(latitude, longitude, currentDate, currentDate);
                showLoadingDialog();
            }
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

                            list = calenderResponse.getData();

                            Log.e(TAG, "onRequestProcessed: " + list);

                            CalenderModel model = new CalenderModel();
                            int UdvegSize = model.getUdveg().size();

                            dateofApiText.setText(list.get(0).getDate());
                            sunriseTxt.setText(list.get(0).getSunrise());
                            sunsetTxt.setText(list.get(0).getSunset());
                            navTxt.setText(list.get(0).getNavkarsi());
                            porsiTxt.setText(list.get(0).getPorsi());
                            sadhaporsiTxt.setText(list.get(0).getSadhporsi());
                            purimadhaTxt.setText(list.get(0).getPurimaddha());
                            avadhhaTxt.setText(list.get(0).getAvadhha());
                            udvegTxt1.setText(list.get(0).getUdveg().get(0));
                            udvegTxt2.setText(list.get(0).getUdveg().get(1));
                            chalTxt1.setText(list.get(0).getChal().get(0));
                            chalTxt2.setText(list.get(0).getChal().get(1));
                            labhTxt1.setText(list.get(0).getLabh().get(0));
                            labhTxt2.setText(list.get(0).getLabh().get(1));
                            amritTxt1.setText(list.get(0).getAmrit().get(0));
                            amritTxt2.setText(list.get(0).getAmrit().get(1));
                            kaalTxt1.setText(list.get(0).getKaal().get(0));
                            kaalTxt2.setText(list.get(0).getKaal().get(1));
                            shubhTxt1.setText(list.get(0).getShubh().get(0));
                            shubhTxt2.setText(list.get(0).getShubh().get(1));
                            rogTxt1.setText(list.get(0).getRog().get(0));
                            rogTxt2.setText(list.get(0).getRog().get(1));

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

    public void showSettingsAlert(){
        alertDialog = new AlertDialog.Builder(getActivity());

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        });
        alertDialog.setCancelable(false);
        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface
                                        dialog, int which) {
                dialog.cancel();
//                finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}




