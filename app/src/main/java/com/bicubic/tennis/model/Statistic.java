package com.bicubic.tennis.model;

/**
 * Created by admin on 8/9/2016.
 */
public class Statistic {

    public static final int ONE_ROW = 0;
    public static final int TWO_ROW = 1;

    String no, player_one, player_two, title;
    int row_type;

    public Statistic(String no, String player_one, String player_two, String title, int row_type) {
        this.no = no;
        this.player_one = player_one;
        this.player_two = player_two;
        this.title = title;
        this.row_type = row_type;
    }

    public Statistic(String no, String player_one, String title, int row_type) {
        this.no = no;
        this.player_one = player_one;
        this.title = title;
        this.row_type = row_type;
    }

    public int getRow_type() {
        return row_type;
    }

    public void setRow_type(int row_type) {
        this.row_type = row_type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPlayer_one() {
        return player_one;
    }

    public void setPlayer_one(String player_one) {
        this.player_one = player_one;
    }

    public String getPlayer_two() {
        return player_two;
    }

    public void setPlayer_two(String player_two) {
        this.player_two = player_two;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
