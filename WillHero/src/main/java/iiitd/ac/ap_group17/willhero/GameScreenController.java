package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.models.Rocket;
import iiitd.ac.ap_group17.willhero.models.Weapon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

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
    protected  void btnSaveClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnSave, 40, 40);
    }


    @FXML
    protected void btnHomeClicked() throws  IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnHome, 40, 40);
        AnchorPane mainHomeScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        gameScreen.getChildren().setAll(mainHomeScreen);
    }


    @FXML
    protected void btnPauseClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnPause, 40, 40);


    }

    @FXML
    public void onMouseEvent(MouseEvent event) {
        Weapon w = new Rocket();
        w.setHeight(50);
        w.setWidth(50);
        w.getCoordinates().setY(HomeController.hero.getCoordinates().getY());
        w.getCoordinates().setX(HomeController.hero.getCoordinates().getX());
        w.getPane().setVisible(false);
        w.mountImage();
        gameScreen.getChildren().add(w.getPane());
        HomeController.hero.useWeapon(w);
    }

    @FXML
    protected void move(MouseEvent event) {

    }

}
