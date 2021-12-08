package iiitd.ac.ap_group17.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameScreenController {
    @FXML
    private Button btnMenu;

    @FXML
    private AnchorPane gameScreen;


    @FXML
    protected void btnClicked() throws IOException {
        AnchorPane inGameMenuScreen = FXMLLoader.load(getClass().getResource("inGameMenu.fxml"));

        gameScreen.getChildren().setAll(inGameMenuScreen);
    }
}
