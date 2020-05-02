package com.bolnica.utils;

import java.util.HashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Session {
    
    private static Session instance;
    
    private final HashMap<String, String> attributes = new HashMap<>();
    
    private Preferences storage = Preferences.userRoot();
    
    private Session() {}
    
    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
        storage.put(key, value);
    }
    
    public String getAttribute(String key) {
        return storage.get(key, null);
    }
    
    public void removeAttribute(String key) {
        attributes.remove(key);
        storage.remove(key);
    }
    
    public void clear() throws BackingStoreException {
        attributes.clear();
        storage.clear();
    }
    
}
