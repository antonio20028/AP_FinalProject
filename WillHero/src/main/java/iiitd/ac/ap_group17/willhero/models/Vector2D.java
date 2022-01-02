package iiitd.ac.ap_group17.willhero.models;

import javafx.scene.shape.Rectangle;
import org.controlsfx.control.decoration.GraphicDecoration;

import java.io.Serializable;

public class Vector2D  extends Rectangle implements Serializable {

    public Vector2D(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public boolean onCollisionWith(Vector2D other) {
        // return  (this.getPane().getLayoutX() + this.getPane().getHeight() / 2 > other.getPane().getLayoutX() && this.getPane().getLayoutX() < other.getPane().getLayoutX() + other.getPane().getWidth()/2 && this.getPane().getLayoutY()+ this.getPane().getHeight()/2 > other.getPane().getLayoutY() &&
        //       this.getPane().getLayoutY() < other.coordinates.getLayoutY()+ other.getPane().getLayoutY() + other.getPane().getHeight());

        return this.getBoundsInParent().intersects(other.getBoundsInParent());
    }

}
