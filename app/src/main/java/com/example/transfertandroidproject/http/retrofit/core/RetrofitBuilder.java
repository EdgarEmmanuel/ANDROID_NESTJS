package com.example.transfertandroidproject.http.retrofit.core;

import com.example.transfertandroidproject.Storage.SharedPrefStorage;
import com.example.transfertandroidproject.config.EnvVariable;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = new EnvVariable().BASE_URL;

    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = buildRetrofit(client);

    private static OkHttpClient buildClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request.Builder builder = request.newBuilder()
                                .addHeader("Accept", "application/json")
                                .addHeader("Connection", "close");

                        request = builder.build();

                        return chain.proceed(request);
                    }
                });

//        if(BuildConfig.DEBUG){
//            builder.addNetworkInterceptor(new StethoInterceptor());
//        }

        return builder.build();
    }

    private static Retrofit buildRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> service){
        return retrofit.create(service);
    }

    //to add the authorization in the header
    public static <T> T createServiceWithAuth(Class<T> service , final SharedPrefStorage prefs){

        OkHttpClient newClient = client.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder builder = request.newBuilder();

                if(prefs.getToken().getToken() != null){
                    builder.addHeader("Authorization", "Bearer " + prefs.getToken().getToken());
                }
                request = builder.build();
                return chain.proceed(request);
            }
        }).authenticator(RetrofitAuthenticator.getInstance(prefs)).build();

        Retrofit newRetrofit = buildRetrofit(newClient);

        return newRetrofit.create(service);

    }



}
