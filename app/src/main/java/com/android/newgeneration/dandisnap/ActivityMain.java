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
    @InjectView(R.id.signup_btn) Button signup_btn;
    @InjectView(R.id.login_btn) Button login_btn;
    @InjectView(R.id.layout1) FrameLayout layout1;
    @InjectView(R.id.layout2) FrameLayout layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example2);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.signup_btn, R.id.login_btn})
    void onButtonClick(Button btn){
        switch(btn.getId()){
            case R.id.signup_btn:
                signup_btn.setTextColor(Color.WHITE);
                login_btn.setTextColor(Color.GRAY);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);

                break;
            case R.id.login_btn:
                login_btn.setTextColor(Color.WHITE);
                signup_btn.setTextColor(Color.GRAY);
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
