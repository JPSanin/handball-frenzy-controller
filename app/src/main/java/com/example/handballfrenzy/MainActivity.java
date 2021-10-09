package com.example.handballfrenzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements onMessageListener {
    private TCPSingleton tcp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Only Instance
        tcp= TCPSingleton.getInstance();

        //Subscription
        tcp.setObserver(this);
    }

    @Override
    public void messageRecieved(String msg) {

    }
}