
package com.android.newgeneration.dandisnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.newgeneration.dandisnap.Action.FragmentAction;
import com.android.newgeneration.dandisnap.Camera.ActivityCamera;
import com.android.newgeneration.dandisnap.Home.FragmentHome;
import com.android.newgeneration.dandisnap.Profile.FragmentProfile;
import com.android.newgeneration.dandisnap.Search.FragmentSearch;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain extends Activity {

    com.android.newgeneration.dandisnap.Home.FragmentHome FragmentHome;
    FragmentAction FragmentActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setInit();
    }

    public void setInit() {
        FragmentHome = new FragmentHome();
        FragmentActivity = new FragmentAction();
        FragmentProfile = new FragmentProfile();
        FragmentSearch = new FragmentSearch();
        getFragmentManager().beginTransaction().add(R.id.main_rl_container, FragmentHome).commit();
    }

    @OnClick({R.id.main_btn_home, R.id.main_btn_search, R.id.main_btn_camera, R.id.main_btn_action, R.id.main_btn_profile})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.main_btn_home:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentHome).commit();
                break;
            case R.id.main_btn_search:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentSearch).commit();
                break;
            case R.id.main_btn_camera:
                Intent intent = new Intent(this, ActivityCamera.class);
                startActivity(intent);
                break;
            case R.id.main_btn_action:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentActivity).commit();
                break;
            case R.id.main_btn_profile:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentProfile).commit();
                break;
        }
    }

}
