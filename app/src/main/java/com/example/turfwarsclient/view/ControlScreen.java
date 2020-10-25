package com.example.turfwarsclient.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.turfwarsclient.R;
import com.example.turfwarsclient.model.Bullet;
import com.example.turfwarsclient.model.Coordinate;
import com.example.turfwarsclient.model.OnMessageListener;
import com.example.turfwarsclient.model.TCPClient;
import com.google.gson.Gson;

public class ControlScreen extends AppCompatActivity implements View.OnClickListener, OnMessageListener {

    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;

    private Button btnShot;

    private ImageView barLife;

    private Coordinate coordinate;
    private Gson gson;
    private String line;
    private boolean buttonPressed;
    private TCPClient tcp;
    private int x,y;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_screen);

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);

        btnShot = findViewById(R.id.btnShot);

        barLife = findViewById(R.id.imgBar);

        tcp = TCPClient.getInstance();
        tcp.setObserver(this);
        buttonPressed = false;

        gson = new Gson();

        btnUp.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        if(y>140){
                                                            coordinate.setPosY(y-3);
                                                            coordinate.setPosX(x);
                                                            line =  gson.toJson(coordinate);
                                                            if(line!=null){
                                                                tcp.sendMessages(line);
                                                            }
                                                        }
                                                                                                          }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnDown.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        if(y<360) {

                                                            coordinate.setPosY(y+3);
                                                            coordinate.setPosX(x);
                                                            line =  gson.toJson(coordinate);
                                                            if(line!=null){
                                                                tcp.sendMessages(line);
                                                            }
                                                        }
                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnRight.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        if(x<1250){
                                                            coordinate.setPosX(x+3);
                                                            coordinate.setPosY(y);
                                                            line =  gson.toJson(coordinate);
                                                            if(line!=null){
                                                                tcp.sendMessages(line);
                                                            }
                                                        }

                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );
        btnLeft.setOnTouchListener(
                (view,event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            buttonPressed = true;
                            new Thread(
                                    ()->{
                                        while (buttonPressed){
                                            runOnUiThread(
                                                    () ->{
                                                        if(x>50){
                                                            coordinate.setPosX(x-3);
                                                            coordinate.setPosY(y);
                                                            line =  gson.toJson(coordinate);
                                                            if(line!=null){
                                                                tcp.sendMessages(line);
                                                            }
                                                        }

                                                    }
                                            );
                                            try {
                                                Thread.sleep(150);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            buttonPressed = false;
                            break;
                    }
                    return true;
                }
        );

        btnShot.setOnClickListener(this);

            coordinate = new Coordinate(x,y);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnShot:
                Bullet bullet = new Bullet(x,y,10,5);
                line = gson.toJson(bullet);
                if(line!=null){
                    tcp.sendMessages(line);
                }
                break;
        }
    }

    @Override
    public void OnMessage(String msg) {

    }

    @Override
    public void newPosition(int x, int y) {
    this.x = x;
    this.y = y;
    }

    @Override
    public void currentLife(String status) {
        if(status.equals("Media")){
            barLife.setImageResource(R.drawable.controlesvidamedia);
        }
        if(status.equals("Baja")){
            barLife.setImageResource(R.drawable.controlesvidabajo);
        }
    }
}