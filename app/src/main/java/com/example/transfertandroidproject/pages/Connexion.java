package com.example.transfertandroidproject.pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.transfertandroidproject.MainActivity;
import com.example.transfertandroidproject.R;
import com.example.transfertandroidproject.Storage.SharedPrefStorage;
import com.example.transfertandroidproject.helpers.Helpers;
import com.example.transfertandroidproject.http.SingletonHttpRequest;
import com.example.transfertandroidproject.http.retrofit.entities.UserToken;
import com.example.transfertandroidproject.services.ConnexionService;

import org.json.JSONArray;
import org.json.JSONException;

public class Connexion extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        //get all the form fields
        EditText text_email = findViewById(R.id.TextEmailAddress);
        EditText text_password = findViewById(R.id.TextPassword);
        Button button_login = findViewById(R.id.button3);
        Button button_goToLoginPage = findViewById(R.id.inscrireButton);
        Button button_passwordForgetButton = findViewById(R.id.passwordForgetButton);

        //the button s'inscrire
        button_goToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(Connexion.this,Inscription.class);
            }
        });


        //the button mot de passe ounbliee
        button_passwordForgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Helpers(Connexion.this).SimpleNotification("MOT DE PASSE OUBLIEE  "
                        , "INFORMATION");
            }
        });



        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //we verify the fields of the form
                if(text_email.getText().toString().trim().equalsIgnoreCase("") &&
                        text_password.getText().toString().trim().equalsIgnoreCase("")){

                    new Helpers(Connexion.this).SimpleNotification(
                            "TOUS KES CHAMPS DOIVENT ETRE RENSEIGNES","ERREUR FORMULAIRE");
                }else{



                    try {
                        // send the data to the APIS
                        new ConnexionService(Connexion.this)
                                .doTheLogin(text_email.getText().toString().trim(),
                                        text_password.getText().toString().trim());

                        // we verify if the data in the sharedPrefs are null
                        UserToken userToken = SharedPrefStorage
                                .getInstance(Connexion.this.getSharedPreferences("prefs",
                                        Context.MODE_PRIVATE))
                                .getToken();

                        if(userToken.getToken()==null){
                            startActivity(
                                    new Intent(Connexion.this, MainActivity.class)
                                    .putExtra("NOTIFICATION_MESSAGE","CES IDENTIFIANTS SONT INCONNUS VEUILLEZ VOUS INSCRIRE")
                            );
                        }else{
                            startActivity(new Intent(Connexion.this,Home.class));
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


    }











}
