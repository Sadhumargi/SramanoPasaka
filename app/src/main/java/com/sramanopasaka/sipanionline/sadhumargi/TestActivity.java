package com.sramanopasaka.sipanionline.sadhumargi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sramanopasaka.sipanionline.sadhumargi.fragments.BasicDetailsFragment;
import com.sramanopasaka.sipanionline.sadhumargi.listener.TabselectionListner;

/**
 * Name    :   pranavjdev
 * Date   : 8/15/17
 * Email : pranavjaydev@gmail.com
 */

public class TestActivity extends AppCompatActivity implements TabselectionListner {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setFragment(BasicDetailsFragment.newInstance(), "");
    }

    protected void setFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();


        // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.contentPanel, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onSelectTab(int index) {

    }

    @Override
    public void onSelectNextTab() {

    }

    @Override
    public void enableNestedScrolling(boolean status) {

    }
}
