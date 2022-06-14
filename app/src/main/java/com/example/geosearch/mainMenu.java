package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.ExecutionException;

public class mainMenu extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtSearch = (EditText) findViewById(R.id.txtSearchQuery);
        nav = (BottomNavigationView) findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.btnBarHome);
        nav.setOnNavigationItemSelectedListener(this);

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Intent intent = new Intent(getApplicationContext(), countryPage.class);
                    intent.putExtra("query",txtSearch.getText().toString());
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.home:
                return true;
            case R.id.map:
                    Intent intentM = new Intent(getApplicationContext(), selectionMap.class);
                    startActivity(intentM);
                    return true;
            case R.id.prof:
                Intent intentP = new Intent(getApplicationContext(), profileActivity.class);
                startActivity(intentP);
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}