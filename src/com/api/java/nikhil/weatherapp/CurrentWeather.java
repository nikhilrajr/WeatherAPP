
package com.api.java.nikhil.weatherapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class CurrentWeather {

	private static final String JSON_RESPONSE_CODE = "cod";
    private static final String JSON_CITY_ID = "id";
    private static final String JSON_CITY_NAME = "name";
    static final String JSON_COORD = "coord";
    static final String JSON_MAIN = "main";
    private static final String JSON_WEATHER = "weather";
    private static final String JSON_DATE_TIME = "dt";

    private final int responseCode;
    private final String rawResponse;
    private final long cityId;
    private final String cityName;
    private final Coord coord;
    private final Main main;
    private final Date dateTime;
    private final int weatherCount;
    private final List<Weather> weatherList;
    
    public CurrentWeather(JSONObject jsonObj) {

        this.cityId = (jsonObj != null) ? jsonObj.optLong(JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
        this.cityName = (jsonObj != null) ? jsonObj.optString(JSON_CITY_NAME, null) : null;
        this.rawResponse = (jsonObj != null) ? jsonObj.toString() : null;
        this.responseCode = (jsonObj != null) ? jsonObj.optInt(JSON_RESPONSE_CODE, Integer.MIN_VALUE) : Integer.MIN_VALUE;
    
        JSONObject coordObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_COORD) : null;
        this.coord = (coordObj != null) ? new Coord(coordObj) : null;

        JSONObject mainObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_MAIN) : null;
        this.main = (mainObj != null) ? new Main(mainObj) : null;
        
        long sec = (jsonObj != null) ? jsonObj.optLong(JSON_DATE_TIME, Long.MIN_VALUE) : Long.MIN_VALUE;
        if (sec != Long.MIN_VALUE) { // converting seconds to Date object
            this.dateTime = new Date(sec * 1000);
        } else {
            this.dateTime = null;
        }

        JSONArray weatherArray = (jsonObj != null) ? jsonObj.optJSONArray(JSON_WEATHER) : new JSONArray();
        
        if(weatherArray != null)
        	this.weatherList = new ArrayList<Weather>(weatherArray.length());
        else
        	this.weatherList = null;
        
        if (weatherArray != null && this.weatherList != Collections.EMPTY_LIST) {
            for (int i = 0; i < weatherArray.length(); i++) {
                JSONObject weatherObj = weatherArray.optJSONObject(i);
                if (weatherObj != null) {
                    this.weatherList.add(new Weather(weatherObj));
                }
            }
        }
        this.weatherCount = this.weatherList.size();
    }
    
    public boolean isValid() {
        return this.responseCode == 200;
    }

    public boolean hasResponseCode() {
        return this.responseCode != Integer.MIN_VALUE;
    }

    public boolean hasRawResponse() {
        return this.rawResponse != null;
    }

    public boolean hasCityCode() {
        return this.cityId != Long.MIN_VALUE;
    }

    public boolean hasCityName() {
        return this.cityName != null && (! "".equals(this.cityName));
    }

    public boolean hasCoordInstance() {
        return coord != null;
    }

    public boolean hasMainInstance() {
        return main != null;
    }

    public boolean hasDateTime() {
        return this.dateTime != null;
    }

    public boolean hasWeatherInstance() {
        return weatherCount != 0;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public int getWeatherCount() {
        return this.weatherCount;
    }

    public Weather getWeatherInstance(int index) {
        return this.weatherList.get(index);
    }

    public long getCityCode() {
        return this.cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public Coord getCoordInstance() {
        return this.coord;
    }

    public Main getMainInstance() {
        return this.main;
    }
}