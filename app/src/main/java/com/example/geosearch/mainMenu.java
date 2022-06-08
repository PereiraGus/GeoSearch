package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.btnBarHome);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.btnBarHome:
                            Intent intentH = new Intent(getApplicationContext(), mainMenu.class);
                            startActivity(intentH);
                        return true;
                    case R.id.btnBarMap:
                        try {
                        Intent intentM = new Intent(getApplicationContext(), selectionMap.class);
                        startActivity(intentM);
                        }
                                catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        return true;
                    case R.id.btnBarProf:
                        Intent intentP = new Intent(getApplicationContext(), profileActivity.class);
                        startActivity(intentP);
                        return true;
                }
                return false;
            }
        });

        String respAPI = null;
        SegundoPlano segundoPlano = new SegundoPlano();
        try {
            respAPI = segundoPlano.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Resultado:",respAPI);
    }
}