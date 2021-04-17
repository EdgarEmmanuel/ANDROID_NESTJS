package com.example.transfertandroidproject.helpers;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfertandroidproject.MainActivity;
import com.example.transfertandroidproject.R;


public class Helpers {
    private static Context context;

    public Helpers(Context context){
        this.context = context;
    }


    public  static Boolean ButtonNotification(String message , String title , String question){

        final Boolean[] isValidated = {false};

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message)
                .setPositiveButton(question, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isValidated[0] = true;
                    }
                })
                .setNegativeButton(question, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isValidated[0] = false;
                    }
                })
                .setTitle(title);

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();

        dialog.show();

        return isValidated[0];
    }


    public static void SimpleNotification (String message , String title){

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message)
                .setTitle(title);

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();

        dialog.show();
    }

}
