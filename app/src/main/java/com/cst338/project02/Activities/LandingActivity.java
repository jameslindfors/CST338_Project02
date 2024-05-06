package com.cst338.project02.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cst338.project02.Adapters.ChargerListRow_Adapter;
import com.cst338.project02.Models.ChargerRowModel;
import com.cst338.project02.R;
import com.cst338.project02.databinding.ActivityLandingBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LandingActivity extends AppCompatActivity {

    ActivityLandingBinding binding;

    ArrayList<ArrayList<String>> stationDetails = new ArrayList<>();
    ArrayList<ChargerRowModel> chargerRowModels = new ArrayList<>();
    String url =
            "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest?format=json&location=93933&api_key=Wo7p9LICDqpQNQaaU1bOX4h8iiFlDPuuMwCGjfdG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        try {
            requestChargerData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    private void setUpChargerRowModels() {
        for (int i = 0; i < stationDetails.size(); i++) {
            chargerRowModels.add(new ChargerRowModel(Integer.parseInt(stationDetails.get(i).get(0)), stationDetails.get(i).get(1), stationDetails.get(i).get(2)));
        }

        RecyclerView recyclerView = findViewById(R.id.chargerDataList);
        ChargerListRow_Adapter adapter = new ChargerListRow_Adapter(this, chargerRowModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void requestChargerData() throws IOException {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray stations = jsonObject.getJSONArray("fuel_stations");
                            for (int i = 0; i < stations.length(); i++) {
                                JSONObject obj = stations.getJSONObject(i);
                                String stationLoc = obj.get("street_address").toString()
                                        + " " + obj.get("city") + ", " + obj.get("state") + " "
                                        + obj.get("zip");
                                stationDetails.add(new ArrayList<>(
                                        Arrays.asList(obj.getString("id"),
                                        obj.getString("station_name"),
                                        stationLoc
                                        )));
                            }
                            setUpChargerRowModels();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

        Volley.newRequestQueue(this).add(req);
    }
}