package com.example.landcy.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by landcy on 10/1/2017.
 */

public class MemeoryCache implements ImageCache{
    LruCache<String, Bitmap> mImageCaches;

    public MemeoryCache() {
        initMemeoryCache();
    }

    private void initMemeoryCache() {
        final int maxMemorty = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemorty / 4;
        mImageCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                return super.sizeOf(key, value);
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCaches.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mImageCaches.get(url);
    }
}