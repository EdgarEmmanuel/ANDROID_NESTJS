package com.example.transfertandroidproject.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.transfertandroidproject.R;

public class About extends AppCompatActivity {

    DrawerLayout drawerLayout ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View v){
        Home.openDrawer(drawerLayout);
    }

    public void  ClickLogo(View v){
        Home.closeDrawer(drawerLayout);
    }

    public void ClickHome(View v){
        startActivity(new Intent(About.this,Home.class));
    }


    public void ClickAboutUs(View v){
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Home.closeDrawer(drawerLayout);
    }
}
