package com.cst338.project02;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cst338.project02.databinding.ActivityChargerListBinding;
import com.cst338.project02.databinding.ActivityLandingPageBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChargerList extends AppCompatActivity {

    ActivityChargerListBinding binding;
    ArrayList<ChargerRowModel> chargerRowModels = new ArrayList<>();
    String url =
            "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest?format=json&location=93933&api_key=Wo7p9LICDqpQNQaaU1bOX4h8iiFlDPuuMwCGjfdG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChargerListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        RecyclerView recyclerView = findViewById(R.id.chargerDataList);
        setUpChargerRowModels();

        ChargerListRow_Adapter adapter = new ChargerListRow_Adapter(this, chargerRowModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET ,url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray stations = jsonObject.getJSONArray("fuel_stations");
                    String x = stations.optJSONObject(0).get("station_name").toString();

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

    private void setUpChargerRowModels() {
       String[] chargerName =  {"Charger 1", "Charger 2"};
       String[] chargerLocations = {"Marina, CA", "Monterey, CA"};

       for (int i = 0; i < chargerName.length; i++) {
           chargerRowModels.add(new ChargerRowModel(chargerName[i], chargerLocations[i]));
       }
    }
}