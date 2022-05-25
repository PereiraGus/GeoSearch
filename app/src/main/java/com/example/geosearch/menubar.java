package com.example.geosearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class menubar extends Fragment {
    public menubar() {
    }
    public static menubar newInstance() {
        menubar fragment = new menubar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menubar, container, false);
    }

    public void goToMain()
    {
        Intent intent = new Intent(getContext(), MainMenu.class);
        startActivity(intent);
    }
}