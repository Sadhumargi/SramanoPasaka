package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.SanghDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateServiceResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.CustomToast;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DataUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.DharmicData;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.model.SanghData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesFragment extends BaseFragment implements GUICallback, DataUpdator {


    @Bind(R.id.samayse)
    CheckBox samayse;

    @Bind(R.id.arthse)
    CheckBox arthse;

    @Bind(R.id.apnerupke)
    CheckBox apnerupke;

    @Bind(R.id.anubavse)
    CheckBox anubavse;

    @Bind(R.id.vicharose)
    CheckBox vicharose;

    @Bind(R.id.others)
    CheckBox others;

    private View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_services_fragment, container, false);

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showDataUi();
    }

    private void showDataUi() {
        SanghData sanghData = OfflineData.getSanghData();
        if (sanghData != null) {
            if (sanghData.getServices() != null) {

                if (!TextUtils.isEmpty(sanghData.getServices().getService_time()) && sanghData.getServices().getService_time().equalsIgnoreCase("1"))
                    samayse.setChecked(true);
                else
                    samayse.setChecked(false);
                if (!TextUtils.isEmpty(sanghData.getServices().getService_experience()) && sanghData.getServices().getService_experience().equalsIgnoreCase("1"))
                    anubavse.setChecked(true);
                else
                    anubavse.setChecked(false);
                if (!TextUtils.isEmpty(sanghData.getServices().getService_thoughts()) && sanghData.getServices().getService_thoughts().equalsIgnoreCase("1"))
                    vicharose.setChecked(true);
                else
                    vicharose.setChecked(false);
                if (!TextUtils.isEmpty(sanghData.getServices().getService_money()) && sanghData.getServices().getService_money().equalsIgnoreCase("1"))
                    arthse.setChecked(true);
                else
                    arthse.setChecked(false);
                if (!TextUtils.isEmpty(sanghData.getServices().getService_office()) && sanghData.getServices().getService_office().equalsIgnoreCase("1"))
                    apnerupke.setChecked(true);

                if (!TextUtils.isEmpty(sanghData.getServices().getService_others()) && sanghData.getServices().getService_others().equalsIgnoreCase("1"))
                    others.setChecked(true);
                else
                    others.setChecked(false);

            }
        }
    }

    private boolean isAnyChanges() {
        boolean isChange = false;
        SanghData sanghData = OfflineData.getSanghData();
        if (sanghData != null) {
            if (sanghData.getServices() != null) {
                if (samayse.isChecked()) {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_time()) && sanghData.getServices().getService_time().equalsIgnoreCase("0"))
                        isChange = true;
                } else {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_time()) && sanghData.getServices().getService_time().equalsIgnoreCase("1"))
                        isChange = true;
                }
                if (anubavse.isChecked()) {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_experience()) && sanghData.getServices().getService_experience().equalsIgnoreCase("0"))
                        isChange = true;
                } else {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_experience()) && sanghData.getServices().getService_experience().equalsIgnoreCase("1"))
                        isChange = true;
                }
                if (vicharose.isChecked()) {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_thoughts()) && sanghData.getServices().getService_thoughts().equalsIgnoreCase("0"))
                        isChange = true;
                } else {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_thoughts()) && sanghData.getServices().getService_thoughts().equalsIgnoreCase("1"))
                        isChange = true;
                }
                if (arthse.isChecked()) {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_money()) && sanghData.getServices().getService_money().equalsIgnoreCase("0"))
                        isChange = true;
                } else {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_money()) && sanghData.getServices().getService_money().equalsIgnoreCase("1"))
                        isChange = true;
                }
                if (apnerupke.isChecked()) {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_office()) && sanghData.getServices().getService_office().equalsIgnoreCase("0"))
                        isChange = true;
                } else {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_office()) && sanghData.getServices().getService_office().equalsIgnoreCase("1"))
                        isChange = true;
                }
                if (others.isChecked()) {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_others()) && sanghData.getServices().getService_others().equalsIgnoreCase("0"))
                        isChange = true;
                } else {
                    if (!TextUtils.isEmpty(sanghData.getServices().getService_others()) && sanghData.getServices().getService_others().equalsIgnoreCase("1"))
                        isChange = true;
                }

            }else {
                if (samayse.isChecked()) {
                    isChange = true;
                }
                if (anubavse.isChecked()) {
                    isChange = true;
                }
                if (vicharose.isChecked()) {
                    isChange = true;
                }
                if (arthse.isChecked()) {
                    isChange = true;
                }
                if (apnerupke.isChecked()) {
                    isChange = true;
                }
                if (others.isChecked()) {
                    isChange = true;
                }
            }

        } else {
            if (samayse.isChecked()) {
                isChange = true;
            }
            if (anubavse.isChecked()) {
                isChange = true;
            }
            if (vicharose.isChecked()) {
                isChange = true;
            }
            if (arthse.isChecked()) {
                isChange = true;
            }
            if (apnerupke.isChecked()) {
                isChange = true;
            }
            if (others.isChecked()) {
                isChange = true;
            }
        }
        return isChange;
    }

    @OnClick(R.id.updateServices)
    public void updateServices() {
        if (isAnyChanges()) {
            LoginModel loginResponse = OfflineData.getLoginData();
            if (loginResponse != null) {
                showLoadingDialog();

                RequestProcessor requestProcessor = new RequestProcessor(ServicesFragment.this);
                requestProcessor.updateServices(loginResponse.getId(), loginResponse.getAppToken(), String.valueOf(samayse.isChecked()), String.valueOf(arthse.isChecked()),
                        String.valueOf(apnerupke.isChecked()), String.valueOf(anubavse.isChecked()), String.valueOf(vicharose.isChecked()),
                        String.valueOf(others.isChecked()));
            }
        } else {
            new CustomToast().showErrorToast(getActivity(), view, "Please select your services and try again");
        }

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                if (guiResponse instanceof UpdateServiceResponse) {
                    UpdateServiceResponse response = (UpdateServiceResponse) guiResponse;
                    if (response != null) {
                        if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {

                            /*if (dharmikActivity == null)
                                dharmikActivity = (DharmikActivity) getActivity();
                            dharmikActivity.loadDharmikData();
*/
                            loadSanghData();
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                new CustomToast().showInformationToast(getActivity(), view, response.getMessage());
                            } else {
                                new CustomToast().showInformationToast(getActivity(), view, "Updated successfully");
                            }

                        } else {
                            if (!TextUtils.isEmpty(response.getMessage())) {
                                new CustomToast().showErrorToast(getActivity(), view, response.getMessage());
                            } else {
                                new CustomToast().showErrorToast(getActivity(), view, "Something went wrong!");
                            }
                        }
                    }
                } else if (guiResponse instanceof SanghDetailsResponse) {
                    SanghDetailsResponse response = (SanghDetailsResponse) guiResponse;
                    if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                        OfflineData.saveSanghResponse(response.getData());
                    /*if (dataUpdator != null)
                        dataUpdator.onDataRefreshed();*/
                        showDataUi();
                    } else {
                        if (!TextUtils.isEmpty(response.getMessage())) {
                            new CustomToast().showErrorToast(getActivity(), view, response.getMessage());
                        } else {
                            new CustomToast().showErrorToast(getActivity(), view, "Something went wrong!");
                        }
                    }
                }
            } else {
                new CustomToast().showErrorToast(getActivity(), view, "Please check your internet connection");
            }
        }
    }

    public void loadSanghData() {
        showLoadingDialog();
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(ServicesFragment.this);
            requestProcessor.getSanghDetails(loginResponse.getId(), loginResponse.getAppToken());
        }
    }

    @Override
    public void onDataRefreshed() {
        showDataUi();
    }

    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_fragment);
    }*/
}
