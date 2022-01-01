package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class SavedGameScreenController {

    @FXML
    private ImageView btnExit;

    @FXML
    private ImageView btnLoadGame;

    public static ToggleGroup group = new ToggleGroup();

    @FXML
    protected void btnExitClicked()  throws  Exception {
        PageController.goBack();
    }

    @FXML
    protected void btnLoadClicked() throws Exception {
        UIAnimationControl.startButtonIllusionAnimation(btnLoadGame, 180, 200);

        ToggleButton selected = (ToggleButton) group.getSelectedToggle();

        TableData game = HomeApplication.getDatabase().getGameByName(selected.getText());
        System.out.println(game.getHero().getPosition());

        GameScreenController.currentGame.read();
    }

}
