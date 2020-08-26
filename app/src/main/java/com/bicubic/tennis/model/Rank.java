package com.bicubic.tennis.model;

/**
 * Created by admin on 8/6/2016.
 */
public class Rank {

    String No, Name, Points;

    public Rank(String no, String name, String points) {
        No = no;
        Name = name;
        Points = points;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }


}
