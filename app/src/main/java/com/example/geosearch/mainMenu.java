package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutionException;

public class mainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtSearch = (EditText) findViewById(R.id.txtSearchQuery);
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
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.KEYCODE_ENTER) {
                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("query",txtSearch.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), searchResult.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}