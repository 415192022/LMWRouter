package com.lmw.lmwrouter.user;


import com.lmw.lmwrouter.lib.util.Utils;
import com.lmw.lmwrouter.user.cache.User;
import com.lmw.lmwrouter.user.cache.UserCache;
import com.lmw.lmwrouter.user.persistence.SharedPrefsUser;

public class PersistentUser {

    private UserCache cache;
    private SharedPrefsUser sharedPrefsUser;

    private PersistentUser() {
        this.cache = new UserCache();
        this.sharedPrefsUser = new SharedPrefsUser(Utils.getApp());
        this.cache.setUser(sharedPrefsUser.load());
    }

    public static PersistentUser getInstance() {
        return PersistentUser.Holder.mInstance;
    }

    synchronized public void saveUser(String user) {
        cache.setUser(user);
        sharedPrefsUser.save(user);
    }

    synchronized public User loadUser() {
        if (cache.getUser() == null) {
            cache.setUser(sharedPrefsUser.load());
        }

        return cache.getUser();
    }

    synchronized public void clearSession() {
        cache.clear();
        cache.setUser(sharedPrefsUser.load());
    }

    synchronized public void clear() {
        cache.clear();
        sharedPrefsUser.clear();
    }

    private static class Holder {
        private static PersistentUser mInstance = new PersistentUser();
    }
}
