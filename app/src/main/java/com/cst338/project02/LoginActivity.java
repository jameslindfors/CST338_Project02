package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.cst338.project02.databinding.ActivityLoginBinding;

import java.util.List;

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
    }

    private void attemptLogin(String username, String password) {
        System.out.println(username + "  " + password + " LOGGING ING");
        List<User> userData = userDAO.loginUser(username, password);
        System.out.println(userData);
//        if(userData.size() > 0){
            Intent intent = new Intent(LoginActivity.this, LandingPage.class);
            startActivity(intent);
            finish();
//        }else{
//            binding.loginPassword.setError("Invalid credentials");
//        }
    }

}