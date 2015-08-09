package com.android.newgeneration.dandisnap.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.android.newgeneration.dandisnap.Main.ActivityMain;
import com.android.newgeneration.dandisnap.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityLogin extends Activity implements OnEditorActionListener {
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
    Button mLoginBtnBackname;
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
    @InjectView(R.id.login_btn_facebook)
    Button mLoginBtnFacebook;
    @InjectView(R.id.login_btn_facebook2)
    Button mLoginBtnFacebook2;
    @InjectView(R.id.login_btn_enter)
    Button mLoginBtnEnter;
    CallbackManager mCallbackManager;
    private static String mBufferemail = "";
    private static String mBuffername = "";
    private static String mBufferusername = "";
    private static String mBufferpsw = "";
    APIService mAPIService;
    RestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mUserData.setCompareOnlogin(1, this);
        setOnEditorActionListener();
        checkOnlogin();
        setInit();
        checkEditText();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://server-newgeneration.azurewebsites.net")
                .build();
        mAPIService = restAdapter.create(APIService.class);


    }


    public void setInit() {
        if (mUserData.getUser_nickname() != null) {
            mLoginEditUsername.setText(mUserData.getUser_nickname());
        }
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
    }

    @OnClick({R.id.login_btn_signup, R.id.login_btn_login, R.id.login_btn_backname, R.id.login_btn_backnick,
            R.id.login_btn_backpsw, R.id.login_btn_findpsw, R.id.login_btn_facebook, R.id.login_btn_facebook2,
            R.id.login_btn_enter})
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
            case R.id.login_btn_facebook:
                loginFacebook();
                break;
            case R.id.login_btn_facebook2:
                loginFacebook();
                break;
            case R.id.login_btn_enter:
                enterToMain();
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
                    // convertLayout(R.id.login_edit_email);
                    handlerUserdata(R.id.login_edit_email);
                    duplication(R.id.login_edit_email);
                }
                break;
            case R.id.login_edit_name:
                convertLayout(R.id.login_edit_name);
                handlerUserdata(R.id.login_edit_name);
                break;
            case R.id.login_edit_nick:
                if (!mLoginEditNick.getText().toString().isEmpty()) {
                   // convertLayout(R.id.login_edit_nick);
                    handlerUserdata(R.id.login_edit_nick);
                    duplication(R.id.login_edit_nick);
                }
                break;
            case R.id.login_edit_makepsw:
                if (!mLoginEditMakepsw.getText().toString().isEmpty()) {
                    handlerUserdata(R.id.login_edit_makepsw);
                    postLogindata();
                    Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                    startActivity(intent);
                    finish();
                }
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
                enterToMain();
                break;
            case R.id.login_edit_email:
                mBufferemail = mLoginEditEmail.getText().toString();
                //mUserData.setUser_email(mLoginEditEmail.getText().toString(), this);
                break;
            case R.id.login_edit_name:
                mBuffername = mLoginEditName.getText().toString();
                // mUserData.setUser_name(mLoginEditName.getText().toString(), this);
                break;
            case R.id.login_edit_nick:
                mBufferusername = mLoginEditNick.getText().toString();
                //  mUserData.setUser_nickname(mLoginEditNick.getText().toString(), this);
                break;
            case R.id.login_edit_makepsw:
                mBufferpsw = mLoginEditMakepsw.getText().toString();
                mUserData.setUser_email(mBufferemail, this);
                mUserData.setUser_name(mBuffername, this);
                mUserData.setUser_nickname(mBufferusername, this);
                mUserData.setUser_password(mBufferpsw, this);
                mUserData.setOnlogin(1, this);
                break;
        }

    }

    public void loginFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                                parseEmail(graphResponse.getJSONObject().optString("email"));
                                if (!mUserData.getUser_email().equals(graphResponse.getJSONObject().optString("email"))) {
                                    mBufferemail = graphResponse.getJSONObject().optString("email");
                                    duplication(R.id.login_edit_email);
                                    //convertLayout(R.id.login_edit_email);
                                    mLoginEditName.setText(mBuffername);
                                } else {
                                    Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                                    startActivity(intent);
                                    finish();
                                    mUserData.setOnlogin(1, getApplicationContext());
                                }
                            }
                        });
                        Bundle bundle = new Bundle();
                        bundle.putString("fields", "email");
                        request.setParameters(bundle);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Log.e("TEST", "CANCEL BTN");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), "로그인 에러", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT).show();
                        LoginManager.getInstance().logOut();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void parseEmail(String string) {
        String email = string;
        int count = email.indexOf("@");
        mBuffername = email.substring(0, count);
    }

    public void enterToMain() {
        if (mLoginEditPsw.getText().toString().equals(mUserData.getUser_password()) && mLoginEditUsername.getText().toString().equals(mUserData.getUser_nickname())
                && !mLoginEditPsw.getText().toString().equals("") && !mLoginEditUsername.getText().toString().equals("")) {
            mLoginTxtError.setText("");
            mUserData.setOnlogin(1, this);
            Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
            startActivity(intent);
            finish();
        } else {
            mLoginTxtError.setText(R.string.login_wrongmsg);
            mLoginTxtError.setTextColor(getResources().getColor(R.color.light_red));
        }
    }

    public void checkEditText() {
        mLoginEditPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mLoginEditPsw.getText().length() != 0) {
                    mLoginBtnFindpsw.setVisibility(View.GONE);
                    mLoginBtnEnter.setVisibility(View.VISIBLE);
                } else {
                    mLoginBtnFindpsw.setVisibility(View.VISIBLE);
                    mLoginBtnEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void duplication(int viewid) {
        switch (viewid) {
            case R.id.login_edit_email:
                mAPIService.getEmail(mBufferemail, new Callback<JsonElement>() {
                    @Override
                    public void success(JsonElement jsonElement, Response response) {
                        Log.d("debug_status", "exist" + jsonElement.getAsJsonObject().get("exist").getAsInt());
                        if (jsonElement.getAsJsonObject().get("exist").getAsInt() == 0) {
                            Toast.makeText(getApplicationContext(), "사용가능", Toast.LENGTH_SHORT).show();
                            convertLayout(R.id.login_edit_email);
                        } else {
                            Toast.makeText(getApplicationContext(), "사용불가", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                         Toast.makeText(getApplicationContext(),"이메일 접속에러",Toast.LENGTH_SHORT).show();
                        Log.d("error",error.getMessage());

                    }
                });
                break;
            case R.id.login_edit_nick:

                Log.d("test"," username = " + mBufferusername);
                mAPIService.getUsername(mBufferusername, new Callback<JsonElement>() {
                    @Override
                    public void success(JsonElement jsonElement, Response response) {
                        Log.d("test",jsonElement.getAsJsonObject().toString());
                        if (jsonElement.getAsJsonObject().get("exist").getAsInt() == 0) {
                            Toast.makeText(getApplicationContext(), "사용가능", Toast.LENGTH_SHORT).show();
                            convertLayout(R.id.login_edit_nick);
                        } else {
                            Toast.makeText(getApplicationContext(), "사용불가", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                         Toast.makeText(getApplicationContext(),"username 접속에러",Toast.LENGTH_SHORT).show();
                        Log.d("error", error.getMessage());
                    }
                });
                break;
        }


    }

    public void postLogindata(){
        mAPIService.login(mBufferemail, mBufferusername, mBuffername, mBufferpsw, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Log.d("test_post", jsonElement.getAsJsonObject().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error",error.getMessage());
            }
        });
    }

}



