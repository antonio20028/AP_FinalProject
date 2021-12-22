package iiitd.ac.ap_group17.willhero.models;

import java.util.Date;

public class Record {
    private Player player;
    private Date date;
    private String time; //optional because we are using java Data

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Player getPlayer() {
        return player;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
