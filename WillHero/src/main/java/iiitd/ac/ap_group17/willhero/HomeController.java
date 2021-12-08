package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.models.Coin;
import iiitd.ac.ap_group17.willhero.models.Hero;
import iiitd.ac.ap_group17.willhero.models.Island;
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
    static ArrayList<Group> coins = new ArrayList<>();

    private void initGame() throws IOException {
        gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));
        Island islandStart = new Island();

        Hero hero = new Hero("/assets/player.png");

        hero.getCoordinates().setY(327);
        hero.getCoordinates().setX(208.0580462158324);
        hero.setHeight(68);
        hero.setWidth(75);
        islandStart.getCoordinates().setX(200);
        islandStart.getCoordinates().setY(390);
        islandStart.setHeight(100);
        islandStart.setWidth(200);
        hero.startJump();
        //loadIslands();
        hero.mountImage();
        islandStart.mountImage();
        loadCoins();
        gameScreen.getChildren().add(hero.getPane());
        gameScreen.getChildren().add(islandStart.getPane());
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
        optionMenuScreen.setVisible(true);

    }

    @FXML
    protected void btnExitClicked() {
        UIAnimationControl.startButtonIllusionAnimation(btnExit, 130, 190);
        System.exit(0);
    }


    @FXML
    protected void btnGoHomeClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnGoHome, 50, 50);
        optionMenuScreen.setVisible(false);
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
        Group coinsGroup = new Group();

        Coin coin = new Coin();
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();

        coin.setHeight(55);
        coin.setWidth(55);
        coin1.setHeight(55);
        coin1.setWidth(55);
        coin2.setHeight(55);
        coin2.setWidth(55);

        coin1.getCoordinates().setX(75);
        coin2.getCoordinates().setX(150);
        coinsGroup.getChildren().add(coin.getPane());
        coinsGroup.getChildren().add(coin1.getPane());
        coinsGroup.getChildren().add(coin2.getPane());

        coin.mountImage(); coin1.mountImage(); coin2.mountImage();

        coinsGroup.setLayoutX(460);
        coinsGroup.setLayoutY(200);

        gameScreen.getChildren().add(coinsGroup);

       /*
        double positionX = new Random().nextDouble(215, 700);
        double positionY = new Random().nextDouble(410, 700);
        for (Coin c:coins) {
            c.getCoordinates().setX(positionX + 15);
            c.getCoordinates().setY(positionY);
            c.setHeight(55);
            c.setWidth(55);
            c.mountImage();
            System.out.println(c.getCoordinates().getX() + ", " +c.getCoordinates().getY());
            gameScreen.getChildren().add(c.getPane());
        }*/
    }
}