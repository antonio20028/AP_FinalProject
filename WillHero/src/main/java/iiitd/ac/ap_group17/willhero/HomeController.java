package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    private AnchorPane home;

    @FXML
    private ImageView btnSavedGame;

    @FXML
    private ImageView btnOption;

    @FXML
    private AnchorPane gamePane;

    @FXML
    protected void btnStartNewGameClicked() {
        startButtonIllusionAnimation(btnNewGame, 115, 255);

        AnchorPane anchorPane = new AnchorPane();

        gamePane = new AnchorPane(home);
        gamePane.getChildren().add(anchorPane);

        KeyFrame start = new KeyFrame(Duration.ZERO,
                new KeyValue(gamePane.translateXProperty(), gamePane.getWidth()),
                new KeyValue(home.translateXProperty(), 0));

        KeyFrame end = new KeyFrame(Duration.seconds(1),
                new KeyValue(gamePane.translateXProperty(), 0),
                new KeyValue(home.translateXProperty(), -gamePane.getWidth())
        );

        Timeline t = new Timeline(start, end);
        t.play();

    }


    @FXML
    protected void btnSavedGameClicked() {
            startButtonIllusionAnimation(btnSavedGame, 130, 190);
    }


    @FXML
    protected void btnOptionClicked() {
        startButtonIllusionAnimation(btnOption, 130, 190);
    }

    @FXML
    protected void btnExitClicked() {
        startButtonIllusionAnimation(btnExit, 130, 190);
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