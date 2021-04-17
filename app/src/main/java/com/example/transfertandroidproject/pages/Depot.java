package com.example.transfertandroidproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.transfertandroidproject.R;

public class Depot extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depot);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View v){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View v){
        closeDrawer(drawerLayout);
    }

    public  static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View v){
        startActivity(new Intent(Depot.this,Home.class));
    }

    public void ClickDepots(View v){
        recreate();

    }

    public void ClickRetraits(View v){
        startActivity(new Intent(Depot.this,Retrait.class));
    }

    public void ClickLogout(View v){
        // deconnexion
    }

    /**
     * go to the Profile page
     */
    public void ClickProfile(View v){
        startActivity(new Intent(Depot.this,Profile.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }




}
