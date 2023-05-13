package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String name;
    public double latitude;
    public double longitude;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();


    public Station(String title, double latitude, double longitude)
    {
        this.name = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Reading getLatestReading() {
        if(readings.size() > 0) {
            return readings.get(readings.size() - 1);
        }
        else {
            return null; //placeholder...
        }
    }

    public float getLatestFahrenheit() {
        return getLatestReading().getFahrenheit();
    }

    public float getLatestBeaufort() {
        return getLatestReading().getBeaufort();
    }

    public String getLatestCodeReadingText() {return getLatestReading().getCodeReadingToText();}

    public String getLatestWindDirection() {return getLatestReading().getWindDirection(); }

    public String getLatitude() {
        NumberFormat formatter = new DecimalFormat("##.###");
        formatter.setMaximumFractionDigits(3);
        return formatter.format(this.latitude);
    }

    public String getLongitude() {
        NumberFormat formatter = new DecimalFormat("##.###");
        formatter.setMaximumFractionDigits(3);
        return formatter.format(this.longitude);
    }

 public String getLatestWeatherIcon() {
        return getLatestReading().weatherIcon;
 }

}

