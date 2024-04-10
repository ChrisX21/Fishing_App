package com.example.fishingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.Fragment.CurrentWeatherFragment;
import com.example.fishingapp.Fragment.ForecastWeatherFragment;
import com.example.fishingapp.Model.ForecastResponse;
import com.example.fishingapp.Model.WeatherResponse;
import com.example.fishingapp.Web.FishingAppApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //ADD LOGIN AND REGISTER FUNCTIONALITY WITH FIREBASE!!!
    //ADD GOOGLE MAPS API INTEGRATION!!!

    private static String url = "https://api.weatherapi.com/v1/";
    private static String apiKey = "d6ad90acc992424fa8784810242603";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton weatherBtn = findViewById(R.id.weatherBtn);
        ImageButton forecastBtn = findViewById(R.id.forecastBtn);
        ImageButton postsBtn = findViewById(R.id.postsBtn);
        EditText cityName = findViewById(R.id.cityName);
        ImageButton saveButton = findViewById(R.id.saveButton);

        SharedPreferences sharedPreferences = getSharedPreferences("city", MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        if(sharedPreferences.contains("city")) {
            String city = sharedPreferences.getString("city", "");
            cityName.setText(city);
        }

        FishingAppApiClient apiClient = retrofit.create(FishingAppApiClient.class);

        saveButton.setOnClickListener(v -> {
            if(cityName.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("city", cityName.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity.this, "City name saved", Toast.LENGTH_SHORT).show();
            }
        });

        //current weather

        weatherBtn.setOnClickListener(v -> {
                    String city = cityName.getText().toString();
                    Call<WeatherResponse> call = apiClient.getCurrentWeather(apiKey, city);
                    call.enqueue(new Callback<WeatherResponse>() {
                        @Override
                        public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                            if (response.isSuccessful()) {
                                WeatherResponse weatherResponse = response.body();

                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new CurrentWeatherFragment(weatherResponse)).commit();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Failed to respond weather data", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
                        }
                    });
                });

        //forecast

        forecastBtn.setOnClickListener(v -> {
            String city = cityName.getText().toString();
            Call<ForecastResponse> call = apiClient.getForecast(apiKey, city, 3);
    call.enqueue(new Callback<ForecastResponse>() {
        @Override
        public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
            if (response.isSuccessful()) {
                ForecastResponse forecastResponse = response.body();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new ForecastWeatherFragment(forecastResponse)).commit();
            }
            else {
                Toast.makeText(MainActivity.this, "Failed to respond forecast data", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailure(Call<ForecastResponse> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Failed to fetch forecast data", Toast.LENGTH_SHORT).show();
        }
    });
});

        //posts

        postsBtn.setOnClickListener(v -> {
            // Handle posts button click
        });
    }
}