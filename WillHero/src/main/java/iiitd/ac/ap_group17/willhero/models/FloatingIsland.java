package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FloatingIsland extends Island {

    public FloatingIsland() {
        this.setHeight(100);
        this.setWidth(200);
        this.getCoordinates().setX(550);
        this.getCoordinates().setY(175);
        this.floatAnimation();
    }

    private void  floatAnimation() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new KeyValue(this.getPane().translateYProperty(), 20)));
        AnimationController.timelines.add(timeline);
        timeline.play();
    }

    @Override
    public void move() {
        super.move();
    }
}
