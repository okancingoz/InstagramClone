package com.okancingoz.instagramclone.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.okancingoz.instagramclone.R;
import com.okancingoz.instagramclone.adapter.PostAdapter;
import com.okancingoz.instagramclone.adapter.UserAdapter;
import com.okancingoz.instagramclone.databinding.ActivityFeedBinding;
import com.okancingoz.instagramclone.databinding.ActivityUserBinding;
import com.okancingoz.instagramclone.model.Post;
import com.okancingoz.instagramclone.model.User;

import java.util.ArrayList;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<User> users;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        users = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getData();

        binding.recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(users);
        binding.recyclerViewUser.setAdapter(userAdapter);
    }

    private void getData(){
        firebaseFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(UserActivity.this, error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }

                if(value != null){
                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String,Object> data = snapshot.getData();

                        //Casting
                        String profileImgUri = (String) data.get("profileimguri");
                        String fullName = (String) data.get("fullname");
                        String userName = (String) data.get("username");

                        User user = new User(profileImgUri,fullName ,userName);
                        users.add(user);
                    }
                    userAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}