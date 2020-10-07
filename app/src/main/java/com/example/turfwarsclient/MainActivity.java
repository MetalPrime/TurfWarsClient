package com.example.turfwarsclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ip1,ip2,ip3,ip4;
    private EditText nameInput;
    private Button btnConnection;
    private TCPClient tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip1 = findViewById(R.id.ip1);
        ip2 = findViewById(R.id.ip2);
        ip3 = findViewById(R.id.ip3);
        ip4 = findViewById(R.id.ip4);
        nameInput = findViewById(R.id.nameInput);
        btnConnection = findViewById(R.id.btnConnection);

        tcp = TCPClient.getInstance();
    }
}