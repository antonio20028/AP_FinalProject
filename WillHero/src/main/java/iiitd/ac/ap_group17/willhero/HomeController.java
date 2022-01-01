package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import iiitd.ac.ap_group17.willhero.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.scene.layout.Background.EMPTY;

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
    static AnchorPane gameScreen;

    static final ArrayList<Island> islands = new ArrayList<>();
    static ArrayList<CoinSet> coins = new ArrayList<>();
    static boolean flag = true;
    static MenuAnimationController menuAnimationController = new MenuAnimationController();

    @FXML
    static Hero hero;


    private Label txtHeroPosition;

    final Island islandStart = new Island();
    final Island island = new Island();
    final Island island1 = new Island();
    final Island island2 = new Island();
    final Island island3 = new Island();
    final FloatingIsland floatingIsland = new FloatingIsland();

    private void initGame() throws IOException{
        hero = new Hero("/assets/helmet/player.png");

        gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));
        txtHeroPosition = new Label(String.valueOf(hero.getPosition()));

        txtHeroPosition.setLayoutX(493.0);
        txtHeroPosition.setLayoutY(30);
        txtHeroPosition.setTextFill(Color.WHITE);
        txtHeroPosition.setFont(new Font("Franklin Gothic Book", 50));




        hero.getCoordinates().setY(327);
        hero.getCoordinates().setX(100);
        hero.setHeight(68);
        hero.setWidth(75);

        islandStart.getCoordinates().setX(100);
        islandStart.getCoordinates().setY(390);

        island.getCoordinates().setX(floatingIsland.getCoordinates().getX() - 100);
        island.getCoordinates().setY(440);
        island1.getCoordinates().setX(floatingIsland.getCoordinates().getX() + 250);
        island1.getCoordinates().setY(390);

        island2.getCoordinates().setX(island1.getCoordinates().getX() + 320);
        island2.getCoordinates().setY(420);

        island3.getCoordinates().setX(floatingIsland.getCoordinates().getX() + 950);
        island3.getCoordinates().setY(390);

        island1.setHeight(100);
        island1.setWidth(200);

        island2.setHeight(100);
        island2.setWidth(200);

        island3.setHeight(100);
        island3.setWidth(200);

        islandStart.setHeight(100);
        islandStart.setWidth(200);

        island.setHeight(90);
        island.setWidth(200);

        hero.jump();

        hero.mountImage();

       if (flag) {
           islands.add(islandStart);
           islands.add(island);
           islands.add(island1);
           islands.add(island2);
           islands.add(island3);
           islands.add(floatingIsland);
           islands.forEach(RigidiBody::mountImage);

       }

        gameScreen.getChildren().add(txtHeroPosition);
        gameScreen.getChildren().add(hero.getPane());
        islands.forEach(is -> gameScreen.getChildren().add(is.getPane()));
    }

    @FXML
    protected void btnStartNewGameClicked() {
        UIAnimationControl.startButtonIllusionAnimation(btnNewGame, 115, 255);
        try {
            initGame();
            flag = false;
            PageController.goToPage(homeRoot, gameScreen, "Win Hero");
            PageController.nextPage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.SPACE) {
                       if (!AnimationController.isPaused) {
                           try {
                               moveIslands();
                               checkCollisions();
                               updatePositionLabel(1);
                           } catch (NullPointerException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }
            });
        } catch (IOException e){
            System.out.println("cant start the game");
        }
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

    private void moveIslands() {

        islands.forEach(Island::move);
      for (Island i: islands) {
          if (i.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                i.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() + 150);
          }
      }
    }

    private void checkCollisions() {


    }

    private void loadRedOrc() {

    }

    private void loadGreenOrc() {

    }

    private void loadBoss() {

    }

    private void loadTNT() {

    }

    private void loadTreasureChest() {

    }

    private void loadCoins() {
        for(int i = 0; i < 1; i++) {
            CoinSet coinSet = new CoinSet();
            coinSet.setLayoutX(new Random().nextDouble(400, 500));
            coinSet.setLayoutY(new Random().nextDouble(270, 300));
            gameScreen.getChildren().add(coinSet);
        }

    }

    public void updatePositionLabel(int v) {
        hero.increasePosition(v);
        txtHeroPosition.setText(String.valueOf(hero.getPosition()));
    }
}