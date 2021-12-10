package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Objects;

public class SavedGameScreenController {
    @FXML
    private ImageView btnExit;

    @FXML
    private ImageView btnLoadGame;

    @FXML
    private AnchorPane savedGameScreen;


    @FXML
    protected void btnExitClicked()  throws  Exception {
        PageController.setCurrentPage(savedGameScreen);
        PageController.goToPage(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))), "Win Hero");
    }

    @FXML
    protected void btnLoadClicked() throws Exception {
        UIAnimationControl.startButtonIllusionAnimation(btnLoadGame, 180, 200);
    }
}
