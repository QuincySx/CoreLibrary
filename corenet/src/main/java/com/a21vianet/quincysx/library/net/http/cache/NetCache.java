package com.a21vianet.quincysx.library.net.http.cache;

import android.content.Context;

import java.io.File;

/**
 * Created by q7728 on 2017/5/25 0025.
 */

public class NetCache {
    private Context mContext;
    /**
     * 缓存目录
     */
    private File mFile;
    /**
     * 是否启用缓存
     */
    private Boolean isEnable = false;

    private long mSize = 3 * 1024;

    public NetCache(Context context, Boolean isEnable) {
        this(context, null, isEnable, 0);
    }

    public NetCache(Context context, Boolean isEnable, long mSize) {
        this(context, null, isEnable, mSize);
    }

    public NetCache(Context context, File file, Boolean isEnable, long size) {
        if(isEnable){
            mContext = context;
            if (mContext == null) {
                throw new RuntimeException("The incoming Context is null");
            }
            if (file == null) {
                this.mFile = context.getExternalCacheDir();
            } else {
                this.mFile = file;
            }
            if (size != 0) {
                mSize = size;
            }
            this.isEnable = isEnable;
        }
    }

    /**
     * 获得缓存目录
     *
     * @return
     */
    public File getFile() {
        return mFile;
    }

    /**
     * 设置缓存目录
     *
     * @param file
     */
    public void setFile(File file) {
        mFile = file;
    }

    /**
     * 检测是否启动缓存
     *
     * @return
     */
    public Boolean isEnable() {
        return isEnable;
    }

    /**
     * 修改缓存启用状态
     *
     * @param enable
     */
    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public long getSize() {
        return mSize;
    }
}
