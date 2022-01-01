package iiitd.ac.ap_group17.willhero.models;

public class Coin extends RigidiBody implements Collectable {

    public Coin() {
        super("/assets/coin.png");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
