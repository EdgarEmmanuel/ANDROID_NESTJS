package com.example.transfertandroidproject.pages;

import android.content.Intent;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transfertandroidproject.MainActivity;
import com.example.transfertandroidproject.R;
import com.example.transfertandroidproject.helpers.Helpers;
import com.example.transfertandroidproject.helpers.Utils;

import java.util.ArrayList;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);


        //get all the variables
        View imageRight = findViewById(R.id.imageRight);
        Button button_seConnecter = findViewById(R.id.inscrireButton);
        EditText editText_textName = findViewById(R.id.TextName);
        EditText editText_TextSurname = findViewById(R.id.TextSurname);
        EditText editText_TextEmail = findViewById(R.id.TextEmail);
        EditText editText_TextPays = findViewById(R.id.TextPays);
        EditText editText_TextAge = findViewById(R.id.TextAge);
        EditText editText_textPhone = findViewById(R.id.textPhone);


        /**
         * event when we click on the the arrow to validate the field
         */
        imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize the array and push the values
                ArrayList<String> arraysOfValues = new ArrayList<>();
                arraysOfValues.add(editText_textName.getText().toString());
                arraysOfValues.add(editText_TextSurname.getText().toString());
                arraysOfValues.add(editText_TextEmail.getText().toString());
                arraysOfValues.add(editText_TextPays.getText().toString());
                arraysOfValues.add(editText_textPhone.getText().toString());

                //array for the integer
                ArrayList<String> arraysOfInteger = new ArrayList<>();
                arraysOfInteger.add(editText_TextAge.getText().toString());

                if(new Utils().verifyStringFields(arraysOfValues)){
                    if(new Utils().verifyIntFields(arraysOfInteger)){
                        new Helpers(Inscription.this).SimpleNotification("ALL IS GOOD"
                                ,"INFORMATION");
                    }else{
                        //only the field age has a problem
                        new Helpers(Inscription.this).SimpleNotification(
                                "THE FIELD AGE SHOULD HAVE A NUMERIC VALUE",
                                "INFORMATION");
                    }

                }else{
                    new Helpers(Inscription.this).SimpleNotification("ALL THE FIELD SHOULD BE FILLED"
                            ,"INFORMATION");
                }
            }
        });

        /**
         * event when we click on the connexion button
         */
        button_seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Intent(Inscription.this,Connexion.class);
            }
        });
    }
}
