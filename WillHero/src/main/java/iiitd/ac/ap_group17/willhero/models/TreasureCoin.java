package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public class TreasureCoin extends Treasure<Coin> {
    private ArrayList<Coin> coins = new ArrayList<>();

    public TreasureCoin() {
        super("/assets/treasure/1.png", "/assets/treasure/6.png");
        getCollectables().addAll(coins);
    }

}
