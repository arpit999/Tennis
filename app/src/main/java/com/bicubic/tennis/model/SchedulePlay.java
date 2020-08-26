package com.bicubic.tennis.model;

/**
 * Created by admin on 8/22/2016.
 */
public class SchedulePlay {


    String tournament,starttime,home_player,home_player_point,away_player,away_player_point;


    public SchedulePlay(String tournament, String starttime, String home_player, String home_player_point, String away_player, String away_player_point) {
        this.tournament = tournament;
        this.starttime = starttime;
        this.home_player = home_player;
        this.home_player_point = home_player_point;
        this.away_player = away_player;
        this.away_player_point = away_player_point;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getHome_player() {
        return home_player;
    }

    public void setHome_player(String home_player) {
        this.home_player = home_player;
    }

    public String getHome_player_point() {
        return home_player_point;
    }

    public void setHome_player_point(String home_player_point) {
        this.home_player_point = home_player_point;
    }

    public String getAway_player() {
        return away_player;
    }

    public void setAway_player(String away_player) {
        this.away_player = away_player;
    }

    public String getAway_player_point() {
        return away_player_point;
    }

    public void setAway_player_point(String away_player_point) {
        this.away_player_point = away_player_point;
    }
}
