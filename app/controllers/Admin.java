package controllers;

import java.util.List;

import models.Reading;
import play.mvc.Controller;

/**
 * The admin page - lists all readings in the database. only accessible directly through url.
 */

public class Admin extends Controller
{
    public static void index() {
        List<Reading> readings = Reading.findAll();
        render ("admin.html", readings);
    }
}