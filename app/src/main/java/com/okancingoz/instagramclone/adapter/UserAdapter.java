package com.okancingoz.instagramclone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.okancingoz.instagramclone.databinding.RecyclerRowUserBinding;
import com.okancingoz.instagramclone.model.User;
import com.okancingoz.instagramclone.util.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private ArrayList<User> users;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      RecyclerRowUserBinding recyclerRowUserBinding = RecyclerRowUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
      return new UserHolder(recyclerRowUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.recyclerRowUserBinding.recyclerItemTxtUserName.setText(users.get(position).userName);
        holder.recyclerRowUserBinding.recyclerItemTxtFullName.setText(users.get(position).fullName);
        String profileImgUri = users.get(position).profileImgUri;
        if (profileImgUri != null) {
            Picasso.get()
                    .load(profileImgUri)
                    .transform(new CircleTransformation())
                    .into(holder.recyclerRowUserBinding.recyclerItemImgUser);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        RecyclerRowUserBinding recyclerRowUserBinding;
        public UserHolder(RecyclerRowUserBinding recyclerRowUserBinding) {
            super(recyclerRowUserBinding.getRoot());
            this.recyclerRowUserBinding = recyclerRowUserBinding;
        }
    }
}
