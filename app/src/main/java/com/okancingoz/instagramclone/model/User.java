package com.okancingoz.instagramclone.model;

public class User {
    public String profileImgUri;
    public String fullName;
    public String userName;

    public User(String profileImgUri, String fullName, String userName) {
        this.profileImgUri = profileImgUri;
        this.fullName = fullName;
        this.userName = userName;
    }
}
