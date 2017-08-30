package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.FamilyAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.RelationAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamilyResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ZoneChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ZonePickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.view;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyDetailsFragment extends BaseFragment implements GUICallback {

    private View view = null;

    private RegistrationPojo registrationPojo = null;

    @Bind(R.id.family_head)
    Spinner familyHead;

    @Bind(R.id.relation)
    Spinner relation;

    @Bind(R.id.relationLast)
    Spinner relationLast;

    @Bind(R.id.familyLayout)
    LinearLayout familyLayout;

    @Bind(R.id.family)
    CheckBox family;

    @Bind(R.id.native_zone)
    EditText zone;

    @Bind(R.id.native_sangh_name)
    Spinner localSanghName;

    @Bind(R.id.native_family)
    Spinner basic_residence;

    private String selectedAnchalId = null;
    private String selectedLocalSanghId = null;
    private String selectedRelationId = null;
    private String selectedFamilyId = null;
    String[] descriptionData = {"Basic", "Family", "Personal"};

    public static FamilyDetailsFragment newInstance() {
        return new FamilyDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.family_details_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StateProgressBar step_view = (StateProgressBar) view.findViewById(R.id.step_view);
        step_view.setStateDescriptionData(descriptionData);
        try {
            registrationPojo = getArguments().getParcelable("DATA");
            RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);

            showLoadingDialog();
            requestProcessor.selectFamily(registrationPojo.getLocalSanghId(), registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
            Log.e("----", registrationPojo + "");
        } catch (Exception ex) {
        }
        familyHead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Family relations = (Family) adapterView.getItemAtPosition(i);
                if (relations != null) {
                    selectedFamilyId = relations.getId();
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> typeadapter = ArrayAdapter.createFromResource(getActivity(), R.array.head_of_family_relation, android.R.layout.simple_spinner_item);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        relation.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        typeadapter,
                        R.layout.head_of_family_relation_selection,
                        getActivity()));
        relation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Relations relations = (Relations) adapterView.getItemAtPosition(i);
                if (relations != null) {
                    selectedRelationId = relations.getId();
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        relationLast.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        typeadapter,
                        R.layout.head_of_family_relation_selection,
                        getActivity()));
        relationLast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        family.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    familyLayout.setVisibility(View.VISIBLE);
                } else {
                    familyLayout.setVisibility(View.GONE);
                }
            }
        });

        zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ZoneListResponse zoneListResponse = OfflineData.getZoneList();
                if (zoneListResponse == null) {
                    RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
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
                            RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                            processor.getLocalSanghList(id);
                        }
                    });
                    zonePickerDialog.show();
                }

            }
        });
        localSanghName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                LocalSanghResponse localSanghResponse = OfflineData.getLocalSanghList();
                if (localSanghResponse != null && localSanghResponse.getData() != null) {

                    for (LocalSangh sanghData : localSanghResponse.getData()) {
                        if (sanghData.getBranch_name().equalsIgnoreCase(selected))
                            selectedLocalSanghId = sanghData.getId();
                    }

                }
                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                showLoadingDialog();
                RequestProcessor requestProcessor = new RequestProcessor(new GUICallback() {
                    @Override
                    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
                        hideLoadingDialog();
                        if (guiResponse != null) {
                            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                                if (guiResponse instanceof FamilyResponse) {

                                    FamilyResponse familyResponse = (FamilyResponse) guiResponse;
                                    if (familyResponse != null) {
                                        List<Family> familyList = familyResponse.getFamilies();
                                        String[] datas = new String[familyList.size()];

                                        for (int i = 0; i < familyList.size(); i++) {
                                            datas[i] = familyList.get(i).getCode() + " " + familyList.get(i).getFirst_name();
                                        }

                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                (getActivity(), android.R.layout.simple_spinner_dropdown_item, datas);


                                        basic_residence.setAdapter(
                                                new NothingSelectedSpinnerAdapter(
                                                        adapter,
                                                        R.layout.basic_residence_selection,
                                                        getActivity()));
                                    }
                                }
                            }
                        }
                    }
                });
                requestProcessor.selectFamily(selectedLocalSanghId, registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, new String[]{""});


        localSanghName.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.native_sangh_selection,
                        getActivity()));

        basic_residence.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.basic_residence_selection,
                        getActivity()));

        basic_residence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void showFamilyHeadUi(ArrayList<Family> familyList) {
       /* String[] datas = new String[familyList.size()];

        for (int i = 0; i < familyList.size(); i++) {
            datas[i] = familyList.get(i).getCode() + " " + familyList.get(i).getFirst_name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, datas);


        familyHead.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.family_head_selection,
                        getActivity()));*/

        FamilyAdapter adapter = new FamilyAdapter
                (getActivity(), R.layout.list_item, familyList);


        familyHead.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.head_of_family_selection,
                        getActivity()));
    }

    private void showRelationUi(ArrayList<Relations> relations) {


        RelationAdapter adapter = new RelationAdapter
                (getActivity(), R.layout.list_item, relations);


        relation.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.head_of_family_relation_selection,
                        getActivity()));
    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof FamilyResponse) {

                    FamilyResponse familyResponse = (FamilyResponse) guiResponse;
                    if (familyResponse != null) {
                        showFamilyHeadUi(familyResponse.getFamilies());
                        showRelationUi(familyResponse.getRelations());
                }
                } else if (guiResponse instanceof LocalSanghResponse) {

                    LocalSanghResponse response = (LocalSanghResponse) guiResponse;
                    if (response != null && response.getData() != null && response.getData().size() > 0) {
                        OfflineData.saveLocalSanghResponse(response);
                        String[] datas = new String[response.getData().size()];

                        for (int i = 0; i < response.getData().size(); i++) {
                            datas[i] = response.getData().get(i).getBranch_name();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (getActivity(), android.R.layout.simple_spinner_dropdown_item, datas);


                        localSanghName.setAdapter(
                                new NothingSelectedSpinnerAdapter(
                                        adapter,
                                        R.layout.native_sangh_selection,
                                        getActivity()));


                    }
                }
            }
        }
    }

    @OnClick(R.id.go_ahead)
    public void goAhead() {
        boolean callAPi = true;
        if (familyHead.getSelectedItem() == null) {
            Toast.makeText(getActivity(), "Family head is required", Toast.LENGTH_SHORT).show();
            callAPi = false;
        }

        if (relation.getSelectedItem() == null) {
            Toast.makeText(getActivity(), "relation is required", Toast.LENGTH_SHORT).show();
            callAPi = false;
        }

        if (family.isChecked()) {
            if (zone.getText().toString().length() == 0) {
                zone.setError("zone name is required");
                zone.requestFocus();
                callAPi = false;
            } else {

                zone.setError(null);
                zone.clearFocus();
            }
            if (localSanghName.getSelectedItem() == null) {
                callAPi = false;
                Toast.makeText(getActivity(), "local sangh is required", Toast.LENGTH_SHORT).show();
            }
            if (basic_residence.getSelectedItem() == null) {
                callAPi = false;
                Toast.makeText(getActivity(), "current residence is required", Toast.LENGTH_SHORT).show();
            }

            if (relationLast.getSelectedItem() == null) {
                Toast.makeText(getActivity(), "relation is required", Toast.LENGTH_SHORT).show();
                callAPi = false;
            }
        }


        if (callAPi) {
            Bundle bundle = new Bundle();
            registrationPojo.setFamilyId(selectedFamilyId);
            registrationPojo.setRelationId(selectedRelationId);
            bundle.putParcelable("DATA", registrationPojo);

            PersonalDetailsFragment familyDetailsFragment = PersonalDetailsFragment.newInstance();
            familyDetailsFragment.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.signUpContainer, familyDetailsFragment);
            //fragmentTransaction.addToBackStack("PersonalDetailsFragment");
            fragmentTransaction.commit();


        }

    }
}

