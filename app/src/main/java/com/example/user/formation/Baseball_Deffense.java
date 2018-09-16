package com.example.user.formation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;

/**
 * Created by USER on 2017-05-14.
 */

public class Baseball_Deffense extends AppCompatActivity {
    String player_rf_name="player3",player_lf_name="player2",player_cf_name="player1",player_ss_name="player7",player_1b_name="player4",
            player_2b_name="player5",player_3b_name="player6",player_p_name="player8",player_c_name="player9";

    String player_rf_attrs="null",player_lf_attrs="null",player_cf_attrs="null",player_ss_attrs="null",player_1b_attrs="null",
            player_2b_attrs="null",player_3b_attrs="null",player_p_attrs="null",player_c_attrs="null";
    int player_rf_age=0,player_lf_age=0,player_cf_age=0,player_ss_age=0,player_1b_age=0,player_2b_age=0,player_3b_age=0,player_p_age=0,player_c_age=0;
    int player_rf_number=0,player_lf_number=0,player_cf_number=0,player_ss_number=0,player_1b_number=0,player_2b_number=0,player_3b_number=0,player_p_number=0,player_c_number=0;
    private ImageView ib_rf, ib_cf, ib_lf, ib_1b, ib_2b, ib_3b, ib_ss, ib_p, ib_c,iv_share;
    private TextView tv_rf, tv_cf, tv_lf, tv_1b, tv_2b, tv_3b, tv_ss, tv_p, tv_c;
    private ImageButton ib_offense;
    private ImageView mutebutton;

