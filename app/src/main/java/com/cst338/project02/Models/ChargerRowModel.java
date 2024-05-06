package com.cst338.project02.Models;

public class ChargerRowModel {
    int stationId;
    String stationName;
    String stationLocation;

    public ChargerRowModel(int stationId, String stationName, String stationLocation) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationLocation = stationLocation;
    }

    public int getStationId() { return stationId; }

    public String getStationName() {
        return stationName;
    }

    public String getStationLocation() {
        return stationLocation;
    }
}
