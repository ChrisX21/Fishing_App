package com.example.fishingapp.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fishingapp.Model.WeatherResponse;
import com.example.fishingapp.R;
import com.squareup.picasso.Picasso;

public class CurrentWeatherFragment extends Fragment {
    private WeatherResponse weatherResponse;
    public CurrentWeatherFragment(WeatherResponse weatherResponse){
        this.weatherResponse = weatherResponse;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_layout, container, false);

        ImageView icon = view.findViewById(R.id.iconImage);

        TextView descriptionText = view.findViewById(R.id.descText);
        TextView temperatureText = view.findViewById(R.id.tempText);
        TextView humidityText = view.findViewById(R.id.humidityText);
        TextView locationText = view.findViewById(R.id.locationText);
        TextView feelsLikeText = view.findViewById(R.id.feelsLikeText);
        TextView cloud = view.findViewById(R.id.cloudText);
        TextView windSpeedText = view.findViewById(R.id.windText);
        TextView windDirText = view.findViewById(R.id.windDirectionText);

        Picasso.get().load("https:" + weatherResponse.getCurrent().getCondition().getIcon()).into(icon);

        descriptionText.setText(weatherResponse.getCurrent().getCondition().getText());
        temperatureText.setText("Temperature: "+ weatherResponse.getCurrent().getTemp_c() + "°C");
        humidityText.setText("Humidity: " + weatherResponse.getCurrent().getHumidity() + "%");
        locationText.setText(weatherResponse.getLocation().getName() + ", " + weatherResponse.getLocation().getCountry());
        cloud.setText("Cloud coverage: " + weatherResponse.getCurrent().getCloud() + "%");
        feelsLikeText.setText("Feels like: " + weatherResponse.getCurrent().getFeelslike_c() + "°C");
        windSpeedText.setText("Wind speed: " + weatherResponse.getCurrent().getWind_kph() + " km/h");
        windDirText.setText("Wind direction: " + weatherResponse.getCurrent().getWind_dir());

        return view;
    }
}
