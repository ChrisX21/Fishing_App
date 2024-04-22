package com.example.fishingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.DAO.PostDao;
import com.example.fishingapp.Entity.Post;
import com.example.fishingapp.R;
import com.example.fishingapp.Db.db;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsHolderView> {
    private List<Post> posts;
    public PostsAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public PostsHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);
        return new PostsHolderView(itemView);
    }

    @Override
    public void onBindViewHolder(PostsHolderView holder, int position) {
        Post currentPost = posts.get(position);
        holder.getTitle().setText(currentPost.getTitle());
        holder.getContent().setText(currentPost.getContent());
        holder.getLocation().setText(currentPost.getLocation());
        holder.getDate().setText(currentPost.getDate().toString());

        holder.getDeleteButton().setOnClickListener(v -> {
            posts.remove(currentPost);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}