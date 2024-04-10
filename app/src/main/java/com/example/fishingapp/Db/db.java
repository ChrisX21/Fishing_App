package com.example.fishingapp.Db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//import com.example.fishingapp.DAO.PostDao;
import com.example.fishingapp.DAO.PostDao;
import com.example.fishingapp.Entity.Post;
@Database(entities = {Post.class}, version = 1)
public abstract class db extends RoomDatabase{
    public abstract PostDao postDao();
}
