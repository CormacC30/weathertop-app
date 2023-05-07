package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller
{


    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);
        render("station.html", station);

    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, double windDirection, int pressure) {
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

}

