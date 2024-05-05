package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;


import com.cst338.project02.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {

    ActivityLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String username = preferences.getString("username", "DefaultUser");
//        int id = preferences.getInt("userID", -1);
        boolean isAdmin = preferences.getBoolean("isAdmin", false);
        if(isAdmin){
            System.out.println("USER IS THE ADMIN");
            binding.adminButton.setVisibility(View.VISIBLE);
        }else{
            System.out.println("USER IS NOT THE ADMIN");
            binding.adminButton.setVisibility(View.INVISIBLE);
        }

        binding.userName.setText(username);

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

        binding.goProfilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfilePage = new Intent(LandingPage.this, ProfilePage.class);
                startActivity(goProfilePage);
            }
        });



        if (!canAccessLocation()) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                            },
                    1337 + 3);
        }
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }

}