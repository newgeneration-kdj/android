package com.android.newgeneration.dandisnap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityLogin extends FragmentActivity implements OnEditorActionListener {
    @InjectView(R.id.signup_btn)
    Button signup_btn;
    @InjectView(R.id.login_btn)
    Button login_btn;
    @InjectView(R.id.layout1)
    FrameLayout layout1;
    @InjectView(R.id.layout2)
    FrameLayout layout2;
    @InjectView(R.id.username_edit)
    EditText username_edit;
    @InjectView(R.id.password_edit)
    EditText password_edit;
    @InjectView(R.id.email_edit)
    EditText email_edit;
    @InjectView(R.id.main_layout)
    FrameLayout main_layout;
    @InjectView(R.id.name_layout)
    FrameLayout name_layout;
    @InjectView(R.id.name_edit)
    EditText name_edit;
    @InjectView(R.id.backsign_btn)
    Button backsign_btn;
    @InjectView(R.id.nickname_layout)
    FrameLayout nickname_layout;
    @InjectView(R.id.nickname_edit)
    EditText nickname_edit;
    @InjectView(R.id.backname_btn)
    Button backname_btn;
    @InjectView(R.id.password_layout)
    FrameLayout password_layout;
    @InjectView(R.id.password1_edit)
    EditText password1_edit;
    @InjectView(R.id.backnick_btn)
    Button backnick_btn;
    UserData userData = new UserData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        setOnEditorActionListener();

    }

    public void setOnEditorActionListener() {
        username_edit.setOnEditorActionListener(this);
        password_edit.setOnEditorActionListener(this);
        email_edit.setOnEditorActionListener(this);
        name_edit.setOnEditorActionListener(this);
        nickname_edit.setOnEditorActionListener(this);
        password1_edit.setOnEditorActionListener(this);
        if (userData.getOnlogin() == 1)
            username_edit.setText(userData.getUser_nickname());
    }

    @OnClick({R.id.signup_btn, R.id.login_btn, R.id.backsign_btn, R.id.backname_btn, R.id.backnick_btn})
    void onButtonClick(View v) {
        switch (v.getId()) {
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
            case R.id.backsign_btn:
                main_layout.setVisibility(View.VISIBLE);
                name_layout.setVisibility(View.GONE);
                break;
            case R.id.backname_btn:
                name_layout.setVisibility(View.VISIBLE);
                nickname_layout.setVisibility(View.GONE);
                break;
            case R.id.backnick_btn:
                nickname_layout.setVisibility(View.VISIBLE);
                password_layout.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (v.getId()) {
            case R.id.username_edit:
                if (userData.getOnlogin() == 1) {
                    username_edit.setText(userData.getUser_nickname());
                }
                break;
            case R.id.password_edit:
                if (password_edit.getText().toString().equals(userData.getUser_password())) {
                    Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "패드워드가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                //완료버튼 누르면 로그인이 되고, 메인화면으로 넘어가는 이벤트
                break;
            case R.id.email_edit:
                main_layout.setVisibility(View.GONE);
                name_layout.setVisibility(View.VISIBLE);
                userData.setUser_email(email_edit.getText().toString());
                break;
            case R.id.name_edit:
                name_layout.setVisibility(View.GONE);
                nickname_layout.setVisibility(View.VISIBLE);
                userData.setUser_name(name_edit.getText().toString());
                break;
            case R.id.nickname_edit:
                nickname_layout.setVisibility(View.GONE);
                password_layout.setVisibility(View.VISIBLE);
                userData.setUser_nickname(nickname_edit.getText().toString());
                break;
            case R.id.password1_edit:
                userData.setUser_password(password1_edit.getText().toString());
                userData.setOnlogin(1);
                Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(intent);
                finish();
               /* Toast.makeText(getApplicationContext(), userData.getUser_email(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), userData.getUser_name(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), userData.getUser_nickname(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), userData.getUser_password(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(userData.getOnlogin()), Toast.LENGTH_SHORT).show();*/


                break;

        }
        return false;
    }
}
