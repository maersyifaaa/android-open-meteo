package dcy.mobile.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("forecast")
    Call<WeatherResponse> getWeatherForecast(
            @Query("daily") String daily,
            @Query("timezone") String timezone,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude,
            @Query("currentWeather") boolean currentWeather
    );
}