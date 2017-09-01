package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sramanopasaka.sipanionline.sadhumargi.ProfileActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.request.LoginRequest;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamilyResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.RegisterProgressUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.SanghChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.listener.StateChangeListner;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ZoneChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;
import com.sramanopasaka.sipanionline.sadhumargi.model.SanghData;
import com.sramanopasaka.sipanionline.sadhumargi.utils.CityPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.PreferenceUtils;
import com.sramanopasaka.sipanionline.sadhumargi.utils.SanghPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.StatePickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ZonePickerDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name    :   pranavjdev
 * Date   : 8/29/17
 * Email : pranavjaydev@gmail.com
 */

public class GeneralDetailsFragment extends BaseFragment implements GUICallback {

    String[] descriptionData = {"Basic", "Family", "Personal"};
    @Bind(R.id.first_name)
    EditText firstName;

    @Bind(R.id.last_name)
    EditText lastName;

    @Bind(R.id.zone)
    EditText zone;

    @Bind(R.id.local_sangh_name)
    EditText localSanghName;


    @Bind(R.id.currentResidence)
    EditText currentResidence;

    @Bind(R.id.city)
    EditText sCity;

    @Bind(R.id.district)
    EditText district;

    @Bind(R.id.radiogrp)
    RadioGroup radiogrp;


    private View view = null;

    private String selectedAnchalId = null;
    private String selectedLocalSanghId = null;


