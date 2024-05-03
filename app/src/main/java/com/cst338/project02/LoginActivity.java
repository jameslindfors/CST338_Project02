package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.cst338.project02.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);


        userDAO = Room.databaseBuilder(this, AppDatabase.class, "User.DB")
                .allowMainThreadQueries()
                .build().userDao();


        binding.loginUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(binding.loginUsername.getText().toString(),
                        binding.loginPassword.getText().toString());
            }
        });

        binding.goRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerPage = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerPage);
            }
        });
    }

    private void attemptLogin(String username, String password) {
        System.out.println(username + "  " + password + " LOGGING ING");


        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = AppDatabase
                        .getInstance(LoginActivity.this)
                        .userDao().loginUser(username, password);
                System.out.println(user);
                if(user != null){
                    Intent goHomePage = new Intent(LoginActivity.this, LandingPage.class);
                    goHomePage.putExtra("USERNAME",username);
                    startActivity(goHomePage);
                }
            }
        }).start();



    }

}