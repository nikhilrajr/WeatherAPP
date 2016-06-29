package com.api.java.nikhil.weatherapp;

import org.json.JSONObject;

public class Coord {
	private static final String JSON_COORD_LATITUDE = "lat";
    private static final String JSON_COORD_LONGITUDE = "lon";

    private final float lat;
    private final float lon;

    Coord(JSONObject jsonObj) {
        this.lat = (float) jsonObj.optDouble(JSON_COORD_LATITUDE, Double.NaN);
        this.lon = (float) jsonObj.optDouble(JSON_COORD_LONGITUDE, Double.NaN);
    }

    public boolean hasLatitude() {
        return !Float.isNaN(this.lat);
    }

    public boolean hasLongitude() {
        return !Float.isNaN(this.lon);
    }

    public float getLatitude() {
        return this.lat;
    }

    public float getLongitude() {
        return this.lon;
    }

}