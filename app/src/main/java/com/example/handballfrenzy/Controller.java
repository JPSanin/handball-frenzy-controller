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
    private int score1;
    private int score2;

    private TextView txtScore1;
    private TextView txtScore2;


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
        txtScore1= findViewById(R.id.txtScore1);
        txtScore2= findViewById(R.id.txtScore2);


        //Get player
        player= Integer.parseInt(getIntent().getExtras().getString("player"));

        if(player==1){
            imgBack.setImageDrawable(getResources().getDrawable(R.drawable.c1,null));
        }
        if(player==2){
            imgBack.setImageDrawable(getResources().getDrawable(R.drawable.c2,null));
        }


        imgLeft.setOnClickListener((v)->{
            tcp.sendMessage("left");
        } );

        imgRight.setOnClickListener((v)->{
            tcp.sendMessage("right");
        } );

        imgUp.setOnClickListener((v)->{
            tcp.sendMessage("jump");
        } );

        imgShoot.setOnClickListener((v)->{
            tcp.sendMessage("shoot");
        } );

        imgSteal.setOnClickListener((v)->{
            tcp.sendMessage("steal");
        } );

    }

    @Override
    public void messageRecieved(String msg) {
        if(msg.equals("1,goal")){
            score1++;
            runOnUiThread(()->{
                if(player==1){
                    txtScore1.setText(""+score1);
                }
                if(player==2){
                    txtScore2.setText(""+score1);
                }
            });
            tcp.sendMessage("1,goal");
        }

        if(msg.equals("2,goal")){
            score2++;
            runOnUiThread(()->{
                if(player==1){
                    txtScore2.setText(""+score2);
                }
                if(player==2){
                    txtScore1.setText(""+score2);
                }
            });
            tcp.sendMessage("2,goal");
        }
    }
}