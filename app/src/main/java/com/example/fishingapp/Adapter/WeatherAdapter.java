package com.example.fishingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.fishingapp.Db.db;
import com.example.fishingapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<HolderView> {

    private db appDatabase;

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_layout, parent, false);
        return new HolderView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}