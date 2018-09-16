package com.example.user.formation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by USER on 2017-05-08.
 */

public class RegistryClass extends AppCompatActivity{
    String st_email,st_password;
    private ImageView email,password,check;
    private EditText etEmail,etPassword,etPasswordConfirm;
    private ImageButton btnDone,btnCancel;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);

        email = (ImageView) findViewById(R.id.iv_email);
        password = (ImageView) findViewById(R.id.iv_password);
        check = (ImageView) findViewById(R.id.iv_check);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordConfirm = (EditText) findViewById(R.id.et_check);

        btnDone = (ImageButton) findViewById(R.id.ib_register);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이메일 입력 확인
                if( etEmail.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistryClass.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                else if( etPassword.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistryClass.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                else if( etPasswordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistryClass.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                else if( !etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ) {
                    Toast.makeText(RegistryClass.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }
                else {
                    progressDialog.setMessage("가입하는 중입니다...");
                    progressDialog.show();
                    st_email = etEmail.getText().toString();
                    st_password = etPassword.getText().toString();
                    //db.addUser(st_email,st_password);
                    firebaseAuth.createUserWithEmailAndPassword(st_email,st_password)
                            .addOnCompleteListener(RegistryClass.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegistryClass.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(RegistryClass.this,"Could not registered...Please try again..",Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
                Intent result = new Intent();
                result.putExtra("email", etEmail.getText().toString());

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }
        });

        btnCancel = (ImageButton) findViewById(R.id.ibRegist);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 비밀번호 일치 검사
        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

                if( password.equals(confirm) ) {
                    etPassword.setTextColor(Color.WHITE);
                    etPasswordConfirm.setTextColor(Color.WHITE);
                } else {
                    etPassword.setTextColor(Color.RED);
                    etPasswordConfirm.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}

