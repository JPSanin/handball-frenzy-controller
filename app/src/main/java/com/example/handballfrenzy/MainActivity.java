package com.example.handballfrenzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements onMessageListener {

    private TCPSingleton tcp;
    private Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Only Instance
        tcp= TCPSingleton.getInstance();

        //Subscription
        tcp.setObserver(this);



        btnConnect= findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener((v)->{
            tcp.run();
        });


    }

    @Override
    public void messageRecieved(String msg) {
        if(msg.equals("Connected succesfully")){
            Toast.makeText(this, "Connected succesfully", Toast.LENGTH_SHORT).show();
            //intent
        }
    }
}