package utilities;

import java.lang.Math;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;

public class Conversion {

    private static HashMap<Integer, String> weatherIcons = new HashMap<>();

    /**
     * initialize the HashMap in a static block.
     * Tried using a constructor for this purpose, (as in Programming labs) but I would get a PersistenceException message, so I came upon static blocks
     */
    static  {
        weatherIcons.put(100, "fa-solid fa-sun");
        weatherIcons.put(200, "fa-solid fa-cloud-sun");
        weatherIcons.put(300, "fa-solid fa-cloud");
        weatherIcons.put(400, "fa-solid fa-cloud-rain");
        weatherIcons.put(500, "fa-solid fa-cloud-showers-heavy");
        weatherIcons.put(600, "fa-solid fa-umbrella");
        weatherIcons.put(700, "fa-solid fa-snowflake");
        weatherIcons.put(800, "fa-solid fa-cloud-bolt");
    }

    public static String getWeatherIcon(int code) {
        return weatherIcons.get(code);
    }

    //no stored variable = avoid stale data VV
    public static float celciusToFahrenheit(float temperature){
        return((temperature * 9/5) + 32);
    }

    public static int kmhToBeaufort(float windSpeed){
        if(windSpeed >= 0.0 && windSpeed < 1.0) {
            return 0;
        }
        if(windSpeed >= 1.0 && windSpeed < 6.0){
            return 1;
        }
        if(windSpeed >= 6.0 && windSpeed < 12.0){
            return 2;
        }
        if(windSpeed >= 12.0 && windSpeed < 20.0){
            return 3;
        }
        if(windSpeed >= 20.0 && windSpeed < 29.0){
            return 4;
        }
        if(windSpeed >= 29.0 && windSpeed < 39.0){
            return 5;
        }
        if(windSpeed >= 39.0 && windSpeed < 50.0){
            return 6;
        }
        if(windSpeed >= 50.0 && windSpeed < 62.0){
            return 7;
        }
        if(windSpeed >= 62.0 && windSpeed < 75.0){
            return 8;
        }
        if(windSpeed >= 75.0 && windSpeed < 89.0){
            return 9;
        }
        if(windSpeed >= 89.0 && windSpeed < 103.0){
            return 10;
        }
        if(windSpeed >= 103.0 && windSpeed <= 117){
            return 11;
        }
        return 0;
    }

    /**
     * Returns String which is passed to the view template and thus displays a short description for Wind
     * @param beaufort
     * @return
     */
    public static String beaufortToText(int beaufort){
        switch (beaufort) {
            case 0:
                return "calm";
            case 1:
                return "Light air";
            case 2:
                return "Light breeze";
            case 3:
                return "Gentle breeze";
            case 4:
                return "Moderate breeze";
            case 5:
                return "Fresh breeze";
            case 6:
                return "Strong breeze";
            case 7:
                return "Near gale";
            case 8:
                return "Gale";
            case 9:
                return "Strong gale";
            case 10:
                return "Storm";
            case 11:
                return "Violent Storm";
            default:
                return "Calm";
        }
    }

    public static String codeReadingToText(int code) {
        switch (code) {
            case 100:
                return "Clear";
            case 200:
                return "Partial Clouds";
            case 300:
                return "Cloudy";
            case 400:
                return "Light Showers";
            case 500:
                return "Heavy Showers";
            case 600:
                return "Rain";
            case 700:
                return "Snow";
            case 800:
                return "Thunder";
            default:
                return "Sunny";
        }
    }

    public static String windDirectionToText(double windDirection) {
        if (windDirection >= 348.75 || windDirection < 11.25) {
            return "North";
        } else if (windDirection >= 11.25 && windDirection < 33.75) {
            return "North-North East";
        } else if (windDirection >= 33.75 && windDirection < 56.25) {
            return "North East";
        } else if (windDirection >= 56.25 && windDirection < 78.75) {
            return "East-North East";
        } else if (windDirection >= 78.75 && windDirection < 101.25) {
            return "East";
        } else if (windDirection >= 101.25 && windDirection < 123.75) {
            return "East-South East";
        } else if (windDirection >= 123.75 && windDirection < 146.25) {
            return "South East";
        } else if (windDirection >= 146.25 && windDirection < 168.75) {
            return "South-South East";
        } else if (windDirection >= 168.75 && windDirection < 191.25) {
            return "South";
        } else if (windDirection >= 191.25 && windDirection < 213.75) {
            return "South-South West";
        } else if (windDirection >= 213.75 && windDirection < 236.25) {
            return "South West";
        } else if (windDirection >= 236.25 && windDirection < 258.75) {
            return "West-South West";
        } else if (windDirection >= 258.75 && windDirection < 281.25) {
            return "West";
        } else if (windDirection >= 281.25 && windDirection < 303.75) {
            return "West-North West";
        } else if (windDirection >= 303.75 && windDirection < 326.25) {
            return "North West";
        } else if (windDirection >= 326.25 && windDirection < 348.75) {
            return "North-North West";
        } else {
            return "North";
        }
    }

    public static double calculateWindChill(float temperature, float windSpeed) {
        return (13.12 + temperature*0.6215 - 11.37*Math.pow(windSpeed, 0.16) + 0.3965*temperature*Math.pow(windSpeed, 0.16));
    }

    public static String celciusToIcon(float temperature) {
        if(temperature <= 5){
            return "fa-solid fa-temperature-empty";
        }
        if(temperature > 5 && temperature <= 10){
            return "fa-solid fa-temperature-low";
        }
        if(temperature > 10 && temperature <= 15){
            return "fa-solid fa-temperature-quarter";
        }
        if(temperature > 15 && temperature <= 20){
            return "fa-solid fa-temperature-half";
        }
        if(temperature > 20 && temperature <= 25){
            return "fa-solid fa-temperature-three-quarters";
        }
        if(temperature > 25 && temperature <= 27.5){
            return "fa-solid fa-temperature-full";
        }
        if(temperature > 27.5){
            return "fa-solid fa-temperature-high";
        }
        return "fa-solid fa-temperature-half";
    }



}

