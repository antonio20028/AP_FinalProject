package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SavedGameScreenController {
    @FXML
    private ImageView btnExit;
    @FXML
    private ImageView btnLoadGame;
    @FXML
    protected void btnExitClicked() {
        startButtonIllusionAnimation(btnExit, 60, 50);
    }
    @FXML
    protected void btnLoadClicked() {
        startButtonIllusionAnimation(btnLoadGame, 180, 200);
    }
    private  void startButtonIllusionAnimation(ImageView btn, double height, double width) {

        double _h = btn.getFitHeight();
        double _w = btn.getFitWidth();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
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
