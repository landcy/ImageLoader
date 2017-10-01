package com.example.landcy.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by landcy on 10/1/2017.
 */

public class DoubleCache implements ImageCache{
    private MemeoryCache mMemoryCache=new MemeoryCache();
    private DiskCache mDiskCache = new DiskCache();
    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if(bitmap == null){
            bitmap = mDiskCache.get(url);
        }
       return bitmap;
    }
}