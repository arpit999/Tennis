package com.bicubic.tennis.model;

/**
 * Created by admin on 8/6/2016.
 */
public class Player {

    String name,image;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
