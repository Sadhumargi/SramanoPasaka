package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamilyResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.NothingSelectedSpinnerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.FamilyHeadChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.listener.RelationsChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.listener.SanghChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ZoneChangeListener;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.LocalSangh;
import com.sramanopasaka.sipanionline.sadhumargi.model.RegistrationPojo;
import com.sramanopasaka.sipanionline.sadhumargi.model.Relations;
import com.sramanopasaka.sipanionline.sadhumargi.utils.FamilyHeadPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.RelationPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.SanghPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ZonePickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyDetailsFragment extends BaseFragment implements GUICallback {

    private View view = null;

    private FamilyHeadPickerDialog familyHeadPickerDialog = null;
    private FamilyHeadPickerDialog nativefamilyPickerDialog = null;
    private RelationPickerDialog relationPickerDialog = null;
    private RelationPickerDialog nativerelationPickerDialog = null;
    private RegistrationPojo registrationPojo = null;

    @Bind(R.id.family_head)
    EditText familyHead;

    @Bind(R.id.relation)
    EditText relation;

    @Bind(R.id.relationLast)
    EditText relationLast;

    @Bind(R.id.familyLayout)
    LinearLayout familyLayout;

    @Bind(R.id.family)
    CheckBox family;

    @Bind(R.id.native_zone)
    EditText nativeZone;

    @Bind(R.id.native_sangh_name)
    EditText nativeSanghName;

    @Bind(R.id.native_family)
    EditText basic_residence;

    private String selectedNativeZoneId = null;
    private String selectedNativeSanghId = null;
    private String selectedNativeFamilyId = null;
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
        /*familyHead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        });*/
        familyHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(familyHeadPickerDialog!=null) {
                    familyHeadPickerDialog.setCancelable(true);
                    familyHeadPickerDialog.show();
                }else{
                    try {
                        registrationPojo = getArguments().getParcelable("DATA");
                        RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);

                        showLoadingDialog();
                        requestProcessor.selectFamily(registrationPojo.getLocalSanghId(), registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
                        Log.e("----", registrationPojo + "");
                    } catch (Exception ex) {
                    }
                }
            }
        });

       relation.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(relationPickerDialog!=null) {
                   relationPickerDialog.setCancelable(true);
                   relationPickerDialog.show();
               }else{
                   try {
                       registrationPojo = getArguments().getParcelable("DATA");
                       RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);

                       showLoadingDialog();
                       requestProcessor.selectFamily(registrationPojo.getLocalSanghId(), registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
                       Log.e("----", registrationPojo + "");
                   } catch (Exception ex) {
                   }
               }
           }
       });
        relationLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nativerelationPickerDialog!=null) {
                    nativerelationPickerDialog.setCancelable(true);
                    nativerelationPickerDialog.show();
                }else{
                    try {
                        registrationPojo = getArguments().getParcelable("DATA");
                        RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);

                        showLoadingDialog();
                        requestProcessor.selectFamily(selectedNativeSanghId, registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
                        Log.e("----", registrationPojo + "");
                    } catch (Exception ex) {
                    }
                }
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

        nativeZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ZoneListResponse zoneListResponse = OfflineData.getZoneList();
                if (zoneListResponse == null) {
                    RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                    processor.selectZoneList();
                } else {
                    final ZonePickerDialog zonePickerDialog = new ZonePickerDialog(getActivity(), zoneListResponse.getZoneList(),getString(R.string.Native_Zone));
                    zonePickerDialog.setZoneChangeListner(new ZoneChangeListener() {
                        @Override
                        public void onZoneSelected(String zoneTxt, String id) {
                            nativeZone.setText(zoneTxt);
                            selectedNativeZoneId = id;
                            nativeZone.setError(null);
                            zonePickerDialog.dismiss();
                            RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                            processor.getLocalSanghList(id);
                        }
                    });
                    zonePickerDialog.show();
                }

            }
        });

        nativeSanghName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(selectedNativeZoneId)) {
                    LocalSanghResponse localSanghResponse = OfflineData.getLocalSanghList();
                    if (localSanghResponse != null && localSanghResponse.getData() != null) {
                        final SanghPickerDialog sanghPickerDialog = new SanghPickerDialog(getActivity(), localSanghResponse.getData(),getString(R.string.Sangh_Name));
                        sanghPickerDialog.setZoneChangeListner(new SanghChangeListener() {
                            @Override
                            public void onSanghSelected(String sangh, String id) {
                                nativeSanghName.setText(sangh);
                                selectedNativeSanghId = id;
                                sanghPickerDialog.dismiss();

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

                                                        if(familyResponse.getFamilies()!=null) {
                                                            nativefamilyPickerDialog = new FamilyHeadPickerDialog(getActivity(), familyResponse.getFamilies(),getString(R.string.Native_Zone));
                                                            nativefamilyPickerDialog.setFamilyHeadChangeListner(new FamilyHeadChangeListener() {
                                                                @Override
                                                                public void onFamilyHeadSelected(String family, String id) {
                                                                    basic_residence.setText(family);
                                                                    selectedNativeFamilyId = id;
                                                                    nativefamilyPickerDialog.dismiss();
                                                                }
                                                            });
                                                        }

                                                        if(familyResponse.getRelations()!=null){
                                                            nativerelationPickerDialog = new RelationPickerDialog(getActivity(),familyResponse.getRelations(),getString(R.string.Relation));
                                                            nativerelationPickerDialog.setRelationsChangeListner(new RelationsChangeListener() {
                                                                @Override
                                                                public void onRelationSelected(String family, String id) {
                                                                    relationLast.setText(family);
                                                                    nativerelationPickerDialog.dismiss();
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                                requestProcessor.selectFamily(selectedNativeSanghId, registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
                            }
                        });
                        sanghPickerDialog.show();

                    } else {
                        if (!TextUtils.isEmpty(selectedNativeZoneId)) {
                            RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                            processor.getLocalSanghList(selectedNativeZoneId);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, new String[]{""});




        basic_residence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nativefamilyPickerDialog!=null) {
                    nativefamilyPickerDialog.setCancelable(true);
                    nativefamilyPickerDialog.show();
                }else{
                    try {
                        registrationPojo = getArguments().getParcelable("DATA");
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


                                                if(familyResponse.getFamilies()!=null) {
                                                    nativefamilyPickerDialog = new FamilyHeadPickerDialog(getActivity(), familyResponse.getFamilies(),getString(R.string.Native_Zone));
                                                    nativefamilyPickerDialog.setFamilyHeadChangeListner(new FamilyHeadChangeListener() {
                                                        @Override
                                                        public void onFamilyHeadSelected(String family, String id) {
                                                            basic_residence.setText(family);
                                                            selectedNativeFamilyId = id;
                                                            nativefamilyPickerDialog.dismiss();
                                                        }
                                                    });
                                                }

                                                if(familyResponse.getRelations()!=null){
                                                    nativerelationPickerDialog = new RelationPickerDialog(getActivity(),familyResponse.getRelations(),getString(R.string.Relation));
                                                    nativerelationPickerDialog.setRelationsChangeListner(new RelationsChangeListener() {
                                                        @Override
                                                        public void onRelationSelected(String family, String id) {
                                                            relationLast.setText(family);
                                                            nativerelationPickerDialog.dismiss();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                        requestProcessor.selectFamily(selectedNativeSanghId, registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
                    } catch (Exception ex) {
                    }
                }
            }
        });

    }

    private void showFamilyHeadUi(ArrayList<Family> familyList) {

        familyHeadPickerDialog = new FamilyHeadPickerDialog(getActivity(),familyList,getString(R.string.HeadofFamil));
        familyHeadPickerDialog.setFamilyHeadChangeListner(new FamilyHeadChangeListener() {
            @Override
            public void onFamilyHeadSelected(String family, String id) {
                familyHead.setText(family);
                selectedFamilyId = id;
                familyHeadPickerDialog.dismiss();
            }
        });


    }

    private void showRelationUi(final ArrayList<Relations> relations) {

        relationPickerDialog = new RelationPickerDialog(getActivity(),relations,getString(R.string.Relation));
        relationPickerDialog.setRelationsChangeListner(new RelationsChangeListener() {
            @Override
            public void onRelationSelected(String family, String id) {
                relation.setText(family);
                selectedRelationId = id;
                relationPickerDialog.dismiss();
            }
        });
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



                    }
                }
            }
        }
    }

    @OnClick(R.id.go_ahead)
    public void goAhead() {
        boolean callAPi = true;
        if (TextUtils.isEmpty(familyHead.getText().toString()) ){
            new CustomToast().Show_Toast(getActivity(), view,
                    "Family head is required");
            callAPi = false;
        }

        if (TextUtils.isEmpty(relation.getText().toString()) && callAPi) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "relation is required");
            callAPi = false;
        }

        if (family.isChecked()  && callAPi) {
            if (nativeZone.getText().toString().length() == 0) {
                nativeZone.setError("zone name is required");
                nativeZone.requestFocus();
                callAPi = false;
            } else {

                nativeZone.setError(null);
                nativeZone.clearFocus();
            }
            if (TextUtils.isEmpty(nativeSanghName.getText().toString())  && callAPi) {
                callAPi = false;
                new CustomToast().Show_Toast(getActivity(), view,
                        "local sangh is required");
            }
            if (TextUtils.isEmpty(basic_residence.getText().toString()) && callAPi) {
                callAPi = false;
                new CustomToast().Show_Toast(getActivity(), view,
                        "current residence is required");
            }

            if (TextUtils.isEmpty(relationLast.getText().toString()) && callAPi) {
                new CustomToast().Show_Toast(getActivity(), view,
                        "relation is required");
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
            fragmentTransaction.addToBackStack("PersonalDetailsFragment");
            fragmentTransaction.commit();


        }

    }
}

