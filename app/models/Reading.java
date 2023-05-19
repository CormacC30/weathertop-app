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

    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;
    public double windDirection;

    public String date;

    public String weatherIcon = utilities.Conversion.getWeatherIcon(this.getCode());

    public Reading(int code, float temperature, float windSpeed, int pressure, double windDirection, LocalDateTime date)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = Conversion.parseDateTime(date);
    }

    public String getTimeStamp() {
            return this.date;
    }

    public String getCodeReadingToText()
    {
        return utilities.Conversion.codeReadingToText(this.code);
    }

    public int getBeaufort() {
        return utilities.Conversion.kmhToBeaufort(this.windSpeed);
    }

    public String getWindDescription(){
        return utilities.Conversion.beaufortToText(this.getBeaufort());
    }

    public float getFahrenheit() {
        return utilities.Conversion.celciusToFahrenheit(this.temperature);
    }

    public float getTemperature() {
        return this.temperature;
    }

    public float getWindSpeed() {
        return this.windSpeed;
    }

    public int getCode() {
        return this.code;
    }

    public int getPressure() {
        return this.pressure;
    }

    public String getWindDirection() {return utilities.Conversion.windDirectionToText(this.windDirection); }

    /**
     * getWindChill() method utilises the imported DecimalFormat class
     * @return a String representation of the wind chill, to two decimal places.
     */
    public String getWindChill() {
        NumberFormat formatter = new DecimalFormat("##.##");
        formatter.setMaximumFractionDigits(2);

        double windChill = utilities.Conversion.calculateWindChill(this.temperature, this.windSpeed);
        return formatter.format(windChill); }

    public String getWeatherIcon(){
        return utilities.Conversion.getWeatherIcon(this.code);
    }

    public String getTemperatureIcon(){
        return utilities.Conversion.celciusToIcon(this.temperature);
    }

}