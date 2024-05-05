package com.cst338.project02.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.FAVORITES_TABLE)
public class Favorites {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "userId")
    public int userId;

    @ColumnInfo(name = "chargerId")
    public int chargerId;

    @ColumnInfo(name = "stationName")
    public String stationName;

    @ColumnInfo(name = "stationLocation")
    public String stationLocation;

    public Favorites(int userId, int chargerId, String stationName, String stationLocation) {
        this.userId = userId;
        this.chargerId = chargerId;
        this.stationName = stationName;
        this.stationLocation = stationLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChargerId() {
        return chargerId;
    }

    public void setChargerId(int chargerId) {
        this.chargerId = chargerId;
    }
}
