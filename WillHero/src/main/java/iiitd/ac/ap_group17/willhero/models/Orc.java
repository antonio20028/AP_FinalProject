package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class Orc extends Character implements Cloneable, Jumpable{

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
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), actionEvent -> {
            this.getPane().setLayoutX(this.getPane().getLayoutX() - 100);
        }));
        timeline.play();
        AnimationController.timelines.add(timeline);
    }


    @Override
    public void onCollision(RigidiBody other) {
        if (other instanceof Obstacle) {
            this.fall();
        } else if (other instanceof Weapon weapon) {
            if(this instanceof RedOrc || this instanceof GreenOrc) {
               if (weapon instanceof Rocket ) {
                   this.fall();
                   this.destroy();
               }
            }
        }
    }

    @Override
    public void jump() {

    }

    @Override
    public Orc clone() {
        try {
            Orc clone = (Orc) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
