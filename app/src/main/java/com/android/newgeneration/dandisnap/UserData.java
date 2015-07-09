package com.android.newgeneration.dandisnap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 천보경 on 2015-07-09.
 */
public class UserData {
    private String user_email;
    private String user_name;
    private String user_nickname;
    private String user_password;
    private int onlogin;
    //  private final String User_Data = "userdata";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static Context context;

    public UserData(Context context) {
        this.context = context;
    }

    public String getUser_email() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_email", "");

    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_email", user_email);
        editor.commit();
    }

    public String getUser_name() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_name", "");
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_name", user_name);
        editor.commit();
    }

    public String getUser_nickname() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_nickname", "");
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_nickname", user_nickname);
        editor.commit();
    }

    public String getUser_password() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_password", "");
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_password", user_password);
        editor.commit();
    }

    public int getOnlogin() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getInt("onlogin", 0);

    }

    public void setOnlogin(int onlogin) {
        this.onlogin = onlogin;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("onlogin",onlogin);
        editor.commit();
    }
}
