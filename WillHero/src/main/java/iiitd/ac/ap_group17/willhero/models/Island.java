package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import iiitd.ac.ap_group17.willhero.HomeController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Timer;

public class Island extends RigidiBody implements Cloneable{

    public Island() {
        super("/assets/islands/island.png");
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
    public Island clone() throws CloneNotSupportedException {
        return (Island) super.clone();
    }
}
