package com.cst338.project02.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cst338.project02.Adapters.ChargerListRow_Adapter;
import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.Favorites;
import com.cst338.project02.Models.ChargerRowModel;
import com.cst338.project02.R;
import com.cst338.project02.databinding.ActivityFavoriteStationsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoriteStationsActivity extends AppCompatActivity {

    ActivityFavoriteStationsBinding binding;

    ArrayList<ArrayList<String>> stationDetails = new ArrayList<>();
    ArrayList<ChargerRowModel> chargerRowModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFavoriteStationsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        getFavoriteStations();


        binding.navigation.setOnItemSelectedListener(item -> {
            Intent intent;
            if (item.getItemId() == R.id.navigation_home) {
                intent = new Intent(this, LandingActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_create){
                intent = new Intent(this, FavoriteStationsActivity.class);
                startActivity(intent);
            }

            return true;
        });
    }

    private void setUpChargerRowModels() {
        for (int i = 0; i < stationDetails.size(); i++) {
            chargerRowModels.add(new ChargerRowModel(Integer.parseInt(stationDetails.get(i).get(0)), stationDetails.get(i).get(1), stationDetails.get(i).get(2)));
        }

        RecyclerView recyclerView = findViewById(R.id.favoriteStationList);
        ChargerListRow_Adapter adapter = new ChargerListRow_Adapter(this, chargerRowModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getFavoriteStations() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("userInfo", MODE_PRIVATE);
                int userId = pref.getInt("userID", 0 );
                List<Favorites> userFavorites = AppDatabase.getInstance(FavoriteStationsActivity.this)
                        .favoritesDAO().getUserFavorites(userId);

                for (int i = 0; i < userFavorites.size(); i++) {
                   stationDetails.add(new ArrayList<>(
                           Arrays.asList(
                                   String.valueOf(userFavorites.get(i).getChargerId()),
                                   userFavorites.get(i).getStationName(),
                                   userFavorites.get(i).getStationLocation()
                           )
                   ));
                }
                setUpChargerRowModels();
            }
        }).start();
    }
}
