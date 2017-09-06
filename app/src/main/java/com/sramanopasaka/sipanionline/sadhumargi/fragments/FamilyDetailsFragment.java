package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.FamilyResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LocalSanghResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.ZoneListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.ClickToSelectEditText;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
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
import com.sramanopasaka.sipanionline.sadhumargi.model.Zone;
import com.sramanopasaka.sipanionline.sadhumargi.utils.FamilyHeadPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.RelationPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.SanghPickerDialog;
import com.sramanopasaka.sipanionline.sadhumargi.utils.ZonePickerDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sipani001 on 29/8/17.
 */

public class FamilyDetailsFragment extends BaseFragment implements GUICallback {

    private View view = null;

    private RegistrationPojo registrationPojo = null;

    @Bind(R.id.family_head)
    ClickToSelectEditText<Family> familyHead;

    @Bind(R.id.textInputLayoutHeadOfFamily)
    TextInputLayout textInputLayoutHeadOfFamily;

    @Bind(R.id.textInputLayoutRelations)
    TextInputLayout textInputLayoutRelations;

    @Bind(R.id.relation)
    ClickToSelectEditText<Relations> relation;

    @Bind(R.id.relationLast)
    ClickToSelectEditText<Relations> relationLast;

    @Bind(R.id.familyLayout)
    LinearLayout familyLayout;

    @Bind(R.id.family)
    CheckBox family;

    @Bind(R.id.native_zone)
    ClickToSelectEditText nativeZone;

    @Bind(R.id.textInputLayoutNativeZone)
    TextInputLayout textInputLayoutNativeZone;

    @Bind(R.id.textInputLayoutNativeSangh)
    TextInputLayout textInputLayoutNativeSangh;

    @Bind(R.id.textInputLayoutNativeFamily)
    TextInputLayout textInputLayoutNativeFamily;

    @Bind(R.id.native_sangh_name)
    ClickToSelectEditText<LocalSangh> nativeSanghName;

    @Bind(R.id.textInputLayoutNativeRelations)
    TextInputLayout textInputLayoutNativeRelations;

    @Bind(R.id.native_family)
    ClickToSelectEditText<Family> basic_residence;

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
        showFamilyHeadUi(null);
        showRelationUi(null);
        showNativeFamilyHeadUi(null);
        showNativeRelationUi(null);
        try {
            registrationPojo = getArguments().getParcelable("DATA");
            RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);

            showLoadingDialog();
            requestProcessor.selectFamily(registrationPojo.getLocalSanghId(), registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
            Log.e("----", registrationPojo + "");
        } catch (Exception ex) {
        }

