package com.example.turfwarsclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ControlScreen extends AppCompatActivity {

    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;

    private Button brnShot;

    private Button btnWeaponRifle;
    private Button btnWeaponShotgun;
    private Button btnWeaponPistol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_screen);
    }
}