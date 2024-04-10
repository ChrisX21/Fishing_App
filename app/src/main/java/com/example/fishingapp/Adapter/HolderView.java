package com.example.fishingapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;

public class HolderView extends RecyclerView.ViewHolder {

    public ImageView getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(ImageView weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public TextView getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(TextView descriptionText) {
        this.descriptionText = descriptionText;
    }

    public TextView getDateText() {
        return dateText;
    }

    public void setDateText(TextView dateText) {
        this.dateText = dateText;
    }

    public TextView getMaxTempText() {
        return maxTempText;
    }

    public void setMaxTempText(TextView maxTempText) {
        this.maxTempText = maxTempText;
    }

    public TextView getMinTempText() {
        return minTempText;
    }

    public void setMinTempText(TextView minTempText) {
        this.minTempText = minTempText;
    }

    public TextView getAvgTempText() {
        return avgTempText;
    }

    public void setAvgTempText(TextView avgTempText) {
        this.avgTempText = avgTempText;
    }

    public TextView getAvgHumidityText() {
        return avgHumidityText;
    }

    public void setAvgHumidityText(TextView avgHumidityText) {
        this.avgHumidityText = avgHumidityText;
    }

    public TextView getMoonPhaseText() {
        return moonPhaseText;
    }

    public void setMoonPhaseText(TextView moonPhaseText) {
        this.moonPhaseText = moonPhaseText;
    }

    private ImageView weatherIcon;
    private TextView descriptionText;
    private TextView dateText;
    private TextView maxTempText;
    private TextView minTempText;
    private TextView avgTempText;
    private TextView avgHumidityText;
    private TextView moonPhaseText;

    public HolderView(View itemView) {
        super(itemView);
            weatherIcon = itemView.findViewById(R.id.iconImage);
            descriptionText = itemView.findViewById(R.id.descText);
            dateText = itemView.findViewById(R.id.dateText);
            maxTempText = itemView.findViewById(R.id.maxTempText);
            minTempText = itemView.findViewById(R.id.minTempText);
            avgTempText = itemView.findViewById(R.id.avgTempText);
            avgHumidityText = itemView.findViewById(R.id.avgHumidityText);
            moonPhaseText = itemView.findViewById(R.id.moonPhaseText);
    }
}

