package com.example.transfertandroidproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.transfertandroidproject.R;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

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
        recreate();
    }

    public void ClickDepots(View v){
        startActivity(new Intent(Home.this,Depot.class));
    }

    public void ClickRetraits(View v){
       startActivity(new Intent(Home.this,Retrait.class));
    }

    public void ClickLogout(View v){
        // deconnexion
    }

    /**
     * go to the Profile page
     */
    public void ClickProfile(View v){
        startActivity(new Intent(Home.this,Profile.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
