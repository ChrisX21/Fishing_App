package com.example.fishingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.Adapter.PostsAdapter;
import com.example.fishingapp.DAO.PostDao;
import com.example.fishingapp.Db.db;
import com.example.fishingapp.Entity.Post;
import com.example.fishingapp.R;

import java.util.List;

public class PostsFragment extends Fragment {
    private List<Post> posts;
    public PostsFragment(List<Post> posts) {
        this.posts = posts;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.postsRecyclerView);
        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
