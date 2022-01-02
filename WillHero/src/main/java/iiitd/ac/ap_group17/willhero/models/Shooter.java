package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public interface Shooter {
    void useWeapon();
    void upgradeWeapon(ArrayList<Weapon> newweapon);
    Weapon getWeapon();
    void set();
}
