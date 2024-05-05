package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.pm.PackageManager;
import android.Manifest;


import com.cst338.project02.databinding.ActivityProfilePageBinding;

public class ProfilePage extends AppCompatActivity {

    ActivityProfilePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.navigation.setOnItemSelectedListener(item -> {
            Intent intent;
            if (item.getItemId() == R.id.navigation_home) {
                intent = new Intent(this, LandingPage.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(this, ProfilePage.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_create){
                intent = new Intent(this, AddChargerPage.class);
                startActivity(intent);
            }

            return true;
        });
    }
}