package com.android.newgeneration.dandisnap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityMain extends FragmentActivity {
    @InjectView(R.id.main_txt_title) TextView mTxtMenuTitle;
    @InjectView(R.id.signup) Button signup;
    @InjectView(R.id.login) Button login;
    @InjectView(R.id.layout1) FrameLayout layout1;
    @InjectView(R.id.layout2) FrameLayout layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.signup, R.id.login})
    void onButtonClick(Button btn){
        switch(btn.getId()){
            case R.id.signup:
                signup.setTextColor(Color.WHITE);
                login.setTextColor(Color.GRAY);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);

                break;
            case R.id.login:
                login.setTextColor(Color.WHITE);
                signup.setTextColor(Color.GRAY);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }
}
