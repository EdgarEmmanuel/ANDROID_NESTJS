package com.example.transfertandroidproject.http.retrofit.core;

import android.widget.Toast;

import com.example.transfertandroidproject.Storage.SharedPrefStorage;
import com.example.transfertandroidproject.http.retrofit.entities.TokenRefresh;
import com.example.transfertandroidproject.http.retrofit.entities.UserToken;
import com.example.transfertandroidproject.http.retrofit.services.UserService;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;

public class RetrofitAuthenticator implements Authenticator {

    private SharedPrefStorage sharedPrefStorage;
    private static  RetrofitAuthenticator retrofitAuthenticator;

    private RetrofitAuthenticator(SharedPrefStorage sharedPrefStorage){
        this.sharedPrefStorage = sharedPrefStorage;
    }

    public static synchronized RetrofitAuthenticator getInstance(SharedPrefStorage sharedPrefStorage){
        if(retrofitAuthenticator==null){
            retrofitAuthenticator = new RetrofitAuthenticator(sharedPrefStorage);
        }
        return retrofitAuthenticator;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (responseCount(response)>=2){
            return null;
        }
        UserService userService = RetrofitBuilder.createService(UserService.class);
        Call<UserToken> user_refreshed =
                userService.getRefresh(new TokenRefresh(sharedPrefStorage.getToken().getRefreshToken()));

       retrofit2.Response<UserToken> responseOfRefresh = user_refreshed.execute();

       if(responseOfRefresh.isSuccessful()){
           UserToken newToken = responseOfRefresh.body();
           sharedPrefStorage.saveToken(newToken);

           return response.request().newBuilder()
                   .header("Authorization","Bearer "+responseOfRefresh.body().getToken())
                   .build();
       }else{
           return null;
       }

    }


    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }


}
