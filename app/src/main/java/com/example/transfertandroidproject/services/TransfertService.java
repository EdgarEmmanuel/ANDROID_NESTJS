package com.example.transfertandroidproject.services;

import android.content.Context;
import android.widget.Toast;

import com.example.transfertandroidproject.Storage.SharedPrefStorage;
import com.example.transfertandroidproject.http.retrofit.core.RetrofitBuilder;
import com.example.transfertandroidproject.http.retrofit.entities.Transfert;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransfertService {
    private Context _ctx;
    private SharedPrefStorage tokenManager;
    private com.example.transfertandroidproject.http.retrofit.services.TransfertService transfertService;

    public TransfertService(Context ctx){
        this._ctx = ctx;

        // create the token manager instance
        tokenManager =  SharedPrefStorage.getInstance(_ctx.getSharedPreferences("prefs",_ctx.MODE_PRIVATE));

        // the transfert service
        transfertService = new RetrofitBuilder().createServiceWithAuth(com.example.transfertandroidproject.http.retrofit.services.TransfertService.class, tokenManager);
    }

    /**
     * get all the transfert
     */

    public void getAllTransfert(){

        Call<Transfert[]> transfert = transfertService.getAllTransfert();

        transfert.enqueue(new Callback<Transfert[]>() {
            @Override
            public void onResponse(Call<Transfert[]> call, Response<Transfert[]> response) {
                int num = response.body().length;
                List<Transfert> allTransfert = new ArrayList<>();
                for (int i =0 ; i<num;i++){
                    allTransfert.add(response.body()[i]);
                }
                Toast.makeText(_ctx,allTransfert.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Transfert[]> call, Throwable t) {
                Toast.makeText(_ctx,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * get one transfert
     */

    public void getTheTransfertWhereUserIsEmetteur(int id){
        Call<Transfert[]> allTransfert = transfertService.getTheTransfertWhereUserIsEmetteur(id);

        allTransfert.enqueue(new Callback<Transfert[]>() {
            @Override
            public void onResponse(Call<Transfert[]> call, Response<Transfert[]> response) {
                int num = response.body().length;
                List<Transfert> allTransferts = new ArrayList<>();
                for (int i =0 ; i<num;i++){
                    allTransferts.add(response.body()[i]);
                }
                Toast.makeText(_ctx,allTransferts.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Transfert[]> call, Throwable t) {
                Toast.makeText(_ctx,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
