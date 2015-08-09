package com.android.newgeneration.dandisnap.Login;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Ãµº¸°æ on 2015-08-02.
 */
public interface LoginService {
    @GET("/duplicate/emails/{email}")
    void getEmail(@Path("email") String email, Callback<JsonElement> callback);

    @GET("/duplicate/usernames/{username}")
    void getUsername(@Path("username") String username, Callback<JsonElement> callback);


}
