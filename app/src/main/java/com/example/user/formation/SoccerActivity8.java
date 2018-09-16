package com.example.user.formation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017-05-25.
 */

public class SoccerActivity8 extends AppCompatActivity {
    String player_name;
    String naming;
    ImageView mutebutton;
    SoccerActivity a;

    float posX, posY;
    int isMoving;
    final int START_DRAG = 0;
    final int END_DRAG = 1;
    static final int FOWARD = 0;
    static final int MIDFILDER = 1;
    static final int DEFENDER = 2;
    static final int GOALKEEPER = 3;

    String name;
    int num, player_age, player_number;
    int flaged = 0;
    float tempX;
    float tempY;

    List<ImageView> imageViewList = new ArrayList<>();
    List<TextView> textViewList = new ArrayList<>();

    int player1_flag = 0, player2_flag = 0, player3_flag = 1, player4_flag = 1, player5_flag = 1, player6_flag = 2,
            player7_flag = 2, player8_flag = 2, player9_flag = 2, player10_flag = 2, player11_flag = 3;
    ImageView iv_player1, iv_player2, iv_player3, iv_player4, iv_player5, iv_player6, iv_player7, iv_player8, iv_player9, iv_player10, iv_player11,iv_share;
    TextView tv_player1, tv_player2, tv_player3, tv_player4, tv_player5, tv_player6, tv_player7, tv_player8, tv_player9, tv_player10, tv_player11,tv_name;
    private float tempX2;
    private float tempY2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = new SoccerActivity();
        setContentView(R.layout.activity_soccer6);

        tv_name = (TextView) findViewById(R.id.tv7_name);
        tv_name.setText(a.getName());

