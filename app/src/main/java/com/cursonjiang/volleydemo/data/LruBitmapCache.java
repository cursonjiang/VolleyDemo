package com.cursonjiang.volleydemo.data;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.cursonjiang.volleydemo.App;

/**
 * Created by root on 15/6/13.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

    // 取运行内存阈值的1/8作为图片缓存
    private static final int MEM_CACHE_SIZE =
            1024 * 1024 * ((ActivityManager) App.getContext()
                                                .getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() / 8;

    public LruBitmapCache() {
        this(MEM_CACHE_SIZE);
    }

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return value.getByteCount();
        }
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }
}
