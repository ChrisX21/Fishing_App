package com.example.fishingapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;

public class HolderView extends RecyclerView.ViewHolder {
    public ImageView getIconImage() {
        return IconImage;
    }

    public void setIconImage(ImageView iconImage) {
        IconImage = iconImage;
    }

    public TextView getTempText() {
        return TempText;
    }

    public void setTempText(TextView tempText) {
        TempText = tempText;
    }

    public TextView getHumidityText() {
        return HumidityText;
    }

    public void setHumidityText(TextView humidityText) {
        HumidityText = humidityText;
    }

    public TextView getLocationText() {
        return LocationText;
    }

    public void setLocationText(TextView locationText) {
        LocationText = locationText;
    }

    public ImageView IconImage;
    public TextView TempText;
    public TextView HumidityText;
    public TextView LocationText;

    public HolderView(@NonNull View itemView) {
        super(itemView);
        IconImage = itemView.findViewById(R.id.iconImage);
        TempText = itemView.findViewById(R.id.tempText);
        HumidityText = itemView.findViewById(R.id.humidityText);
        LocationText = itemView.findViewById(R.id.locationText);
    }


}
