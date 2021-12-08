package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SavedGameScreenController {
    @FXML
    private ImageView btnExit;

    @FXML
    private ImageView btnLoadGame;

    @FXML
    private AnchorPane savedGameScreen;


    @FXML
    protected void btnExitClicked()  throws  Exception {
        AnchorPane homeScreen = FXMLLoader.load(getClass().getResource("home.fxml"));
        UIAnimationControl.startButtonIllusionAnimation(btnExit, 60, 50);
        savedGameScreen.getChildren().setAll(homeScreen);
    }

    @FXML
    protected void btnLoadClicked() throws Exception {
        UIAnimationControl.startButtonIllusionAnimation(btnLoadGame, 180, 200);
    }
}
