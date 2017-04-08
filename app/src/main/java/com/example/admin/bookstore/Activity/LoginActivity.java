package com.example.admin.bookstore.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.bookstore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button btnDangKy, btnDangNhap;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String id;
    CheckBox chkRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtPassword.getText().toString().length() == 0) {
                    edtPassword.setError("Chưa nhập mật khẩu!");
                    edtPassword.requestFocus();

                }else
                if (edtEmail.getText().toString().length() == 0) {
                    edtEmail.setError("Chưa nhập email!");
                    edtEmail.requestFocus();

                }else  if (edtEmail.getText().toString().equals("User") &&  edtPassword.getText().toString().equals("User")) {


                    // tạo đối tượng getSharedPreferences
                    SharedPreferences pref = getSharedPreferences("myData", MODE_PRIVATE);
                    // tạo đối tượng Editor
                    SharedPreferences.Editor editor = pref.edit();
                    // kiểm tra xem đã check chưa
                    boolean checkR = chkRemember.isChecked();
                    if (!checkR) {
                        // xoá dữ liệu lưu trữ trước đó
                        editor.clear();
                    } else {
                        // lưu file vào editor
                        editor.putString("email", edtEmail.getText().toString());
                        editor.putString("pass", edtPassword.getText().toString());
                        editor.putBoolean("chk", chkRemember.isChecked());
                    }
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    // chấp nhận lưu xuống file
                    editor.commit();
                }else{
                    Toast.makeText(LoginActivity.this,"Pass or User sai ",Toast.LENGTH_SHORT).show();
                }


            }
        });
        restoringPreferences();

    }



    private void init() {
        edtEmail = (EditText) findViewById(R.id.edtEmailDangNhap);
        edtPassword = (EditText) findViewById(R.id.edtPasswordDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKyDangNhap);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhapDangNhap);
        chkRemember = (CheckBox) findViewById(R.id.checkBox);


    }

    private void restoringPreferences() {
        SharedPreferences pref = getSharedPreferences("myData", MODE_PRIVATE);
        boolean ck = pref.getBoolean("chk", false);
        if (ck) {
            String email = pref.getString("email", "");
            String pass = pref.getString("pass", "");
            edtEmail.setText(email);
            edtPassword.setText(pass);
        }
        chkRemember.setChecked(ck);
    }

}
