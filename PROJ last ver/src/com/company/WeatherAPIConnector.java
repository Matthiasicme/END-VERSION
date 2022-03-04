package com.company;

import org.json.JSONObject;

import java.io.IOException;
import java.text.MessageFormat;


public class WeatherAPIConnector {
    private static String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/onecall?lat={0}&lon={1}&appid=6d3be0cf8606812a5623b693d6b9197e&exclude=minutely,hourly,daily,alerts&units=metric";
    private static String DAILY_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/onecall?lat={0}&lon={1}&appid=6d3be0cf8606812a5623b693d6b9197e&exclude=current,minutely,hourly,alerts&units=metric";
    private static String HOURLY_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/onecall?lat={0}&lon={1}&appid=6d3be0cf8606812a5623b693d6b9197e&exclude=current,minutely,daily,alerts&units=metric";
    public static String OPEN_WEATHER_API_KEY = "http://api.openweathermap.org/data/2.5/onecall?lat={0}&lon={1}&appid=6d3be0cf8606812a5623b693d6b9197e&exclude=current,minutely,daily,alerts&units=metric";

    public enum TimeOfDay {
        EVE,
        NIGHT,
        DAY,
        MORN
    }


    public WeatherCondition getCurrentWeather(Location location) throws IOException {
        String url = MessageFormat.format(CURRENT_WEATHER_API_URL, location.latitude, location.longitude);

        RESTAPIConnector restapiConnector = new RESTAPIConnector();
        return mapResponseToCurrentWeatherCondition(restapiConnector.getJSONObject(url));
    }

    public WeatherCondition getTomorrowWeather(Location location, TimeOfDay timeOfDay) throws IOException {
        String url = MessageFormat.format(DAILY_WEATHER_API_URL, location.latitude, location.longitude);

        RESTAPIConnector restapiConnector = new RESTAPIConnector();
        return mapResponseToTomorrowWeatherCondition(restapiConnector.getJSONObject(url), timeOfDay);
    }

    public WeatherCondition getSpecifiedWeather(Location location, int hour) throws IOException {
        String url = MessageFormat.format(HOURLY_WEATHER_API_URL, location.latitude, location.longitude);

        RESTAPIConnector restapiConnector = new RESTAPIConnector();
        return mapResponseToSpecifiedWeatherCondition(restapiConnector.getJSONObject(url), hour);
    }

    private WeatherCondition mapResponseToSpecifiedWeatherCondition(JSONObject response, int hour) {
        return mapResponseToWeatherCondition(response.getJSONArray("hourly").getJSONObject(hour));
    }

    private WeatherCondition mapResponseToTomorrowWeatherCondition(JSONObject response, TimeOfDay timeOfDay) {
        return mapDailyResponseToWeatherCondition(response.getJSONArray("daily").getJSONObject(1), timeOfDay);
    }

    private WeatherCondition mapResponseToCurrentWeatherCondition(JSONObject response) {
        return mapResponseToWeatherCondition(response.getJSONObject("current"));
    }

    private WeatherCondition mapResponseToWeatherCondition(JSONObject response) {
        String weatherType = response.getJSONArray("weather").getJSONObject(0).getString("main");

        WeatherCondition weatherStatus = mapWeatherStringToWeatherCondition(weatherType);

        return new WeatherCondition(
                response.getDouble("temp"),
                response.getDouble("feels_like"),
                response.getDouble("uvi"),
                response.getDouble("wind_speed"),
                weatherStatus.isRaining(),
                weatherStatus.isSnowing(),
                weatherStatus.isSunShining());
    }

    private WeatherCondition mapDailyResponseToWeatherCondition(JSONObject response, TimeOfDay timeOfDay) {
        String weatherType = response.getJSONArray("weather").getJSONObject(0).getString("main");

        WeatherCondition weatherStatus = mapWeatherStringToWeatherCondition(weatherType);

        return new WeatherCondition(
                response.getJSONObject("temp").getDouble(timeOfDay.toString().toLowerCase()),
                response.getJSONObject("feels_like").getDouble(timeOfDay.toString().toLowerCase()),
                response.getDouble("uvi"),
                response.getDouble("wind_speed"),
                weatherStatus.isRaining(),
                weatherStatus.isSnowing(),
                weatherStatus.isSunShining());
    }

    private WeatherCondition mapWeatherStringToWeatherCondition(String weatherString) {
        boolean isRaining = false;
        boolean isSnowing = false;
        boolean isSunShining = false;

        switch (weatherString) {
            case "Drizzle":
            case "Thunderstorm":
            case "Rain":
                isRaining = true;
                break;
            case "Snow":
                isSnowing = true;
                break;
            case "Clear":
                isSunShining = true;
                break;
        }

        return new WeatherCondition(
                0,
                0,
                0,
                0,
                isRaining,
                isSnowing,
                isSunShining);
    }

    public static void main(String[] args) throws IOException {
        WeatherAPIConnector weatherConnector = new WeatherAPIConnector();
        WeatherCondition weatherNow = weatherConnector.getSpecifiedWeather(new Location("Gdańsk", 54.398834, 18.512464), 3);
        System.out.println(weatherNow);
    }
}
