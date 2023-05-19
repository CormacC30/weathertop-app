package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Start, page index()
 * used to render the start.html view
 */
public class Start extends Controller
{
  public static void index() {
    Logger.info("Rendering Start");
    render ("start.html");
  }
}
