package com.okancingoz.instagramclone.adapter;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.okancingoz.instagramclone.databinding.RecyclerRowFeedBinding;
import com.okancingoz.instagramclone.model.Post;
import com.okancingoz.instagramclone.util.CircleTransformation;
import com.okancingoz.instagramclone.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private ArrayList<Post> posts;

    public  PostAdapter(ArrayList<Post> posts){
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowFeedBinding recyclerRowFeedBinding = RecyclerRowFeedBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(recyclerRowFeedBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.recyclerRowFeedBinding.recyclerItemTxtUserName.setText(posts.get(position).userName);
        holder.recyclerRowFeedBinding.recyclerItemTxtComment.setText(posts.get(position).comment);
        Picasso.get().load(posts.get(position).downloadUrl).into(holder.recyclerRowFeedBinding.recyclerItemImageView);
        String profileImgUri = posts.get(position).profileImgUri;
        if (profileImgUri != null) {
            Picasso.get()
                    .load(profileImgUri)
                    .transform(new CircleTransformation())
                    .into(holder.recyclerRowFeedBinding.recyclerItemImgUser);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        RecyclerRowFeedBinding recyclerRowFeedBinding;
        public PostHolder(RecyclerRowFeedBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowFeedBinding = recyclerRowBinding;
        }
    }
}