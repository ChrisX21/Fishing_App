package com.example.fishingapp.Model;

import com.example.fishingapp.Model.ForecastDay;

public class Forecast {
    private ForecastDay[] forecastDay;

    public ForecastDay[] getForecastday() {
        return forecastDay;
    }

    public void setForecastday(ForecastDay[] forecastday) {
        this.forecastDay = forecastDay;
    }
}