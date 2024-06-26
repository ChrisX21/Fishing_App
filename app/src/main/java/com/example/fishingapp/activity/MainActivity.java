package com.example.fishingapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.activity.LoginActivity;
import com.example.fishingapp.Entity.Post;
import com.example.fishingapp.Fragment.CreatePostFragment;
import com.example.fishingapp.Fragment.CurrentWeatherFragment;
import com.example.fishingapp.Fragment.ForecastWeatherFragment;
import com.example.fishingapp.Fragment.PostsFragment;
import com.example.fishingapp.Model.ForecastResponse;
import com.example.fishingapp.Model.WeatherResponse;
import com.example.fishingapp.R;
import com.example.fishingapp.Web.FishingAppApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String url = "https://api.weatherapi.com/v1/";
    private static String apiKey = "d6ad90acc992424fa8784810242603";
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private List<Post> posts;

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

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        posts = new ArrayList<>();

        ImageButton weatherBtn = findViewById(R.id.weatherBtn);
        ImageButton forecastBtn = findViewById(R.id.forecastBtn);
        ImageButton postsBtn = findViewById(R.id.postsBtn);
        EditText cityName = findViewById(R.id.cityName);
        ImageButton saveButton = findViewById(R.id.saveButton);
        ImageButton createPostButton = findViewById(R.id.addButton);
        TextView user = findViewById(R.id.user);
        ImageButton logout = findViewById(R.id.logoutBtn);

        user.setText(mAuth.getCurrentUser().getEmail());

        logout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        SharedPreferences sharedPreferences = getSharedPreferences("city", MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        if (sharedPreferences.contains("city")) {
            String city = sharedPreferences.getString("city", "");
            cityName.setText(city);
        }

        saveButton.setOnClickListener(v -> {
            if (cityName.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("city", cityName.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity.this, "City name saved", Toast.LENGTH_SHORT).show();
            }
        });

        FishingAppApiClient apiClient = retrofit.create(FishingAppApiClient.class);

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
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to respond weather data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
                }
            });
        });

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
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to respond forecast data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ForecastResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to fetch forecast data", Toast.LENGTH_SHORT).show();
                }
            });
        });

        postsBtn.setOnClickListener(v -> fetchPostsFromFirestore());

        createPostButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new CreatePostFragment(posts)).commit();
        });
    }

    private void fetchPostsFromFirestore() {
        firestore.collection("posts").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                posts.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Post post = document.toObject(Post.class);
                    posts.add(post);
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new PostsFragment(posts)).commit();
            } else {
                Toast.makeText(MainActivity.this, "Failed to retrieve posts", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
