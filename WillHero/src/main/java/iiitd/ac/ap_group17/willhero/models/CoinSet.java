package iiitd.ac.ap_group17.willhero.models;

import javafx.scene.Group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class CoinSet extends Group implements Cloneable, Serializable {

    public CoinSet() {
        Coin coin = new Coin();
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();
        coin.setHeight(55);
        coin.setWidth(55);
        coin1.setHeight(55);
        coin1.setWidth(55);
        coin2.setHeight(55);
        coin2.setWidth(55);
        coin1.getCoordinates().setX(75);
        coin2.getCoordinates().setX(150);
        this.getChildren().add(coin.getPane());
        this.getChildren().add(coin1.getPane());
        this.getChildren().add(coin2.getPane());
        coin.mountImage(); coin1.mountImage(); coin2.mountImage();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