        mutebutton = (ImageView) findViewById(R.id.muted4);
        mutebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flaged == 0) {
                    stopService(new Intent(SoccerActivity8.this, MusicService.class));
                    flaged = 1;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.notmute, getApplicationContext().getTheme()));
                } else {
                    startService(new Intent(SoccerActivity8.this, MusicService.class));
                    flaged = 0;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.mute, getApplicationContext().getTheme()));
                }
            }
        });
        name = a.tag;
        iv_share = (ImageView) findViewById(R.id.share7);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareKakao();
            }
        });

        iv_player1 = (ImageView) findViewById(R.id.iv7_player1);
        iv_player1.setOnClickListener(new MyListener());
        iv_player1.setOnTouchListener(new DraggingListener());

        iv_player2 = (ImageView) findViewById(R.id.iv7_player2);
        iv_player2.setOnClickListener(new MyListener());
        iv_player2.setOnTouchListener(new DraggingListener());

        iv_player3 = (ImageView) findViewById(R.id.iv7_player3);
        iv_player3.setOnClickListener(new MyListener());
        iv_player3.setOnTouchListener(new DraggingListener());

        iv_player4 = (ImageView) findViewById(R.id.iv7_player4);
        iv_player4.setOnClickListener(new MyListener());
        iv_player4.setOnTouchListener(new DraggingListener());

        iv_player5 = (ImageView) findViewById(R.id.iv7_player5);
        iv_player5.setOnClickListener(new MyListener());
        iv_player5.setOnTouchListener(new DraggingListener());

        iv_player6 = (ImageView) findViewById(R.id.iv7_player6);
        iv_player6.setOnClickListener(new MyListener());
        iv_player6.setOnTouchListener(new DraggingListener());

        iv_player7 = (ImageView) findViewById(R.id.iv7_player7);
        iv_player7.setOnClickListener(new MyListener());
        iv_player7.setOnTouchListener(new DraggingListener());

        iv_player8 = (ImageView) findViewById(R.id.iv7_player8);
        iv_player8.setOnClickListener(new MyListener());
        iv_player8.setOnTouchListener(new DraggingListener());

        iv_player9 = (ImageView) findViewById(R.id.iv7_player9);
        iv_player9.setOnClickListener(new MyListener());
        iv_player9.setOnTouchListener(new DraggingListener());

        iv_player10 = (ImageView) findViewById(R.id.iv7_player10);
        iv_player10.setOnClickListener(new MyListener());
        iv_player10.setOnTouchListener(new DraggingListener());

        iv_player11 = (ImageView) findViewById(R.id.iv7_player11);
        iv_player11.setOnClickListener(new MyListener());
        iv_player11.setOnTouchListener(new DraggingListener());


        tv_player1 = (TextView) findViewById(R.id.tv7_player1);
        tv_player2 = (TextView) findViewById(R.id.tv7_player2);
        tv_player3 = (TextView) findViewById(R.id.tv7_player3);
        tv_player4 = (TextView) findViewById(R.id.tv7_player4);
        tv_player5 = (TextView) findViewById(R.id.tv7_player5);
        tv_player6 = (TextView) findViewById(R.id.tv7_player6);
        tv_player7 = (TextView) findViewById(R.id.tv7_player7);
        tv_player8 = (TextView) findViewById(R.id.tv7_player8);
        tv_player9 = (TextView) findViewById(R.id.tv7_player9);
        tv_player10 = (TextView) findViewById(R.id.tv7_player10);
        tv_player11 = (TextView) findViewById(R.id.tv7_player11);

        imageViewList.add(iv_player1);
        imageViewList.add(iv_player2);
        imageViewList.add(iv_player3);
        imageViewList.add(iv_player4);
        imageViewList.add(iv_player5);
        imageViewList.add(iv_player6);
        imageViewList.add(iv_player7);
        imageViewList.add(iv_player8);
        imageViewList.add(iv_player9);
        imageViewList.add(iv_player10);
        imageViewList.add(iv_player11);

        textViewList.add(tv_player1);
        textViewList.add(tv_player2);
        textViewList.add(tv_player3);
        textViewList.add(tv_player4);
        textViewList.add(tv_player5);
        textViewList.add(tv_player6);
        textViewList.add(tv_player7);
        textViewList.add(tv_player8);
        textViewList.add(tv_player9);
        textViewList.add(tv_player10);
        textViewList.add(tv_player11);
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

    class DraggingListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                tempY = v.getY();
                tempX = v.getX();


                posX = event.getX();
                posY = event.getY();

                tempX2 = (int) event.getRawX() - posX + 15;
                tempY2 = (int) event.getRawY() - posY + 50;

                //Toast.makeText(Baseball_Deffense.this,"Drag start",Toast.LENGTH_SHORT).show();
                isMoving = START_DRAG;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                for (int i = 0; i < imageViewList.size(); i++) {

                    if (v.getId() == imageViewList.get(i).getId())
                        continue;
                    if ((v.getX() - imageViewList.get(i).getX()) * (v.getX() - imageViewList.get(i).getX()) +
                            (v.getY() - imageViewList.get(i).getY()) * (v.getY() - imageViewList.get(i).getY()) < (v.getWidth() / 2) * (v.getWidth() / 2)) {
                        Toast.makeText(SoccerActivity8.this, "변경완료", Toast.LENGTH_SHORT).show();

                        v.setX(imageViewList.get(i).getX());
                        v.setY(imageViewList.get(i).getY());

                        imageViewList.get(i).setX(tempX);
                        imageViewList.get(i).setY(tempY);
                        textViewList.get(i).setX(tempX2);
                        textViewList.get(i).setY(tempY2);

                    }

                }
                if (v == iv_player1) {
                    setImage(player1_flag, iv_player1);
                } else if (v == iv_player2) {
                    setImage(player2_flag, iv_player2);
                } else if (v == iv_player3) {
                    setImage(player3_flag, iv_player3);
                } else if (v == iv_player4) {
                    setImage(player4_flag, iv_player4);
                } else if (v == iv_player5) {
                    setImage(player5_flag, iv_player5);
                } else if (v == iv_player6) {
                    setImage(player6_flag, iv_player6);
                } else if (v == iv_player7) {
                    setImage(player7_flag, iv_player7);
                } else if (v == iv_player8) {
                    setImage(player8_flag, iv_player8);
                } else if (v == iv_player9) {
                    setImage(player9_flag, iv_player9);
                } else if (v == iv_player10) {
                    setImage(player10_flag, iv_player10);
                } else if (v == iv_player11) {
                    setImage(player11_flag, iv_player11);
                } else {
                }
                isMoving = END_DRAG;

            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (isMoving == START_DRAG) {
                    v.setX((int) event.getRawX() - posX);
                    v.setY((int) event.getRawY() - posY);
                    if (v == iv_player1) {
                        setMovingImage(player1_flag, iv_player1);
                        tv_player1.setX((int) event.getRawX() - posX + 15);
                        tv_player1.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player2) {
                        setMovingImage(player2_flag, iv_player2);
                        tv_player2.setX((int) event.getRawX() - posX + 15);
                        tv_player2.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player3) {
                        setMovingImage(player3_flag, iv_player3);
                        tv_player3.setX((int) event.getRawX() - posX + 15);
                        tv_player3.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player4) {
                        setMovingImage(player4_flag, iv_player4);
                        tv_player4.setX((int) event.getRawX() - posX + 15);
                        tv_player4.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player5) {
                        setMovingImage(player5_flag, iv_player5);
                        tv_player5.setX((int) event.getRawX() - posX + 15);
                        tv_player5.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player6) {
                        setMovingImage(player6_flag, iv_player6);
                        tv_player6.setX((int) event.getRawX() - posX + 15);
                        tv_player6.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player7) {
                        setMovingImage(player7_flag, iv_player7);
                        tv_player7.setX((int) event.getRawX() - posX + 15);
                        tv_player7.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player8) {
                        setMovingImage(player8_flag, iv_player8);
                        tv_player8.setX((int) event.getRawX() - posX + 15);
                        tv_player8.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player9) {
                        setMovingImage(player9_flag, iv_player9);
                        tv_player9.setX((int) event.getRawX() - posX + 15);
                        tv_player9.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player10) {
                        setMovingImage(player10_flag, iv_player10);
                        tv_player10.setX((int) event.getRawX() - posX + 15);
                        tv_player10.setY((int) event.getRawY() - posY + 100);
                    } else if (v == iv_player11) {
                        setMovingImage(player11_flag, iv_player11);
                        tv_player11.setX((int) event.getRawX() - posX + 15);
                        tv_player11.setY((int) event.getRawY() - posY + 100);
                    } else {
                    }
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
            if (System.currentTimeMillis() > btnPressTime + 1000) {
                btnPressTime = System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() <= btnPressTime + 1000) {
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.soccer_dialog, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(SoccerActivity8.this);
                alert.setTitle("Input your Player Information");
                alert.setIcon(R.drawable.iv_soccer);
                alert.setView(dialogView);
                alert.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edit_name = (EditText) dialogView.findViewById(R.id.et_name);
                        EditText edit_age = (EditText) dialogView.findViewById(R.id.et_age);
                        EditText edit_number = (EditText) dialogView.findViewById(R.id.et_number);
                        RadioGroup rg = (RadioGroup) dialogView.findViewById(R.id.radiogroup2);
                        RadioButton rb1 = (RadioButton) dialogView.findViewById(R.id.rb_fw);
                        RadioButton rb2 = (RadioButton) dialogView.findViewById(R.id.rb_mid);
                        RadioButton rb3 = (RadioButton) dialogView.findViewById(R.id.rb_df);
                        RadioButton rb4 = (RadioButton) dialogView.findViewById(R.id.rb_gk);


                        if (edit_name.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (edit_age.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 나이을 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (edit_number.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            player_name = edit_name.getText().toString();
                            player_age = Integer.parseInt(edit_age.getText().toString());
                            player_number = Integer.parseInt(edit_number.getText().toString());
                            if (rb1.isChecked()) {
                                if (view == iv_player1) {
                                    player1_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player2) {
                                    player2_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player3) {
                                    player3_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player4) {
                                    player4_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player5) {
                                    player5_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player6) {
                                    player6_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player7) {
                                    player7_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player8) {
                                    player8_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player9) {
                                    player9_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player10) {
                                    player10_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else if (view == iv_player11) {
                                    player11_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
                                } else {
                                }
                            } else if (rb2.isChecked()) {
                                if (view == iv_player1) {
                                    player1_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player2) {
                                    player2_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player3) {
                                    player3_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player4) {
                                    player4_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player5) {
                                    player5_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player6) {
                                    player6_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player7) {
                                    player7_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player8) {
                                    player8_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player9) {
                                    player9_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player10) {
                                    player10_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else if (view == iv_player11) {
                                    player11_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
                                } else {
                                }
                            } else if (rb3.isChecked()) {
                                if (view == iv_player1) {
                                    player1_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player2) {
                                    player2_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player3) {
                                    player3_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player4) {
                                    player4_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player5) {
                                    player5_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player6) {
                                    player6_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player7) {
                                    player7_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player8) {
                                    player8_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player9) {
                                    player9_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player10) {
                                    player10_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else if (view == iv_player11) {
                                    player11_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
                                } else {
                                }
                            } else if (rb4.isChecked()) {
                                if (view == iv_player1) {
                                    player1_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player2) {
                                    player2_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player3) {
                                    player3_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player4) {
                                    player4_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player5) {
                                    player5_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player6) {
                                    player6_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player7) {
                                    player7_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player8) {
                                    player8_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player9) {
                                    player9_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player10) {
                                    player10_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else if (view == iv_player11) {
                                    player11_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
                                } else {
                                }
                            } else {
                            }

                            if (view == iv_player1) {
                                tv_player1.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player2) {
                                tv_player2.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player3) {
                                tv_player3.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player4) {
                                tv_player4.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player5) {
                                tv_player5.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player6) {
                                tv_player6.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player7) {
                                tv_player7.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player8) {
                                tv_player8.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player9) {
                                tv_player9.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player10) {
                                tv_player10.setText(player_name + "(" + player_number + ")");
                            } else if (view == iv_player11) {
                                tv_player11.setText(player_name + "(" + player_number + ")");
                            } else {
                            }
                            Toast.makeText(getApplicationContext(), "Member 설정변경", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        }
    }

    private void setImage(int position, ImageView imageView) {
        if (position == FOWARD) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.foward, getApplicationContext().getTheme()));
        } else if (position == MIDFILDER) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.mid, getApplicationContext().getTheme()));
        } else if (position == DEFENDER) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.defender, getApplicationContext().getTheme()));
        } else if (position == GOALKEEPER) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.goalkeeper, getApplicationContext().getTheme()));
        }
    }

    private void setMovingImage(int position, ImageView imageView) {
        if (position == FOWARD) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_attack, getApplicationContext().getTheme()));
        } else if (position == MIDFILDER) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_mid, getApplicationContext().getTheme()));
        } else if (position == DEFENDER) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_defender, getApplicationContext().getTheme()));
        } else if (position == GOALKEEPER) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_gk, getApplicationContext().getTheme()));
        }
    }
}
