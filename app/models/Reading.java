package models;

import javax.persistence.Entity;

import play.Logger;
import play.db.jpa.Model;
import utilities.Conversion;

import java.text.DecimalFormat;
import java.text.NumberFormat;


@Entity
public class Reading extends Model
{

    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;
    public double windDirection;


    public Reading(int code, float temperature, float windSpeed, int pressure, double windDirection)
    {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }
    
    public String getCodeReadingToText()
    {
        return utilities.Conversion.codeReadingToText(this.code);
    }

    public float getBeaufort() {
        return utilities.Conversion.kmhToBeaufort(this.windSpeed);
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
     * @return
     */
    public String getWindChill() {
        NumberFormat formatter = new DecimalFormat("##.##");
        formatter.setMaximumFractionDigits(2);

        double windChill = utilities.Conversion.calculateWindChill(this.temperature, this.windSpeed);
        return formatter.format(windChill); }

}