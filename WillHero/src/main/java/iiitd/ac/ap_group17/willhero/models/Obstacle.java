package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Obstacle extends RigidiBody {

    public Obstacle(String path) {
        super(path);
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
}
