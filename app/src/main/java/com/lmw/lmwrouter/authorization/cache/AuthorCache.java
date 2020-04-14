package com.lmw.lmwrouter.authorization.cache;


import com.lmw.lmwrouter.authorization.persistence.SharedPrefsAuthorPersistor;

public class AuthorCache {

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAuthor(String token) {
        this.author = new Author(SharedPrefsAuthorPersistor.AUTHORIZATION, "Bearer " + token);
    }

    public void clear() {
        author = null;
    }
}
