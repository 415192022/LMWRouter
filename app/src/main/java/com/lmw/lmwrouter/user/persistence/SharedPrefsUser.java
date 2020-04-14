package com.lmw.lmwrouter.user.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.lmw.lmwrouter.user.cache.User;


public class SharedPrefsUser {
    public static final String USER = "User";
    private final SharedPreferences sharedPreferences;


    public SharedPrefsUser(Context context) {
        this(context.getSharedPreferences("UserPersistence", Context.MODE_PRIVATE));
    }

    public SharedPrefsUser(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public void save(String user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER, user);
        editor.commit();
    }

    public User load() {
        String author = sharedPreferences.getString(USER, "");
        if (author.isEmpty()) {
            return null;
        } else {
            return new User(USER, author);
        }
    }

    public void clear() {
        sharedPreferences.edit().clear().commit();
    }
}
