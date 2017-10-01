package com.example.landcy.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by landcy on 10/1/2017.
 */

public interface ImageCache {
    void put(String url, Bitmap bitmap);
    Bitmap get(String url);
}

