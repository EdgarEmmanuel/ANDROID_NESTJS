package com.example.transfertandroidproject.Storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.transfertandroidproject.http.retrofit.entities.UserToken;
import com.example.transfertandroidproject.pages.Home;

public class SharedPrefStorage {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static SharedPrefStorage INSTANCE = null;

    public SharedPrefStorage(SharedPreferences preferences){
        this.sharedPreferences = preferences;
        this.editor = this.sharedPreferences.edit();
    }


    public static synchronized SharedPrefStorage getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new SharedPrefStorage(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(UserToken token){
        editor.putString("ACCESS_TOKEN", token.getToken()).commit();
        editor.putString("REFRESH_TOKEN",token.getRefreshToken()).commit();
        editor.putString("EXPIRES_IN",token.getExpiresIn()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
        editor.remove("EXPIRES_IN").commit();
    }

    public UserToken getToken(){
        UserToken token = new UserToken();
        token.setToken(sharedPreferences.getString("ACCESS_TOKEN", null));
        token.setExpiresIn(sharedPreferences.getString("EXPIRES_IN",null));
        token.setRefreshToken(sharedPreferences.getString("REFRESH_TOKEN",null));
        return token;
    }

    public Boolean isUserLoggedIn(){
        if(this.getToken().getToken()!=null){
            return true;
        }else{
            return false;
        }
    }






}
