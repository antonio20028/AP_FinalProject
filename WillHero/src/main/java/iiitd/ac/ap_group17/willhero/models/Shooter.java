package iiitd.ac.ap_group17.willhero.models;

public interface Shooter {
    void useWeapon(Weapon weapon);
    void upgradeWeaopon(Weapon newweapon);
    Weapon getWeapo();
    void set(Weapon weapon);
}
