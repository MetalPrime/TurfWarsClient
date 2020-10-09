package com.example.turfwarsclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnMessageListener {

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
        tcp.setObserver(this);

        btnConnection.setOnClickListener(
                (v) -> {
                    tcp.setNumberServer(ip1.getText().toString()+"."+ip2.getText().toString()+"."+ip3.getText().toString()+"."+ip4.getText().toString());
                    new Thread(
                            () ->{
                                if(tcp.getNumberServer()!=null){
                                    tcp.start();
                                    if(tcp.isAlive()){
                                        try {
                                            Thread.sleep(300);
                                            tcp.sendMessages("Dandole duro");
                                            Log.e("entro","confirmamos entro");
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }

                            }

                    ).start();

                }
        );
    }

    @Override
    public void OnMessage(String msg) {

    }


}