package com.cst338.project02.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RatingsDAO {
    @Insert
    void insert(Ratings... ratings);

    @Query("SELECT * FROM " + AppDatabase.RATINGS_TABLE + " WHERE chargerId = :chargerId")
    Ratings getChargerRating(int chargerId);

    @Query("UPDATE " + AppDatabase.RATINGS_TABLE + " SET rating = :rating WHERE chargerId = :chargerId")
    void updateChargerRating(int chargerId, int rating);
}
