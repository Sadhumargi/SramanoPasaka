package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.ClickToSelectEditText;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;
import com.sramanopasaka.sipanionline.sadhumargi.model.Salutation;
import com.sramanopasaka.sipanionline.sadhumargi.model.Zone;

import java.util.ArrayList;
import java.util.List;

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

  /*  @Bind(R.id.zone)
    EditText zone;
*/

    @Bind(R.id.zone)
    ClickToSelectEditText<Zone> anchalTxt;

    @Bind(R.id.local_sangh_name)
    ClickToSelectEditText<LocalSangh> localSanghName;


    @Bind(R.id.currentResidence)
    ClickToSelectEditText<LocalSangh> currentResidence;

    @Bind(R.id.city)
    ClickToSelectEditText<City> sCity;

    @Bind(R.id.district)
    EditText district;

    /*@Bind(R.id.radiogrp)
    RadioGroup radiogrp;*/

    @Bind(R.id.textInputLayoutAnchal)
    TextInputLayout textInputLayoutAnchal;

    @Bind(R.id.textInputLayoutFirstName)
    TextInputLayout textInputLayoutFirstName;

    @Bind(R.id.textInputLayoutLastName)
    TextInputLayout textInputLayoutLastName;

    @Bind(R.id.textInputLayoutLocalSangh)
    TextInputLayout textInputLayoutLocalSangh;

    @Bind(R.id.textInputLayoutCity)
    TextInputLayout textInputLayoutCity;

    @Bind(R.id.textInputLayoutCurrentResident)
    TextInputLayout textInputLayoutCurrentResident;

    @Bind(R.id.textInputLayoutSalutation)
    TextInputLayout textInputLayoutSalutation;

    @Bind(R.id.salutation)
    ClickToSelectEditText<Salutation> salutation;

    List<Salutation> salutationList = new ArrayList<>();


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

        salutationList.add(new Salutation("Mr.","Shree"));
        salutationList.add(new Salutation("Mrs.","Shrimati"));
        salutationList.add(new Salutation("Mis.","Kumari"));

        salutation.setItems(salutationList);
        salutation.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Salutation>() {
            @Override
            public void onItemSelectedListener(Salutation item, int selectedIndex) {
                salutation.setText(item.getLabel());
            }

            @Override
            public void onNoDataSet() {

            }
        });


        ZoneListResponse zoneListResponse = OfflineData.getZoneList();
        if (zoneListResponse == null) {
            RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
            processor.selectZoneList();
            showLoadingDialog();
        } else {
            anchalTxt.setItems(zoneListResponse.getZoneList());
            anchalTxt.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Zone>() {
                @Override
                public void onItemSelectedListener(Zone item, int selectedIndex) {
                    anchalTxt.setText(item.getName());
                    localSanghName.setText("");
                    currentResidence.setText("");
                    selectedAnchalId = item.getAnchal_id();

                    RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                    processor.getLocalSanghList(item.getAnchal_id());
                }

                @Override
                public void onNoDataSet() {

                }
            });
        }

        localSanghName.setItems(null);
        localSanghName.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<LocalSangh>() {
            @Override
            public void onItemSelectedListener(LocalSangh item, int selectedIndex) {
                localSanghName.setText(item.getBranch_name());
                selectedLocalSanghId = item.getId();
            }

            @Override
            public void onNoDataSet() {
                new CustomToast().showErrorToast(getActivity(), view,
                        "Please select anchal first");
            }
        });


        currentResidence.setItems(null);
        currentResidence.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<LocalSangh>() {
            @Override
            public void onItemSelectedListener(LocalSangh item, int selectedIndex) {
                currentResidence.setText(item.getBranch_name());
            }

            @Override
            public void onNoDataSet() {
                new CustomToast().showErrorToast(getActivity(), view,
                        "Please select anchal first");
            }
        });






        CityListResponse cityListResponse = OfflineData.getCityList();
        if (cityListResponse != null) {
            sCity.setItems(cityListResponse.getCityList());
            sCity.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<City>() {
                @Override
                public void onItemSelectedListener(City item, int selectedIndex) {
                    sCity.setText(item.getCity_name());
                    textInputLayoutCity.setError(null);
                }

                @Override
                public void onNoDataSet() {

                }
            });
        } else {
            showLoadingDialog();
            RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
            processor.getCityList();
        }
    }

    @OnClick(R.id.goAhead)
    public void goAhead() {
        boolean callAPi = true;

        if (salutation.getText().toString().length() == 0) {
            textInputLayoutSalutation.setError("salutation is required");
            salutation.requestFocus();
            callAPi = false;
        } else {
            salutation.clearFocus();
            textInputLayoutSalutation.setError(null);
        }


        if (sCity.getText().toString().length() == 0) {
            textInputLayoutCity.setError("City name is required");
            sCity.requestFocus();
            callAPi = false;
        } else {
            sCity.clearFocus();
            textInputLayoutCity.setError(null);
        }

        if (lastName.getText().toString().length() == 0) {
            textInputLayoutLastName.setError("Last name is required");
            lastName.requestFocus();
            callAPi = false;
        } else {

            textInputLayoutLastName.setError(null);
            lastName.clearFocus();
        }

        if (firstName.getText().toString().length() == 0) {
            textInputLayoutFirstName.setError("First name is required");
            firstName.requestFocus();
            callAPi = false;
        } else {

            textInputLayoutFirstName.setError(null);
            firstName.clearFocus();
        }

        if (anchalTxt.getText().toString().length() == 0) {
            textInputLayoutAnchal.setError("Anchal name is required");
            anchalTxt.requestFocus();
            callAPi = false;
        } else {

            textInputLayoutAnchal.setError(null);
            anchalTxt.clearFocus();
        }
        if (localSanghName.getText().toString().length() == 0) {
            textInputLayoutLocalSangh.setError("localSangh name is required");
            localSanghName.requestFocus();
            callAPi = false;
        } else {

            textInputLayoutLocalSangh.setError(null);
            localSanghName.clearFocus();
        }

        if (currentResidence.getText().toString().length() == 0) {
            textInputLayoutCurrentResident.setError("localSangh name is required");
            currentResidence.requestFocus();
            callAPi = false;
        } else {

            textInputLayoutCurrentResident.setError(null);
            currentResidence.clearFocus();
        }

       /* int selectedId = radiogrp.getCheckedRadioButtonId();
        if (selectedId == -1 && callAPi) {
            radiogrp.requestFocus();
            radiogrp.requestFocusFromTouch();
            new CustomToast().showErrorToast(getActivity(),view,"Please select the salutation");
            callAPi = false;
        }*/

        if (callAPi && !localSanghName.getText().toString().equalsIgnoreCase(currentResidence.getText().toString())) {
            callAPi = false;
            new CustomToast().showErrorToast(getActivity(),view,"current residence and local sangh should be same");
        }

        if (callAPi) {
            RegistrationPojo registrationPojo = new RegistrationPojo(selectedAnchalId, selectedLocalSanghId, firstName.getText().toString(), lastName.getText().toString(), salutation.getText().toString(), sCity.getText().toString(), district.getText().toString());
            Bundle bundle = new Bundle();
            bundle.putParcelable("DATA", registrationPojo);

            FamilyDetailsFragment familyDetailsFragment = FamilyDetailsFragment.newInstance();
            familyDetailsFragment.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.signUpContainer, familyDetailsFragment);
            fragmentTransaction.addToBackStack("FamilyDetailsFragment");
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
                        sCity.setItems(stateListResponse.getCityList());
                        sCity.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<City>() {
                            @Override
                            public void onItemSelectedListener(City item, int selectedIndex) {
                                sCity.setText(item.getCity_name());
                                textInputLayoutCity.setError(null);
                            }

                            @Override
                            public void onNoDataSet() {

                            }
                        });
                    }
                } else if (guiResponse instanceof ZoneListResponse) {

                    ZoneListResponse response = (ZoneListResponse) guiResponse;
                    if (response != null && response.getZoneList() != null && response.getZoneList().size() > 0) {
                        OfflineData.saveZoneResponse(response);
                        new CustomToast().showInformationToast(getActivity(), view, "Please select anchal");

                        anchalTxt.setItems(response.getZoneList());
                        anchalTxt.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Zone>() {
                            @Override
                            public void onItemSelectedListener(Zone item, int selectedIndex) {
                                anchalTxt.setText(item.getName());
                                localSanghName.setText("");
                                currentResidence.setText("");
                                selectedAnchalId = item.getAnchal_id();

                                RequestProcessor processor = new RequestProcessor(GeneralDetailsFragment.this);
                                processor.getLocalSanghList(item.getAnchal_id());
                            }

                            @Override
                            public void onNoDataSet() {

                            }
                        });


                    }
                } else if (guiResponse instanceof LocalSanghResponse) {

                    LocalSanghResponse response = (LocalSanghResponse) guiResponse;
                    if (response != null && response.getData() != null && response.getData().size() > 0) {
                        OfflineData.saveLocalSanghResponse(response);
                        localSanghName.setItems(response.getData());
                        currentResidence.setItems(response.getData());

                    }
                }

            }else{
                new CustomToast().showErrorToast(getActivity(),view,"Please check your internet connection");
            }

        }
    }
}
