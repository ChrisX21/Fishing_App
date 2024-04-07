package com.example.fishingapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "Posts")
public class Post {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public String Title;
    public String Content;
    public String Image;
}
