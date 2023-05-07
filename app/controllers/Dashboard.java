package controllers;

import java.util.List;

import utilities.Conversion;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");
    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }

  public static void addStation(String name) {
    Station station = new Station (name);
    Logger.info("Adding a new weather station: " + name);
    station.save();
    redirect("/dashboard");
  }

}

