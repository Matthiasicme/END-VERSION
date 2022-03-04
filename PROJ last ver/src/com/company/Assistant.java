package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assistant {
    SuggestionEngine suggestionEngine;
    WeatherAPIConnector weatherAPIConnector;

    Scanner scanner = new Scanner(System.in);

    Location home;
    Location work;
    Location lake;
    Location beach;
    Location school;
    List<Location> locations = new ArrayList<>();

    public Assistant(SuggestionEngine suggestionEngine, WeatherAPIConnector weatherAPIConnector) {
        this.suggestionEngine = suggestionEngine;
        this.weatherAPIConnector = weatherAPIConnector;

        locations.add(new Location("Gdansk", 54.398834, 18.512464));
        locations.add(new Location("Frankfurt", 50.072333, 8.683209));
        locations.add(new Location("Lyon", 45.764403, 4.893503));
        locations.add(new Location("Alicante", 38.368600, -0.424794));
        locations.add(new Location("Oslo", 59.905200, 10.859301));

        home = locations.get(0);
        work = locations.get(1);
        lake = locations.get(2);
        beach = locations.get(3);
        school = locations.get(4);
    }

    public void addLocations() {

        System.out.println("Which location do you want to add? (home, work, lake, beach, school)");
        String decision = scanner.nextLine();
        System.out.println(decision);
        if(this.locations == null){
            this.locations = new ArrayList<>();
        }
        Location newLocation;
        if (decision.equals("work")) {

            newLocation = new Location("Gdansk",54.398834, 18.512464) ;
            this.work = newLocation;

        } if (decision.equals("home")) {
            newLocation = new Location("Frankfurt", 50.072333, 8.683209);
            this.home = newLocation;

        } if (decision.equals("lake")){

            newLocation = new Location("Lyon", 45.764403, 4.893503);
            this.lake = newLocation;

        } if (decision.equals("beach")){

            newLocation = new Location("Alicante", 38.368600, -0.424794);
            this.beach = newLocation;

        } if (decision.equals("school")){

            newLocation = new Location("Oslo", 59.905200, 10.859301);
            this.school = newLocation;

        } else {
            System.out.println( "Invalid value - You had to choose one of the top");
            return;
        }
        if(this.locations != null){
            this.locations.add(newLocation);
        }

    }

    public void whatWearNow() {
        Location chosenLocation = getLocationFromUser();

        try {
            WeatherCondition weatherCondition = weatherAPIConnector.getCurrentWeather(chosenLocation);

            printWeatherCondition(weatherCondition);
            printSuggestedClothing(suggestionEngine.getSuggestion(weatherCondition));
        } catch (IOException e) {
            System.out.println("Weather API connection error!");
        }
    }

    public void whatWearTomorrow() {
        try {
            WeatherCondition weatherAtHome = weatherAPIConnector.getTomorrowWeather(home, WeatherAPIConnector.TimeOfDay.MORN);
            WeatherCondition weatherAtWork = weatherAPIConnector.getTomorrowWeather(work, WeatherAPIConnector.TimeOfDay.EVE);

            printWeatherConditionSpan(weatherAtHome, weatherAtWork, "in the morning at home", "in the evening at work");
            printSuggestedClothing(suggestionEngine.getSuggestionForSpan(weatherAtHome, weatherAtWork));
        } catch (IOException e) {
            System.out.println("Weather API connection error!");
        }
    }

    public void whatWearSomewhere() {
        Location chosenLocation = getLocationFromUser();

        int hour = getHourFromUser();

        try {
            WeatherCondition weatherCondition = weatherAPIConnector.getSpecifiedWeather(chosenLocation, hour);

            printWeatherCondition(weatherCondition);
            printSuggestedClothing(suggestionEngine.getSuggestion(weatherCondition));
        } catch (IOException e) {
            System.out.println("Weather API connection error!");
        }
    }

    public void planTrip() {
        System.out.println("Not working yet");
    }

    private int getHourFromUser() {
        System.out.println("Please put in the time in HH:mm format:");
        String input = scanner.nextLine();

        String hourPart = input.split(":")[0];

        return Integer.parseInt(hourPart);
    }

    private Location getLocationFromUser() {
        printLocations();

        int locationNumber = Integer.parseInt(scanner.nextLine());
        return locations.get(locationNumber - 1);
    }

    private void printLocations() {
        System.out.println("-----Could you tell me please here are you?");
        System.out.println("1. Home: " + home.name);
        System.out.println("2. Work: " + work.name);
        System.out.println("3. Location: " + locations.get(2).name);
        System.out.println("4. Location: " + locations.get(3).name);
        System.out.println("5. Location: " + locations.get(4).name);
    }

    private void printWeatherConditionSpan(WeatherCondition startWeatherCondition, WeatherCondition endWeatherCondition, String startConditionDescription, String endConditionDescription) {
        System.out.println("--Weather condition " + startConditionDescription + ":");
        printWeatherCondition(startWeatherCondition);
        System.out.println("--Weather condition " + endConditionDescription + ":");
        printWeatherCondition(endWeatherCondition);
    }

    private void printWeatherCondition(WeatherCondition weatherCondition) {
        System.out.println("-----Weather conditions: ");
        System.out.println("Feels like: " + weatherCondition.getFeelsLike() + "°C");
        System.out.println("Temp: " + weatherCondition.getTemp() + "°C");
        System.out.println("UV Index: " + weatherCondition.getUvIndex());
        System.out.println("Wind speed: " + weatherCondition.getWindSpeed() + "km/h");

        if (weatherCondition.isSunShining()) {
            System.out.println("Clear sky!");
        }
        if (weatherCondition.isRaining()) {
            System.out.println("Rain!");
        }
        if (weatherCondition.isSnowing()) {
            System.out.println("Snow!");
        }
    }

    private void printSuggestedClothing(List<Clothing> suggestion) {
        System.out.println(" You can wear:");
        for (Clothing clothing : suggestion) {
            System.out.println(clothing.getName());
        }
    }
}
