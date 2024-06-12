package com.example.fishingapp.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;

public class PostsHolderView extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView content;
    private TextView location;
    private TextView date;
    private ImageButton deleteButton;
    private ImageView imageView;

    public PostsHolderView(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleText);
        content = itemView.findViewById(R.id.contentText);
        location = itemView.findViewById(R.id.locationText);
        date = itemView.findViewById(R.id.dateText);
        deleteButton = itemView.findViewById(R.id.deleteButton);
        imageView = itemView.findViewById(R.id.imageView);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getContent() {
        return content;
    }

    public TextView getLocation() {
        return location;
    }

    public TextView getDate() {
        return date;
    }

    public ImageButton getDeleteButton() {
        return deleteButton;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
