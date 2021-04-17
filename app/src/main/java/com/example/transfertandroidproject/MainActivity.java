package com.example.transfertandroidproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transfertandroidproject.Storage.SharedPrefStorage;
import com.example.transfertandroidproject.helpers.Helpers;
import com.example.transfertandroidproject.pages.Connexion;
import com.example.transfertandroidproject.pages.Home;
import com.example.transfertandroidproject.pages.Inscription;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * we get the intent of the activity
         */
        Intent intent = getIntent();

        /**
         * we get the variable for the intent
         * @ps this will be the name for the variable in the system
         */
        String message = intent.getStringExtra("NOTIFICATION_MESSAGE");

        /**
         * we check if the variable is not  null and if yes we do the action
         */
        if(message!=null){
            new Helpers(MainActivity.this)
                    .SimpleNotification(message,"INFORMATION");
        }

        // get the data in the user storage
        SharedPrefStorage instance = SharedPrefStorage
                .getInstance(this.getSharedPreferences("prefs", Context.MODE_PRIVATE));

        Button button_connexion = findViewById(R.id.button);
        Button button_inscription = findViewById(R.id.button2);


        //redirection to connexion page
        button_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //startActivity(new Intent(MainActivity.this, Connexion.class));
                startActivity(new Intent(MainActivity.this, Connexion.class));
            }
        });

        //redirection to page inscription
        button_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Inscription.class));
            }
        });


    }
}