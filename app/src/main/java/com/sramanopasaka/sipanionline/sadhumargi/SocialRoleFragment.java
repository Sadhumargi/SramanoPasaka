package com.sramanopasaka.sipanionline.sadhumargi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BaseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DataUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;

import butterknife.Bind;

import static com.sramanopasaka.sipanionline.sadhumargi.R.id.fab;

public class SocialRoleFragment extends BaseFragment implements GUICallback, DataUpdator {

    private View view = null;

    private int ADD_SOCIALROLE_REQUEST_CODE = 112;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_businesslist,container,false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddSocialRoleActivity.class);
                startActivityForResult(intent, ADD_SOCIALROLE_REQUEST_CODE);
            }
        });
      //  loadExamList();

    }

    @Override
    public void onDataRefreshed() {

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {

    }

    public static SocialRoleFragment newInstance() {

        return new SocialRoleFragment();
    }



   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialrole_fragment);
    }*/
}
