package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public class TreasureWeapon extends Treasure<Weapon>{
    private ArrayList<Weapon> weapons;

    public TreasureWeapon() {
        super("/assets/chest/1.png");
        getCollectables().addAll(weapons);
    }
}
