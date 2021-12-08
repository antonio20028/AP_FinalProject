package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.security.Key;

public class HomeController {

    @FXML
    private  ImageView btnNewGame;

    @FXML
    private ImageView btnExit;

    @FXML
    private AnchorPane homeRoot;

    @FXML
    private ImageView btnSavedGame;

    @FXML
    private ImageView btnOption;


    @FXML
    protected void btnStartNewGameClicked() throws IOException {

        AnchorPane gameScreen = FXMLLoader.load(getClass().getResource("gameScreen.fxml"));
        startButtonIllusionAnimation(btnNewGame, 115, 255);
        homeRoot.getChildren().setAll(gameScreen);

    }

    @FXML
    protected void btnSavedGameClicked() throws IOException {
            startButtonIllusionAnimation(btnSavedGame, 130, 190);
            AnchorPane savedGameScreen = FXMLLoader.load(getClass().getResource("savedgame.fxml"));
            homeRoot.getChildren().setAll(savedGameScreen);
    }


    @FXML
    protected void btnOptionClicked() throws IOException {
        DialogPane optionDialogBox = FXMLLoader.load(getClass().getResource("optionDialogScreen.fxml"));
        startButtonIllusionAnimation(btnOption, 130, 190);
        homeRoot.getChildren().add(optionDialogBox);
    }

    @FXML
    protected void btnExitClicked() {
        startButtonIllusionAnimation(btnExit, 130, 190);
        System.exit(0);
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