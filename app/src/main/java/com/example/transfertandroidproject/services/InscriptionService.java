package com.example.transfertandroidproject.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.transfertandroidproject.config.EnvVariable;
import com.example.transfertandroidproject.helpers.Helpers;
import com.example.transfertandroidproject.http.SingletonHttpRequest;
import com.example.transfertandroidproject.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class InscriptionService {

    private Context _ctx;
    public InscriptionService(Context ctx){
        this._ctx = ctx;
    }


    public void doTheLogin(User user) throws JSONException {

        //create the object
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", user.getEmail());
        jsonBody.put("password", user.getPassword());
        jsonBody.put("country", user.getCountry());
        jsonBody.put("surname",user.getSurname());
        jsonBody.put("name",user.getName());
        jsonBody.put("matricule",user.getMatricule());
        jsonBody.put("age",user.getAge());
        jsonBody.put("phone",user.getPhone());
        final String requestBody = jsonBody.toString();

        //set the url
        String URL = EnvVariable.BASE_URL+"user";

        //JsonRequest jsonRequest = new
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,URL,null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")){

                        try {
                            System.out.println(response.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        JSONObject json = new JSONObject();
                        Toast.makeText(_ctx,response.toString(),Toast.LENGTH_LONG).show();

                    }else{

                        new Helpers(_ctx).SimpleNotification ("IDENTIFIANTS INCORRECTES VEUILLEZ " +
                                "VOUS INSCRIRE" , "INFORMATION");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(_ctx,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody
                            .getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

        };


        //add the request
        new SingletonHttpRequest(_ctx).addToRequestQueue(stringRequest);
    }












}
