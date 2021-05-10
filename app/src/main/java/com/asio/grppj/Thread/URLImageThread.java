package com.asio.grppj.Thread;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.asio.grppj.R;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLImageThread extends Thread {
        private static final String TAG = "URLImageThread";

        public String imageUrl = "";
        public Bitmap bmp;
        public void run() {


            URL url = null;
            try {
                url = new URL(imageUrl);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
