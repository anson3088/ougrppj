package com.asio.grppj.util;

public class TemperatureConverter {

    // Convert to Celsius
    public static double convertFahrenheitToCelsius(double fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    // Convert to Fahrenheit
    public static double convertCelsiusToFahrenheit(double celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}

