package com.company;

import java.util.ArrayList;
import java.util.List;

public class SuggestionEngine {
    private List<Clothing> availableClothing;

    public SuggestionEngine(List<Clothing> availableClothing) {
        this.availableClothing = availableClothing;
    }

    public List<Clothing> getSuggestion(WeatherCondition weatherCondition) {
        List<Clothing> result = new ArrayList<>();

        for (Clothing clothing : availableClothing) {
            if (shouldBeWorn(clothing, weatherCondition) || shouldBeWornExtra(clothing, weatherCondition)) {
                result.add(clothing);
            }
        }

        return result;
    }

    public List<Clothing> getSuggestionForSpan(WeatherCondition startWeatherCondition, WeatherCondition endWeatherCondition) {
        List<Clothing> result = new ArrayList<>();

        for (Clothing clothing : availableClothing) {
            if ((shouldBeWorn(clothing, startWeatherCondition) && shouldBeWorn(clothing, endWeatherCondition))
                    || (shouldBeWornExtra(clothing, startWeatherCondition) || shouldBeWornExtra(clothing, endWeatherCondition)))
            {
                result.add(clothing);
            }
        }

        return result;
    }

    private boolean shouldBeWorn(Clothing clothing, WeatherCondition weatherCondition) {

        if (clothing.isWearWhenRaining() || clothing.isWearWhenSnowing() || clothing.isWearWhenSunshine())
        {
            return false;
        }

        return isGoodFitForWeatherCondition(clothing, weatherCondition);
    }

    private boolean shouldBeWornExtra(Clothing clothing, WeatherCondition weatherCondition) {
        if (!isGoodFitForWeatherCondition(clothing, weatherCondition))
        {
            return false;
        }

        if (weatherCondition.getUvIndex() > 5 && clothing.isWearWhenSunshine())
        {
            return true;
        }
        if (weatherCondition.isSnowing() && clothing.isWearWhenSnowing())
        {
            return true;
        }
        if (weatherCondition.isRaining() && clothing.isWearWhenRaining())
        {
            return true;
        }

        return false;
    }

    private boolean isGoodFitForWeatherCondition(Clothing clothing, WeatherCondition weatherCondition) {
        if (weatherCondition.getFeelsLike() > clothing.getMaxTemp()
                || weatherCondition.getFeelsLike() < clothing.getMinTemp())
        {
            return false;
        }
        if (weatherCondition.isRaining() && !clothing.isGoodForRain())
        {
            return false;
        }
        if (weatherCondition.isSnowing() && !clothing.isGoodForSnow())
        {
            return false;
        }
        if (weatherCondition.getWindSpeed() > 15 && !clothing.isGoodForWind())
        {
            return false;
        }

        return true;
    }
}
