package com.lmw.lmwrouter.user.cache;

import com.lmw.lmwrouter.user.persistence.SharedPrefsUser;

public class UserCache {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(String user) {
        this.user = new User(SharedPrefsUser.USER, user);
    }

    public void clear() {
        user = null;
    }
}
