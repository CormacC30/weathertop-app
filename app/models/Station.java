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
    public List<Reading> readings = new ArrayList<Reading>();


    public Station(String title, double latitude, double longitude) {
        this.name = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Reading getLatestReading() {
        if (readings.size() > 0) {
            return readings.get(readings.size() - 1);
        } else {
            return null; //placeholder...
        }
    }

    public float getLatestFahrenheit() {
        return getLatestReading().getFahrenheit();
    }

    public float getLatestBeaufort() {
        return getLatestReading().getBeaufort();
    }

    public String getLatestCodeReadingText() {
        return getLatestReading().getCodeReadingToText();
    }

    public String getLatestWindDirection() {
        return getLatestReading().getWindDirection();
    }

    public String getLatitude() {
        NumberFormat formatter = new DecimalFormat("##.###");
        formatter.setMaximumFractionDigits(3);
        return formatter.format(this.latitude);
    }

    public String getLongitude() {
        NumberFormat formatter = new DecimalFormat("##.###");
        formatter.setMaximumFractionDigits(3);
        return formatter.format(this.longitude);
    }

    public String getLatestWeatherIcon() {
        return getLatestReading().weatherIcon;
    }


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
     * @param readingSize This method creates a subList of the readings ArrayList which is used to create a trend
     * @return subList of readings defined by readingSize
     */
    public List<Reading> latestReading(int readingSize) {
        List<Reading> latestReadings = null;
        if (readings.size() > 0) {
            latestReadings = readings.subList(readings.size() - readingSize, readings.size());
        }
        return latestReadings;
    }


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

