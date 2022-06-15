package com.example.geosearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class error extends Fragment {
    private TextView txtError;

    public error(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_error, container, false);
        /*
        TextView txtError = new TextView(this.getActivity());
        txtError = (TextView) view.findViewById(R.id.txtErrorText);
        Bundle args = getArguments();

        changeError(args);*/
        return view;
    }

    public void backToMenu(View view){
        Intent intent = new Intent(getActivity(), mainMenu.class);
        startActivity(intent);
    }

    public void changeError(Bundle args){
        String error = args.getString("error");

        switch(error){
            case "connec":
                txtError.setText(getString(R.string.errorConnec));
                break;
            case "query":
                txtError.setText(getString(R.string.errorFind));
                break;
        }
    }
}