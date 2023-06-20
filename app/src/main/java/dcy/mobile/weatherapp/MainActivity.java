package dcy.mobile.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvWeather;
    private TextView tvLocation, tvConditionBsr, tvTemperatureN, tvWSNumber, tvWS, tvLNumber, tvLatitude, tvLGNumber, tvLongitude;
    private TextView tvDailyWeather;
    private ImageView ivCondition;
    private WeatherAdapter wadapter;
    private List<Weather> weathers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvDailyWeather = this.findViewById(R.id.tvDailyWeather);

        this.ivCondition = this.findViewById(R.id.ivCondition);
        this.tvLocation = this.findViewById(R.id.tvLocation);
        this.tvConditionBsr = this.findViewById(R.id.tvConditionBsr);
        this.tvTemperatureN = this.findViewById(R.id.tvTemperatureN);
        this.tvWS = this.findViewById(R.id.tvWS);
        this.tvWSNumber = this.findViewById(R.id.tvWSNumber);
        this.tvLatitude = this.findViewById(R.id.tvLatitude);
        this.tvLNumber = this.findViewById(R.id.tvLNumber);
        this.tvLongitude = this.findViewById(R.id.tvLongitude);
        this.tvLGNumber = this.findViewById(R.id.tvLGNumber);

        this.tvWS.setText("Wind Speed");
        this.tvLatitude.setText("Latitude");
        this.tvLongitude.setText("Longitude");
        this.tvDailyWeather.setText("Daily Weather");

        rvWeather = this.findViewById(R.id.rvWeather);

        Bundle WeatherVolley = getIntent().getExtras();
        int wc = WeatherVolley.getInt("code");
        String temperature = WeatherVolley.getString("temperature");
        String windSpeed = WeatherVolley.getString("windSpeed");
        String latitude = WeatherVolley.getString("latitude");
        String longitude = WeatherVolley.getString("longitude");
        weathers = (List<Weather>) WeatherVolley.getSerializable("dataWeather");

        Bundle WeatherRetrofit = getIntent().getExtras();
        int Rcode = WeatherRetrofit.getInt("code");
        String Rtemperature = WeatherVolley.getString("temperature");
        String RwindSpeed = WeatherVolley.getString("windSpeed");
        String Rlatitude = WeatherVolley.getString("latitude");
        String Rlongitude = WeatherVolley.getString("longitude");
        weathers = (List<Weather>) WeatherRetrofit.getSerializable("dataWeather");

        Weather w = new Weather();

        if (WeatherVolley != null){
            w.setCodeCondition(wc);
            ivCondition.setImageResource(w.getImg());
            tvConditionBsr.setText(w.getWeather());
            tvTemperatureN.setText(temperature);
            tvWSNumber.setText(windSpeed);
            tvLNumber.setText(latitude);
            tvLGNumber.setText(longitude);

            if (latitude.equals("-8.0") && longitude.equals("112.625")){
                tvLocation.setText("Malang");
            } else
                tvLocation.setText("data not found");
            } else if (WeatherRetrofit != null){
                w.setCodeCondition(Rcode);
                ivCondition.setImageResource(w.getImg());
                tvConditionBsr.setText(w.getWeather());
                tvTemperatureN.setText(Rtemperature);
                tvWSNumber.setText(RwindSpeed);
                tvLNumber.setText(Rlatitude);
                tvLGNumber.setText(Rlongitude);
            }

            wadapter = new WeatherAdapter(this, weathers);
            rvWeather.setLayoutManager(new LinearLayoutManager(this));
            rvWeather.setAdapter(wadapter);
    }
}