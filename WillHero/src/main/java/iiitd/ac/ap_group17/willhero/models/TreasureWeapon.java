package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public class TreasureWeapon extends Treasure<Weapon>{
    private ArrayList<Weapon> weapons = new ArrayList<>();

    public TreasureWeapon() {
        super("/assets/treasure/ChestClosed.png", "/assets/treasure/ChestOpen.png");
        this.setHeight(75);
        this.setWidth(100);
        weapons.add(new Rocket());
        weapons.add(new Rocket());
        this.getCollectables().addAll(weapons);
    }
}
