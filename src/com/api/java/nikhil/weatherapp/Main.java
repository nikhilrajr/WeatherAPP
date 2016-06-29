package com.api.java.nikhil.weatherapp;

import org.json.JSONObject;

public class Main {

	private static final String JSON_MAIN_TEMP = "temp";
    private static final String JSON_MAIN_PRESSURE = "pressure";
    private static final String JSON_MAIN_HUMIDITY = "humidity";

    private final float temp;
    private final float pressure;
    private final float humidity;

    public Main(JSONObject jsonObj) {
        this.temp = (float) jsonObj.optDouble(JSON_MAIN_TEMP, Double.NaN);
        this.pressure = (float) jsonObj.optDouble(JSON_MAIN_PRESSURE, Double.NaN);
        this.humidity = (float) jsonObj.optDouble(JSON_MAIN_HUMIDITY, Double.NaN);
    }

    public boolean hasTemperature() {
        return !Float.isNaN(this.temp);
    }

    public boolean hasPressure() {
        return !Float.isNaN(this.pressure);
    }

    public boolean hasHumidity() {
        return !Float.isNaN(this.humidity);
    }

    public float getTemperature() {
        return this.temp;
    }

    public float getPressure() {
        return this.pressure;
    }

    public float getHumidity() {
        return this.humidity;
    }
}
