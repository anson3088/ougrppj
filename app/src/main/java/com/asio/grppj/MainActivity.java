package com.asio.grppj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.asio.grppj.Thread.JsonHandlerThread;
import com.asio.grppj.Thread.URLImageThread;
import com.asio.grppj.models.ContactInfo;
import com.asio.grppj.models.CurrentWeather;
import com.asio.grppj.models.ForecastWeather;
import com.asio.grppj.models.GlobalWeather;
import com.asio.grppj.models.Temperature;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ListView listView;
    private TextView general_situation_textView;
    private TextView forecast_desc_title_textView;
    private TextView outlook_textView;
    private TextView current_weather_textView;
    private TextView humidity_textView;
    private ImageView current_weather_imageView;
    private JsonHandlerThread jsonHandlerThread = new JsonHandlerThread();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        //GW
        general_situation_textView = (TextView) findViewById(R.id.general_situation_textView);
        forecast_desc_title_textView = (TextView) findViewById(R.id.forecast_desc_title_textView);
        outlook_textView = (TextView) findViewById(R.id.outlook_textView);


        //CW
        current_weather_textView = (TextView) findViewById(R.id.current_weather_textView);
        humidity_textView = (TextView) findViewById(R.id.humidity_textView);
        current_weather_imageView = (ImageView) findViewById(R.id.current_weather_imageView);

        // Get contact list with thread class

        try {
            gwInit();
            cwInit();
            fwInit();

        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException: " + e.getMessage());
        }
    }

    public void jsonHandlerSetup(String url) throws InterruptedException{
        jsonHandlerThread = new JsonHandlerThread();
        jsonHandlerThread.jsonUrl = url;
        jsonHandlerThread.start();
        jsonHandlerThread.join();
    }

    public void gwInit() throws InterruptedException {
        jsonHandlerSetup(getString(R.string.HKO_GLOBAL_API));
        GlobalWeather gw = new GlobalWeather(jsonHandlerThread.jsonObj);
        general_situation_textView.setText(GlobalWeather.generalSituation);
        forecast_desc_title_textView.setText(GlobalWeather.forecastDesc);
        outlook_textView.setText(GlobalWeather.outlook);
    }

    public void cwInit() throws InterruptedException {
        jsonHandlerSetup(getString(R.string.HKO_CURRENT_WEATHER_API));
        CurrentWeather cw = new CurrentWeather(jsonHandlerThread.jsonObj);
        current_weather_textView.setText(cw.currentTemperature.getTemperature(true));
        humidity_textView.setText(cw.humidity+"%");
        URLImageThread urlImageThread = new URLImageThread();
        urlImageThread.imageUrl =  getString(R.string.HKO_CURRENT_WEATHER_ICON_URL).replace("{ICON}", cw.icon);;
        urlImageThread.start();
        urlImageThread.join();
        current_weather_imageView.setImageBitmap(urlImageThread.bmp);
    }

    public void fwInit() throws InterruptedException {
        jsonHandlerSetup(getString(R.string.HKO_9DAYS_API));
        ForecastWeather cw = new ForecastWeather(jsonHandlerThread.jsonObj);
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                ForecastWeather.dateInfoList,
                R.layout.list_item,
                new String[] { ForecastWeather.FORECASTDATE, ForecastWeather.WEEK, ForecastWeather.FORECASTRH,
                        ForecastWeather.FORECASTMAXTEMP, ForecastWeather.FORECASTMINTEMP
                        //, ForecastWeather.ICON
                },
                new int[] { R.id.date_textView, R.id.weekday_textView, R.id.humidity_textView,
                        R.id.max_temperature_textView, R.id.min_temperature_textView
                        //, R.id.weather_imageView
                }
        );





        // Associate the adapter with the ListView
        listView.setAdapter(adapter);
            /*
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HashMap<String, String> contact = ContactInfo.contactList.get(position);
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle(contact.get(ContactInfo.NAME));
                            builder.setMessage("Mobile: " + contact.get(ContactInfo.MOBILE));
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
            );
            */

    }

}