package com.cst338.project02;

import android.os.Bundle;
import android.util.Pair;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChargerList extends AppCompatActivity {

    ActivityChargerListBinding binding;
    ArrayList<Pair<String, String>> stationDetails = new ArrayList<>();
    ArrayList<ChargerRowModel> chargerRowModels = new ArrayList<>();
    String url =
            "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest?format=json&location=93933&api_key=Wo7p9LICDqpQNQaaU1bOX4h8iiFlDPuuMwCGjfdG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChargerListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        requestChargerData();
    }

    private void setUpChargerRowModels() {
        for (int i = 0; i < stationDetails.size(); i++) {
            chargerRowModels.add(new ChargerRowModel(stationDetails.get(i).first, stationDetails.get(i).second));
        }

        RecyclerView recyclerView = findViewById(R.id.chargerDataList);
        ChargerListRow_Adapter adapter = new ChargerListRow_Adapter(this, chargerRowModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void requestChargerData() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray stations = jsonObject.getJSONArray("fuel_stations");
                            for (int i = 0; i < stations.length(); i++) {
                                JSONObject obj = stations.getJSONObject(i);
                                String stationLoc = obj.get("street_address").toString()
                                        + " " + obj.get("city") + " " + obj.get("zip");
                                stationDetails.add(new Pair<String, String>(obj.get("station_name").toString(), stationLoc));
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