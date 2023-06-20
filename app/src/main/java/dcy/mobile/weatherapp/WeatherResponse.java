package dcy.mobile.weatherapp;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherResponse {
    @SerializedName("current_weather")
    private CurrentWeather currentWeather;

    @SerializedName("daily")
    private DailyWeather dailyWeather;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public CurrentWeather getCurrentWeather() {

        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public DailyWeather getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(DailyWeather dailyWeather) {

        this.dailyWeather = dailyWeather;
    }

    public static class CurrentWeather {
        @SerializedName("weathercode")
        private int weatherCode;

        @SerializedName("temperature")
        private String temperature;

        @SerializedName("windspeed")
        private String windSpeed;

        public int getWeatherCode() {
            return weatherCode;
        }

        public void setWeatherCode(int weatherCode) {
            this.weatherCode = weatherCode;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }
    }

    public static class DailyWeather {
        @SerializedName("time")
        private String[] time;

        @SerializedName("weathercode")
        private int[] weatherCode;

        public String[] getTime() {
            return time;
        }

        public void setTime(String[] time) {
            this.time = time;
        }

        public int[] getWeatherCode() {
            return weatherCode;
        }

        public void setWeatherCode(int[] weatherCode) {
            this.weatherCode = weatherCode;

        }
    }
}
