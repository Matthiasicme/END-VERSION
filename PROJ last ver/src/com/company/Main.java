package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Assistant assistant = new Assistant(new SuggestionEngine(new ClothingConfiguration().getAllClothes()), new WeatherAPIConnector());

        int option;
        do {
            System.out.println("Hi here is your Clothing Assistant");
            System.out.println("You have these options (do not enter 6): ");
            System.out.println("1 - Add locations:");
            System.out.println("2 - Check what you should wear now:");
            System.out.println("3 - Check what you should wear for work tomorrow:");
            System.out.println("4 - What should you wear in some other location at specified time:");
            System.out.println("5 - plan a trip");
            System.out.println("0 - exit");

            option = scanner.nextInt();

            switch (option) {

                case 1:
                    assistant.addLocations();
                    break;
                case 2:
                    assistant.whatWearNow();
                    break;
                case 3:
                    assistant.whatWearTomorrow();
                    break;
                case 4:
                    assistant.whatWearSomewhere();
                    break;
                case 5:
                    assistant.planTrip();
                    break;
                case 6:
                    System.out.println("You shouldn't click that");
                    break;
                case 0: break;
                default:
                    System.out.println("It's not a valid option");
            }


        }while (option!=0);

        PositionAPIConnector positionAPIConnector = new PositionAPIConnector();
      JSONArray positionAPIConnectorDataNow = positionAPIConnector.getJSONArray("Alicante");
      System.out.println(positionAPIConnectorDataNow);


    }
}
