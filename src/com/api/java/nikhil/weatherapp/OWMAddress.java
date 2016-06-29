package com.api.java.nikhil.weatherapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OWMAddress {

	private static final String MODE = "json";
    private static final String ENCODING = "UTF-8";
    private static final String URL_API = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String PARAM_APPID = "appId=";
    private static final String PARAM_MODE = "mode=";

    private String mode;
    private String appId;

    public OWMAddress(String appId) {
    	this.mode = MODE;
    	this.appId = appId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String currentWeatherByCityName(String cityName) throws UnsupportedEncodingException {
        String url = new StringBuilder()
                .append(URL_API).append(URLEncoder.encode(cityName, ENCODING)).append("&")
                .append(PARAM_MODE).append(this.mode).append("&")
                .append(PARAM_APPID).append(this.appId)
                .toString();
        return url;
    }
}