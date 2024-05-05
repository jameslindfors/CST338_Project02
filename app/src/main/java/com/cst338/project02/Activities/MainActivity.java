package com.cst338.project02.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.User;
import com.cst338.project02.Data.UserDAO;
import com.cst338.project02.Data.UserRepository;
import com.cst338.project02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserRepository repository;
    private static final String TAG = "USERLOG";
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        repository = UserRepository.getRepository(getApplication());


        AppDatabase.getInstance(getApplicationContext());
        binding.loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });

        binding.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertUser(){
        User user = new User("testuser1", "testuser1", false);
        repository.insertUser(user);
    }
}