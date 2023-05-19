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
  /**
   * index() gives an ArrayList of all stations for the logged in member (from member class)
   * This list is then rendered in dashboard.html.
   */

  public static void index()
  {
    Logger.info("Rendering Admin");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", stations);
  }

  /**
   * addStation is used to add new station to the dashboard/member's station ArrayList.
   * Takes in three parameters: station name, latitude and longitude.
   * @param name
   * @param latitude
   * @param longitude
   */
  public static void addStation(String name, double latitude, double longitude) {

    Logger.info("Adding a new weather station: " + name + latitude + longitude);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station (name, latitude, longitude);
    member.stations.add(station);
    station.save();
    redirect("/dashboard");
  }

  /**
   * this method is used for deleting stations for the logged in member.
   * triggered from the dashboard delete station button
   * sends station id to this controller and station is removed from the stations ArrayList.
   * @param id
   */
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

