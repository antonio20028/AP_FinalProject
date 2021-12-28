package iiitd.ac.ap_group17.willhero.models;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Vector2D  extends Rectangle implements Serializable {

    public Vector2D(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
}
