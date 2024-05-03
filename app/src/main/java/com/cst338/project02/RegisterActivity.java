package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cst338.project02.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        binding.registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userUsername = binding.registerUsername.getText().toString();
                String userPassword = binding.registerPassword.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                        UserDAO dao = db.userDao();
                        System.out.println(userUsername);
                        System.out.println(userPassword);
                        User newUser = new User(userUsername, userPassword, false);
                        dao.insert(newUser);

                    }
                }).start();
                Intent goHomePage = new Intent(getApplicationContext(), LandingPage.class);

                goHomePage.putExtra("USERNAME", userUsername);

                startActivity(goHomePage);
            }
        });

        binding.goLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerPage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerPage);
            }
        });

    }
}