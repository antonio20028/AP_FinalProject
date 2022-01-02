package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import iiitd.ac.ap_group17.willhero.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private  ImageView btnNewGame;

    @FXML
    private ImageView btnPlaySound;

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


    static final ArrayList<Island> islands = new ArrayList<>();
    static ArrayList<CoinSet> coins = new ArrayList<>();
    static final ArrayList<Orc> orcs = new ArrayList<>();
    static final ArrayList<Treasure<Weapon>> treasureWeapons = new ArrayList<>();
    static final ArrayList<Treasure<Coin>>  treasureCoins = new ArrayList<>();

    static boolean flag = true;
    static MenuAnimationController menuAnimationController = new MenuAnimationController();

    @FXML
    public static Hero hero;

    String str = "C:\\Users\\DEEKSHA SINGH\\OneDrive\\Documents\\AP_PROJECT\\AP_FinalProject\\WillHero\\src\\main\\resources\\sounds\\game-music-7408.mp3";
    Media media = new Media(new File(str).toURI().toString());

    MediaPlayer mediaPlayer = new MediaPlayer(media);
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void setBtnPlaySound(ActionEvent event){
        UIAnimationControl.startButtonIllusionAnimation(btnPlaySound, 50, 50);
        mediaPlayer.play();
    }

    private Label txtHeroPosition;

    final Island islandStart = new Island();
    final Island island = new Island();
    final Island island1 = new Island();
    final Island island2 = new Island();
    final Island island3 = new Island();

    final FloatingIsland floatingIsland = new FloatingIsland();
    final static FallingPlatform fallingPlatform = new FallingPlatform();

    private void initGame() throws IOException {
        hero = new Hero("/assets/helmet/player.png");
        RedOrc orc = new RedOrc();

        gameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gameScreen.fxml")));
        txtHeroPosition = new Label(String.valueOf(hero.getPosition()));

        txtHeroPosition.setLayoutX(493.0);
        txtHeroPosition.setLayoutY(30);
        txtHeroPosition.setTextFill(Color.WHITE);
        txtHeroPosition.setFont(new Font("Franklin Gothic Book", 50));

        TreasureWeapon treasureWeapon = new TreasureWeapon();

        fallingPlatform.getCoordinates().setX(50);
        fallingPlatform.getCoordinates().setY(390);
        fallingPlatform.setWidth(gameScreen.getWidth());
        fallingPlatform.setHeight(0.05);

        hero.getCoordinates().setY(327);
        hero.getCoordinates().setX(100);
        hero.setHeight(68);
        hero.setWidth(75);

        orc.getCoordinates().setY(327);
        orc.getCoordinates().setX(900);
        hero.setHeight(70);
        orc.setWidth(80);

        islandStart.getCoordinates().setX(100);
        islandStart.getCoordinates().setY(390);

        island.getCoordinates().setX(450);
        island.getCoordinates().setY(390);

        treasureWeapon.getCoordinates().setX(500);
        treasureWeapon.getCoordinates().setY(335);

        treasureWeapon.setHeight(60);
        treasureWeapon.setWidth(100);

        island1.getCoordinates().setX(850);
        island1.getCoordinates().setY(390);

        island2.getCoordinates().setX(1170);
        island2.getCoordinates().setY(390);

        island3.getCoordinates().setX(1600);
        island3.getCoordinates().setY(390);

        island1.setHeight(100);
        island1.setWidth(200);

        island2.setHeight(100);
        island2.setWidth(200);

        island3.setHeight(100);
        island3.setWidth(200);

        islandStart.setHeight(100);
        islandStart.setWidth(200);

        island.setHeight(100);
        island.setWidth(200);

        hero.jump();

        hero.mountImage();
        fallingPlatform.mountImage();

       if (flag) {
           islands.add(islandStart);
           islands.add(island);
           islands.add(island1);
           islands.add(island2);
           islands.add(island3);
           islands.add(floatingIsland);
           islands.forEach(RigidiBody::mountImage);

           orcs.add(orc);
           orcs.forEach(RigidiBody::mountImage);
           orcs.forEach(Orc::jump);

           treasureWeapons.add(treasureWeapon);
           treasureWeapons.forEach(RigidiBody::mountImage);
       }
        gameScreen.getChildren().add(txtHeroPosition);
        gameScreen.getChildren().add(hero.getPane());

        islands.forEach(is -> gameScreen.getChildren().add(is.getPane()));
        orcs.forEach(or -> gameScreen.getChildren().add(or.getPane()));
        gameScreen.getChildren().add(fallingPlatform.getPane());
        treasureWeapons.forEach(tr -> gameScreen.getChildren().add(tr.getPane()));
        HomeApplication.colliderThread.run();
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
                               moveOrcs();
                               moveTreasures();
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
                i.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() + 100);
          }
      }

    }

    private void moveOrcs() {
        orcs.forEach(Orc::move);
        for (Orc orc: orcs) {
            if (orc.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                orc.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }
    }

    private void moveTreasures() {
        treasureWeapons.forEach(Treasure::move);
        for (Treasure treasure: treasureWeapons){
            if (treasure.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                treasure.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }
    }

    public static void checkCollisions(){

        boolean collisions[] = new boolean[10];

        Island tmp = null;
        Orc orc = null;
        TreasureWeapon treasureWeapon = null;

        for (Island i: islands) {
            if (hero.onCollisionWith(i)){
                tmp = i;
                collisions[0] = true;

            }

            for(Orc o: orcs) {
                if (o.onCollisionWith(i)) {
                    o.onCollision(i);
                }
            }
        }

        // for orcs
        for(Orc o: orcs) {
            if (hero.onCollisionWith(o)) {
                orc = o;
                collisions[1] = true;
            }
        }

        //for Treasures
        for(Treasure treasure: treasureWeapons){
            if (hero.onCollisionWith(treasure)) {
                treasureWeapon = (TreasureWeapon) treasure;
                collisions[2] = true;
            }
        }

        if (collisions[0]) {
            hero.onCollision(tmp);
        } else if (hero.onCollisionWith(fallingPlatform)) {
            hero.onCollision(fallingPlatform);
        }

        if (collisions[1]) {
            hero.onCollision(orc);
        }

        if (collisions[2]) {
            hero.onCollision(treasureWeapon);
        }


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