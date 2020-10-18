package com.example.turfwarsclient.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.turfwarsclient.R;
import com.example.turfwarsclient.model.Coordinate;
import com.example.turfwarsclient.model.OnMessageListener;
import com.google.gson.Gson;

public class ControlScreen extends AppCompatActivity implements View.OnClickListener, OnMessageListener {

    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;

    private Button brnShot;

    private Button btnWeaponRifle;
    private Button btnWeaponShotgun;
    private Button btnWeaponPistol;

    private Coordinate coordinate;
    private Gson gson;
    private String line;
    private boolean buttonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_screen);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void OnMessage(String msg) {

    }
}