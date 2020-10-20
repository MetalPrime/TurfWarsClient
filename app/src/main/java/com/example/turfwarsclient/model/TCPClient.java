package com.example.turfwarsclient.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPClient extends Thread {

    private TCPClient() {

    }

    protected static TCPClient instanceUnique;

    public static  TCPClient getInstance() {
        if (instanceUnique==null){
            instanceUnique = new TCPClient();
        }
        return instanceUnique;
    }



    public String getNumberServer() {
        return numberServer;
    }

    public void setNumberServer(String numberServer) {
        this.numberServer = numberServer;
    }

    private String numberServer;
    private Socket socket;
    private OnMessageListener observer;
    private BufferedWriter writer;
    private BufferedReader reader;

    public void setObserver(OnMessageListener observer) {
        // TODO Auto-generated method stub
        this.observer = observer;
    }

    @Override
    public void run() {
        super.run();
        try {
            Log.e("Status","Esperando conexión");
            socket = new Socket(numberServer,5000);
            Log.e("Status","Conexión establecida");
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            writer = new BufferedWriter(osw);

            while (true){
                System.out.println("Esperando Mensaje");
                String line = reader.readLine();
                System.out.println("Recibido:"+" "+line);
                Gson gson = new Gson();
                Generic generic = gson.fromJson(line, Generic.class);

                switch(generic.getType()) {
                    case "Coordinate":
                        Coordinate coord = gson.fromJson(line, Coordinate.class);
                        this.observer.newPosition(coord.getPosX(), coord.getPosY());
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages(String msg){
        new Thread(
                () ->{
                    try {
                        writer.write(msg+"\n");
                        writer.flush();
                    } catch (IOException e){
                        e.printStackTrace();

                    }
                }
        ).start();


    }
}
