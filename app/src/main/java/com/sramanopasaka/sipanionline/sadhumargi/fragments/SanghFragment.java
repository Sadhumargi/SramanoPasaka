package com.sramanopasaka.sipanionline.sadhumargi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.R;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DharmikDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.SanghDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.ActionBarUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Name    :   pranavjdev
 * Date   : 8/17/17
 * Email : pranavjaydev@gmail.com
 */

public class SanghFragment extends BaseFragment implements GUICallback{
    private View view = null;
    private ActionBarUpdator actionBarUpdator = null;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Bind(R.id.socialRoleButton)
    RadioButton socialRoleButton;

    @Bind(R.id.servicesButton)
    RadioButton servicesButton;

    @Bind(R.id.hobbiesButton)
    RadioButton hobbiesButton;

    @Bind(R.id.segmentGroup)
    SegmentedGroup segmentGroup;




    public static SanghFragment newInstance() {
        return new SanghFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sangh, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            actionBarUpdator = (ActionBarUpdator) getActivity();
            actionBarUpdator.onUpdateTitile(getString(R.string.Dharmik));
        }catch (Exception ex){

        }

        segmentGroup.setTintColor(ContextCompat.getColor(getActivity(),R.color.profile_status_color));

      /*  viewPager = (ViewPager) view.findViewById(R.id.vp_dharmik);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_dharmik);
        setUpViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));
*/

        loadSanghData();


        socialRoleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    setFragment(KnowledgeFragment.newInstance(),"");
                }
            }
        });
        servicesButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    setFragment(PromiseFragment.newInstance(),"");
                }
            }
        });
        hobbiesButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    setFragment(HobbyFragment.newInstance(),"");
                }
            }
        });

        setFragment(HobbyFragment.newInstance(),"");

    }

    public void loadSanghData() {
        showLoadingDialog();
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(SanghFragment.this);
            requestProcessor.getSanghDetails(loginResponse.getId(), loginResponse.getAppToken());
        }
    }

    protected void setFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();


        // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frameContainer, fragment, tag);
        fragmentTransaction.commit();
    }

    private void setUpViewpager(ViewPager viewPager) {

        SanghFragment.ViewPagerAdapter adapter = new SanghFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PromiseFragment().newInstance(), "नियम/त्याग");
        adapter.addFragment(new ExamListingFragment().newInstance(), "परीक्षाएं ");

        //adapter.addFragment(new ParikshaFragment(), "परीक्षाएं ");
        adapter.addFragment(new KnowledgeFragment().newInstance(), "ज्ञान");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onRequestProcessed(GUIResponse guiResponse, RequestStatus requestStatus) {
        hideLoadingDialog();
        if (guiResponse != null) {
            if (requestStatus.equals(RequestStatus.SUCCESS)) {
                SanghDetailsResponse response = (SanghDetailsResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    OfflineData.saveSanghResponse(response.getData());

                } else {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public List<Fragment> mFragentList = new ArrayList<>();
        public List<String> mFragentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);


        }

        @Override
        public Fragment getItem(int position) {
            return mFragentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragentList.add(fragment);
            mFragentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragentTitleList.get(position);
        }

    }

}
