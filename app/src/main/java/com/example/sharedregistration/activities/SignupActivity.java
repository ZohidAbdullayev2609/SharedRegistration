package com.example.sharedregistration.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedregistration.NextActivity;
import com.example.sharedregistration.databinding.ActivitySignupBinding;
import com.example.sharedregistration.model.User;
import com.example.sharedregistration.utils.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    MySharedPreference mySharedPreference;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mySharedPreference = MySharedPreference.getInstance(SignupActivity.this);

        binding.btnSignup.setOnClickListener(view -> {
            String firstName = binding.signupFirstName.getText().toString();
            String lastName = binding.signupLastName.getText().toString();
            String email = binding.signupEmail.getText().toString();
            String password = binding.signupPassword1.getText().toString();
            String confirmPassword = binding.signupPassword2.getText().toString();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                    || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(firstName, lastName, email, password, confirmPassword);

                Gson gson = new Gson();

                Type type = new TypeToken<ArrayList<User>>() {
                }.getType();

                list = new ArrayList<>();
                String userData = mySharedPreference.getUserData();
                if (!userData.equals("")) {
                    list = gson.fromJson(userData, type);
                }
                list.add(user);
                String s = gson.toJson(list);
                mySharedPreference.setUserData(s);
                Intent intent = new Intent(SignupActivity.this, NextActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.singStrelka.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
        });


    }
}