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
public class Station extends Model {
    public String name;
    public double latitude;
    public double longitude;
    @OneToMany(cascade = CascadeType.ALL)
    //readings ArrayList of readings initialised here
    public List<Reading> readings = new ArrayList<Reading>();

    /**
     * Station constructor. Takes in:
     * @param title
     * @param latitude
     * @param longitude
     */
    public Station(String title, double latitude, double longitude) {
        this.name = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * getLatestReading() gets the latest reading from the list of readings.
     * @return most recent reading
     */
    public Reading getLatestReading() {
        if (readings.size() > 0) {
            return readings.get(readings.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * getLatestFahrenheit() gets the latest temperature reading in deg F
     * @return
     */
    public float getLatestFahrenheit() {
        return getLatestReading().getFahrenheit();
    }

    /**
     * getLatestBeaufort() gets the latest wind speed reading in Bft
     * @return
     */
    public float getLatestBeaufort() {
        return getLatestReading().getBeaufort();
    }

    /**
     * getLatestCodeReadingToText() gets the latest weather description as a String
     * @return
     */
    public String getLatestCodeReadingText() {
        return getLatestReading().getCodeReadingToText();
    }

    /**
     * getLatestWindDirection() gets the latest compass wind direction in text as a String
     * @return
     */
    public String getLatestWindDirection() {
        return getLatestReading().getWindDirection();
    }

    /**
     * getLatitude() gets the latitude as input by user or read from database
     * new NumberFormat object is used to
     * @return the latitude to three decimal places
     */
    public String getLatitude() {
        NumberFormat formatter = new DecimalFormat("##.###");
        formatter.setMaximumFractionDigits(3);
        return formatter.format(this.latitude);
    }

    /**
     * getLongitue() gets the longitude as input by user or read from database
     * new NumberFormat object is used to
     * @return the longitude to three decimal places
     */
    public String getLongitude() {
        NumberFormat formatter = new DecimalFormat("##.###");
        formatter.setMaximumFractionDigits(3);
        return formatter.format(this.longitude);
    }

    /**
     * getLatestWeatherIcon() gets the latest weather icon string.
     * @return
     */
    public String getLatestWeatherIcon() {
        return getLatestReading().weatherIcon;
    }

    /**
     * getMaxTemperature() finds the reading in the readings ArrayList with the highest value
     * @returns this reading's temperature
     */
    public float getMaxTemperature() {
        if (readings.size() != 0) {
            Reading maxReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getTemperature() > maxReading.getTemperature()) {
                    maxReading = reading;
                }
            }
            return maxReading.getTemperature();
        }
        return 0;
    }

    /**
     * finds the reading in the readings ArrayList with the lowest temp value
     * @returns this reading's temperature
     */
    public float getMinTemperature() {
        if (readings.size() != 0) {
            Reading minReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getTemperature() < minReading.getTemperature()) {
                    minReading = reading;
                }
            }
            return minReading.getTemperature();
        }
        return 0;
    }

    /**
     * finds the reading in the readings ArrayList with the highest windspeed value
     * @returns this reading's windspeed
     */
    public float getMaxWindSpeed() {
        if (readings.size() != 0) {
            Reading maxReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getWindSpeed() > maxReading.getWindSpeed()) {
                    maxReading = reading;
                }
            }
            return maxReading.getWindSpeed();
        }
        return 0;
    }

    /**
     * finds the reading in the readings ArrayList with the lowest windspeed value
     * @returns this reading's windspeed
     */
    public float getMinWindSpeed() {
        if (readings.size() != 0) {
            Reading minReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getWindSpeed() < minReading.getWindSpeed()) {
                    minReading = reading;
                }
            }
            return minReading.getWindSpeed();
        }
        return 0;
    }
    /**
     * finds the reading in the readings ArrayList with the highest pressure value
     * @returns this reading's pressure
     */
    public int getMaxPressure() {
        if (readings.size() != 0) {
            Reading maxReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getPressure() > maxReading.getPressure()) {
                    maxReading = reading;
                }
            }
            return maxReading.getPressure();
        }
        return 0;
    }
    /**
     * finds the reading in the readings ArrayList with the lowest pressure value
     * @returns this reading's pressure
     */
    public int getMinPressure() {
        if (readings.size() != 0) {
            Reading minReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getPressure() < minReading.getPressure()) {
                    minReading = reading;
                }
            }
            return minReading.getPressure();
        }
        return 0;
    }

    /**
     * This method creates a subList of the readings ArrayList which is used to create a trend
     *
     * @param readingSize takes in the number of readings in the subList.
     * @return subList of readings defined by readingSize
     */
    public List<Reading> latestReading(int readingSize) {
        List<Reading> latestReadings = null;
        if (readings.size() > 0) {
            latestReadings = readings.subList(readings.size() - readingSize, readings.size());
        }
        return latestReadings;
    }

    /**
     * This method is for trending temperature. If three successive readings in the latestReading subList are increasing:
     * @return a String which is read into the latestreading partial, and dashboard views. Or a null String if successive readings don't increase/decrease relative to each other.
     */
    public String trendTemperature() {
        if (readings.size() >= 3) {
            if (latestReading(3).get(0).getTemperature() < latestReading(3).get(1).getTemperature()
                    && latestReading(3).get(1).getTemperature() < latestReading(3).get(2).getTemperature()) {
                return "fa-solid fa-arrow-up";
            }
            if (latestReading(3).get(0).getTemperature() > latestReading(3).get(1).getTemperature()
                    && latestReading(3).get(1).getTemperature() > latestReading(3).get(2).getTemperature()) {
                return "fa-solid fa-arrow-down";
            } else return null;
        } else if (readings.size() < 3) {
            return null;
        }
        return null;
    }

    /**
     * This method is for trending wind speed. If three successive readings in the latestReading subList are increasing:
     * @return a String which is read into the latestreading partial, and dashboard views. Or a null String if successive readings don't increase/decrease relative to each other.
     */
    public String trendWindSpeed() {
        if (readings.size() >= 3) {
            if (latestReading(3).get(0).getWindSpeed() < latestReading(3).get(1).getWindSpeed()
                    && latestReading(3).get(1).getWindSpeed() < latestReading(3).get(2).getWindSpeed()) {
                return "fa-solid fa-arrow-up";
            }
            if (latestReading(3).get(0).getWindSpeed() > latestReading(3).get(1).getWindSpeed()
                    && latestReading(3).get(1).getWindSpeed() > latestReading(3).get(2).getWindSpeed()) {
                return "fa-solid fa-arrow-down";
            } else return null;
        } else if (readings.size() < 3) {
            return null;
        }
        return null;
    }

    /**
     * This method is for trending pressure. If three successive readings in the latestReading subList are increasing:
     * @return a String which is read into the latestreading partial, and dashboard views. Or a null String if successive readings don't increase/decrease relative to each other.
     */
    public String trendPressure() {
        if (readings.size() >= 3) {
            if (latestReading(3).get(0).getPressure() < latestReading(3).get(1).getPressure()
                    && latestReading(3).get(1).getPressure() < latestReading(3).get(2).getPressure()) {
                return "fa-solid fa-arrow-up";
            }
            if (latestReading(3).get(0).getPressure() > latestReading(3).get(1).getPressure()
                    && latestReading(3).get(1).getPressure() > latestReading(3).get(2).getPressure()) {
                return "fa-solid fa-arrow-down";
            } else return null;
        } else if (readings.size() < 3) {
            return null;
        }
        return null;
    }

}

