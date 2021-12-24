package iiitd.ac.ap_group17.willhero.models;

public class Island extends RigidiBody implements Cloneable{
    public Island() {
        super("/assets/islands/island.png");
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
