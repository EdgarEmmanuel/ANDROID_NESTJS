package com.example.transfertandroidproject.services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transfertandroidproject.Storage.SharedPrefStorage;
import com.example.transfertandroidproject.http.retrofit.core.RetrofitBuilder;
import com.example.transfertandroidproject.http.retrofit.entities.User;
import com.example.transfertandroidproject.http.retrofit.entities.UserLoginInfo;
import com.example.transfertandroidproject.http.retrofit.entities.UserToken;
import com.example.transfertandroidproject.http.retrofit.services.UserService;
import com.example.transfertandroidproject.pages.Connexion;
import com.example.transfertandroidproject.pages.Home;
import com.example.transfertandroidproject.pages.Inscription;

import org.json.JSONException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnexionService {
    Context _ctx;
    private UserService userService;

    public ConnexionService(Context ctx){
        this._ctx = ctx;

        //create the retrofit instance
        userService= new RetrofitBuilder().createService(UserService.class);
    }

    public void doTheLogin(String email , String password) throws JSONException {
       Call<UserToken> user = userService.getOneUser(new UserLoginInfo(email,password));

        user.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {

                if(response.body().getToken()==null){

                        //we clear all the data in the shared prefs
                    new SharedPrefStorage(_ctx.getSharedPreferences("prefs",Context.MODE_PRIVATE))
                            .deleteToken();

                }else{

                    // put the token information in the sharedPrefs storage
                    new SharedPrefStorage(_ctx.getSharedPreferences("prefs",Context.MODE_PRIVATE))
                            .saveToken(response.body());

                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                new SharedPrefStorage(_ctx.getSharedPreferences("prefs",Context.MODE_PRIVATE))
                        .deleteToken();
            }
        });
    }


    /**
     * get all users
     */
    public void getAllUsers(){
        //d o te request
        Call<List<User>> users = userService.getAllUser();

        // wait for the response
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(_ctx,"code : "+response.code(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(_ctx,response.toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(_ctx,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }









}
