package com.asio.grppj.models;

import android.util.Log;

import com.asio.grppj.util.TemperatureConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeather {

    private static final String TAG = "CurrentWeather";
    public static Temperature currentTemperature;//
    public static String humidity = "";//濕度
    public static String icon = "";//

    public CurrentWeather(JSONObject rhrread){
        try {
            JSONObject jsonObj = rhrread;
            JSONArray jTemperature = jsonObj.getJSONObject("temperature").getJSONArray("data");
            JSONArray jHumidity = jsonObj.getJSONObject("humidity").getJSONArray("data");
            JSONArray jIcon = jsonObj.getJSONArray("icon");
            JSONObject tmp;
            for (int i = 0; i < jTemperature.length(); i++) {
                tmp = jTemperature.getJSONObject(i);
                if(tmp.getString("place").equals("香港天文台")){
                    currentTemperature = new Temperature(tmp.getString("value"), tmp.getString("unit"));

                    break;
                }
            }
            tmp = jHumidity.getJSONObject(0);
            humidity = tmp.getString("value");
            icon = jIcon.getString(0);

        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            Log.e(TAG, "Error: " + e.getMessage());
        }
    }

}
