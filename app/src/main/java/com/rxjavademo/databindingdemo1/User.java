package com.rxjavademo.databindingdemo1;

public class User {
    private String name, address, followerCount, followingCount;

    public User(String name, String address, String followerCount, String followingCount) {
        this.name = name;
        this.address = address;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(String followerCount) {
        this.followerCount = followerCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }
}
