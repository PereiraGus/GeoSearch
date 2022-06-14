package com.example.geosearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void backToMenu (View view){
        Intent intent = new Intent(getApplicationContext(), mainMenu.class);
        startActivity(intent);
    }
}