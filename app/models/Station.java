package models;

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
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();


    public Station(String title)
    {
        this.name = title;
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


}

