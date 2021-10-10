package com.example.handballfrenzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class instructionsReadyUp extends AppCompatActivity  implements onMessageListener{

    private TCPSingleton tcp;
    private Button btnReady;
    private TextView txtReady;
    private int player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_ready_up);

        //hide action bar
        getSupportActionBar().hide();

        //Only Instance
        tcp= TCPSingleton.getInstance();

        //Subscription
        tcp.setObserver(this);


        btnReady= findViewById(R.id.btnReady);
        txtReady= findViewById(R.id.txtReady);

        //Get player
        player= Integer.parseInt(getIntent().getExtras().getString("player"));
        //Toast.makeText(this, "Player:"+player, Toast.LENGTH_SHORT).show();

        btnReady.setOnClickListener((v)->{
            tcp.sendMessage("Ready");
            txtReady.setText("Player "+ player +" Ready!");
        });

    }

    @Override
    public void messageRecieved(String msg) {
        if(msg.equals("Players Ready")){
            Intent i = new Intent(this, Controller.class);
            i.putExtra("player", ""+player);
            startActivity(i);
            finish();
        }
    }
}