package com.cst338.project02.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cst338.project02.databinding.ActivityChargerDetailsBinding;

import org.json.JSONObject;

import java.io.IOException;

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
            Integer id = extras.getInt("stationId");
            String stationName = extras.getString("stationName");
            String stationLocation = extras.getString("stationLocation");

            try {
                requestChargerData(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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

    private void requestChargerData(int stationId) throws IOException {
        String url = urlBuilder(stationId);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONObject obj = jsonObject.getJSONObject("alt_fuel_station");

                            binding.stationHours.setText(obj.getString("access_days_time"));
                            binding.stationPhone.setText(obj.getString("station_phone"));
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

    private String urlBuilder(int stationId) {
        String base = "https://developer.nrel.gov/api/alt-fuel-stations/v1/";
        String params = ".json?api_key=Wo7p9LICDqpQNQaaU1bOX4h8iiFlDPuuMwCGjfdG";
        return base + stationId + params;
    }
}