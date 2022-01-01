package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import iiitd.ac.ap_group17.willhero.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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

    private void initGame() throws IOException{
        hero = new Hero("/assets/helmet/player.png");
        gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));

        Island islandStart = new Island();
        Island island = new Island();
        Island island1 = new Island();
        Island island2 = new Island();
        Island island3 = new Island();

        FloatingIsland floatingIsland = new FloatingIsland();

        hero.getCoordinates().setY(327);
        hero.getCoordinates().setX(100);
        hero.setHeight(68);
        hero.setWidth(75);

        islandStart.getCoordinates().setX(100);
        islandStart.getCoordinates().setY(390);

        island.getCoordinates().setX(floatingIsland.getCoordinates().getX() - 100);
        island.getCoordinates().setY(440);
        island1.getCoordinates().setX(floatingIsland.getCoordinates().getX() + 250);
        island1.getCoordinates().setY(500);

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
            islands.add(floatingIsland);
            //islands.add(island1);
            //islands.add(island2);
            //islands.add(island3);
            islands.forEach(RigidiBody::mountImage);
        }

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
                        try {
                            moveIslands();
                            checkCollisions();
                        } catch (NullPointerException e) {
                            System.out.println("Start Game");
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
        Timeline moveTimeline = new Timeline();

        synchronized (islands) {
            for (Island island: islands) {
                double lay_x = island.getImageView().getLayoutX();
                moveTimeline.setCycleCount(1);
                moveTimeline.setAutoReverse(false);
                moveTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), actionEvent -> {
                    island.getImageView().setLayoutX(lay_x - 100);
                }));
                moveTimeline.play();
            }
        }

        AnimationController.timelines.add(moveTimeline);
    }

    private void checkCollisions() {

    }

    private void loadIsland() throws InterruptedException {

    }

    private void loadFloatingIsland() {
        Island is = new Island();
        is.getCoordinates().setY(390);
        is.getCoordinates().setX(1000);
        is.setWidth(200);
        is.setHeight(100);
        is.mountImage();
        islands.add(is);
        gameScreen.getChildren().add(islands.get(islands.size() - 1).getPane()); //after added, make it visible in gameScreen
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
}