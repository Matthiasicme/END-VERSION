package com.company;

public class Clothing {
    private final double minTemp;
    private final double maxTemp;
    private final boolean isGoodForRain;
    private final boolean isGoodForSnow;
    private final boolean isGoodForWind;

    private final boolean wearWhenSunshine;
    private final boolean wearWhenRaining;
    private final boolean wearWhenSnowing;

    private final String name;
    private final Type type;

    public enum Type {
        HEADGEAR,
        TORSO,
        HANDS,
        LEGS,
        SHOES,
        ACCESSORY,
    }

    public Clothing(double minTemp, double maxTemp, boolean isGoodForRain, boolean isGoodForSnow, boolean isGoodForWind, boolean wearWhenSunshine, boolean wearWhenRaining, boolean wearWhenSnowing, String name, Type type) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.isGoodForRain = isGoodForRain;
        this.isGoodForSnow = isGoodForSnow;
        this.isGoodForWind = isGoodForWind;
        this.wearWhenSunshine = wearWhenSunshine;
        this.wearWhenRaining = wearWhenRaining;
        this.wearWhenSnowing = wearWhenSnowing;
        this.name = name;
        this.type = type;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public boolean isGoodForRain() {
        return isGoodForRain;
    }

    public boolean isGoodForSnow() {
        return isGoodForSnow;
    }

    public boolean isGoodForWind() {
        return isGoodForWind;
    }

    public boolean isWearWhenSunshine() {
        return wearWhenSunshine;
    }

    public String getName() {
        return name;
    }

    public boolean isWearWhenRaining() {
        return wearWhenRaining;
    }

    public boolean isWearWhenSnowing() {
        return wearWhenSnowing;
    }

    public Type getType() {
        return type;
    }
}
