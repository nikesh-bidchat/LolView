package com.bidchat.nik.lolview;

import java.io.Serializable;

/**
 * Created by AndroidTest on 3/22/2017.
 */

public class User implements Serializable {
    private int userId;
    private String userName;
    private String userImageUrl;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
