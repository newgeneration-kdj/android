
package com.android.newgeneration.dandisnap.Main;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.newgeneration.dandisnap.Action.FragmentAction;
import com.android.newgeneration.dandisnap.Camera.ActivityCamera;
import com.android.newgeneration.dandisnap.Home.FragmentHome;
import com.android.newgeneration.dandisnap.Profile.FragmentProfile;
import com.android.newgeneration.dandisnap.R;
import com.android.newgeneration.dandisnap.Search.FragmentSearch;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain extends Activity {

    com.android.newgeneration.dandisnap.Home.FragmentHome FragmentHome;
    com.android.newgeneration.dandisnap.Action.FragmentAction FragmentAction;
    com.android.newgeneration.dandisnap.Profile.FragmentProfile FragmentProfile;
    com.android.newgeneration.dandisnap.Search.FragmentSearch FragmentSearch;

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
    LinkedListFragment mLinkedListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setInit();
    }

    public void setInit() {
        FragmentHome = new FragmentHome();
        FragmentAction = new FragmentAction();
        FragmentProfile = new FragmentProfile();
        FragmentSearch = new FragmentSearch();
        mLinkedListFragment = new LinkedListFragment();
        getFragmentManager().beginTransaction().add(R.id.main_rl_container, FragmentHome).commit();
        mLinkedListFragment.add(FragmentHome);
    }

    @OnClick({R.id.main_btn_home, R.id.main_btn_search, R.id.main_btn_camera, R.id.main_btn_action, R.id.main_btn_profile})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.main_btn_home:
                replaceFragment(FragmentHome);
                break;
            case R.id.main_btn_search:
                replaceFragment(FragmentSearch);
                break;
            case R.id.main_btn_camera:
                Intent intent = new Intent(this, ActivityCamera.class);
                startActivity(intent);
                break;
            case R.id.main_btn_action:
                replaceFragment(FragmentAction);
                break;
            case R.id.main_btn_profile:
                replaceFragment(FragmentProfile);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mLinkedListFragment.deleteLastNode();
        Fragment previousFragment = mLinkedListFragment.Lastfragment();
        if (previousFragment == null) {
            super.onBackPressed();
        } else {
            getFragmentManager().beginTransaction().replace(R.id.main_rl_container, previousFragment).commit();
        }
    }

    void replaceFragment(Fragment fragment)
    {
        getFragmentManager().beginTransaction().replace(R.id.main_rl_container, fragment).commit();
        mLinkedListFragment.delete(fragment);
        mLinkedListFragment.add(fragment);
    }

}





