package com.example.fishingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.Adapter.WeatherAdapter;
import com.example.fishingapp.Model.Forecast;
import com.example.fishingapp.Model.ForecastResponse;
import com.example.fishingapp.R;

public class ForecastWeatherFragment extends Fragment {
    private ForecastResponse forecastResponse;

    public ForecastWeatherFragment(ForecastResponse forecastData) {
        this.forecastResponse = forecastData;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast_weather, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        WeatherAdapter adapter = new WeatherAdapter(forecastResponse);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
