package com.api.java.nikhil.weatherapp;

import org.json.JSONObject;

public class Weather {

	private static final String JSON_WEATHER_ID = "id";
    private static final String JSON_WEATHER_MAIN = "main";

    private final int id;
    private final String name;

   
    Weather(JSONObject jsonObj) {
        this.id = jsonObj.optInt(JSON_WEATHER_ID, Integer.MIN_VALUE);
        this.name = jsonObj.optString(JSON_WEATHER_MAIN, null);
    }

    public boolean hasWeatherCode() {
        return this.id != Integer.MIN_VALUE;
    }

    public boolean hasWeatherName() {
        return this.name != null && (! "".equals(this.name));
    }

    public int getWeatherCode() {
        return this.id;
    }

    public String getWeatherName() {
        return this.name;
    }
}