package com.cst338.project02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.Favorites;
import com.cst338.project02.Data.FavoritesDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FavoritesDBTest {

    @Mock
    Context mockContext;
    private FavoritesDAO favoritesDAO;
    private AppDatabase zapmapDB;

    @Before
    public void createDB() {
        zapmapDB = Room.inMemoryDatabaseBuilder(mockContext, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        favoritesDAO = zapmapDB.favoritesDAO();
    }

    @After
    public void closeConnection() {
        zapmapDB.close();
    }

    @Test
    public void addFavoriteStation() {
       new Thread(new Runnable() {
            @Override
            public void run() {
                Favorites station = new Favorites(1, 240979, "HAMPTON MARINA HAMPTON EV 2", "120 Reservation Rd");
                favoritesDAO.insert(station);

                List<Favorites> userStations = favoritesDAO.getUserFavorites(1);

                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertFalse(userStations.isEmpty());
                        assertEquals(userStations.get(0).getStationName(), "HAMPTON MARINA HAMPTON EV 2");
                    }
                });
            }
        });
    }

    @Test
    public void getUsersFavorites() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Favorites station1 = new Favorites(1, 240979, "HAMPTON MARINA HAMPTON EV 2", "120 Reservation Rd");
                Favorites station2 = new Favorites(1, 201315, "The Dunes On Monterey Bay - Tesla Supercharger", "145 General Stilwell Drive");
                favoritesDAO.insert(station1);
                favoritesDAO.insert(station2);

                List<Favorites> userStations = favoritesDAO.getUserFavorites(1);

                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertFalse(userStations.isEmpty());
                        assertEquals(userStations.size(), 2);
                        assertEquals(userStations.get(1).getChargerId(), station2.getChargerId());
                    }
                });
            }
        });
    }
}
