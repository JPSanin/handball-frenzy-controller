package com.example.handballfrenzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements onMessageListener {

    private TCPSingleton tcp;
    private Button btnConnect;
    private int player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hide action bar
        getSupportActionBar().hide();

        //Only Instance
        tcp= TCPSingleton.getInstance();

        //Subscription
        tcp.setObserver(this);


        btnConnect= findViewById(R.id.btnConnect);

        player=0;
        btnConnect.setOnClickListener((v)->{
            tcp.start();
            btnConnect.setEnabled(false);
        });



    }

    @Override
    public void messageRecieved(String msg) {
        if(msg.equals("1")){
            player=1;
        }

        if(msg.equals("2")){
            player=2;
        }


        if(msg.equals("Players Connected")){
            Intent i = new Intent(this, instructionsReadyUp.class);
            i.putExtra("player", ""+player);
            startActivity(i);
            finish();
        }
    }
}