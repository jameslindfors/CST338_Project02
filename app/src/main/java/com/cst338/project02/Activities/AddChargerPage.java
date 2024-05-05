package com.cst338.project02.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cst338.project02.R;
import com.cst338.project02.databinding.ActivityAddChargerBinding;

public class AddChargerPage extends AppCompatActivity {

    ActivityAddChargerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddChargerBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        binding.addChargerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chargerName = binding.chargerNameText.getText().toString();
                String chargerAddress = binding.chargerAddress.getText().toString();
                String chargerCost = binding.chargerCost.getText().toString();


            }
        });

        binding.navigation.setOnItemSelectedListener(item -> {
            Intent intent;
            if (item.getItemId() == R.id.navigation_home) {
                intent = new Intent(this, LandingActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_create){
                intent = new Intent(this, AddChargerPage.class);
                startActivity(intent);
            }

            return true;
        });
    }
}