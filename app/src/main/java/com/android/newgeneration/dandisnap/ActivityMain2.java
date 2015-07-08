
package com.android.newgeneration.dandisnap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain2 extends Activity {

    FragmentHome FragmentHome;

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
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        setInit();
    }

    public void setInit() {
        FragmentHome = new FragmentHome();
        getFragmentManager().beginTransaction().add(R.id.fragment_layout, FragmentHome).commit();
    }

    @OnClick({R.id.fragment_activity_btn, R.id.fragment_home_btn, R.id.fragment_camera_btn, R.id.fragment_profile_btn, R.id.fragment_search_btn})
    void onButtonClick(Button btn) {
        switch (btn.getId()) {
            case R.id.fragment_home_btn:
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, FragmentHome).commit();
                break;
            case R.id.fragment_search_btn:

                break;
            case R.id.fragment_camera_btn:

                break;
            case R.id.fragment_activity_btn:

                break;
            case R.id.fragment_profile_btn:

                break;
        }
    }

}
