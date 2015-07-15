package com.android.newgeneration.dandisnap.Login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.newgeneration.dandisnap.R;

/**
 * Created by 천보경 on 2015-07-09.
 */
public class UserData {
    private static final String mFilename = "userdata";
    private String mUseremail;
    private String mUsername;
    private String mUsernickname;
    private String mUserpassword;
    private int mOnlogin;
    private volatile static UserData uniqueInstance = null;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    static Context mContext;


    private UserData() {
    }

    public static UserData getInstance() {
        if (uniqueInstance == null) {
            // 이렇게 하면 처음에만 동기화 된다
            synchronized (UserData.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new UserData();
                }
            }
        }
        return uniqueInstance;
    }

    //Sharedpreference에서 ActivityLogin에서 값을 받아올 때 ActivityLogin의 정보가 필요하므로 Context 설정!!
    public String getUser_email() {
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        return mSharedPreferences.getString(String.valueOf(R.string.userdata_key_email), "");

    }

    public void setUser_email(String mUseremail, Context mContext) {
        this.mUseremail = mUseremail;
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(String.valueOf(R.string.userdata_key_email), mUseremail);
        mEditor.commit();
    }

    public String getUser_name() {
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        return mSharedPreferences.getString(String.valueOf(R.string.userdata_key_name), "");
    }

    public void setUser_name(String mUsername, Context mContext) {
        this.mUsername = mUsername;
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(String.valueOf(R.string.userdata_key_name), mUsername);
        mEditor.commit();
    }

    public String getUser_nickname() {
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        return mSharedPreferences.getString(String.valueOf(R.string.userdata_key_nick), "");
    }

    public void setUser_nickname(String mUsernickname, Context mContext) {
        this.mUsernickname = mUsernickname;
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(String.valueOf(R.string.userdata_key_nick), mUsernickname);
        mEditor.commit();
    }

    public String getUser_password() {
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        return mSharedPreferences.getString(String.valueOf(R.string.userdata_key_psw), "");
    }

    public void setUser_password(String mUserpassword, Context mContext) {
        this.mUserpassword = mUserpassword;
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(String.valueOf(R.string.userdata_key_psw), mUserpassword);
        mEditor.commit();
    }

    public int getOnlogin() {
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        return mSharedPreferences.getInt(String.valueOf(R.string.userdata_key_onlogin), 0);

    }

    public void setOnlogin(int mOnlogin, Context mContext) {
        this.mOnlogin = mOnlogin;
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(String.valueOf(R.string.userdata_key_onlogin), mOnlogin);
        mEditor.commit();

    }

    public void setCompareOnlogin(int comp_Onlogin, Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(String.valueOf(R.string.userdata_key_compare), 1);
        mEditor.commit();
    }

    public int getCompareOnlogin() {
        mSharedPreferences = mContext.getSharedPreferences(mFilename, Activity.MODE_PRIVATE);
        return mSharedPreferences.getInt(String.valueOf(R.string.userdata_key_compare), 0);
    }
}
