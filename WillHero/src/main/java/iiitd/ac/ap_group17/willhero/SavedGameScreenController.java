package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class SavedGameScreenController {

    @FXML
    private ImageView btnExit;

    @FXML
    private ImageView btnLoadGame;

    @FXML
    private AnchorPane savedGameScreen;

    public static ToggleGroup group = new ToggleGroup();

    @FXML
    protected void btnExitClicked()  throws  Exception {
        PageController.goBack();
    }


    @FXML
    protected void btnLoadClicked() throws Exception {
        UIAnimationControl.startButtonIllusionAnimation(btnLoadGame, 180, 200);
        FXMLLoader gameScreenLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("gamescreen.fxml")));
        AnchorPane gameScreen = gameScreenLoader.load();

        ToggleButton selected = (ToggleButton) group.getSelectedToggle();
        TableData game = HomeApplication.getDatabase().getGameByName(selected.getText());
        System.out.println(game.getIslands().size());
        System.out.println(game.getHero().getPosition());
        GameScreenController.currentGame.read();
        GameScreenController controller = gameScreenLoader.getController();
        PageController.goToPage(savedGameScreen, gameScreen, "Will hero");
        controller.initGameElements(game, gameScreen);
    }

}
