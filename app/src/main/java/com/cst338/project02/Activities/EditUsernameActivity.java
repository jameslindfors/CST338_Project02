package com.cst338.project02.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.UserDAO;
import com.cst338.project02.databinding.ActivityEditUsernameBinding;

public class EditUsernameActivity extends AppCompatActivity {

    ActivityEditUsernameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditUsernameBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        binding.backPriofileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(EditUsernameActivity.this, AccountSettingsActivity.class);
                startActivity(goBack);
            }
        });

        binding.changeUsernameFinalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDAO dao = db.userDao();
                SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                int id = preferences.getInt("userID", -1);

                dao.updateUsername(id, binding.changeUsernameEditText.getText().toString());
            }

        });
    }
}