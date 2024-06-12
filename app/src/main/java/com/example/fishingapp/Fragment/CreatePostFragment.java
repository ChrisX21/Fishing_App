package com.example.fishingapp.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.fishingapp.Entity.Post;
import com.example.fishingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.UUID;

public class CreatePostFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private List<Post> posts;
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private FirebaseStorage storage;

    private Uri imageUri;
    private ImageView imageView;

    public CreatePostFragment(List<Post> posts) {
        this.posts = posts;
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_post_layout, container, false);

        EditText title = view.findViewById(R.id.titleEditText);
        EditText content = view.findViewById(R.id.contentEditText);
        EditText location = view.findViewById(R.id.locationEditText);
        EditText date = view.findViewById(R.id.dateEditText);
        imageView = view.findViewById(R.id.imageView);
        ImageButton createButton = view.findViewById(R.id.createButton);
        ImageButton selectImageButton = view.findViewById(R.id.selectImageButton);

        selectImageButton.setOnClickListener(v -> openFileChooser());

        createButton.setOnClickListener(v -> {
            if (title.getText().toString().isEmpty() || content.getText().toString().isEmpty() || location.getText().toString().isEmpty() || imageUri == null) {
                Toast.makeText(getContext(), "Empty fields are not permitted", Toast.LENGTH_SHORT).show();
                return;
            }

            // Upload image to Firebase Storage
            StorageReference storageReference = storage.getReference("posts/" + UUID.randomUUID().toString());
            storageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                // Save post to Firestore with the image URL
                String postId = UUID.randomUUID().toString();
                Post post = new Post(postId, title.getText().toString(), content.getText().toString(), location.getText().toString(), date.getText().toString(), uri.toString());
                firestore.collection("posts").document(postId).set(post)
                        .addOnSuccessListener(aVoid -> {
                            posts.add(post);
                            title.setText("");
                            content.setText("");
                            location.setText("");
                            date.setText("");
                            imageView.setImageURI(null);
                            imageUri = null;
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new PostsFragment(posts)).commit();
                            Toast.makeText(getContext(), "Post created successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to create post", Toast.LENGTH_SHORT).show());
            })).addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to upload image", Toast.LENGTH_SHORT).show());
        });

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
