package com.asio.grppj.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GlobalWeather {

    private static final String TAG = "GlobalWeather";
    public static String generalSituation = "";
    public static String tcInfo = "";
    public static String fireDangerWarning = "";
    public static String forecastPeriod = "";
    public static String forecastDesc = "";
    public static String outlook = "";
    public static String updateTime = "";

    public GlobalWeather(JSONObject flw){
        try {
            generalSituation=flw.getString("generalSituation");
            tcInfo=flw.getString("tcInfo");
            fireDangerWarning=flw.getString("fireDangerWarning");
            forecastPeriod=flw.getString("forecastPeriod");
            forecastDesc=flw.getString("forecastDesc");
            outlook=flw.getString("outlook");
            updateTime=flw.getString("updateTime");

        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }

}
