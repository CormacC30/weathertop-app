package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.time.LocalDateTime;

public class StationCtrl extends Controller
{
    /**
     * index method fetches the station corresponding to the station it receives
     * renders the station.html view
     * @param id
     */
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);
        render("station.html", station);
    }

    /**
     * addReading method is used to create a new reading object
     * six parameters are taken in
     * @param id
     * @param code
     * @param temperature
     * @param windSpeed
     * @param windDirection
     * @param pressure
     * @param timeStamp
     * once created it is added to the readings ArrayList
     */
    public static void addReading(Long id, int code, float temperature, float windSpeed, double windDirection, int pressure, LocalDateTime timeStamp) {

        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection, timeStamp);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);

    }

    /**
     * deleteReading method deletes a reading object
     * takes in the station id and reading id
     * @param id
     * @param readingid
     * reading is found and subsequently removed from the reading ArrayList and deleted
     */
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

