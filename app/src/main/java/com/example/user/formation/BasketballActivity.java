package com.example.user.formation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
 * Created by USER on 2017-05-10.
 */

public class BasketballActivity extends AppCompatActivity {

    int pg_flag=0,sg_flag=1,pf_flag=2,sf_flag=3,c_flag=4;
    String pg_name,sg_name,pf_name,sf_name,c_name;
    int pg_height,sg_height,pf_height,sf_height,c_height;
    int pg_number,sg_number,pf_number,sf_number,c_number;

    private ImageView mutebutton;
    private ImageView iv_pg,iv_sg,iv_pf,iv_sf,iv_c,iv_share;
    private TextView tv_pg,tv_sg,tv_pf,tv_sf,tv_c;

    boolean flag=true;
    int flaged=0;
    float posX, posY;
    float tempX;
    float tempY;
    private float tempX2;
    private float tempY2;

    int isMoving;
    final int START_DRAG = 0;
    final int END_DRAG = 1;
    static final int PG = 0;
    static final int SG = 1;
    static final int PF = 2;
    static final int SF = 3;
    static final int C = 4;

    List<ImageView> imageViewList = new ArrayList<>();
    List<TextView> textViewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);


        mutebutton = (ImageView) findViewById(R.id.mutebutton2);
        mutebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flaged == 0){
                    stopService(new Intent(BasketballActivity.this,MusicService.class));
                    flaged = 1;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.notmute,getApplicationContext().getTheme()));
                }else{
                    startService(new Intent(BasketballActivity.this,MusicService.class));
                    flaged = 0;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.mute,getApplicationContext().getTheme()));
                }
            }
        });

        iv_share = (ImageView) findViewById(R.id.share9);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareKakao();
            }
        });

        iv_pg = (ImageView) findViewById(R.id.basketball_pg);
        iv_pg.setOnTouchListener(new DraggingListener());
        iv_pg.setOnClickListener(new MyListener());


        iv_sg = (ImageView) findViewById(R.id.basketball_sg);
        iv_sg.setOnTouchListener(new DraggingListener());
        iv_sg.setOnClickListener(new MyListener());

        iv_pf = (ImageView) findViewById(R.id.basketball_pf);
        iv_pf.setOnTouchListener(new DraggingListener());
        iv_pf.setOnClickListener(new MyListener());

        iv_sf = (ImageView) findViewById(R.id.basketball_sf);
        iv_sf.setOnTouchListener(new DraggingListener());
        iv_sf.setOnClickListener(new MyListener());

        iv_c = (ImageView) findViewById(R.id.basketball_C);
        iv_c.setOnTouchListener(new DraggingListener());
        iv_c.setOnClickListener(new MyListener());

        tv_pg = (TextView) findViewById(R.id.text_pg);
        tv_sg = (TextView) findViewById(R.id.text_sg);
        tv_pf = (TextView) findViewById(R.id.text_pf);
        tv_sf = (TextView) findViewById(R.id.text_sf);
        tv_c = (TextView) findViewById(R.id.text_c);

        imageViewList.add(iv_pg);
        imageViewList.add(iv_sg);
        imageViewList.add(iv_pf);
        imageViewList.add(iv_sf);
        imageViewList.add(iv_c);

        textViewList.add(tv_pg);
        textViewList.add(tv_sg);
        textViewList.add(tv_pf);
        textViewList.add(tv_sf);
        textViewList.add(tv_c);

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
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                tempY = v.getY();
                tempX = v.getX();


                posX = event.getX();
                posY = event.getY();

                tempX2 = (int) event.getRawX() - posX + 15;
                tempY2 = (int) event.getRawY() - posY + 90;

                //Toast.makeText(Baseball_Deffense.this,"Drag start",Toast.LENGTH_SHORT).show();
                isMoving = START_DRAG;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    for (int i = 0; i < imageViewList.size(); i++) {

                        if (v.getId() == imageViewList.get(i).getId())
                            continue;
                        if ((v.getX() - imageViewList.get(i).getX()) * (v.getX() - imageViewList.get(i).getX()) +
                                (v.getY() - imageViewList.get(i).getY()) * (v.getY() - imageViewList.get(i).getY()) < (v.getWidth() / 2) * (v.getWidth() / 2)) {
                            Toast.makeText(BasketballActivity.this, "변경완료", Toast.LENGTH_SHORT).show();

                            v.setX(imageViewList.get(i).getX());
                            v.setY(imageViewList.get(i).getY());

                            imageViewList.get(i).setX(tempX);
                            imageViewList.get(i).setY(tempY);
                            textViewList.get(i).setX(tempX2);
                            textViewList.get(i).setY(tempY2);

                        }
                    }
                if (v == iv_pg) {
                    setImage(pg_flag, iv_pg);
                } else if (v == iv_sg) {
                    setImage(sg_flag, iv_sg);
                } else if (v == iv_pf) {
                    setImage(pf_flag, iv_pf);
                } else if (v == iv_sf) {
                    setImage(sf_flag, iv_sf);
                } else if (v == iv_c) {
                    setImage(c_flag, iv_c);
                }else {
                }
                isMoving = END_DRAG;
            }else if(event.getAction()==MotionEvent.ACTION_MOVE){
                if (isMoving == START_DRAG) {
                    v.setX((int) event.getRawX() - posX);
                    v.setY((int) event.getRawY() - posY);
                    if (v == iv_pg) {
                        setMovingImage(pg_flag, iv_pg);
                        tv_pg.setX((int) event.getRawX() - posX + 15);
                        tv_pg.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_sg) {
                        setMovingImage(sg_flag, iv_sg);
                        tv_sg.setX((int) event.getRawX() - posX + 15);
                        tv_sg.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_pf) {
                        setMovingImage(pf_flag, iv_pf);
                        tv_pf.setX((int) event.getRawX() - posX + 15);
                        tv_pf.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_sf) {
                        setMovingImage(sf_flag, iv_sf);
                        tv_sf.setX((int) event.getRawX() - posX + 15);
                        tv_sf.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_c) {
                        setMovingImage(c_flag, iv_c);
                        tv_c.setX((int) event.getRawX() - posX + 15);
                        tv_c.setY((int) event.getRawY() - posY + 150);
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
            if(System.currentTimeMillis()>btnPressTime+1000){
                btnPressTime=System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() <= btnPressTime + 1000) {
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.basketball_dialog, null);

                AlertDialog.Builder alert2 = new AlertDialog.Builder(BasketballActivity.this);
                alert2.setTitle("Input your Player Information");
                alert2.setIcon(R.drawable.basketball_dialog_image);
                alert2.setView(dialogView);
                alert2.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_name = (EditText) dialogView.findViewById(R.id.edittext_name);
                        EditText et_number = (EditText) dialogView.findViewById(R.id.edittext_number);
                        EditText et_height = (EditText) dialogView.findViewById(R.id.edittext_height);

                        RadioGroup rg = (RadioGroup) dialogView.findViewById(R.id.radiogroup3);
                        RadioButton rb1 = (RadioButton) dialogView.findViewById(R.id.radio_pg);
                        RadioButton rb2 = (RadioButton) dialogView.findViewById(R.id.radio_sg);
                        RadioButton rb3 = (RadioButton) dialogView.findViewById(R.id.radio_pf);
                        RadioButton rb4 = (RadioButton) dialogView.findViewById(R.id.radio_sf);
                        RadioButton rb5 = (RadioButton) dialogView.findViewById(R.id.radio_c);


                        if (et_name.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else if (et_height.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 신장을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else if (et_number.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            if(view == iv_pg) {
                                pg_name = et_name.getText().toString();
                                pg_height = Integer.parseInt(et_height.getText().toString());
                                pg_number = Integer.parseInt(et_number.getText().toString());
                            }else if(view == iv_sg) {
                                sg_name = et_name.getText().toString();
                                sg_height = Integer.parseInt(et_height.getText().toString());
                                sg_number = Integer.parseInt(et_number.getText().toString());
                            }else if(view == iv_pf) {
                                pf_name = et_name.getText().toString();
                                pf_height = Integer.parseInt(et_height.getText().toString());
                                pf_number = Integer.parseInt(et_number.getText().toString());
                            }else if(view == iv_sf) {
                                sf_name = et_name.getText().toString();
                                sf_height = Integer.parseInt(et_height.getText().toString());
                                sf_number = Integer.parseInt(et_number.getText().toString());
                            }else if(view == iv_c) {
                                c_name = et_name.getText().toString();
                                c_height = Integer.parseInt(et_height.getText().toString());
                                c_number = Integer.parseInt(et_number.getText().toString());
                            }else {}
                            if (rb1.isChecked()) {
                                if(view ==  iv_pg){
                                    pg_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sg){
                                    sg_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_pf){
                                    pf_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sf){
                                    sf_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_c){
                                    c_flag = 0;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg, getApplicationContext().getTheme()));
                                }else {}
                            } else if (rb2.isChecked()) {
                                if(view ==  iv_pg){
                                    pg_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sg){
                                    sg_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_pf){
                                    pf_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sf){
                                    sf_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg, getApplicationContext().getTheme()));
                                }else if(view ==  iv_c){
                                    c_flag = 1;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg, getApplicationContext().getTheme()));
                                }else {}
                            } else if (rb3.isChecked()) {
                                if(view ==  iv_pg){
                                    pg_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sg){
                                    sg_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_pf){
                                    pf_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sf){
                                    sf_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_c){
                                    c_flag = 2;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf, getApplicationContext().getTheme()));
                                }else {}
                            } else if (rb4.isChecked()) {
                                if(view ==  iv_pg){
                                    pg_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sg){
                                    sg_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_pf){
                                    pf_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sf){
                                    sf_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf, getApplicationContext().getTheme()));
                                }else if(view ==  iv_c){
                                    c_flag = 3;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf, getApplicationContext().getTheme()));
                                }else {}
                            } else if (rb5.isChecked()) {
                                if(view ==  iv_pg){
                                    pg_flag = 4;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sg){
                                    sg_flag = 4;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c, getApplicationContext().getTheme()));
                                }else if(view ==  iv_pf){
                                    pf_flag = 4;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c, getApplicationContext().getTheme()));
                                }else if(view ==  iv_sf){
                                    sf_flag = 4;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c, getApplicationContext().getTheme()));
                                }else if(view ==  iv_c){
                                    c_flag = 4;
                                    view.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c, getApplicationContext().getTheme()));
                                }else {}
                            } else {}

                            if (view == iv_pg) {
                                tv_pg.setText(pg_name + "(" + pg_number + ")");
                            }else if (view == iv_sg) {
                                tv_sg.setText(sg_name + "(" + sg_number + ")");
                            }else if (view == iv_pf) {
                                tv_pf.setText(pf_name + "(" + pf_number + ")");
                            }else if (view == iv_sf) {
                                tv_sf.setText(sf_name + "(" + sf_number + ")");
                            }else if (view == iv_c) {
                                tv_c.setText(c_name + "(" + c_number + ")");
                            }else {}
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
        private void setImage(int position, ImageView imageView) {
            if (position == PG) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg, getApplicationContext().getTheme()));
            } else if (position == SG) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg, getApplicationContext().getTheme()));
            } else if (position == PF) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf, getApplicationContext().getTheme()));
            } else if (position == SF) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf, getApplicationContext().getTheme()));
            } else if (position == C) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c, getApplicationContext().getTheme()));
            }
        }

        private void setMovingImage(int position, ImageView imageView) {
            if (position == PG) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pg2, getApplicationContext().getTheme()));
            } else if (position == SG) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sg2, getApplicationContext().getTheme()));
            } else if (position == PF) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_pf2, getApplicationContext().getTheme()));
            } else if (position == SF) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_sf2, getApplicationContext().getTheme()));
            } else if (position == C) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.basketball_c2, getApplicationContext().getTheme()));
            }
        }
}