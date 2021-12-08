package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class UIAnimationControl {

    public static void startButtonIllusionAnimation(ImageView btn, double height, double width) {

        double _h = btn.getFitHeight();
        double _w = btn.getFitWidth();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(3);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), actionEvent -> {
            btn.setFitHeight(height);
            btn.setFitWidth(width);
        }));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), actionEvent -> {
            btn.setFitHeight(_h);
            btn.setFitWidth(_w);
        }));

        timeline.play();
    }
}
