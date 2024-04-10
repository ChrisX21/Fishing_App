package com.example.fishingapp.Web;

import com.example.fishingapp.Model.ForecastResponse;
import com.example.fishingapp.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FishingAppApiClient {
    @GET("current.json")
    Call<WeatherResponse> getCurrentWeather(@Query("key") String apiKey, @Query("q") String cityName);

    @GET("forecast.json")
    Call<ForecastResponse> getForecast(@Query("key") String apiKey, @Query("q") String cityName, @Query("days") int days);
}