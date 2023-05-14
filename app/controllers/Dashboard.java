package controllers;

import java.util.List;
import models.Member;
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
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", stations);
  }

  public static void addStation(String name, double latitude, double longitude) {

    Logger.info("Adding a new weather station: " + name + latitude + longitude);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station (name, latitude, longitude);
    member.stations.add(station);
    station.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    Logger.info("Deleting a station: " + station.name);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

}

