package com.bicubic.tennis.model;

/**
 * Created by admin on 8/5/2016.
 */
public class News {

    String title,image,time;

    public News(String title, String image, String time) {
        this.title = title;
        this.image = image;
        this.time = time;
    }

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
