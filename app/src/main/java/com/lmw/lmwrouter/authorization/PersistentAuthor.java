package com.lmw.lmwrouter.authorization;

import com.lmw.lmwrouter.authorization.cache.Author;
import com.lmw.lmwrouter.authorization.cache.AuthorCache;
import com.lmw.lmwrouter.authorization.persistence.SharedPrefsAuthorPersistor;
import com.lmw.lmwrouter.lib.util.Utils;


public class PersistentAuthor {

    private AuthorCache cache;
    private SharedPrefsAuthorPersistor persistor;

    private PersistentAuthor() {
        this.cache = new AuthorCache();
        this.persistor = new SharedPrefsAuthorPersistor(Utils.getApp());
        this.cache.setAuthor(persistor.load());
    }

    public static PersistentAuthor getInstance() {
        return PersistentAuthor.Holder.mInstance;
    }

    synchronized public void saveAuthor(String token) {
        cache.setAuthor(token);
        persistor.save(token);
    }

    synchronized public Author loadAuthor() {
        if (cache.getAuthor() == null) {
            cache.setAuthor(persistor.load());
        }

        return cache.getAuthor();
    }

    synchronized public boolean hasLogin() {
        if (loadAuthor() == null) {
            return false;
        }

        return true;
    }

    synchronized public void clearSession() {
        cache.clear();
        cache.setAuthor(persistor.load());
    }

    synchronized public void clear() {
        cache.clear();
        persistor.clear();
    }

    private static class Holder {
        private static PersistentAuthor mInstance = new PersistentAuthor();
    }
}
