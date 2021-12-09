package iiitd.ac.ap_group17.willhero.models;

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
        this.mountImage();
        move();
    }

    private void  floatAnimation() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new KeyValue(this.getPane().translateYProperty(), 20)));
        timeline.play();
    }

    @Override
    public void move() {
        this.floatAnimation();
    }
}
