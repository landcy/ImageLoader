package com.example.landcy.imageloader;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by landcy on 10/1/2017.
 */

public class DiskCache implements ImageCache{
    static String cacheDir= "sdcard/cache/";
    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream= new FileOutputStream(cacheDir+url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
            
        }catch(IOException e){
            e.printStackTrace();
        }finally {
           try{ if(fileOutputStream!=null){
                fileOutputStream.close();
            }}catch(IOException e){
               e.printStackTrace();
           }
        }

    }
    @Override
    public Bitmap get(String url) {
        return mImageCaches.get(url);
    }
}