package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public class Orc extends Character implements Cloneable{

    ArrayList<Weapon> weapons  = new ArrayList<>();

    private String color;

    public Orc(String color,String path) {
        super(path);
        this.color = color;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void onCollision(RigidiBody other) {

        for (Weapon weapon : weapons) {
            if (weapon.getCoordinates() == other.getCoordinates()) {
                fall();
            }
        }

    }

}
