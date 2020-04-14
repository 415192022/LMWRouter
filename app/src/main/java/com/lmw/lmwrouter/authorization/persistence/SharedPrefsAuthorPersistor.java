package com.lmw.lmwrouter.authorization.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.lmw.lmwrouter.authorization.cache.Author;


public class SharedPrefsAuthorPersistor {

    public static final String AUTHORIZATION = "Authorization";
    private final SharedPreferences sharedPreferences;


    public SharedPrefsAuthorPersistor(Context context) {
        this(context.getSharedPreferences("AuthorPersistence", Context.MODE_PRIVATE));
    }

    public SharedPrefsAuthorPersistor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public void save(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTHORIZATION, "Bearer " + token);
        editor.commit();
    }

    public Author load() {
        String author = sharedPreferences.getString(AUTHORIZATION, "");
        if (author.isEmpty()) {
            return null;
        } else {
            return new Author(AUTHORIZATION, author);
        }
    }

    public void clear() {
        sharedPreferences.edit().clear().commit();
    }
}
