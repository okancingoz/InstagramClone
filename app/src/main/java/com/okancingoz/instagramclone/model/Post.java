package com.okancingoz.instagramclone.model;

import android.net.Uri;

public class Post {
    public String userName;
    public String comment;
    public String downloadUrl;
    public String profileImgUri;
    public Post(String userName, String comment, String downloadUrl, String profileImgUri) {
        this.userName = userName;
        this.comment = comment;
        this.downloadUrl = downloadUrl;
        this.profileImgUri = profileImgUri;
    }
}