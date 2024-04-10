package com.example.fishingapp.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.R;

public class PostsHolderView extends RecyclerView.ViewHolder{
    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getContent() {
        return content;
    }

    public void setContent(TextView content) {
        this.content = content;
    }

    public TextView getLocation() {
        return location;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }
    public ImageButton getDeleteButton() {
        return deleteButton;
    }
    public void setDeleteButton(ImageButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    private TextView title;
    private TextView content;
    private TextView location;
    private ImageButton deleteButton;
    public PostsHolderView(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleText);
        content = itemView.findViewById(R.id.contentText);
        location = itemView.findViewById(R.id.locationText);
        deleteButton = itemView.findViewById(R.id.deleteButton);
    }
}
