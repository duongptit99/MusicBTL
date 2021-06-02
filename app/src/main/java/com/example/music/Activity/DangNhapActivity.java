package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.music.Model.User;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    EditText editTextUserName,editTextPassWord;
    Button btnLogin;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initView();

        btnLogin.setOnClickListener(v -> {
            intent = new Intent(DangNhapActivity.this,MainActivity.class);
            GetData(editTextUserName.getText().toString(),editTextPassWord.getText().toString());

        });


    }

    private void GetData(String user, String pass) {
        Dataservice dataservice = APIService.getService();
        Call<String> callback= dataservice.GetUser(user,pass);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String kq= response.body();
                if(kq.equals("OK")){
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else {
                    Toast.makeText(DangNhapActivity.this, "Sai thông tin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void initView() {
        editTextPassWord = findViewById(R.id.edit_text_password);
        editTextUserName = findViewById(R.id.edit_text_username);
        btnLogin = findViewById(R.id.button_login);
    }
}