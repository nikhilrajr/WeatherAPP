
package com.main.java.nikhil.weatherapp;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;

import com.api.java.nikhil.weatherapp.CurrentWeather;
import com.api.java.nikhil.weatherapp.OpenWeatherMap;



public class CurrentWeatherTest {

	public static void main(String[] args) throws IOException {
		OpenWeatherMap owm = new OpenWeatherMap("8dcdc63df9e7546b77e7cb23fdd8a065");
		CurrentWeather cw;
		String city = null,condition = null,temperature = null,status_Date=null;
		float lat = 0,lon=0,pressure=0,humidity=0;
		
		try {
			cw = owm.currentWeatherByCityName("melbourne");

			if (!cw.isValid()) {
				System.out.println("Reponse is inValid!");
			} else {
				if (cw.hasCityName()) {
					city = cw.getCityName().toUpperCase();
					city = city.substring(0, 3);
				}
				
				if (cw.hasCoordInstance()) {
					lat = cw.getCoordInstance().getLatitude();
					lon = cw.getCoordInstance().getLongitude();
				}
				
				if (cw.hasDateTime()) {
					Date date=cw.getDateTime();
					SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd 'T' HH:mm:ss zzz");
					status_Date = df.format(date);
				}
				for(int i=0;i<cw.getWeatherCount();i++){
					condition = cw.getWeatherInstance(i).getWeatherName();
				}
				
				if (cw.hasMainInstance()) {
					DecimalFormat df = new DecimalFormat();
					float temp=(float) (cw.getMainInstance().getTemperature()-273.15);
					temperature=df.format(temp);
					if(temp>0)
						temperature = "+"+temperature;
					
					pressure=cw.getMainInstance().getPressure();
					humidity=cw.getMainInstance().getHumidity();
				}
				System.out.println(city+"|"+lat+","+lon+"|"+status_Date+"|"+condition+"|"+temperature+"|"+pressure+"|"+humidity);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
