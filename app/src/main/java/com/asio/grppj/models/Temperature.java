package com.asio.grppj.models;

import android.util.Log;

import com.asio.grppj.util.TemperatureConverter;

public class Temperature {
    private static final String TAG = "Temperature";
    public String temperature = "";
    public String temperatureUnit = "";

    public Temperature(String temperature, String temperatureUnit) {
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
    }

    public String getTemperature(Boolean requestCelsius){
        if(requestCelsius){
            if(!temperatureUnit.equals("C")){
                temperature = TemperatureConverter.convertFahrenheitToCelsius(Double.parseDouble(temperature))+"";
                temperatureUnit = "C";
            }

        }
        else{
            if(!temperatureUnit.equals("F")){
                temperature = TemperatureConverter.convertCelsiusToFahrenheit(Double.parseDouble(temperature))+"";
                temperatureUnit = "F";
            }
        }

        return temperature+"°"+temperatureUnit;

    }

}