        family.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    familyLayout.setVisibility(View.VISIBLE);
                    ZoneListResponse zoneListResponse = OfflineData.getZoneList();
                    if (zoneListResponse == null) {
                        RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                        processor.selectZoneList();
                    } else {

                        nativeZone.setItems(zoneListResponse.getZoneList());
                        nativeZone.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Zone>() {
                            @Override
                            public void onItemSelectedListener(Zone item, int selectedIndex) {
                                nativeZone.setText(item.getLabel());
                                nativeSanghName.setText("");
                                basic_residence.setText("");
                                selectedNativeZoneId = item.getAnchal_id();
                                nativeZone.setError(null);
                                showLoadingDialog();
                                RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                                processor.getLocalSanghList(item.getAnchal_id());
                                textInputLayoutNativeZone.setError(null);
                                nativeZone.clearFocus();
                                nativeZone.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                            }

                            @Override
                            public void onNoDataSet() {
                                showLoadingDialog();
                                RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                                processor.selectZoneList();
                            }
                        });

                    }

                } else {
                    familyLayout.setVisibility(View.GONE);
                }
            }
        });

       /* nativeZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ZoneListResponse zoneListResponse = OfflineData.getZoneList();
                if (zoneListResponse == null) {
                    RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                    processor.selectZoneList();
                } else {
                    final ZonePickerDialog zonePickerDialog = new ZonePickerDialog(getActivity(), zoneListResponse.getZoneList(), getString(R.string.Native_Zone));
                    zonePickerDialog.setZoneChangeListner(new ZoneChangeListener() {
                        @Override
                        public void onZoneSelected(String zoneTxt, String id) {
                            nativeZone.setText(zoneTxt);
                            nativeSanghName.setText("");
                            basic_residence.setText("");
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
        });*/

        nativeSanghName.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<LocalSangh>() {
            @Override
            public void onItemSelectedListener(LocalSangh item, int selectedIndex) {

            }

            @Override
            public void onNoDataSet() {

                new CustomToast().showErrorToast(getActivity(),view,"Please select native zone first");
            }
        });
          nativeSanghName.setItems(null);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_dropdown_item, new String[]{""});


    }

    private void showFamilyHeadUi(ArrayList<Family> familyList) {



        familyHead.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Family>() {
            @Override
            public void onItemSelectedListener(Family item, int selectedIndex) {
                familyHead.setText(item.getLabel());
                selectedFamilyId = item.getId();
                textInputLayoutHeadOfFamily.setError(null);
                familyHead.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
            }

            @Override
            public void onNoDataSet() {
                new CustomToast().showErrorToast(getActivity(), view, "No families associated with this");
                RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);
                showLoadingDialog();
                requestProcessor.selectFamily(registrationPojo.getLocalSanghId(), registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
            }
        });
        familyHead.setItems(familyList);

    }

    private void showRelationUi(final ArrayList<Relations> relations) {



        relation.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Relations>() {
            @Override
            public void onItemSelectedListener(Relations item, int selectedIndex) {
                relation.setText(item.getRelation());
                selectedRelationId = item.getId();
                textInputLayoutRelations.setError(null);
                relation.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                textInputLayoutRelations.setError(null);
            }

            @Override
            public void onNoDataSet() {
                new CustomToast().showErrorToast(getActivity(), view, "No relations found");
                RequestProcessor requestProcessor = new RequestProcessor(FamilyDetailsFragment.this);
                showLoadingDialog();
                requestProcessor.selectFamily(registrationPojo.getLocalSanghId(), registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
            }
        });
        relation.setItems(relations);

    }


    private void showNativeFamilyHeadUi(ArrayList<Family> familyList) {



        basic_residence.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Family>() {
            @Override
            public void onItemSelectedListener(Family item, int selectedIndex) {
                basic_residence.setText(item.getLabel());
                selectedNativeFamilyId = item.getId();
                textInputLayoutNativeFamily.setError(null);
                basic_residence.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
            }

            @Override
            public void onNoDataSet() {

                new CustomToast().showErrorToast(getActivity(),view,"Please select local sangh first");
            }
        });
        basic_residence.setItems(familyList);

    }

    private void showNativeRelationUi(final ArrayList<Relations> relations) {



        relationLast.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<Relations>() {
            @Override
            public void onItemSelectedListener(Relations item, int selectedIndex) {
                relationLast.setText(item.getLabel());
                textInputLayoutNativeRelations.setError(null);
                relationLast.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
            }

            @Override
            public void onNoDataSet() {
                new CustomToast().showErrorToast(getActivity(),view,"Please select family first");
            }
        });
        relationLast.setItems(relations);

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
                        nativeSanghName.setItems(response.getData());
                        nativeSanghName.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<LocalSangh>() {
                            @Override
                            public void onItemSelectedListener(LocalSangh item, int selectedIndex) {

                                nativeSanghName.setText(item.getLabel());
                                selectedNativeSanghId = item.getId();
                                nativeSanghName.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                                textInputLayoutNativeSangh.setError(null);

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

                                                        if (familyResponse.getFamilies() != null) {



                                                            showNativeFamilyHeadUi(familyResponse.getFamilies());

                                                        }

                                                        if (familyResponse.getRelations() != null) {
                                                            showNativeRelationUi(familyResponse.getRelations());

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                });
                                requestProcessor.selectFamily(selectedNativeSanghId, registrationPojo.getFirstName(), registrationPojo.getLastName(), registrationPojo.getCity());
                            }

                            @Override
                            public void onNoDataSet() {
                                showLoadingDialog();
                                RequestProcessor processor = new RequestProcessor(FamilyDetailsFragment.this);
                                processor.selectZoneList();
                            }
                        });

                    }
                }
            } else {
                new CustomToast().showErrorToast(getActivity(), view, "Please check your internet connection");
                showFamilyHeadUi(null);
            }
        } else {
            new CustomToast().showErrorToast(getActivity(), view, "Please check your internet connection");
        }
    }

    @OnClick(R.id.go_ahead)
    public void goAhead() {
        boolean callAPi = true;

        if (TextUtils.isEmpty(familyHead.getText().toString())) {
            textInputLayoutHeadOfFamily.setError("Family head is required");
            familyHead.requestFocus();
            familyHead.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
            callAPi = false;
        } else {

            textInputLayoutHeadOfFamily.setError(null);
            familyHead.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
        }

        if (TextUtils.isEmpty(relation.getText().toString())) {
            textInputLayoutRelations.setError("Relation is required");
            relation.requestFocus();
            relation.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
            callAPi = false;
        } else {
            relation.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
            textInputLayoutRelations.setError(null);
        }


        if (family.isChecked() && callAPi) {
            if (TextUtils.isEmpty(nativeZone.getText().toString())) {
                textInputLayoutNativeZone.setError("zone name is required");
                nativeZone.requestFocus();
                nativeZone.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
                callAPi = false;
            } else {

                textInputLayoutNativeZone.setError(null);
                nativeZone.clearFocus();
                nativeZone.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
            }
            if (TextUtils.isEmpty(nativeSanghName.getText().toString()) && callAPi) {
                callAPi = false;
                textInputLayoutNativeSangh.setError("local sangh is required");
                nativeSanghName.requestFocus();
                nativeSanghName.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
            } else {
                nativeSanghName.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                textInputLayoutNativeSangh.setError(null);
                nativeSanghName.clearFocus();
            }
            if (TextUtils.isEmpty(basic_residence.getText().toString()) && callAPi) {
                callAPi = false;
                textInputLayoutNativeFamily.setError("current residence is required");
                basic_residence.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
                basic_residence.requestFocus();
            } else {
                textInputLayoutNativeFamily.setError(null);
                basic_residence.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
                basic_residence.clearFocus();
            }
            if (TextUtils.isEmpty(relationLast.getText().toString()) && callAPi) {
                callAPi = false;
                textInputLayoutNativeRelations.setError("relation is required");
                relationLast.requestFocus();
                relationLast.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_error_border));
            } else {
                textInputLayoutNativeRelations.setError(null);
                relationLast.clearFocus();
                relationLast.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.edt_border));
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

