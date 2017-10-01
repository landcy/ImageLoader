package com.example.landcy.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by landcy on 10/1/2017.
 */

public class ImageLoader {
    ImageCache mImageCache = new ImageCache();
    ExecutorService mExcutorServie = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url,final ImageView imageView){
        Bitmap bitmap = mImageCache.get(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExcutorServie.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap1 = downLoadImage(url);
                if(bitmap1 == null){
                    return ;
                }
                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap1);
                }
                mImageCache.put(url,bitmap1);
            }
        });
    }

    private Bitmap downLoadImage(String iamgeUrl) {
        Bitmap bitmap = null;
        try{
           URL url =  new URL(iamgeUrl);
            final HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream( connection.getInputStream());
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
