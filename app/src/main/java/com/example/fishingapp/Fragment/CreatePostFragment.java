package com.example.fishingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.DAO.PostDao;
import com.example.fishingapp.Entity.Post;
import com.example.fishingapp.R;

import java.util.List;

public class CreatePostFragment extends Fragment {
    private PostDao postDao;

    private List<Post> posts;
    public CreatePostFragment(PostDao postDao, List<Post> posts) {
        this.postDao = postDao;
        this.posts = posts;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.create_post_layout, container, false);

    EditText title = view.findViewById(R.id.titleText);
    EditText content = view.findViewById(R.id.contentText);
    EditText location = view.findViewById(R.id.locationText);
    ImageButton createButton = view.findViewById(R.id.createButton);

    createButton.setOnClickListener(v -> {
        if(title.getText().toString().isEmpty() || content.getText().toString().isEmpty() || location.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Empty fields are not permitted", Toast.LENGTH_SHORT).show();
            return;
        }
        Post post = new Post(title.getText().toString(), content.getText().toString(), location.getText().toString());
        postDao.insertPost(post);
        posts.add(post);
        title.setText("");
        content.setText("");
        location.setText("");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new PostsFragment(posts)).commit();
    });

    return view;
    }
}