    boolean flag=true;
    float getX, getY;
    int isMoving;
    int flaged = 0;
    final int START_DRAG = 0;
    final int END_DRAG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseball_defense);

        mutebutton = (ImageView) findViewById(R.id.mutebutton);
        mutebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flaged == 0){
                    stopService(new Intent(Baseball_Deffense.this,MusicService.class));
                    flaged = 1;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.notmute,getApplicationContext().getTheme()));
                }else{
                    startService(new Intent(Baseball_Deffense.this,MusicService.class));
                    flaged = 0;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.mute,getApplicationContext().getTheme()));
                }
            }
        });

        iv_share = (ImageView) findViewById(R.id.share11);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareKakao();
            }
        });

        ib_offense = (ImageButton) findViewById(R.id.iv_offense);
        ib_offense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.baseball_offense_dialog, null);

                AlertDialog.Builder alert3 = new AlertDialog.Builder(Baseball_Deffense.this);
                alert3.setTitle("Batting Order");
                alert3.setIcon(R.drawable.baseball_dialog_image);
                alert3.setView(dialogView);


                EditText player1_name = (EditText) dialogView.findViewById(R.id.Player1_name);
                EditText player1_number = (EditText) dialogView.findViewById(R.id.Player1_number);
                EditText player1_attrs = (EditText) dialogView.findViewById(R.id.Player1_attrs);

                EditText player2_name = (EditText) dialogView.findViewById(R.id.Player2_name);
                EditText player2_number = (EditText) dialogView.findViewById(R.id.Player2_number);
                EditText player2_attrs = (EditText) dialogView.findViewById(R.id.Player2_attrs);

                EditText player3_name = (EditText) dialogView.findViewById(R.id.Player3_name);
                EditText player3_number = (EditText) dialogView.findViewById(R.id.Player3_number);
                EditText player3_attrs = (EditText) dialogView.findViewById(R.id.Player3_attrs);

                EditText player4_name = (EditText) dialogView.findViewById(R.id.Player4_name);
                EditText player4_number = (EditText) dialogView.findViewById(R.id.Player4_number);
                EditText player4_attrs = (EditText) dialogView.findViewById(R.id.Player4_attrs);

                EditText player5_name = (EditText) dialogView.findViewById(R.id.Player5_name);
                EditText player5_number = (EditText) dialogView.findViewById(R.id.Player5_number);
                EditText player5_attrs = (EditText) dialogView.findViewById(R.id.Player5_attrs);

                EditText player6_name = (EditText) dialogView.findViewById(R.id.Player6_name);
                EditText player6_number = (EditText) dialogView.findViewById(R.id.Player6_number);
                EditText player6_attrs = (EditText) dialogView.findViewById(R.id.Player6_attrs);

                EditText player7_name = (EditText) dialogView.findViewById(R.id.Player7_name);
                EditText player7_number = (EditText) dialogView.findViewById(R.id.Player7_number);
                EditText player7_attrs = (EditText) dialogView.findViewById(R.id.Player7_attrs);

                EditText player8_name = (EditText) dialogView.findViewById(R.id.Player8_name);
                EditText player8_number = (EditText) dialogView.findViewById(R.id.Player8_number);
                EditText player8_attrs = (EditText) dialogView.findViewById(R.id.Player8_attrs);

                EditText player9_name = (EditText) dialogView.findViewById(R.id.Player9_name);
                EditText player9_number = (EditText) dialogView.findViewById(R.id.Player9_number);
                EditText player9_attrs = (EditText) dialogView.findViewById(R.id.Player9_attrs);


                player1_name.setText(player_cf_name);
                player1_attrs.setText(player_cf_attrs);
                player1_number.setText(String.valueOf(player_cf_number));

                player2_name.setText(player_lf_name);
                player2_attrs.setText(player_lf_attrs);
                player2_number.setText(String.valueOf(player_lf_number));

                player3_name.setText(player_rf_name);
                player3_attrs.setText(player_rf_attrs);
                player3_number.setText(String.valueOf(player_rf_number));

                player4_name.setText(player_1b_name);
                player4_attrs.setText(player_1b_attrs);
                player4_number.setText(String.valueOf(player_1b_number));

                player5_name.setText(player_2b_name);
                player5_attrs.setText(player_2b_attrs);
                player5_number.setText(String.valueOf(player_2b_number));

                player6_name.setText(player_3b_name);
                player6_attrs.setText(player_3b_attrs);
                player6_number.setText(String.valueOf(player_3b_number));

                player7_name.setText(player_ss_name);
                player7_attrs.setText(player_ss_attrs);
                player7_number.setText(String.valueOf(player_ss_number));

                player8_name.setText(player_p_name);
                player8_attrs.setText(player_p_attrs);
                player8_number.setText(String.valueOf(player_p_number));

                player9_name.setText(player_c_name);
                player9_attrs.setText(player_c_attrs);
                player9_number.setText(String.valueOf(player_c_number));



                alert3.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                    AlertDialog dialog = alert3.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
        });


        ib_rf = (ImageView) findViewById(R.id.iv_rf);
        ib_rf.setOnClickListener(new MyListener());
        ib_rf.setOnTouchListener(new DraggingListener());

        ib_lf = (ImageView) findViewById(R.id.iv_lf);
        ib_lf.setOnClickListener(new MyListener());
        ib_lf.setOnTouchListener(new DraggingListener());

        ib_cf = (ImageView) findViewById(R.id.iv_cf);
        ib_cf.setOnClickListener(new MyListener());
        ib_cf.setOnTouchListener(new DraggingListener());

        ib_1b = (ImageView) findViewById(R.id.iv_1b);
        ib_1b.setOnClickListener(new MyListener());
        ib_1b.setOnTouchListener(new DraggingListener());

        ib_2b = (ImageView) findViewById(R.id.iv_2b);
        ib_2b.setOnClickListener(new MyListener());
        ib_2b.setOnTouchListener(new DraggingListener());

        ib_3b = (ImageView) findViewById(R.id.iv_3b);
        ib_3b.setOnClickListener(new MyListener());
        ib_3b.setOnTouchListener(new DraggingListener());

        ib_ss = (ImageView) findViewById(R.id.iv_ss);
        ib_ss.setOnClickListener(new MyListener());
        ib_ss.setOnTouchListener(new DraggingListener());

        ib_p = (ImageView) findViewById(R.id.iv_p);
        ib_p.setOnClickListener(new MyListener());
        ib_p.setOnTouchListener(new DraggingListener());

        ib_c = (ImageView) findViewById(R.id.iv_c);
        ib_c.setOnClickListener(new MyListener());
        ib_c.setOnTouchListener(new DraggingListener());


        tv_rf = (TextView) findViewById(R.id.TV_RF);
        tv_lf = (TextView) findViewById(R.id.TV_LF);
        tv_cf = (TextView) findViewById(R.id.TV_CF);
        tv_1b = (TextView) findViewById(R.id.TV_1B);
        tv_2b = (TextView) findViewById(R.id.TV_2B);
        tv_3b = (TextView) findViewById(R.id.TV_3B);
        tv_ss = (TextView) findViewById(R.id.TV_SS);
        tv_p = (TextView) findViewById(R.id.TV_P);
        tv_c = (TextView) findViewById(R.id.TV_C);
    }

    public void shareKakao()
    {
        try{
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

        /*메시지 추가*/
            kakaoBuilder.addText("Make Your Own Formation!!.");

        /*이미지 가로/세로 사이즈는 80px 보다 커야하며, 이미지 용량은 500kb 이하로 제한된다.*/
            String url = "http://www.pngmart.com/files/3/Sports-Ball-PNG-File.png";
            kakaoBuilder.addImage(url, 160, 160);

        /*앱 실행버튼 추가*/
            kakaoBuilder.addAppButton("앱 실행 혹은 다운로드");

        /*메시지 발송*/
            kakaoLink.sendMessage(kakaoBuilder, this);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    class DraggingListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if(flag){
                    getX = event.getX();
                    getY = event.getY();
                    flag = false;
                }
                //Toast.makeText(Baseball_Deffense.this,"Drag start",Toast.LENGTH_SHORT).show();
                isMoving =START_DRAG;
            }else if(event.getAction()==MotionEvent.ACTION_UP){
                //Toast.makeText(Baseball_Deffense.this,"Drag End", Toast.LENGTH_SHORT).show();
                isMoving = END_DRAG;
            }else if(event.getAction()==MotionEvent.ACTION_MOVE){
                if(isMoving==START_DRAG){
                    v.setX((int)event.getRawX()-getX);
                    v.setY((int)event.getRawY()-getY);
                    if(v == ib_1b ){
                        tv_1b.setX((int)event.getRawX()-getX+20);
                        tv_1b.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_2b ){
                        tv_2b.setX((int)event.getRawX()-getX+20);
                        tv_2b.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_3b ){
                        tv_3b.setX((int)event.getRawX()-getX+20);
                        tv_3b.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_ss ){
                        tv_ss.setX((int)event.getRawX()-getX+20);
                        tv_ss.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_p ){
                        tv_p.setX((int)event.getRawX()-getX+20);
                        tv_p.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_c ){
                        tv_c.setX((int)event.getRawX()-getX+20);
                        tv_c.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_lf ){
                        tv_lf.setX((int)event.getRawX()-getX+20);
                        tv_lf.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_rf ){
                        tv_rf.setX((int)event.getRawX()-getX+20);
                        tv_rf.setY((int)event.getRawY()-getY+150);
                    }else if(v == ib_cf ){
                        tv_cf.setX((int)event.getRawX()-getX+20);
                        tv_cf.setY((int)event.getRawY()-getY+100);
                    }else {}
                }
            }
            return false;
        }
    }

    class MyListener implements View.OnClickListener {
        ImageView view;
        long btnPressTime = 0;
        @Override
        public void onClick(View v) {
            view = (ImageView) v;
            if(System.currentTimeMillis()>btnPressTime+1000){
                btnPressTime=System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() <= btnPressTime + 1000) {
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.baseball_dialog, null);

                AlertDialog.Builder alert2 = new AlertDialog.Builder(Baseball_Deffense.this);
                alert2.setTitle("Input your Player Information");
                alert2.setIcon(R.drawable.baseball_dialog_image);
                alert2.setView(dialogView);
                alert2.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_name = (EditText) dialogView.findViewById(R.id.et_name2);
                        EditText et_number = (EditText) dialogView.findViewById(R.id.et_number2);
                        EditText et_age = (EditText) dialogView.findViewById(R.id.et_age2);

                        RadioGroup rg = (RadioGroup) dialogView.findViewById(R.id.radiogroup);
                        RadioButton rb1 = (RadioButton) dialogView.findViewById(R.id.radioButton);
                        RadioButton rb2 = (RadioButton) dialogView.findViewById(R.id.radioButton2);
                        RadioButton rb3 = (RadioButton) dialogView.findViewById(R.id.radioButton3);
                        RadioButton rb4 = (RadioButton) dialogView.findViewById(R.id.radioButton4);
                        RadioButton rb5 = (RadioButton) dialogView.findViewById(R.id.radioButton5);
                        RadioButton rb6 = (RadioButton) dialogView.findViewById(R.id.radioButton6);
                        RadioButton rb7 = (RadioButton) dialogView.findViewById(R.id.radioButton7);
                        RadioButton rb8 = (RadioButton) dialogView.findViewById(R.id.radioButton8);
                        RadioButton rb9 = (RadioButton) dialogView.findViewById(R.id.radioButton9);
                        RadioButton rb_left = (RadioButton) dialogView.findViewById(R.id.left_radiobtn);
                        RadioButton rb_right = (RadioButton) dialogView.findViewById(R.id.right_radiobtn);

                        if (et_name.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else if (et_age.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 나이을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else if (et_number.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            if(view == ib_lf) {
                                player_lf_name = et_name.getText().toString();
                                player_lf_age = Integer.parseInt(et_age.getText().toString());
                                player_lf_number = Integer.parseInt(et_number.getText().toString());
                                if (rb_left.isChecked()) {
                                    player_lf_attrs = "LEFT";
                                } else if(rb_right.isChecked()){
                                    player_lf_attrs = "RIGHT";
                                }else{}
                            }else if(view == ib_rf) {
                                player_rf_name = et_name.getText().toString();
                                player_rf_age = Integer.parseInt(et_age.getText().toString());
                                player_rf_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_rf_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_rf_attrs ="RIGHT";
                                }else {}
                            }else if(view == ib_cf) {
                                player_cf_name = et_name.getText().toString();
                                player_cf_age = Integer.parseInt(et_age.getText().toString());
                                player_cf_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_cf_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_cf_attrs ="RIGHT";
                                }else{}
                            }else if(view == ib_1b) {
                                player_1b_name = et_name.getText().toString();
                                player_1b_age = Integer.parseInt(et_age.getText().toString());
                                player_1b_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_1b_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_1b_attrs ="RIGHT";
                                }else{}
                            }else if(view == ib_2b) {
                                player_2b_name = et_name.getText().toString();
                                player_2b_age = Integer.parseInt(et_age.getText().toString());
                                player_2b_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_2b_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_2b_attrs ="RIGHT";
                                }else{}
                            }else if(view == ib_3b) {
                                player_3b_name = et_name.getText().toString();
                                player_3b_age = Integer.parseInt(et_age.getText().toString());
                                player_3b_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_3b_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_3b_attrs ="RIGHT";
                                }else{}
                            }else if(view == ib_ss) {
                                player_ss_name = et_name.getText().toString();
                                player_ss_age = Integer.parseInt(et_age.getText().toString());
                                player_ss_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_ss_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_ss_attrs ="RIGHT";
                                }else{}
                            }else if(view == ib_p) {
                                player_p_name = et_name.getText().toString();
                                player_p_age = Integer.parseInt(et_age.getText().toString());
                                player_p_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_p_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_p_attrs ="RIGHT";
                                }else{}
                            }else if(view == ib_c) {
                                player_c_name = et_name.getText().toString();
                                player_c_age = Integer.parseInt(et_age.getText().toString());
                                player_c_number = Integer.parseInt(et_number.getText().toString());
                                if(rb_left.isChecked()){
                                    player_c_attrs = "LEFT";
                                }else if(rb_right.isChecked()){
                                    player_c_attrs ="RIGHT";
                                }else{}
                            }else {}
                            if (rb1.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_cf, getApplicationContext().getTheme()));
                            } else if (rb2.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_lf, getApplicationContext().getTheme()));
                            } else if (rb3.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_rf, getApplicationContext().getTheme()));
                            } else if (rb4.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_1b, getApplicationContext().getTheme()));
                            } else if (rb5.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_2b, getApplicationContext().getTheme()));
                            } else if (rb6.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_3b, getApplicationContext().getTheme()));
                            } else if (rb7.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_ss, getApplicationContext().getTheme()));
                            } else if (rb8.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_p, getApplicationContext().getTheme()));
                            } else if (rb9.isChecked()) {
                                view.setImageDrawable(getResources().getDrawable(R.drawable.ib_c, getApplicationContext().getTheme()));
                            } else {
                            }

                            if (view == ib_cf) {
                                tv_cf.setText(player_cf_name + "(" + player_cf_number + ")");
                            } else if (view == ib_lf) {
                                tv_lf.setText(player_lf_name + "(" + player_lf_number + ")");
                            } else if (view == ib_rf) {
                                tv_rf.setText(player_rf_name + "(" + player_rf_number + ")");
                            } else if (view == ib_1b) {
                                tv_1b.setText(player_1b_name + "(" + player_1b_number + ")");
                            } else if (view == ib_2b) {
                                tv_2b.setText(player_2b_name + "(" + player_2b_number + ")");
                            } else if (view == ib_3b) {
                                tv_3b.setText(player_3b_name + "(" + player_3b_number + ")");
                            } else if (view == ib_ss) {
                                tv_ss.setText(player_ss_name + "(" + player_ss_number + ")");
                            } else if (view == ib_p) {
                                tv_p.setText(player_p_name + "(" + player_p_number + ")");
                            } else if (view == ib_c) {
                                tv_c.setText(player_c_name + "(" + player_c_number + ")");
                            } else {
                            }
                            Toast.makeText(getApplicationContext(), "Member 설정변경", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = alert2.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        }
    }
}