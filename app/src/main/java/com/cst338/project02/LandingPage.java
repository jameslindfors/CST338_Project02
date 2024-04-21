package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.cst338.project02.databinding.ActivityLandingPageBinding;
import com.cst338.project02.databinding.ActivityLoginBinding;

public class LandingPage extends AppCompatActivity {

    ActivityLandingPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
    }
}