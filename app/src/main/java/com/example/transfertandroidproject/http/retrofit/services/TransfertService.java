package com.example.transfertandroidproject.http.retrofit.services;

import com.example.transfertandroidproject.http.retrofit.entities.Transfert;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TransfertService {

    @GET("transfert")
    Call<Transfert[]> getAllTransfert();

    @GET("emetteur/{id}")
    Call<Transfert[]> getTheTransfertWhereUserIsEmetteur(@Path("id") int id);
}
