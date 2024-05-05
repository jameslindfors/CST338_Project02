package com.cst338.project02.Models;

public class ChargerRowModel {
    String stationName;
    String stationLocation;

    public ChargerRowModel(String stationName, String stationLocation) {
        this.stationName = stationName;
        this.stationLocation = stationLocation;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationLocation() {
        return stationLocation;
    }
}
