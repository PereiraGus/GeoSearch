package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView nav;
    private LinearLayout layoutLogin;
    private LinearLayout layoutNoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        layoutLogin = (LinearLayout) findViewById(R.id.layoutLogin);
        layoutNoLogin = (LinearLayout) findViewById(R.id.layoutNoLogin);

        nav = (BottomNavigationView) findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.btnBarProf);
        nav.setOnNavigationItemSelectedListener(this);
    }

    public void goToSignIn (View view){
        Intent intent = new Intent(getApplicationContext(),signIn.class);
    }

    public void goToLogin (View view){
        Intent intent = new Intent(getApplicationContext(),login.class);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.home:
                Intent intentH = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(intentH);
                return true;
            case R.id.map:
                Intent intentM = new Intent(getApplicationContext(), selectionMap.class);
                startActivity(intentM);
                return true;
            case R.id.prof:
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}