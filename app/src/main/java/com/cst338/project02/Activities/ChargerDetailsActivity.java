package com.cst338.project02.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.project02.R;
import com.cst338.project02.databinding.ActivityChargerDetailsBinding;
import com.cst338.project02.databinding.ActivityLandingBinding;

public class ChargerDetailsActivity extends AppCompatActivity {

    ActivityChargerDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChargerDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            String id = extras.getString("stationId");
            String stationName = extras.getString("stationName");
            String stationLocation = extras.getString("stationLocation");

            binding.stationName.setText(stationName);
            binding.stationLocation.setText(stationLocation);
        }

        binding.goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}