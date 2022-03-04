package com.company;

public class WeatherCondition {
    private final double temp;
    private final double feelsLike;
    private final double uvIndex;
    private final double windSpeed;
    private final boolean isRaining;
    private final boolean isSnowing;
    private final boolean isSunShining;

    public WeatherCondition(double temp, double feelsLike, double uvIndex, double windSpeed, boolean isRaining, boolean isSnowing, boolean isSunShining) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.uvIndex = uvIndex;
        this.windSpeed = windSpeed;
        this.isRaining = isRaining;
        this.isSnowing = isSnowing;
        this.isSunShining = isSunShining;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public boolean isSnowing() {
        return isSnowing;
    }

    public boolean isSunShining() {
        return isSunShining;
    }

    public double getTemp() {
        return temp;
    }
}
