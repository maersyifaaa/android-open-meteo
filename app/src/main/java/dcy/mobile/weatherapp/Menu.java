package dcy.mobile.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class Menu extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private Button btnVolley, btnRetrofit;
    private RequestQueue requestQueue;
    private List<Weather> weathers = new ArrayList<>();
    private WeatherAPI weatherService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

        btnVolley = this.findViewById(R.id.btnVolley);
        btnRetrofit = this.findViewById(R.id.btnRetrofit);

        btnVolley.setOnClickListener(this);
        btnRetrofit.setOnClickListener(this);

        this.requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnVolley) {
            JsonObjectRequest sr = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto",
                    null,
                    this,
                    this
            );
            this.requestQueue.add(sr);

        } else if (view == btnRetrofit){
            Gson gson = new GsonBuilder().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.open-meteo.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            weatherService = retrofit.create(WeatherAPI.class);

            double latitude = -7.6;
            double longitude = 106.445;

            Call<WeatherResponse> call = weatherService.getWeatherForecast(
                   "weathercode", //daily,
                    "auto", //timezone,
                    "2023-05-19", // start_date,
                    "2023-05-25", // end_date
                    latitude, //latitude,
                    longitude, //longitude,
                    true //currentWeather
            );

            call.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {
                    if (response.isSuccessful()) {
                        WeatherResponse weatherResponse = response.body();
                        WeatherResponse.CurrentWeather currentWeather = weatherResponse.getCurrentWeather();
                        WeatherResponse.DailyWeather dailyWeather = weatherResponse.getDailyWeather();

                        int code = currentWeather.getWeatherCode();
                        String temperature = currentWeather.getTemperature();
                        String windSpeed = currentWeather.getWindSpeed();
                        String latitude = String.valueOf(weatherResponse.getLatitude());
                        String longitude = String.valueOf(weatherResponse.getLongitude());

                        // tambah data
                        String[] time = dailyWeather.getTime();
                        int[] weatherCode = dailyWeather.getWeatherCode();
                        for (int i = 0; i < time.length; i++) {
                            String date = time[i];
                            int wc = weatherCode[i];
                            weathers.add(new Weather(wc, date));
                        }

                        Intent WeatherRetrofit = new Intent(Menu.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("code", code);
                        bundle.putString("temperature", temperature);
                        bundle.putString("windSpeed", windSpeed);
                        bundle.putString("latitude", latitude);
                        bundle.putString("longitude", longitude);
                        bundle.putSerializable("dataWeather", (Serializable) weathers);
                        WeatherRetrofit.putExtras(bundle);
                        startActivity(WeatherRetrofit);
                    }
                }

        @Override
        public void onFailure(Call<WeatherResponse> call, Throwable t) {
            Toast.makeText(Menu.this, "Failed to fetch weather data", Toast.LENGTH_SHORT);
            Log.e("WeatherApp", "Failed to fetch weather data", t);
            }
        });
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONObject jsonObject = response.getJSONObject("current_weather");
            int code = jsonObject.getInt("weathercode");
            String temperature = jsonObject.getString("temperature");
            String windSpeed = jsonObject.getString("windspeed");
            String latitude = response.getString("latitude");
            String longitude = response.getString("longitude");


            // tambah data
            JSONObject daily = response.getJSONObject("daily");
            JSONArray time = daily.getJSONArray("time");
            JSONArray weathercode = daily.getJSONArray("weathercode");
            for (int i = 0; i < time.length(); i++){
                String date = time.getString(i);
                int wc = weathercode.getInt(i);
                weathers.add(new Weather(wc, date));
            }

            Intent WeatherVolley= new Intent(Menu.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("code", code);
            bundle.putString("temperature", temperature);
            bundle.putString("windSpeed", windSpeed);
            bundle.putString("latitude", latitude);
            bundle.putString("longitude", longitude);
            bundle.putSerializable("dataWeather", (Serializable) weathers);
            WeatherVolley.putExtras(bundle);
            startActivity(WeatherVolley);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(Menu.this, "Failed to fetch weather data", Toast.LENGTH_SHORT);
    }
}
