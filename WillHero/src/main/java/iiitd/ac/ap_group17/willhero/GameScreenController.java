package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.models.Rocket;
import iiitd.ac.ap_group17.willhero.models.Weapon;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class GameScreenController {


    @FXML
    private ImageView btnSave;

    @FXML
    private ImageView btnPause;

    @FXML
    private ImageView btnHome;

    @FXML
    private Pane backgroundImage;

    @FXML
    private AnchorPane gameScreen;

    @FXML
    private Label lblPause;


    @FXML
    protected  void btnSaveClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnSave, 40, 40);
    }


    @FXML
    protected void btnHomeClicked() throws  IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnHome, 40, 40);
        PageController.goBack();
    }


    @FXML
    protected void btnPauseClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnPause, 40, 40);

        if (AnimationController.timelines.get(0).getStatus() == Animation.Status.RUNNING) {
            lblPause.setVisible(true);
            Timeline t = new Timeline();
            t.setAutoReverse(true);
            t.setCycleCount(Timeline.INDEFINITE);
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.6), new KeyValue(lblPause.opacityProperty(), 0.3, Interpolator.EASE_IN)));
            t.play();
            AnimationController.pauseAll();
        } else if (AnimationController.timelines.get(0).getStatus() == Animation.Status.PAUSED){
            lblPause.setVisible(false);
            AnimationController.resumeAll();
        }
    }



}
