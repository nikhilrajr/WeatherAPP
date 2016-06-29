
package com.api.java.nikhil.weatherapp;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherMap {
 
    private final OWMAddress owmAddress;
    private final OWMResponse owmResponse;
    private final OWMProxy owmProxy;

    public OpenWeatherMap(String apiKey) {
    	this.owmAddress = new OWMAddress(apiKey);
        this.owmProxy = new OWMProxy(null, Integer.MIN_VALUE, null, null);
        this.owmResponse = new OWMResponse(owmAddress, owmProxy);
    }

    public OWMAddress getOwmAddressInstance() {
        return owmAddress;
    }

    public String getApiKey() {
        return owmAddress.getAppId();
    }

    public void setApiKey(String appId) {
        owmAddress.setAppId(appId);
    }

    public void setProxy(String ip, int port) {
        owmProxy.setIp(ip);
        owmProxy.setPort(port);
        owmProxy.setUser(null);
        owmProxy.setPass(null);
    }

    public void setProxy(String ip, int port, String user, String pass) {
        owmProxy.setIp(ip);
        owmProxy.setPort(port);
        owmProxy.setUser(user);
        owmProxy.setPass(pass);
    }

    public CurrentWeather currentWeatherByCityName(String cityName)
            throws IOException, JSONException {
        String response = owmResponse.currentWeatherByCityName(cityName);
        return this.currentWeatherFromRawResponse(response);
    }

    public CurrentWeather currentWeatherFromRawResponse(String response)
            throws JSONException {
        JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
        return new CurrentWeather(jsonObj);
    }
}
