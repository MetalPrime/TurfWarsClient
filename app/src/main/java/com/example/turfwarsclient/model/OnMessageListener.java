package com.example.turfwarsclient.model;

public interface OnMessageListener {

    public void OnMessage(String msg);

    public void newPosition(int x, int y);

    public void currentLife(String status);
}
