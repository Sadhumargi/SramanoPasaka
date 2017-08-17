 package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.AddExamActivity;
import com.sramanopasaka.sipanionline.sadhumargi.DharmikActivity;
import com.sramanopasaka.sipanionline.sadhumargi.EducationActivity;
import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.EducationListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.adapters.ExamListAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteEducationResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.EducationListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DataUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.DharmicData;
import com.sramanopasaka.sipanionline.sadhumargi.model.Education;
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.utils.DialogueUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

 /**
  * Name    :   pranavjdev
  * Date   : 8/15/17
  * Email : pranavjaydev@gmail.com
  */
 
 public class ExamListingFragment extends BaseFragment implements GUICallback,DataUpdator,ExamListAdapter.EditDeleteActionListener {
 
     private View view = null;
 
     @Bind(R.id.businessRecycler)
     RecyclerView recyclerView;
 
     @Bind(R.id.txt_no_address)
     TextView txtNoAddress;
 
     @Bind(R.id.fab)
     FloatingActionButton fab;

     private int ADD_EXAM_REQUEST_CODE = 111;

     private DharmikActivity dharmikActivity = null;
     public static ExamListingFragment newInstance() {
         return new ExamListingFragment();
     }
 
     @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_businesslist, container, false);
         ButterKnife.bind(this, view);
         return view;
     }
 
     @Override
     public void onActivityCreated(@Nullable Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(),AddExamActivity.class);
                 startActivityForResult(intent,ADD_EXAM_REQUEST_CODE);
             }
         });
 
     }

     @Override
     public void setUserVisibleHint(boolean isVisibleToUser) {
         super.setUserVisibleHint(isVisibleToUser);
         if (isVisibleToUser) {

             Handler mHandler = new Handler();
             mHandler.postDelayed(new Runnable() {

                 @Override
                 public void run() {
                     if (getActivity() != null) {

                         loadExamList();
                         dharmikActivity = (DharmikActivity) getActivity();
                         dharmikActivity.setDataUpdator(ExamListingFragment.this);

                     }
                 }

             }, 100L);

         }
     }
 
     private void loadExamList(){
         DharmicData dharmicData = OfflineData.getDharmikData();
         if (dharmicData != null) {
             if (dharmicData.getExams() != null) {
                 showEducationList(dharmicData.getExams());
             } else {
                 ExamListAdapter adapter = new ExamListAdapter(getActivity(), null, this);
                 recyclerView.setAdapter(adapter);
                 txtNoAddress.setVisibility(View.VISIBLE);
             }
         } else {
             ExamListAdapter adapter = new ExamListAdapter(getActivity(), null, this);
             recyclerView.setAdapter(adapter);
             txtNoAddress.setVisibility(View.VISIBLE);
         }
     }
 
     private void showEducationList(List<Exams> list) {
         if (list != null && list.size() != 0) {
             txtNoAddress.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
             LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
             layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
             recyclerView.setLayoutManager(layoutManager);
             ExamListAdapter adapter = new ExamListAdapter(getActivity(), list, this);
             recyclerView.setAdapter(adapter);
         } else {
             ExamListAdapter adapter = new ExamListAdapter(getActivity(), null, this);
             recyclerView.setAdapter(adapter);
             txtNoAddress.setVisibility(View.VISIBLE);
         }
     }
 
 
 
     @Override
     public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
         hideLoadingDialog();
         if (guiResponse != null) {
             if (requestStatus.equals(RequestStatus.SUCCESS)) {
                  if (guiResponse instanceof DeleteExamResponse) {
                     DeleteExamResponse response = (DeleteExamResponse) guiResponse;
                     if (response != null) {
                         if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
 
                             if (!TextUtils.isEmpty(response.getMessage())) {
                                 Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(getActivity(), "Exam removed", Toast.LENGTH_SHORT).show();
                             }
                             if (dharmikActivity == null)
                                 dharmikActivity = (DharmikActivity) getActivity();
                             dharmikActivity.loadDharmikData();
                             //loadExamList();
                         } else {
                             if (!TextUtils.isEmpty(response.getMessage())) {
                                 Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                             }
                         }
                     }
                 }
             }
         }
     }
 
 
 
 
     @Override
     public void delete(final Exams exams) {
         DialogueUtils.showDialogOKCancel(getActivity(), "Delete Exam", "Are you sure to delete this exam ?", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 LoginModel loginResponse = OfflineData.getLoginData();
                 if (loginResponse != null) {
 
 
                     RequestProcessor requestProcessor = new RequestProcessor(ExamListingFragment.this);
                     requestProcessor.removeExam(loginResponse.getId(), loginResponse.getAppToken(),exams);
                 }
 
                 dialogInterface.dismiss();
             }
         }, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.dismiss();
             }
         });
     }

     @Override
     public void onDataRefreshed() {
         loadExamList();
     }

     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == ADD_EXAM_REQUEST_CODE) {
             if (resultCode == getActivity().RESULT_OK) {
                 if (dharmikActivity == null)
                     dharmikActivity = (DharmikActivity) getActivity();
                 dharmikActivity.loadDharmikData();
             }
         }
     }
 }
