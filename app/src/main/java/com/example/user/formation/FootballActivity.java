package com.example.user.formation;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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
 * Created by USER on 2017-05-09.
 */

public class FootballActivity extends AppCompatActivity {

    int flag = 0;
    String player1_name, player2_name, player3_name, player4_name, player5_name;
    int player1_age, player2_age, player3_age, player4_age, player5_age;
    int player1_number, player2_number, player3_number, player4_number, player5_number;

    private ImageView mutebutton, settingbtn;
    private ImageView iv_player1, iv_player2, iv_player3, iv_player4, iv_player5,iv_share;
    private TextView tv_player1, tv_player2, tv_player3, tv_player4, tv_player5;

    int flaged = 0;
    float posX, posY;
    float tempX;
    float tempY;
    private float tempX2;
    private float tempY2;

    int isMoving;
    final int START_DRAG = 0;
    final int END_DRAG = 1;
    static final int FLAG1 = 0;
    static final int FLAG2 = 1;
    static final int FLAG3 = 2;
    static final int FLAG4 = 3;
    static final int FLAG5 = 4;
    static final int FLAG6 = 5;
    static final int FLAG7 = 6;
    static final int FLAG8 = 7;

    List<ImageView> imageViewList = new ArrayList<>();
    List<TextView> textViewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        settingbtn = (ImageView) findViewById(R.id.settingbutton);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMeUniform();
            }
        });
        mutebutton = (ImageView) findViewById(R.id.mutebtn);
        mutebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flaged == 0) {
                    stopService(new Intent(FootballActivity.this, MusicService.class));
                    flaged = 1;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.notmute, getApplicationContext().getTheme()));
                } else {
                    startService(new Intent(FootballActivity.this, MusicService.class));
                    flaged = 0;
                    mutebutton.setImageDrawable(getResources().getDrawable(R.drawable.mute, getApplicationContext().getTheme()));
                }
            }
        });

        iv_share = (ImageView) findViewById(R.id.share10);
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareKakao();
            }
        });

        iv_player1 = (ImageView) findViewById(R.id.futsal_player1);
        iv_player1.setOnTouchListener(new DraggingListener());
        iv_player1.setOnClickListener(new MyListener());


        iv_player2 = (ImageView) findViewById(R.id.futsal_player2);
        iv_player2.setOnTouchListener(new DraggingListener());
        iv_player2.setOnClickListener(new MyListener());

        iv_player3 = (ImageView) findViewById(R.id.futsal_player3);
        iv_player3.setOnTouchListener(new DraggingListener());
        iv_player3.setOnClickListener(new MyListener());

        iv_player4 = (ImageView) findViewById(R.id.futsal_player4);
        iv_player4.setOnTouchListener(new DraggingListener());
        iv_player4.setOnClickListener(new MyListener());

        iv_player5 = (ImageView) findViewById(R.id.futsal_player5);
        iv_player5.setOnTouchListener(new DraggingListener());
        iv_player5.setOnClickListener(new MyListener());

        tv_player1 = (TextView) findViewById(R.id.tvv_player1);
        tv_player2 = (TextView) findViewById(R.id.tvv_player2);
        tv_player3 = (TextView) findViewById(R.id.tvv_player3);
        tv_player4 = (TextView) findViewById(R.id.tvv_player4);
        tv_player5 = (TextView) findViewById(R.id.tvv_player5);

        imageViewList.add(iv_player1);
        imageViewList.add(iv_player2);
        imageViewList.add(iv_player3);
        imageViewList.add(iv_player4);
        imageViewList.add(iv_player5);

        textViewList.add(tv_player1);
        textViewList.add(tv_player2);
        textViewList.add(tv_player3);
        textViewList.add(tv_player4);
        textViewList.add(tv_player5);

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
                tempY2 = (int) event.getRawY() - posY + 90;

                //Toast.makeText(Baseball_Deffense.this,"Drag start",Toast.LENGTH_SHORT).show();
                isMoving = START_DRAG;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                for (int i = 0; i < imageViewList.size(); i++) {

                    if (v.getId() == imageViewList.get(i).getId())
                        continue;
                    if ((v.getX() - imageViewList.get(i).getX()) * (v.getX() - imageViewList.get(i).getX()) +
                            (v.getY() - imageViewList.get(i).getY()) * (v.getY() - imageViewList.get(i).getY()) < (v.getWidth() / 2) * (v.getWidth() / 2)) {
                        Toast.makeText(FootballActivity.this, "변경완료", Toast.LENGTH_SHORT).show();

                        v.setX(imageViewList.get(i).getX());
                        v.setY(imageViewList.get(i).getY());

                        imageViewList.get(i).setX(tempX);
                        imageViewList.get(i).setY(tempY);
                        textViewList.get(i).setX(tempX2);
                        textViewList.get(i).setY(tempY2);

                    }
                }
                if (v == iv_player1) {
                    setImage(flag, iv_player1);
                } else if (v == iv_player2) {
                    setImage(flag, iv_player2);
                } else if (v == iv_player3) {
                    setImage(flag, iv_player3);
                } else if (v == iv_player4) {
                    setImage(flag, iv_player4);
                } else if (v == iv_player5) {
                    setImage(flag, iv_player5);
                } else {
                }
                isMoving = END_DRAG;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (isMoving == START_DRAG) {
                    v.setX((int) event.getRawX() - posX);
                    v.setY((int) event.getRawY() - posY);
                    if (v == iv_player1) {
                        setMovingImage(flag, iv_player1);
                        tv_player1.setX((int) event.getRawX() - posX + 15);
                        tv_player1.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_player2) {
                        setMovingImage(flag, iv_player2);
                        tv_player2.setX((int) event.getRawX() - posX + 15);
                        tv_player2.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_player3) {
                        setMovingImage(flag, iv_player3);
                        tv_player3.setX((int) event.getRawX() - posX + 15);
                        tv_player3.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_player4) {
                        setMovingImage(flag, iv_player4);
                        tv_player4.setX((int) event.getRawX() - posX + 15);
                        tv_player4.setY((int) event.getRawY() - posY + 150);
                    } else if (v == iv_player5) {
                        setMovingImage(flag, iv_player5);
                        tv_player5.setX((int) event.getRawX() - posX + 15);
                        tv_player5.setY((int) event.getRawY() - posY + 150);
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
                final View dialogView = inflater.inflate(R.layout.futsal_dialog, null);

                AlertDialog.Builder alert2 = new AlertDialog.Builder(FootballActivity.this);
                alert2.setTitle("Input your Player Information");
                alert2.setIcon(R.drawable.tag3);
                alert2.setView(dialogView);
                alert2.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_name = (EditText) dialogView.findViewById(R.id.edittext_futsal_name);
                        EditText et_number = (EditText) dialogView.findViewById(R.id.edittext_futsal_number);
                        EditText et_age = (EditText) dialogView.findViewById(R.id.edittext_futsal_age);


                        if (et_name.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else if (et_number.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 번호을 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else if (et_age.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(), "선수 나이를 입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            if (view == iv_player1) {
                                player1_name = et_name.getText().toString();
                                player1_age = Integer.parseInt(et_age.getText().toString());
                                player1_number = Integer.parseInt(et_number.getText().toString());
                            } else if (view == iv_player2) {
                                player2_name = et_name.getText().toString();
                                player2_age = Integer.parseInt(et_age.getText().toString());
                                player2_number = Integer.parseInt(et_number.getText().toString());
                            } else if (view == iv_player3) {
                                player3_name = et_name.getText().toString();
                                player3_age = Integer.parseInt(et_age.getText().toString());
                                player3_number = Integer.parseInt(et_number.getText().toString());
                            } else if (view == iv_player4) {
                                player4_name = et_name.getText().toString();
                                player4_age = Integer.parseInt(et_age.getText().toString());
                                player4_number = Integer.parseInt(et_number.getText().toString());
                            } else if (view == iv_player5) {
                                player5_name = et_name.getText().toString();
                                player5_age = Integer.parseInt(et_age.getText().toString());
                                player5_number = Integer.parseInt(et_number.getText().toString());
                            } else {
                            }


                            if (view == iv_player1) {
                                tv_player1.setText(player1_name + "(" + player1_number + ")");
                            } else if (view == iv_player2) {
                                tv_player2.setText(player2_name + "(" + player2_number + ")");
                            } else if (view == iv_player3) {
                                tv_player3.setText(player3_name + "(" + player3_number + ")");
                            } else if (view == iv_player4) {
                                tv_player4.setText(player4_name + "(" + player4_number + ")");
                            } else if (view == iv_player5) {
                                tv_player5.setText(player5_name + "(" + player5_number + ")");
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

    private void setImage(int position, ImageView imageView) {
        if (position == FLAG1) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv, getApplicationContext().getTheme()));
        } else if (position == FLAG2) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv2, getApplicationContext().getTheme()));
        } else if (position == FLAG3) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv3, getApplicationContext().getTheme()));
        } else if (position == FLAG4) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv4, getApplicationContext().getTheme()));
        } else if (position == FLAG5) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv5, getApplicationContext().getTheme()));
        } else if (position == FLAG6) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv6, getApplicationContext().getTheme()));
        } else if (position == FLAG7) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv7, getApplicationContext().getTheme()));
        } else if (position == FLAG8) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv8, getApplicationContext().getTheme()));
        }
    }

    private void setMovingImage(int position, ImageView imageView) {
        if (position == FLAG1) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG2) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG3) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG4) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG5) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG6) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG7) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        } else if (position == FLAG8) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.shirt, getApplicationContext().getTheme()));
        }
    }

    public void showMeUniform(){
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.uniform_select_dialog, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(FootballActivity.this);
        alert.setTitle("Choose Uniform settings");
        alert.setIcon(R.drawable.shirt);
        alert.setView(dialogView);
        alert.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RadioGroup rg = (RadioGroup) dialogView.findViewById(R.id.radioradio);
                RadioButton rb1 = (RadioButton) dialogView.findViewById(R.id.radio_barcelona);
                RadioButton rb2 = (RadioButton) dialogView.findViewById(R.id.radio_boca);
                RadioButton rb3 = (RadioButton) dialogView.findViewById(R.id.radio_ajax);
                RadioButton rb4 = (RadioButton) dialogView.findViewById(R.id.radio_arsenal);
                RadioButton rb5 = (RadioButton) dialogView.findViewById(R.id.radio_bayern);
                RadioButton rb6 = (RadioButton) dialogView.findViewById(R.id.radio_dortmund);
                RadioButton rb7 = (RadioButton) dialogView.findViewById(R.id.radio_celtics);
                RadioButton rb8 = (RadioButton) dialogView.findViewById(R.id.radio_sharlke);

                if (rb1.isChecked()) {
                    flag = 0;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv, getApplicationContext().getTheme()));
                }else if (rb2.isChecked()) {
                    flag = 1;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv2, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv2, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv2, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv2, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv2, getApplicationContext().getTheme()));
                }else if (rb3.isChecked()) {
                    flag = 2;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv3, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv3, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv3, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv3, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv3, getApplicationContext().getTheme()));
                }else if (rb4.isChecked()) {
                    flag = 3;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv4, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv4, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv4, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv4, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv4, getApplicationContext().getTheme()));
                }else if (rb5.isChecked()) {
                    flag = 4;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv5, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv5, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv5, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv5, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv5, getApplicationContext().getTheme()));
                }else if (rb6.isChecked()) {
                    flag = 5;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv6, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv6, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv6, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv6, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv6, getApplicationContext().getTheme()));
                }else if (rb7.isChecked()) {
                    flag = 6;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv7, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv7, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv7, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv7, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv7, getApplicationContext().getTheme()));
                }else if (rb8.isChecked()) {
                    flag = 7;
                    iv_player1.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv8, getApplicationContext().getTheme()));
                    iv_player2.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv8, getApplicationContext().getTheme()));
                    iv_player3.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv8, getApplicationContext().getTheme()));
                    iv_player4.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv8, getApplicationContext().getTheme()));
                    iv_player5.setImageDrawable(getResources().getDrawable(R.drawable.futsal_iv8, getApplicationContext().getTheme()));
                }else {}
                Toast.makeText(getApplicationContext(), "Uniform 설정변경", Toast.LENGTH_SHORT).show();
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