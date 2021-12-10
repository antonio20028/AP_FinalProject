package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.models.Rocket;
import iiitd.ac.ap_group17.willhero.models.Weapon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    protected  void btnSaveClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnSave, 40, 40);
    }


    @FXML
    protected void btnHomeClicked() throws  IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnHome, 40, 40);
        PageController.setCurrentPage(gameScreen);
        PageController.goToPage(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
    }


    @FXML
    protected void btnPauseClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnPause, 40, 40);

    }

}
