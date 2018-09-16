package com.example.user.formation;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by USER on 2017-05-09.
 */

public class MainButtonActivity extends AppCompatActivity {
    private ImageButton soccerBTN,footbalBTN,baseballBTN,basketballBTN,logoutBTN;
    private boolean flag = true;
    private MainActivity main;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startService(new Intent(MainButtonActivity.this, MusicService.class)); //Activity 시작과 함께 배경음악을 재생한다.
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        main = new MainActivity();
        flag = main.getloginState();

        firebaseAuth = FirebaseAuth.getInstance();

        soccerBTN = (ImageButton) findViewById(R.id.soccerBTN);
        footbalBTN = (ImageButton) findViewById(R.id.footballBTN);
        baseballBTN = (ImageButton) findViewById(R.id.baseballBTN);
        basketballBTN = (ImageButton) findViewById(R.id.basketballBTN);
        logoutBTN = (ImageButton) findViewById(R.id.logoutBTN);


        soccerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainButtonActivity.this, SoccerActivity.class);
                startActivity(intent);
            }
        });

        footbalBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainButtonActivity.this, FootballActivity.class);
                startActivity(intent);
            }
        });

        baseballBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainButtonActivity.this, Baseball_Deffense.class);
                startActivity(intent);
            }
        });

        basketballBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainButtonActivity.this, BasketballActivity.class);
                startActivity(intent);
            }
        });

        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(MainButtonActivity.this);
                alert_confirm.setMessage("Logout 하시겠습니까??").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'YES'
                                main.flag = false;
                                Intent intent = new Intent(MainButtonActivity.this, MainActivity.class);
                                startActivity(intent);
                                //session.setLoggedIn(false);
                                firebaseAuth.signOut();
                                stopService(new Intent(MainButtonActivity.this, MusicService.class));
                                Toast.makeText(getApplicationContext(), "로그아웃 중입니다.", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
            }
        });
    }


        public boolean onKeyDown(int keyCode, KeyEvent event){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK:
                    stopService(new Intent(MainButtonActivity.this,MusicService.class));            }
            return super.onKeyDown(keyCode, event);
        }

    }


