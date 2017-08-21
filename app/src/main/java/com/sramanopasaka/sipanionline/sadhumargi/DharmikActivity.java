package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DharmikDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.GUIResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ExamListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.KnowledgeFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ParikshaFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.PromiseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.DataUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import java.util.ArrayList;
import java.util.List;

public class DharmikActivity extends BaseActivity implements GUICallback {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    public void setDataUpdator(DataUpdator dataUpdator) {
        this.dataUpdator = dataUpdator;
    }

    private DataUpdator dataUpdator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dharmik);


        toolbar = (Toolbar) findViewById(R.id.tool_dharmik);
        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow_patasala);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>धार्मिक विवरण</font>"));

        viewPager = (ViewPager) findViewById(R.id.vp_dharmik);
        tabLayout = (TabLayout) findViewById(R.id.tab_dharmik);
        setUpViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#da5617"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#040303"), Color.parseColor("#040303"));


        loadDharmikData();
    }

    public void loadDharmikData() {
        showLoadingDialog();
        LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(DharmikActivity.this);
            requestProcessor.getDharmikDetails(loginResponse.getId(), loginResponse.getAppToken());
        }
    }

    private void setUpViewpager(ViewPager viewPager) {

        DharmikActivity.ViewPagerAdapter adapter = new DharmikActivity.ViewPagerAdapter(getSupportFragmentManager());
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
                DharmikDetailsResponse response = (DharmikDetailsResponse) guiResponse;
                if (!TextUtils.isEmpty(response.getStatus()) && response.getStatus().equalsIgnoreCase("success")) {
                    OfflineData.saveDharmicResponse(response.getData());
                    if (dataUpdator != null)
                        dataUpdator.onDataRefreshed();
                } else {
                    if (!TextUtils.isEmpty(response.getMessage())) {
                        Toast.makeText(DharmikActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DharmikActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
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

