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
import javafx.scene.input.MouseEvent;
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
    static AnchorPane gameScreen;

    //
    //private Hero hero = new Hero("/assets/helmet/player.png");

    static ArrayList<Island> islands = new ArrayList<>();
    static ArrayList<CoinSet> coins = new ArrayList<>();
    static MenuAnimationController menuAnimationController = new MenuAnimationController();

    @FXML
    static Hero hero;

   // static AnchorPane gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));
    private void initGame() throws IOException{
        hero = new Hero("/assets/helmet/player.png");
        gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));
        Island islandStart = new Island();
        Island island = new Island();
        Island island1 = new Island();
        RedOrc redorc = new RedOrc();
        GreenOrc greenOrc = new GreenOrc();

        TreasureWeapon treasureWeapon = new TreasureWeapon();

        TNT tnt = new TNT();

        Boss boss = new Boss();
        FloatingIsland floatingIsland = new FloatingIsland();

        hero.getCoordinates().setY(327);
        hero.getCoordinates().setX(100);
        hero.setHeight(68);
        hero.setWidth(75);

        redorc.getCoordinates().setX(floatingIsland.getCoordinates().getX()-60);
        redorc.getCoordinates().setY(378);
        greenOrc.getCoordinates().setX(floatingIsland.getCoordinates().getX() + 40);
        greenOrc.getCoordinates().setY(378);

        treasureWeapon.getCoordinates().setX(750);
        treasureWeapon.getCoordinates().setY(328);
        tnt.getCoordinates().setX(900);
        tnt.getCoordinates().setY(350);

        boss.getCoordinates().setX(floatingIsland.getCoordinates().getX());
        boss.getCoordinates().setY(100);

        islandStart.getCoordinates().setX(100);
        islandStart.getCoordinates().setY(390);

        island.getCoordinates().setX(floatingIsland.getCoordinates().getX() - 100);
        island.getCoordinates().setY(440);

        island1.getCoordinates().setY(400);
        island1.getCoordinates().setX(floatingIsland.getCoordinates().getX() + 200);

        islandStart.setHeight(100);
        islandStart.setWidth(200);

        island.setHeight(90);
        island.setWidth(200);

        island1.setHeight(100);
        island1.setWidth(250);

        hero.jump();
        redorc.jump();
        boss.jump();
        greenOrc.jump();

        greenOrc.mountImage();
        hero.mountImage();
        redorc.mountImage();
        boss.mountImage();
        islandStart.mountImage();
        island1.mountImage();
        treasureWeapon.mountImage();
        island.mountImage();
        tnt.mountImage();
        loadCoins();
        gameScreen.getChildren().add(floatingIsland.getPane());
        gameScreen.getChildren().add(hero.getPane());
        gameScreen.getChildren().add(redorc.getPane());
        gameScreen.getChildren().add(islandStart.getPane());
        gameScreen.getChildren().add(island.getPane());
        gameScreen.getChildren().add(boss.getPane());
        gameScreen.getChildren().add(treasureWeapon.getPane());
        gameScreen.getChildren().add(island1.getPane());
        gameScreen.getChildren().add(tnt.getPane());
        gameScreen.getChildren().add(greenOrc.getPane());
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
            coinSet.setLayoutX(new Random().nextDouble(400, 500));
            coinSet.setLayoutY(new Random().nextDouble(270, 300));

            gameScreen.getChildren().add(coinSet);
        }

    }
}