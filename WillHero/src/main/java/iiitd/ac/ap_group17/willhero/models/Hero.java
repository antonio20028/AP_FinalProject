package iiitd.ac.ap_group17.willhero.models;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Hero extends Character{


    public Hero(String path) {
        super(path);
    }

    public void startJump() {
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(this.getPane().translateYProperty(), -(this.getHeight() + 15))));
        timeline.play();
    }

}
