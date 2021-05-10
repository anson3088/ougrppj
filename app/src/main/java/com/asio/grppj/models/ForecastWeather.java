package com.asio.grppj.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ForecastWeather {

    private static final String TAG = "ForecastWeather";
    public static String FORECASTDATE = "forecastDate";
    public static String WEEK = "week";
    public static String FORECASTMAXTEMP = "forecastMaxtemp";
    public static String FORECASTMINTEMP = "forecastMintemp";
    public static String FORECASTRH = "forecastrh";
    public static String ICON = "ForecastIcon";

    public static ArrayList<HashMap<String, String>> dateInfoList = new ArrayList<>();
    public static ArrayList<HashMap<String, Temperature>> dateTemperatureList = new ArrayList<>();
    public static HashMap<Integer, ArrayList> dataMap = new HashMap<>();

    public ForecastWeather(JSONObject fnd){
        dataMap.put(0,dateInfoList);
        dataMap.put(1,dateTemperatureList);
        try {
            JSONObject jsonObj = fnd;
            JSONArray jWeatherForecast = jsonObj.getJSONArray("weatherForecast");
            JSONObject tmp;
            for (int i = 0; i < jWeatherForecast.length(); i++) {
                tmp = jWeatherForecast.getJSONObject(i);
                tmp.getJSONObject("forecastMaxtemp").getString("value");
                addForecastWeather(tmp.getString("forecastDate"),
                        tmp.getString("week"),
                        tmp.getJSONObject("forecastMaxtemp").getString("value"),
                        tmp.getJSONObject("forecastMaxtemp").getString("unit"),
                        tmp.getJSONObject("forecastMintemp").getString("value"),
                        tmp.getJSONObject("forecastMintemp").getString("unit"),
                        tmp.getJSONObject("forecastMaxrh").getString("value"),
                        tmp.getJSONObject("forecastMinrh").getString("value"),
                        tmp.getString("ForecastIcon")
                );
            }

        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            e.printStackTrace();
        }

    }


    // Creates and add contact to contact list
    public static void addForecastWeather(String forecastDate, String week, String forecastMaxtempValue, String forecastMaxtempUnit,
                                          String forecastMintempValue, String forecastMintempUnit, String forecastMaxrh, String forecastMinrh, String forecastIcon) {
        try{
            HashMap<String, String> dateInfo = new HashMap<>();
            HashMap<String, Temperature> dateTemperature = new HashMap<>();
            SimpleDateFormat  fdate1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat  fdate2 = new SimpleDateFormat("yyyy-MM-dd");
            dateInfo.put(FORECASTDATE, fdate2.format(fdate1.parse(forecastDate)));
            dateInfo.put(WEEK, "("+week+")");
            dateInfo.put(FORECASTRH, forecastMinrh+"-"+forecastMaxrh+"%");
            dateInfo.put(FORECASTMAXTEMP, new Temperature(forecastMaxtempValue, forecastMaxtempUnit).getTemperature(true)+"  ");
            dateInfo.put(FORECASTMINTEMP, "/ " +new Temperature(forecastMintempValue, forecastMintempUnit).getTemperature(true));
            dateInfo.put(ICON, forecastMinrh);
            dateTemperature.put(FORECASTMAXTEMP,new Temperature(forecastMaxtempValue, forecastMaxtempUnit));
            dateTemperature.put(FORECASTMINTEMP,new Temperature(forecastMintempValue, forecastMintempUnit));
            dateInfoList.add(dateInfo);
            dateTemperatureList.add(dateTemperature);
        } catch (Exception e){
            Log.e(TAG, "Error: " + e.getMessage());
        }
    }

}
