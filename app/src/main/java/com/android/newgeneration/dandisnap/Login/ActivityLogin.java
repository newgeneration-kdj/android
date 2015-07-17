package com.android.newgeneration.dandisnap.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.android.newgeneration.dandisnap.Main.ActivityMain;
import com.android.newgeneration.dandisnap.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ActivityLogin extends FragmentActivity implements OnEditorActionListener {
    @InjectView(R.id.login_btn_signup)
    Button mLoginBtnSignup;
    @InjectView(R.id.login_btn_login)
    Button mLoginBtnLogin;
    @InjectView(R.id.login_rl_signup)
    RelativeLayout mLoginRlSignup;
    @InjectView(R.id.login_rl_login)
    RelativeLayout mLoginRlLogin;
    @InjectView(R.id.login_edit_username)
    EditText mLoginEditUsername;
    @InjectView(R.id.login_edit_psw)
    EditText mLoginEditPsw;
    @InjectView(R.id.login_edit_email)
    EditText mLoginEditEmail;
    @InjectView(R.id.login_fl_maincontainer)
    FrameLayout mLoginFlMaincontainer;
    @InjectView(R.id.login_fl_namecontainer)
    FrameLayout mLoginflNamecontainer;
    @InjectView(R.id.login_edit_name)
    EditText mLoginEditName;
    @InjectView(R.id.login_btn_backname)
    Button mloginBtnBackname;
    @InjectView(R.id.login_fl_nickcontainer)
    FrameLayout mLoginFlNickcontainer;
    @InjectView(R.id.login_edit_nick)
    EditText mLoginEditNick;
    @InjectView(R.id.login_btn_backnick)
    Button mLoginBtnBacknick;
    @InjectView(R.id.login_fl_pswcontainer)
    FrameLayout mLoginFlPswcontainer;
    @InjectView(R.id.login_edit_makepsw)
    EditText mLoginEditMakepsw;
    @InjectView(R.id.login_btn_backpsw)
    Button mLoginBtnBackpsw;
    @InjectView(R.id.login_txt_error)
    TextView mLoginTxtError;
    @InjectView(R.id.login_btn_findpsw)
    Button mLoginBtnFindpsw;
    UserData mUserData = UserData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mUserData.setCompareOnlogin(1, this);
        setOnEditorActionListener();
        checkOnlogin();
    }


    public void checkOnlogin() {
        if (mUserData.getOnlogin() == mUserData.getCompareOnlogin()) {
            Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
            startActivity(intent);
            finish();
        }
    }

    public void setOnEditorActionListener() {
        mLoginEditUsername.setOnEditorActionListener(this);
        mLoginEditPsw.setOnEditorActionListener(this);
        mLoginEditEmail.setOnEditorActionListener(this);
        mLoginEditName.setOnEditorActionListener(this);
        mLoginEditNick.setOnEditorActionListener(this);
        mLoginEditMakepsw.setOnEditorActionListener(this);
       /* if (mUserData.getOnlogin() == mUserData.getCompareOnlogin())
            mLoginEditUsername.setText(mUserData.getUser_nickname());*/
    }

    @OnClick({R.id.login_btn_signup, R.id.login_btn_login, R.id.login_btn_backname, R.id.login_btn_backnick, R.id.login_btn_backpsw, R.id.login_btn_findpsw})
    void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_signup:
                convertLayout(R.id.login_btn_signup);
                break;
            case R.id.login_btn_login:
                convertLayout(R.id.login_btn_login);
                break;
            case R.id.login_btn_backname:
                convertLayout(R.id.login_btn_backname);
                break;
            case R.id.login_btn_backnick:
                convertLayout(R.id.login_btn_backnick);
                break;
            case R.id.login_btn_backpsw:
                convertLayout(R.id.login_btn_backpsw);
                break;
            case R.id.login_btn_findpsw:
                Intent intent = new Intent(getApplicationContext(), ActivityFindpsw.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (v.getId()) {
            case R.id.login_edit_username:
                break;
            case R.id.login_edit_psw:
                handlerUserdata(R.id.login_edit_psw);
                break;
            case R.id.login_edit_email:
                if (!mLoginEditEmail.getText().toString().isEmpty()) {
                    convertLayout(R.id.login_edit_email);
                    handlerUserdata(R.id.login_edit_email);
                }
                break;
            case R.id.login_edit_name:
                convertLayout(R.id.login_edit_name);
                handlerUserdata(R.id.login_edit_name);
                break;
            case R.id.login_edit_nick:
                convertLayout(R.id.login_edit_nick);
                handlerUserdata(R.id.login_edit_nick);
                break;
            case R.id.login_edit_makepsw:
                handlerUserdata(R.id.login_edit_makepsw);
                Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }

    public void convertLayout(int viewId) {
        switch (viewId) {
            case R.id.login_btn_signup:
                mLoginBtnSignup.setTextColor(Color.WHITE);
                mLoginBtnLogin.setTextColor(Color.GRAY);
                mLoginRlSignup.setVisibility(View.VISIBLE);
                mLoginRlLogin.setVisibility(View.GONE);
                break;
            case R.id.login_btn_login:
                mLoginBtnLogin.setTextColor(Color.WHITE);
                mLoginBtnSignup.setTextColor(Color.GRAY);
                mLoginRlSignup.setVisibility(View.GONE);
                mLoginRlLogin.setVisibility(View.VISIBLE);
                mLoginTxtError.setText("");
                break;
            case R.id.login_btn_backname:
                mLoginFlMaincontainer.setVisibility(View.VISIBLE);
                mLoginflNamecontainer.setVisibility(View.GONE);
                break;
            case R.id.login_btn_backnick:
                mLoginflNamecontainer.setVisibility(View.VISIBLE);
                mLoginFlNickcontainer.setVisibility(View.GONE);
                break;
            case R.id.login_btn_backpsw:
                mLoginFlNickcontainer.setVisibility(View.VISIBLE);
                mLoginFlPswcontainer.setVisibility(View.GONE);
                break;
            case R.id.login_edit_email:
                mLoginFlMaincontainer.setVisibility(View.GONE);
                mLoginflNamecontainer.setVisibility(View.VISIBLE);
                break;
            case R.id.login_edit_name:
                mLoginflNamecontainer.setVisibility(View.GONE);
                mLoginFlNickcontainer.setVisibility(View.VISIBLE);
                break;
            case R.id.login_edit_nick:
                mLoginFlNickcontainer.setVisibility(View.GONE);
                mLoginFlPswcontainer.setVisibility(View.VISIBLE);
                break;
        }

    }
    public void handlerUserdata(int viewId) {
        switch (viewId) {
            case R.id.login_edit_psw:
                if (mLoginEditUsername.getText().toString().isEmpty()) {
                    mLoginTxtError.setText(R.string.login_username);
                } else if (mLoginEditPsw.getText().toString().isEmpty()) {
                    mLoginTxtError.setText(R.string.login_psw);
                } else if (mLoginEditPsw.getText().toString().equals(mUserData.getUser_password()) && mLoginEditUsername.getText().toString().equals(mUserData.getUser_nickname())) {
                    mLoginTxtError.setText("");
                    mUserData.setOnlogin(1, this);
                    Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    mLoginTxtError.setText(R.string.login_wrongmsg);
                }
                //완료버튼 누르면 로그인이 되고, 메인화면으로 넘어가는 이벤트
                break;
            case R.id.login_edit_email:
                mUserData.setUser_email(mLoginEditEmail.getText().toString(), this);
                break;
            case R.id.login_edit_name:
                mUserData.setUser_name(mLoginEditName.getText().toString(), this);
                break;
            case R.id.login_edit_nick:
                mUserData.setUser_nickname(mLoginEditNick.getText().toString(), this);
                break;
            case R.id.login_edit_makepsw:
                mUserData.setUser_password(mLoginEditMakepsw.getText().toString(), this);
                mUserData.setOnlogin(1, this);
                break;
        }

    }
}
