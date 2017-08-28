/*
package com.sramanopasaka.sipanionline.sadhumargi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;

import com.sramanopasaka.sipanionline.sadhumargi.adapters.FindFamilyRecyclerAdapter;
import com.sramanopasaka.sipanionline.sadhumargi.cms.task.RequestProcessor;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.DharmicFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.ExamListingFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.KnowledgeFragment;
import com.sramanopasaka.sipanionline.sadhumargi.fragments.PromiseFragment;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.DelayAutoCompleteTextView;
import com.sramanopasaka.sipanionline.sadhumargi.helpers.OfflineData;
import com.sramanopasaka.sipanionline.sadhumargi.listener.SearchFamilyUpdator;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;
import com.sramanopasaka.sipanionline.sadhumargi.model.Family;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

*/
/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 *//*


*/
/*
public class TestActivity extends AppCompatActivity implements SearchFamilyUpdator {
    @Bind(R.id.eTxtFindNBookTabFind)
    DelayAutoCompleteTextView eTxtFindNBookTabFind;

    @Bind(R.id.pb_loading_indicator)
    ProgressBar progressBar;

    @Bind(R.id.resultRecycler)
    RecyclerView resultRecycler;
*//*


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);

        eTxtFindNBookTabFind.setThreshold(2);
        eTxtFindNBookTabFind.setLoadingIndicator(progressBar);
        eTxtFindNBookTabFind.addTextChangedListener(getTextWatcher());
// findSpecialityAdapter = new FindSpecialityAdapter(getActivity(), findPresenter, city);
//        eTxtFindNBookTabFind.setAdapter(findSpecialityAdapter);


      */
/*  eTxtFindNBookTabFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                FindSpecialityModel model = (FindSpecialityModel) adapterView.getItemAtPosition(position);
                eTxtFindNBookTabFind.setText("");

                doctorSearchPojo = new DoctorSearchPojo(model.getName(), "", 0, city, 0, 0, "", "");

                Intent intent = new Intent(getActivity(), DoctorListActivity.class);
                intent.putExtra("data", doctorSearchPojo);

                getActivity().startActivity(intent);
            }
        });*//*


    }
    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                FindFamilyRecyclerAdapter findFamilyRecyclerAdapter  = new FindFamilyRecyclerAdapter(TestActivity.this, TestActivity.this);
                if (editable.length() > 1) {
                    eTxtFindNBookTabFind.showFilterProgress();
                    progressBar.setVisibility(View.VISIBLE);
                    findFamilyRecyclerAdapter.getFilter().filter(editable);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(TestActivity.this);
                    resultRecycler.setLayoutManager(layoutManager);
                    resultRecycler.setAdapter(findFamilyRecyclerAdapter);
                } else {
                    findFamilyRecyclerAdapter.setResultList(new ArrayList<Family>());
                }
            }
        };
    }

    @Override
    public void onQuerryChanged(String querry) {

       /* LoginModel loginResponse = OfflineData.getLoginData();
        if (loginResponse != null) {


            RequestProcessor requestProcessor = new RequestProcessor(DharmicFragment.this);
            requestProcessor.getDharmikDetails(loginResponse.getId(), loginResponse.getAppToken());
        }*/

   /* @Override
    public void onResultReceived() {

    }
}*/

