package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.security.auth.callback.Callback;

public class ColliderThread implements Runnable{

    @Override
    public void run() {
        Timeline timeline = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(20), actionEvent -> {
           GameScreenController.checkCollisions();
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
