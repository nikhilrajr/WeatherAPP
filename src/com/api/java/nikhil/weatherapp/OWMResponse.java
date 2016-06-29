package com.api.java.nikhil.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class OWMResponse {

	private final OWMAddress owmAddress;
    private final OWMProxy owmProxy;

    public OWMResponse(OWMAddress owmAddress, OWMProxy owmProxy) {
        this.owmAddress = owmAddress;
        this.owmProxy = owmProxy;
    }

    public String currentWeatherByCityName(String cityName) throws UnsupportedEncodingException {
        String address = owmAddress.currentWeatherByCityName(cityName);
        return httpGET(address);
    }

    private String httpGET(String requestAddress) {
        URL request;
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        String tmpStr;
        String response = null;

        try {
            request = new URL(requestAddress);

            if (owmProxy.getProxy() != null) {
                connection = (HttpURLConnection) request.openConnection(owmProxy.getProxy());
            } else {
                connection = (HttpURLConnection) request.openConnection();
            }

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String encoding = connection.getContentEncoding();

                try {
                    if (encoding != null && "gzip".equalsIgnoreCase(encoding)) {
                        reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
                    } else if (encoding != null && "deflate".equalsIgnoreCase(encoding)) {
                        reader = new BufferedReader(new InputStreamReader(new InflaterInputStream(connection.getInputStream(), new Inflater(true))));
                    } else {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    }

                    while ((tmpStr = reader.readLine()) != null) {
                        response = tmpStr;
                    }
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }
                }
            } else { // if HttpURLConnection is not okay
                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((tmpStr = reader.readLine()) != null) {
                        response = tmpStr;
                    }
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    }
                }

                // if response is bad
                System.err.println("Bad Response: " + response + "\n");
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            response = null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }
}
