package com.example.sharedregistration.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedregistration.NextActivity;
import com.example.sharedregistration.databinding.ActivityLoginBinding;
import com.example.sharedregistration.model.User;
import com.example.sharedregistration.utils.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    MySharedPreference mySharedPreference;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mySharedPreference = MySharedPreference.getInstance(LoginActivity.this);

        binding.btnLogin.setOnClickListener(view -> {
            String loginEmail = binding.loginEmail.getText().toString();
            String loginPassword = binding.loginPassword.getText().toString();

            if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT).show();
            } else if (loginEmail.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Pochtangizni kiriting", Toast.LENGTH_SHORT).show();
            } else if (loginPassword.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Parolingizni kiriting", Toast.LENGTH_SHORT).show();
            } else {
                Gson gson = new Gson();

                Type type = new TypeToken<ArrayList<User>>(){}.getType();

                list = new ArrayList<>();

                String userData = mySharedPreference.getUserData();
                if (userData.equals("")){
                    Toast.makeText(LoginActivity.this, "Login yoki parol noto'g'ri kiritilgan", Toast.LENGTH_SHORT).show();
                } else {
                    list = gson.fromJson(userData,type);
                    for (int i = 0; i < list.size(); i++) {
                        if (loginEmail.equals(list.get(i).getEmail()) && loginPassword.equals(list.get(i).getPassword())){
                            Intent intent = new Intent(LoginActivity.this, NextActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "Login yoki parol noto'g'ri kiritilgan", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        binding.loginStrelka.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        binding.textSingin.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }
}