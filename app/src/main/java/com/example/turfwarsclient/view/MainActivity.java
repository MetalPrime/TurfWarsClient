package com.example.turfwarsclient.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.turfwarsclient.model.Name;
import com.example.turfwarsclient.model.OnMessageListener;
import com.example.turfwarsclient.R;
import com.example.turfwarsclient.model.TCPClient;
import com.google.gson.Gson;

import static com.example.turfwarsclient.model.TCPClient.*;

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
                                            runOnUiThread(
                                                    ()->{
                                                        Log.e("entro","confirmamos entro");
                                                        Gson gson = new Gson();
                                                        Name name = new Name(nameInput.getText().toString());
                                                        String line = gson.toJson(name);
                                                        tcp.sendMessages(line);
                                                        Intent i = new Intent(this,ControlScreen.class);
                                                        startActivity(i);


                                                    }
                                            );

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

    @Override
    public void newPosition(int x, int y) {

    }


}