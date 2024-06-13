package com.example.fishingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.Model.ForecastResponse;
import com.example.fishingapp.R;
import com.squareup.picasso.Picasso;

public class WeatherAdapter extends RecyclerView.Adapter<HolderView> {

    private ForecastResponse forecastData;

    public WeatherAdapter(ForecastResponse forecastData) {
        this.forecastData = forecastData;
    }
    @Override
    public HolderView onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_layout, parent, false);
        return new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder( HolderView holder, int position) {
        holder.getDateText().setText(forecastData.getForecast().getForecastday()[position].getDate());
        holder.getDescriptionText().setText(forecastData.getForecast().getForecastday()[position].getDay().getCondition().getText());
        holder.getMaxTempText().setText("Max Temperature: " + forecastData.getForecast().getForecastday()[position].getDay().getMaxtemp_c() + "°C");
        holder.getMinTempText().setText("Min Temperature: " + forecastData.getForecast().getForecastday()[position].getDay().getMintemp_c() + "°C");
        holder.getAvgTempText().setText("Average Temperature: " + forecastData.getForecast().getForecastday()[position].getDay().getAvgtemp_c() + "°C");
        holder.getAvgHumidityText().setText("Average Humidity: " + forecastData.getForecast().getForecastday()[position].getDay().getAvghumidity() + "%");
        holder.getMoonPhaseText().setText("Moon Phase: " + forecastData.getForecast().getForecastday()[position].getAstro().getMoon_phase());

        Picasso.get().load("https:" + forecastData.getForecast().getForecastday()[position].getDay().getCondition().getIcon()).into(holder.getWeatherIcon());
    }

    @Override
    public int getItemCount() {
        return forecastData.getForecast().getForecastday().length;
    }
}