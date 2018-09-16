package com.example.user.formation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by USER on 2017-05-09.
 */

public class SoccerActivity extends AppCompatActivity {
    ImageView iv_team,iv_shape;
    ImageView mutebutton;
    private EditText et_team,et_shape;
    private ImageButton ib_search,ib_make,ib_cancel;
    public int formation_num=0;
    public String tag2;
    public static String tag;
    int flaged=0;
    String info[] = new String[] {"4-4-2 (Normal 1)","4-3-3 (Normal 2)","4-2-3-1 (Double Volante)",
            "4-3-2-1 (Trees)","3-4-3 (3 Back)","3-5-2 (2 WingBacks)","5-3-2 (Sweeper)","2-3-5 (Pyramid)" };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer);


        mutebutton = (ImageView) findViewById(R.id.mutebutton3);
        mutebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flaged == 0){
                    stopService(new Intent(SoccerActivity.this,MusicService.class));
                    flaged = 1;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.notmute,getApplicationContext().getTheme()));
                }else{
                    startService(new Intent(SoccerActivity.this,MusicService.class));
                    flaged = 0;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.mute,getApplicationContext().getTheme()));
                }
            }
        });

        iv_team = (ImageView) findViewById(R.id.iv_team);
        iv_shape = (ImageView) findViewById(R.id.iv_shape);
        et_team = (EditText) findViewById(R.id.et_team);
        et_shape = (EditText) findViewById(R.id.et_shape);
        ib_search = (ImageButton) findViewById(R.id.ib_search);
        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SoccerActivity.this);

                builder.setTitle("Formation Select");
                builder.setItems(info, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which)
                        {
                            case 0:
                                et_shape.setText(info[0]);
                                formation_num=0;
                                tag2 = info[0];
                                break;
                            case 1:
                                et_shape.setText(info[1]);
                                formation_num=1;
                                tag2 = info[1];
                                break;
                            case 2:
                                et_shape.setText(info[2]);
                                formation_num=2;
                                tag2 = info[2];
                                break;
                            case 3:
                                et_shape.setText(info[3]);
                                formation_num=3;
                                tag2 = info[3];
                                break;
                            case 4:
                                et_shape.setText(info[4]);
                                formation_num=4;
                                tag2 = info[4];
                                break;
                            case 5:
                                et_shape.setText(info[5]);
                                formation_num=5;
                                tag2 = info[5];
                                break;
                            case 6:
                                et_shape.setText(info[6]);
                                formation_num=6;
                                tag2 = info[6];
                                break;
                            case 7:
                                et_shape.setText(info[7]);
                                formation_num=7;
                                tag2 = info[7];
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        ib_make = (ImageButton) findViewById(R.id.ib_make);
        ib_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = et_team.getText().toString();
                tag2 = et_shape.getText().toString();
                if(tag.length() == 0 ) {
                    Toast.makeText(SoccerActivity.this, "Team Name을 입력하세요!", Toast.LENGTH_SHORT).show();
                }else if(tag2.length()==0){
                    Toast.makeText(SoccerActivity.this, "Team Formation을 선택하세요!", Toast.LENGTH_SHORT).show();
                }else {
                    if(tag2.equals(info[0])){ formation_num =0;}
                    else if(tag2.equals(info[1])){ formation_num =1;}
                    else if(tag2.equals(info[2])){ formation_num =2;}
                    else if(tag2.equals(info[3])){ formation_num =3;}
                    else if(tag2.equals(info[4])){ formation_num =4;}
                    else if(tag2.equals(info[5])){ formation_num =5;}
                    else if(tag2.equals(info[6])){ formation_num =6;}
                    else if(tag2.equals(info[7])){ formation_num =7;}
                    else { formation_num = 10;}
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(SoccerActivity.this);
                    alert_confirm.setMessage(tag + " 을 생성하시겠습니까??").setCancelable(false).setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 'YES'
                                    switch(formation_num){
                                        case 0:
                                            Intent intent = new Intent(SoccerActivity.this, SoccerActivity2.class);
                                            startActivity(intent);
                                            break;
                                        case 1:
                                            Intent intent2 = new Intent(SoccerActivity.this, SoccerActivity3.class);
                                            startActivity(intent2);
                                            break;
                                        case 2:
                                            Intent intent3 = new Intent(SoccerActivity.this, SoccerActivity4.class);
                                            startActivity(intent3);
                                            break;
                                        case 3:
                                            Intent intent4 = new Intent(SoccerActivity.this, SoccerActivity5.class);
                                            startActivity(intent4);
                                            break;
                                        case 4:
                                            Intent intent5 = new Intent(SoccerActivity.this, SoccerActivity6.class);
                                            startActivity(intent5);
                                            break;
                                        case 5:
                                            Intent intent6 = new Intent(SoccerActivity.this, SoccerActivity7.class);
                                            startActivity(intent6);
                                            break;
                                        case 6:
                                            Intent intent7 = new Intent(SoccerActivity.this, SoccerActivity8.class);
                                            startActivity(intent7);
                                            break;
                                        case 7:
                                            Intent intent8 = new Intent(SoccerActivity.this, SoccerActivity9.class);
                                            startActivity(intent8);
                                            break;
                                        default:
                                            Toast.makeText(getApplicationContext(),"해당되는 포메이션이 없습니다!!\n 다시 선택해주세요.",Toast.LENGTH_SHORT).show();
                                            break;
                                    }
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
            }
        });

        ib_cancel = (ImageButton) findViewById(R.id.ibRegist);
        ib_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public String getName(){
        return tag;
    }
}
