package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import iiitd.ac.ap_group17.willhero.HomeController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class Hero extends Character implements Jumpable{
    ArrayList<Weapon> weapons  = new ArrayList<>();

    public Hero(String path) {
        super(path);

    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    private void startJump() {
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(this.getPane().translateYProperty(), -(this.getHeight() + 15))));
        AnimationController.timelines.add(timeline);
        timeline.play();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void jump() {
        startJump();
    }

    public void useWeapon(Weapon weapon) {
        if (weapon instanceof Rocket rocket) {
            rocket.getPane().setVisible(true);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(rocket.getPane().translateXProperty(), 500)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(rocket.getPane().visibleProperty(), false)));
            AnimationController.timelines.add(timeline);
            timeline.play();
        }
    }

    @Override
    public void onCollision(RigidiBody other) {

    }
}
