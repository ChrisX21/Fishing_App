package com.example.fishingapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "Posts")
public class Post {
    public Post(String id, String title, String content, String location, String date, String imageUrl) {
        Id = id;
        Title = title;
        Content = content;
        Location = location;
        Date = date;
        ImageUrl = imageUrl;
    }

    public Post(){}

    @PrimaryKey
    @NonNull
    public String Id;
    public String Title;
    public String Content;
    public String Location;
    public String Date;
    public String ImageUrl;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
