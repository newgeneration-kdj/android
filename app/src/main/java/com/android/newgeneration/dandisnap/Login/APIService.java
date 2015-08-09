package com.android.newgeneration.dandisnap.Login;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Ãµº¸°æ on 2015-08-02.
 */
public interface APIService {
    @GET("/duplicate/emails/{email}")
    void getEmail(@Path("email") String email, Callback<JsonElement> callback);

    @GET("/duplicate/usernames/{username}")
    void getUsername(@Path("username") String username, Callback<JsonElement> callback);

<<<<<<< HEAD:app/src/main/java/com/android/newgeneration/dandisnap/Login/LoginService.java
=======
    @FormUrlEncoded
    @POST("/login")
    void login(@Field("email") String email , @Field("username") String username, @Field("name") String name, @Field("password") String password, Callback<JsonElement> callback);
>>>>>>> d0f059de3ac6b96886f77f1ea06f109598355196:app/src/main/java/com/android/newgeneration/dandisnap/Login/APIService.java

}
