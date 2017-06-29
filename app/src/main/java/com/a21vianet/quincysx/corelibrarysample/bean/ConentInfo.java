package com.a21vianet.quincysx.corelibrarysample.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public class ConentInfo {
    @SerializedName("_id")
    private String id;
    @SerializedName("content")
    private String content;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
