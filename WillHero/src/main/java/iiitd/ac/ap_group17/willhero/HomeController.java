package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.security.Key;
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
    private AnchorPane gameScreen;

    static ArrayList<Island> islands = new ArrayList<>();
    static ArrayList<CoinSet> coins = new ArrayList<>();
    static MenuAnimationController menuAnimationController = new MenuAnimationController();

    private void initGame() throws IOException{
        gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));
        Island islandStart = new Island();
        Island island = new Island();
        RedOrc redorc = new RedOrc();
        FloatingIsland floatingIsland = new FloatingIsland();
        Hero hero = new Hero("/assets/helmet/player.png");

        hero.getCoordinates().setY(327);
        hero.getCoordinates().setX(100);
        hero.setHeight(68);
        hero.setWidth(75);

        redorc.getCoordinates().setX(floatingIsland.getCoordinates().getX()+50);
        redorc.getCoordinates().setY(378);

        islandStart.getCoordinates().setX(100);
        islandStart.getCoordinates().setY(390);

        island.getCoordinates().setX(floatingIsland.getCoordinates().getX());
        island.getCoordinates().setY(440);

        islandStart.setHeight(100);
        islandStart.setWidth(200);

        island.setHeight(100);
        island.setWidth(200);

        hero.jump();
        redorc.jump();
       //loadIslands();
        hero.mountImage();
        redorc.mountImage();
        islandStart.mountImage();
        island.mountImage();
        loadCoins();

        //hero.fall();
        gameScreen.getChildren().add(floatingIsland.getPane());
        gameScreen.getChildren().add(hero.getPane());
        gameScreen.getChildren().add(redorc.getPane());
        gameScreen.getChildren().add(islandStart.getPane());
        gameScreen.getChildren().add(island.getPane());


    }

    @FXML
    protected void btnStartNewGameClicked() {
        UIAnimationControl.startButtonIllusionAnimation(btnNewGame, 115, 255);
        try {
            initGame();
            homeRoot.getChildren().setAll(gameScreen);
        } catch (IOException e){
            System.out.println("cant start the game");
        }
    }

    @FXML
    protected void btnSavedGameClicked() throws IOException {
            UIAnimationControl.startButtonIllusionAnimation(btnSavedGame, 130, 190);
            AnchorPane savedGameScreen = FXMLLoader.load(getClass().getResource("savedgame.fxml"));
            homeRoot.getChildren().setAll(savedGameScreen);
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

    private void loadIslands() {
        for (int i = 0; i < 3; i++) {
            islands.add(new Island());
        }
        for (Island i:islands) {
            i.getCoordinates().setX(new Random().nextDouble(200, 1000));
            i.getCoordinates().setY(new Random().nextDouble(390, 800));
            i.setHeight(new Random().nextDouble(75, 120));
            i.setWidth(new Random().nextDouble(200, 215));
            i.mountImage();
        }
        for (int i = 0; i < islands.size(); i++) {
            gameScreen.getChildren().add(islands.get(i).getPane());
        }
    }

    private void loadCoins() {

        for(int i = 0; i < 1; i++) {
            CoinSet coinSet = new CoinSet();
            coinSet.setLayoutX(new Random().nextDouble(560, 600));
            coinSet.setLayoutY(new Random().nextDouble(270, 300));

            gameScreen.getChildren().add(coinSet);
        }

    }
}