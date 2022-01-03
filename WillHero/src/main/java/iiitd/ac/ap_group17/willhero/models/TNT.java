package iiitd.ac.ap_group17.willhero.models;

import javafx.scene.image.Image;

import java.util.Objects;

public class TNT extends Obstacle {

    public TNT() {
        super("/assets/TNT.png");
        this.setHeight(50);
        this.setWidth(50);
    }

    @Override
    public void onCollision(RigidiBody other) {
        super.onCollision(other);
        burst();
    }

    public void burst() {
        this.getImageView().setImage(new Image(Objects.requireNonNull(getClass().getResource("/assets/blast.png")).toString()));
        this.destroy();
    }
}
