package com.cst338.project02.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.RATINGS_TABLE)
public class Ratings {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "chargerId")
    public int chargerId;

    @ColumnInfo(name = "rating")
    public int rating;

    public Ratings(int chargerId, int rating) {
        this.chargerId = chargerId;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChargerId() {
        return chargerId;
    }

    public void setChargerId(int chargerId) {
        this.chargerId = chargerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
