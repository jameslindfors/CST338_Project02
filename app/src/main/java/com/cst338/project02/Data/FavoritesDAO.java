package com.cst338.project02.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritesDAO {
    @Insert
    void insert(Favorites... favorites);

    @Query("SELECT * FROM " + AppDatabase.FAVORITES_TABLE + " WHERE userId = :userID")
    List<Favorites> getUserFavorites(int userID);

    @Query("DELETE FROM " + AppDatabase.FAVORITES_TABLE + " WHERE chargerId = :chargerId AND userId = :userId")
    void unfavoriteCharger(int chargerId, int userId);
}
