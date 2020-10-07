package com.example.turfwarsclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient extends Thread {

    private TCPClient() {

    }

    protected static TCPClient instanceUnique;

    protected static  TCPClient getInstance() {
        if (instanceUnique==null){
            instanceUnique = new TCPClient();
        }
        return instanceUnique;
    }

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
            socket = new Socket((InetAddress) null,5000);
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
                this.observer.OnMessage(line);
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
