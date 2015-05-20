package com.example.bean;

import com.avos.avoscloud.AVClassName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hd94 on 2015/5/13.
 */

public class News_Bean {
    @DatabaseField
    private String content;
    @DatabaseField
    private String title;
    @DatabaseField(id = true)
    private String images;










    @Override
    public String toString() {
        return "News_Bean{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", images='" + images + '\'' +
                ", images_id=" + images_id +
                '}';
    }

    @DatabaseField
    private int images_id;

    public int getImages_id() {
        return images_id;
    }

    public void setImages_id(int images_id) {
        this.images_id = images_id;
    }






    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }



}
