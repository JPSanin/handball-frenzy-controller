package com.example.handballfrenzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Controller extends AppCompatActivity implements onMessageListener{

    private ImageView imgBack;
    private ImageView imgLeft;
    private ImageView imgRight;
    private ImageView imgUp;
    private ImageView imgShoot;
    private ImageView imgSteal;

    private TCPSingleton tcp;
    private int player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);



        //hide action bar
        getSupportActionBar().hide();

        //Only Instance
        tcp= TCPSingleton.getInstance();

        //Subscription
        tcp.setObserver(this);


        imgBack= findViewById(R.id.imgBack);
        imgLeft= findViewById(R.id.imgLeft);
        imgRight= findViewById(R.id.imgRight);
        imgUp= findViewById(R.id.imgUp);
        imgShoot= findViewById(R.id.imgShoot);
        imgSteal= findViewById(R.id.imgSteal);



        //Get player
        player= Integer.parseInt(getIntent().getExtras().getString("player"));

        if(player==1){
            imgBack.setImageDrawable(getResources().getDrawable(R.drawable.bg1,null));
        }
        if(player==2){
            imgBack.setImageDrawable(getResources().getDrawable(R.drawable.bg2,null));
        }
    }

    @Override
    public void messageRecieved(String msg) {

    }
}