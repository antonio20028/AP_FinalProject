package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.IOException;

public class GameScreenController {

    @FXML
    private ImageView btnSave;

    @FXML
    private ImageView btnPause;

    @FXML
    private ImageView btnHome;

    @FXML
    private ImageView btnExit;

    @FXML
    private ImageView btnNewGame;

    @FXML
    private Pane backgroundImage;

    @FXML
    private AnchorPane gameScreen;

    @FXML
    private Label lblPause;


    public static TableData currentGame;

    @FXML
    protected  void btnSaveClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnSave, 40, 40);
        currentGame.update(HomeController.hero, HomeController.islands, HomeController.coins);
        currentGame.write();
    }

    @FXML
    protected void btnHomeClicked() throws  IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnHome, 40, 40);
        PageController.goBack();
    }


    @FXML
    protected void btnPauseClicked() throws IOException {

        UIAnimationControl.startButtonIllusionAnimation(btnPause, 40, 40);
        if (AnimationController.timelines.get(0).getStatus() == Animation.Status.RUNNING) {
            lblPause.setVisible(true);
            btnPause.setImage(new Image(this.getClass().getResource("/assets/controls/play.png").toString()));
            Timeline t = new Timeline();
            t.setAutoReverse(true);
            t.setCycleCount(Timeline.INDEFINITE);
            t.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), new KeyValue(lblPause.opacityProperty(), 0.3, Interpolator.EASE_IN)));
            t.play();
            AnimationController.pauseAll();
        } else if (AnimationController.timelines.get(0).getStatus() == Animation.Status.PAUSED){
            lblPause.setVisible(false);
            btnPause.setImage(new Image(this.getClass().getResource("/assets/controls/b_9.png").toString()));
            AnimationController.resumeAll();
        }
    }

    @FXML
    protected void btnExitClicked() {
        UIAnimationControl.startButtonIllusionAnimation(btnExit, 130, 190);
        System.exit(0);
    }

//    @FXML
//    protected void btnStartNewGameClicked() {
//        UIAnimationControl.startButtonIllusionAnimation(btnNewGame, 115, 255);
//        try {
//            initGame();
//            flag = false;
//            PageController.goToPage(homeRoot, gameScreen, "Win Hero");
//            PageController.nextPage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
//                @Override
//                public void handle(KeyEvent event) {
//                    if (event.getCode() == KeyCode.SPACE) {
//                        if (!AnimationController.isPaused) {
//                            try {
//                                moveIslands();
//                                moveOrcs();
//                                moveTreasures();
//                                updatePositionLabel(1);
//                            } catch (NullPointerException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            });
//        } catch (IOException e){
//            System.out.println("cant start the game");
//        }
//    }

}
