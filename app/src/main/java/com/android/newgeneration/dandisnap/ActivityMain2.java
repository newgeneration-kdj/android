package com.android.newgeneration.dandisnap;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain2 extends Activity{

    int mCurrentFragmentIndex;
    public final static int FRAGMENT_HOME = 0;
    public final static int FRAGMENT_SEARCH = 1;
    public final static int FRAGMENT_CAMERA = 2;
    public final static int FRAGMENT_ACTIVITY = 3;
    public final static int FRAGMENT_PROFILE = 4;

    @InjectView(R.id.fragment_home_btn) Button fragment_home_btn;
    @InjectView(R.id.fragment_search_btn) Button fragment_search_btn;
    @InjectView(R.id.fragment_camera_btn) Button fragment_camera_btn;
    @InjectView(R.id.fragment_activity_btn) Button fragment_activity_btn;
    @InjectView(R.id.fragment_profile_btn) Button fragment_profile_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mCurrentFragmentIndex = FRAGMENT_HOME;
        fragmentReplace(mCurrentFragmentIndex);
    }

    public void fragmentReplace(int reqNewFragmentIndex) {
        Fragment newFragment = getFragment(reqNewFragmentIndex);
        final FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.fragment_layout, newFragment);
        transaction.commit();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_HOME:
                newFragment = new FragmentHome();
                break;
            case FRAGMENT_SEARCH:
                break;
            case FRAGMENT_CAMERA:
                break;
            case FRAGMENT_ACTIVITY:
                break;
            case FRAGMENT_PROFILE:
                break;
            default:
                break;
        }
        return newFragment;
    }

    @OnClick( {R.id.fragment_home_btn, R.id.fragment_search_btn, R.id.fragment_camera_btn, R.id.fragment_activity_btn, R.id.fragment_profile_btn })
    void onClickButton (Button btn) {
        switch(btn.getId()) {
            case R.id.fragment_home_btn:
                mCurrentFragmentIndex = FRAGMENT_HOME;
                fragmentReplace(mCurrentFragmentIndex);
                break;
            case R.id.fragment_search_btn:
                mCurrentFragmentIndex = FRAGMENT_SEARCH;
                fragmentReplace(mCurrentFragmentIndex);
                break;
            case R.id.fragment_camera_btn:
                mCurrentFragmentIndex = FRAGMENT_CAMERA;
                fragmentReplace(mCurrentFragmentIndex);
                break;
            case R.id.fragment_activity_btn:
                mCurrentFragmentIndex = FRAGMENT_ACTIVITY;
                fragmentReplace(mCurrentFragmentIndex);
                break;
            case R.id.fragment_profile_btn:
                mCurrentFragmentIndex = FRAGMENT_PROFILE;
                fragmentReplace(mCurrentFragmentIndex);
                break;
        }
    }

}
