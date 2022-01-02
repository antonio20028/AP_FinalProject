package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.Objects;
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
    private ImageView btnGoHome;

    @FXML
    private AnchorPane optionMenuScreen;

    @FXML
    public static AnchorPane gameScreen;

    static MenuAnimationController menuAnimationController = new MenuAnimationController();


    @FXML
    protected void btnStartNewGameClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnNewGame, 115, 255);
        FXMLLoader gameScreenLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("gamescreen.fxml")));
        AnchorPane gameScreen = gameScreenLoader.load();
        GameScreenController controller = gameScreenLoader.getController();
        PageController.goToPage(homeRoot, gameScreen, "Will hero");
        controller.initGameElements(new TableData(gameScreen), gameScreen);


    }

    @FXML
    protected void btnSavedGameClicked() throws IOException {

        UIAnimationControl.startButtonIllusionAnimation(btnSavedGame, 130, 190);
        AnchorPane savedGameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("savedgame.fxml")));

        double gap = 50;
        if (HomeApplication.getDatabase().getSavedGames().size() != 0) {
            for (TableData t : HomeApplication.getDatabase().getSavedGames()) {
                ToggleButton button = new ToggleButton(t.getName());
                button.setLayoutX(440);
                button.setLayoutY(130 + gap);
                button.setToggleGroup(SavedGameScreenController.group);
                gap = gap + 50;
                savedGameScreen.getChildren().add(button);
            }
        } else {
            Label label = new Label("No Saved Games");
            label.setLayoutX(440);
            label.setLayoutY(180);
            savedGameScreen.getChildren().add(label);
        }
        PageController.goToPage(homeRoot,savedGameScreen , "Win Hero - Saved Games");
    }

    @FXML
    protected void btnOptionClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnOption, 130, 190);
        menuAnimationController.setMenu(optionMenuScreen);
        menuAnimationController.play();
    }

    @FXML
    protected void btnExitClicked() {
        UIAnimationControl.startButtonIllusionAnimation(btnExit, 130, 190);
        System.exit(0);
    }

    @FXML
    protected void btnGoHomeClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnGoHome, 50, 50);
        menuAnimationController.reverse();
    }

}

