package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.time.LocalDateTime;

public class StationCtrl extends Controller
{

    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);
        render("station.html", station);

    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, double windDirection, int pressure, LocalDateTime timeStamp) {
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection, timeStamp);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing reading" + reading.id);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }
}

