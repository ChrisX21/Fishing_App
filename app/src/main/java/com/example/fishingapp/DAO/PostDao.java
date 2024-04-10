package com.example.fishingapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fishingapp.Entity.Post;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insertPost(Post post);

    @Delete
    void deletePost(Post post);

    @Update
    void updatePost(Post post);

    @Query("SELECT * FROM Posts")
    List<Post> getAllPosts();

}