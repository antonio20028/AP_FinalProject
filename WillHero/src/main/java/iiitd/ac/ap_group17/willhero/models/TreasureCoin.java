package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public class TreasureCoin extends Treasure<Coin> {
    private ArrayList<Coin> coins = new ArrayList<>();

    public TreasureCoin() {
        super("/assets/chest/1.png");
        getCollectables().addAll(coins);
    }

}
