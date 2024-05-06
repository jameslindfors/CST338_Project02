package com.cst338.project02.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.User;
import com.cst338.project02.Data.UserDAO;
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

                if(userUsername.isEmpty()){
                    invalidUsername();
                }else if (userPassword.isEmpty()){
                    invalidPassword();
                }else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                            UserDAO dao = db.userDao();
                            System.out.println(userUsername);
                            System.out.println(userPassword);
                            User newUser = new User(userUsername, userPassword, false);

                            SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username", userUsername);
                            editor.putInt("userID", newUser.getId());
                            editor.putBoolean("isAdmin", newUser.isAdmin());
                            editor.apply();


                            dao.insert(newUser);

                        }
                    }).start();
                    Intent goHomePage = new Intent(getApplicationContext(), LandingActivity.class);

                    goHomePage.putExtra("USERNAME", userUsername);

                    startActivity(goHomePage);
                }
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

    public void invalidUsername(){
        Toast.makeText(this, "Username is empty. Enter valid username", Toast.LENGTH_SHORT).show();
    }
    public void invalidPassword(){
        Toast.makeText(this, "Password is empty. Enter valid password", Toast.LENGTH_SHORT).show();
    }
}