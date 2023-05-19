package models;

import javax.persistence.Entity;

import play.Logger;
import play.db.jpa.Model;
import utilities.Conversion;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class Reading extends Model
{
    //weather code
    public int code;
    //temperature
    public float temperature;
    //wind speed
    public float windSpeed;
    //pressure
    public int pressure;
    //wind direction
    public double windDirection;
    //date
    public String date;
    //weather Icon
    public String weatherIcon = utilities.Conversion.getWeatherIcon(this.getCode());

    /**
     * Reading constructor. Takes in six parameters to create a reading object:
     * @param code
     * @param temperature
     * @param windSpeed
     * @param pressure
     * @param windDirection
     * @param date takes in the localDateTime object and String is constructed using Conversions.parseDateTime method
     */
    public Reading(int code, float temperature, float windSpeed, int pressure, double windDirection, LocalDateTime date)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = Conversion.parseDateTime(date);
    }

    /**
     * The getTimeStamp() method is called by the listreadings.html partial.
     * @returns the date String
     */
    public String getTimeStamp() {
            return this.date;
    }

    /**
     * getCodeReadingToText() method uses the conversions class to convert to String representation
     * is called by the listreadings.html partial
     * @returns the text interpretation of the weather code
     */
    public String getCodeReadingToText()
    {
        return utilities.Conversion.codeReadingToText(this.code);
    }

    /**
     * getBeaufort() called by the listreadings.html partial.
     * Uses the Conversions class to change km/h to Beaufort
     * @return the Beaufort wind speed.
     */
    public int getBeaufort() {
        return utilities.Conversion.kmhToBeaufort(this.windSpeed);
    }

    /**
     * getWindDescription() called by the listreadings.html partial.
     * Uses the Conversions class to convert the beaufort scale to a description (String)
     * @returns the description
     */
    public String getWindDescription(){
        return utilities.Conversion.beaufortToText(this.getBeaufort());
    }

    /**
     * getFahrenheit() called by the listreadings.html partial.
     * ses the Conversions class to convert celcius to fahrenheit
     * @returns the temperature in Fahrenheit
     */
    public float getFahrenheit() {
        return utilities.Conversion.celciusToFahrenheit(this.temperature);
    }

    /**
     * Temperature getter
     * @return
     */
    public float getTemperature() {
        return this.temperature;
    }

    /**
     * Wind speed getter
     * @return
     */
    public float getWindSpeed() {
        return this.windSpeed;
    }

    /**
     * weather code getter
     * @return
     */
    public int getCode() {
        return this.code;
    }

    public int getPressure() {
        return this.pressure;
    }

    public String getWindDirection() {return utilities.Conversion.windDirectionToText(this.windDirection); }

    /**
     * getWindChill() method utilises the imported DecimalFormat class
     * @return a String representation of the wind chill, method in Conversions class, to two decimal places.
     */
    public String getWindChill() {
        NumberFormat formatter = new DecimalFormat("##.##");
        formatter.setMaximumFractionDigits(2);

        double windChill = utilities.Conversion.calculateWindChill(this.temperature, this.windSpeed);
        return formatter.format(windChill); }

    /**
     * WeatherIcon getter. passed to the html views listreadings, latestreadings.
     * Calls a method from the Conversions class.
     * @returns weather icon String
     */
    public String getWeatherIcon(){
        return utilities.Conversion.getWeatherIcon(this.code);
    }

    /**
     * Temperature icons: different temperature icons for specific ranges.
     * depending on temperature value, an icon is selected based on the range it falls within
     * @return
     */
    public String getTemperatureIcon(){
        return utilities.Conversion.celciusToIcon(this.temperature);
    }

}