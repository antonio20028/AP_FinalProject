package iiitd.ac.ap_group17.willhero.data;

import iiitd.ac.ap_group17.willhero.HomeApplication;
import iiitd.ac.ap_group17.willhero.models.*;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TableData implements Serializable {

    @Serial
    private static final long serialVersionUID = 40L;

    private ArrayList<Island> islands;
    private Hero hero;
    private ArrayList<CoinSet> coins;


    private final String name;
    private final String date;

    public TableData() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm");
        date = dateTime.format(formatter);
        name = "Game saved on " + " " + date;
    }

    //this method is to load Game
    public void read() throws IOException, ClassNotFoundException {
        HomeApplication.deserialize();
    }


    //this method is to save game.
    public void write() throws IOException {
        HomeApplication.getDatabase().getSavedGames().add(this);
        HomeApplication.serialize();
    }

    public void update(Hero hero, ArrayList<Island> islands, ArrayList<CoinSet> coins ) {
        this.islands = islands;
        this.coins = coins;
        this.hero = hero;
    }


    public ArrayList<CoinSet> getCoins() {
        return coins;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public Hero getHero() {
        return hero;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
