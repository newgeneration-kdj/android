
package com.android.newgeneration.dandisnap.Main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.newgeneration.dandisnap.Action.FragmentAction;
import com.android.newgeneration.dandisnap.Camera.ActivityCamera;
import com.android.newgeneration.dandisnap.Home.FragmentHome;
import com.android.newgeneration.dandisnap.Profile.FragmentProfile;
import com.android.newgeneration.dandisnap.R;
import com.android.newgeneration.dandisnap.Search.FragmentSearch;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain extends Activity {

    com.android.newgeneration.dandisnap.Home.FragmentHome mFragmentHome;
    com.android.newgeneration.dandisnap.Action.FragmentAction mFragmentAction;
    com.android.newgeneration.dandisnap.Profile.FragmentProfile mFragmentProfile;
    com.android.newgeneration.dandisnap.Search.FragmentSearch mFragmentSearch;

    @InjectView(R.id.main_btn_home)
    Button mMainBtnHome;
    @InjectView(R.id.main_btn_search)
    Button mMainBtnSearch;
    @InjectView(R.id.main_btn_camera)
    Button mMainBtnCamera;
    @InjectView(R.id.main_btn_action)
    Button mMainBtnAction;
    @InjectView(R.id.main_btn_profile)
    Button mMainBtnProfile;
    ArrayList<Fragment> mArrayListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setInit();
    }

    public void setInit() {
        mFragmentHome = new FragmentHome();
        mFragmentAction = new FragmentAction();
        mFragmentProfile = new FragmentProfile();
        mFragmentSearch = new FragmentSearch();
        mArrayListFragment = new ArrayList<>();
        setFragment();
        mArrayListFragment.add(mFragmentHome);
        connectFragmentToBtn();
    }

    @OnClick({R.id.main_btn_home, R.id.main_btn_search, R.id.main_btn_camera, R.id.main_btn_action, R.id.main_btn_profile})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.main_btn_home:
                replaceFragment(mFragmentHome);
                break;
            case R.id.main_btn_search:
                replaceFragment(mFragmentSearch);
                break;
            case R.id.main_btn_camera:
                Intent intent = new Intent(this, ActivityCamera.class);
                startActivity(intent);
                break;
            case R.id.main_btn_action:
                replaceFragment(mFragmentAction);
                break;
            case R.id.main_btn_profile:
                replaceFragment(mFragmentProfile);
                break;
        }
        connectFragmentToBtn();
    }

    @Override
    public void onBackPressed() {
        Fragment presentFragment = mArrayListFragment.get(mArrayListFragment.size() - 1);
        mArrayListFragment.remove(mArrayListFragment.size() - 1);
        if (mArrayListFragment.size() == 0) {
            super.onBackPressed();
        } else {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.hide(presentFragment);
            Fragment previousFragment = mArrayListFragment.get(mArrayListFragment.size() - 1);
            ft.show(previousFragment);
            ft.commit();
            connectFragmentToBtn();
        }
    }

    public void setFragment()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.main_rl_container, mFragmentHome);
        ft.add(R.id.main_rl_container, mFragmentSearch);
        ft.add(R.id.main_rl_container, mFragmentAction);
        ft.add(R.id.main_rl_container, mFragmentProfile);
        ft.show(mFragmentHome);
        ft.hide(mFragmentSearch);
        ft.hide(mFragmentAction);
        ft.hide(mFragmentProfile);
        ft.commit();
    }

    public void replaceFragment(Fragment nextfragment) {
        Fragment presentFragment = mArrayListFragment.get(mArrayListFragment.size() - 1);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(presentFragment);
        ft.show(nextfragment);
        ft.commit();
        int index = mArrayListFragment.indexOf(nextfragment);
        if (index != -1)
            mArrayListFragment.remove(index);
        mArrayListFragment.add(nextfragment);
    }

    public void connectFragmentToBtn()
    {
        Fragment presentFragment = mArrayListFragment.get(mArrayListFragment.size() - 1);
        if(presentFragment == mFragmentHome)
        {
            setBtnSelectd(mMainBtnHome);
        }
        else if(presentFragment == mFragmentSearch)
        {
            setBtnSelectd(mMainBtnSearch);
        }
        else if(presentFragment == mFragmentAction)
        {
            setBtnSelectd(mMainBtnAction);
        }
        else if(presentFragment == mFragmentProfile)
        {
            setBtnSelectd(mMainBtnProfile);
        }
    }

    public void setBtnSelectd(Button btn)
    {
        mMainBtnHome.setSelected(false);
        mMainBtnSearch.setSelected(false);
        mMainBtnAction.setSelected(false);
        mMainBtnProfile.setSelected(false);
        btn.setSelected(true);
    }

}







