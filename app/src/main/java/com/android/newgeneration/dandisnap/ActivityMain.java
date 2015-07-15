
package com.android.newgeneration.dandisnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.android.newgeneration.dandisnap.Action.FragmentActivities;
import com.android.newgeneration.dandisnap.Camera.ActivityCamera;
import com.android.newgeneration.dandisnap.Home.FragmentHome;
import com.android.newgeneration.dandisnap.Profile.FragmentProfile;
import com.android.newgeneration.dandisnap.Search.FragmentSearch;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain extends Activity {

    com.android.newgeneration.dandisnap.Home.FragmentHome FragmentHome;
    FragmentActivities FragmentActivity;
    com.android.newgeneration.dandisnap.Profile.FragmentProfile FragmentProfile;
    com.android.newgeneration.dandisnap.Search.FragmentSearch FragmentSearch;

    @InjectView(R.id.fragment_home_btn)
    Button fragment_home_btn;
    @InjectView(R.id.fragment_search_btn)
    Button fragment_search_btn;
    @InjectView(R.id.fragment_camera_btn)
    Button fragment_camera_btn;
    @InjectView(R.id.fragment_activity_btn)
    Button fragment_activity_btn;
    @InjectView(R.id.fragment_profile_btn)
    Button fragment_profile_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setInit();
    }

    public void setInit() {
        FragmentHome = new FragmentHome();
        FragmentActivity = new FragmentActivities();
        FragmentProfile = new FragmentProfile();
        FragmentSearch = new FragmentSearch();
        getFragmentManager().beginTransaction().add(R.id.fragment_layout, FragmentHome).commit();
    }

    @OnClick({R.id.fragment_activity_btn, R.id.fragment_home_btn, R.id.fragment_camera_btn, R.id.fragment_profile_btn, R.id.fragment_search_btn})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.fragment_home_btn:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentHome).commit();
                break;
            case R.id.fragment_search_btn:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentSearch).commit();
                break;
            case R.id.fragment_camera_btn:
                Intent intent = new Intent(this, ActivityCamera.class);
                startActivity(intent);
                break;
            case R.id.fragment_activity_btn:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentActivity).commit();
                break;
            case R.id.fragment_profile_btn:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentProfile).commit();
                break;
        }
    }

}
