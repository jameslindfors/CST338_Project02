package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cst338.project02.databinding.ActivityAccountSettingsBinding;

public class AccountSettings extends AppCompatActivity {

    ActivityAccountSettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountSettingsBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        binding.backButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfilePage = new Intent(AccountSettings.this, ProfilePage.class);
                startActivity(goProfilePage);
            }
        });
    }
}