    public static GeneralDetailsFragment newInstance() {
        return new GeneralDetailsFragment();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StateProgressBar step_view = (StateProgressBar) view.findViewById(R.id.step_view);
        step_view.setStateDescriptionData(descriptionData);

        zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ZoneListResponse zoneListResponse = OfflineData.getZoneList();
                if (zoneListResponse == null) {
                    RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                    processor.selectZoneList();
                } else {
                    final ZonePickerDialog zonePickerDialog = new ZonePickerDialog(getActivity(), zoneListResponse.getZoneList());
                    zonePickerDialog.setZoneChangeListner(new ZoneChangeListener() {
                        @Override
                        public void onZoneSelected(String zoneTxt, String id) {
                            zone.setText(zoneTxt);
                            selectedAnchalId = id;
                            zone.setError(null);
                            zonePickerDialog.dismiss();
                            RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                            processor.getLocalSanghList(id);
                        }
                    });
                    zonePickerDialog.show();
                }

            }
        });

        localSanghName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(selectedAnchalId)) {
                    LocalSanghResponse localSanghResponse = OfflineData.getLocalSanghList();
                    if (localSanghResponse != null && localSanghResponse.getData() != null) {
                        final SanghPickerDialog sanghPickerDialog = new SanghPickerDialog(getActivity(), localSanghResponse.getData());
                        sanghPickerDialog.setZoneChangeListner(new SanghChangeListener() {
                            @Override
                            public void onSanghSelected(String sangh, String id) {
                                localSanghName.setText(sangh);
                                selectedLocalSanghId = id;
                                sanghPickerDialog.dismiss();
                            }
                        });
                        sanghPickerDialog.show();

                    } else {
                        if (!TextUtils.isEmpty(selectedAnchalId)) {
                            RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                            processor.getLocalSanghList(selectedAnchalId);
                        } else {
                            Log.e("----", "please select achal");
                        }
                    }
                }else{
                    new CustomToast().Show_Toast(getActivity(), view,
                            "Please select anchal first");
                }

            }
        });

        currentResidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(selectedAnchalId)) {
                    LocalSanghResponse localSanghResponse = OfflineData.getLocalSanghList();
                    if (localSanghResponse != null && localSanghResponse.getData() != null) {
                        final SanghPickerDialog sanghPickerDialog = new SanghPickerDialog(getActivity(), localSanghResponse.getData());
                        sanghPickerDialog.setZoneChangeListner(new SanghChangeListener() {
                            @Override
                            public void onSanghSelected(String sangh, String id) {
                                currentResidence.setText(sangh);
                                sanghPickerDialog.dismiss();
                            }
                        });
                        sanghPickerDialog.show();

                    } else {
                        if (!TextUtils.isEmpty(selectedAnchalId)) {
                            RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                            processor.getLocalSanghList(selectedAnchalId);
                        } else {
                            Log.e("----", "please select achal");
                        }
                    }
                }else{
                    new CustomToast().Show_Toast(getActivity(), view,
                            "Please select anchal first");
                }

            }
        });

       /* localSanghName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String selected = (String) adapterView.getItemAtPosition(i);
                LocalSanghResponse localSanghResponse = OfflineData.getLocalSanghList();
                if(localSanghResponse!=null && localSanghResponse.getData()!=null){

                    for(LocalSangh sanghData : localSanghResponse.getData()){
                        if(sanghData.getBranch_name().equalsIgnoreCase(selected))
                            selectedLocalSanghId = sanghData.getId();
                    }

                }
                try {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                }catch (Exception ex ){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
      /*  currentResidence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                }catch (Exception ex ){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        sCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityListResponse cityListResponse = OfflineData.getCityList();
                if (cityListResponse != null) {
                    final CityPickerDialog statePickerDialog = new CityPickerDialog(getActivity(), cityListResponse.getCityList());
                    statePickerDialog.setStateChangeListner(new StateChangeListner() {
                        @Override
                        public void onStateSelected(String state) {
                            sCity.setText(state);
                            sCity.setError(null);
                            statePickerDialog.dismiss();
                        }
                    });
                    statePickerDialog.show();
                } else {
                    showLoadingDialog();
                    RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                    processor.getCityList();
                }
            }
        });


      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, new String[]{""});

*/
     /*   localSanghName.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.local_sangh_selection,
                        getActivity()));
*/

       /* currentResidence.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.current_resident_selection,
                        getActivity()));
*/

    }

    @OnClick(R.id.goAhead)
    public void goAhead() {
        boolean callAPi = true;
        if (sCity.getText().toString().length() == 0) {
            sCity.setError("City name is required");
            sCity.requestFocus();
            callAPi = false;
        } else {

            sCity.setError(null);
        }

        if (lastName.getText().toString().length() == 0) {
            lastName.setError("Last name is required");
            lastName.requestFocus();
            callAPi = false;
        } else {

            lastName.setError(null);
            lastName.clearFocus();
        }

        if (firstName.getText().toString().length() == 0) {
            firstName.setError("First name is required");
            firstName.requestFocus();
            callAPi = false;
        } else {

            firstName.setError(null);
            firstName.clearFocus();
        }

        if (zone.getText().toString().length() == 0) {
            zone.setError("zone name is required");
            zone.requestFocus();
            callAPi = false;
        } else {

            zone.setError(null);
            zone.clearFocus();
        }
        if (localSanghName.getText().toString().length() == 0) {
            localSanghName.setError("localSangh name is required");
            localSanghName.requestFocus();
            callAPi = false;
        } else {

            localSanghName.setError(null);
            localSanghName.clearFocus();
        }

        int selectedId = radiogrp.getCheckedRadioButtonId();
        if (selectedId == -1 && callAPi) {
            radiogrp.requestFocus();
            radiogrp.requestFocusFromTouch();
            Toast.makeText(getActivity(), "Please select the salution", Toast.LENGTH_SHORT).show();
            callAPi = false;
        }

        if(callAPi && !localSanghName.getText().toString().equalsIgnoreCase(currentResidence.getText().toString())){
            callAPi = false;
            Toast.makeText(getActivity(), "current residence and local sangh should be same", Toast.LENGTH_SHORT).show();
        }

        if (callAPi) {
            RegistrationPojo registrationPojo = new RegistrationPojo(selectedAnchalId, selectedLocalSanghId, firstName.getText().toString(), lastName.getText().toString(), ((RadioButton) view.findViewById(selectedId)).getText().toString(), sCity.getText().toString(), district.getText().toString());
            Bundle bundle = new Bundle();
            bundle.putParcelable("DATA", registrationPojo);

            FamilyDetailsFragment familyDetailsFragment = FamilyDetailsFragment.newInstance();
            familyDetailsFragment.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.signUpContainer, familyDetailsFragment);
          //  fragmentTransaction.addToBackStack("FamilyDetailsFragment");
            fragmentTransaction.commit();


        }

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {

                if (guiResponse instanceof CityListResponse) {
                    CityListResponse stateListResponse = (CityListResponse) guiResponse;
                    if (stateListResponse != null) {
                        OfflineData.saveCityResponse(stateListResponse);
                        final CityPickerDialog statePickerDialog = new CityPickerDialog(getActivity(), stateListResponse.getCityList());
                        statePickerDialog.setStateChangeListner(new StateChangeListner() {
                            @Override
                            public void onStateSelected(String state) {
                                sCity.setText(state);
                                sCity.setError(null);
                                statePickerDialog.dismiss();
                            }
                        });
                        statePickerDialog.show();
                    }
                } else if (guiResponse instanceof ZoneListResponse) {

                    ZoneListResponse response = (ZoneListResponse) guiResponse;
                    if (response != null && response.getZoneList() != null && response.getZoneList().size() > 0) {
                        OfflineData.saveZoneResponse(response);

                        final ZonePickerDialog zonePickerDialog = new ZonePickerDialog(getActivity(), response.getZoneList());
                        zonePickerDialog.setZoneChangeListner(new ZoneChangeListener() {
                            @Override
                            public void onZoneSelected(String zoneTxt, String id) {
                                zone.setText(zoneTxt);
                                zone.setError(null);
                                zonePickerDialog.dismiss();
                                selectedAnchalId = id;

                                RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                                processor.getLocalSanghList(id);


                            }
                        });
                        zonePickerDialog.show();

                           /* String[] datas = new String[response.getZoneList().size()];

                            for (int i = 0; i < response.getZoneList().size(); i++) {
                                datas[i] = response.getZoneList().get(i).getName();
                            }


                            *//*ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (getActivity(), android.R.layout.simple_spinner_dropdown_item, datas);
                            anchal.setAdapter(adapter);*/


                    }
                } else if (guiResponse instanceof LocalSanghResponse) {

                    LocalSanghResponse response = (LocalSanghResponse) guiResponse;
                    if (response != null && response.getData() != null && response.getData().size() > 0) {
                        OfflineData.saveLocalSanghResponse(response);
                       /* String[] datas = new String[response.getData().size()];

                        for (int i = 0; i < response.getData().size(); i++) {
                            datas[i] = response.getData().get(i).getBranch_name();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (getActivity(), android.R.layout.simple_spinner_dropdown_item, datas);


                        localSanghName.setAdapter(
                                new NothingSelectedSpinnerAdapter(
                                        adapter,
                                        R.layout.local_sangh_selection,
                                        getActivity()));


                        currentResidence.setAdapter(
                                new NothingSelectedSpinnerAdapter(
                                        adapter,
                                        R.layout.current_resident_selection,
                                        getActivity()));*/


                    }
                }

            }

        }
    }
}
