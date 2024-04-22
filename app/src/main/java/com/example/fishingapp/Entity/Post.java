package com.example.fishingapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import java.sql.Date;

@Entity(tableName = "Posts")
public class Post {
    public Post(String title, String content, String location, String date) {
        Title = title;
        Content = content;
        Location = location;
        Date = date;
    }

    public Post(){}
    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "title")
    public String Title;
    @ColumnInfo(name = "content")
    public String Content;
    @ColumnInfo(name = "location")
    public String Location;
    @ColumnInfo(name = "date")
    public String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
}
