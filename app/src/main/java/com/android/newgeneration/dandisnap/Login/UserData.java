package com.android.newgeneration.dandisnap.Login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 천보경 on 2015-07-09.
 */
public class UserData{
    private String user_email;
    private String user_name;
    private String user_nickname;
    private String user_password;
    private int onlogin;
    private volatile static UserData uniqueInstance=null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static Context context;


    private UserData(){    }

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
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_email", "");

    }

    public void setUser_email(String user_email,Context context) {
        this.user_email = user_email;
        this.context = context;
        sharedPreferences =context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_email", user_email);
        editor.commit();
    }

    public String getUser_name() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_name", "");
    }

    public void setUser_name(String user_name,Context context) {
        this.user_name = user_name;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_name", user_name);
        editor.commit();
    }

    public String getUser_nickname() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_nickname", "");
    }

    public void setUser_nickname(String user_nickname,Context context) {
        this.user_nickname = user_nickname;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_nickname", user_nickname);
        editor.commit();
    }

    public String getUser_password() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("user_password", "");
    }

    public void setUser_password(String user_password,Context context) {
        this.user_password = user_password;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userdata",Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user_password", user_password);
        editor.commit();
    }

    public int getOnlogin() {
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        return sharedPreferences.getInt("onlogin", 0);

    }

    public void setOnlogin(int onlogin, Context context) {
        this.onlogin = onlogin;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userdata", Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("onlogin", onlogin);
        editor.commit();

    }
    public void setCompareOnlogin(int comp_Onlogin, Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userdata",Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("com_Onlogin",1);
        editor.commit();
    }
    public int getCompareOnlogin(){
        sharedPreferences = context.getSharedPreferences("userdata",Activity.MODE_PRIVATE);
        return sharedPreferences.getInt("com_Onlogin",0);
    }
}
