package com.example.fishingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishingapp.Adapter.PostsAdapter;
import com.example.fishingapp.Entity.Post;
import com.example.fishingapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    private List<Post> posts = new ArrayList<>();

    public PostsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.postsRecyclerView);
        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("posts").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Post post = document.toObject(Post.class);
                            posts.add(post);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast toast = Toast.makeText(getContext(), "Error getting posts", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

        return view;
    }
}
