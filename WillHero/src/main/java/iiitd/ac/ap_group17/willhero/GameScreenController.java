package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import iiitd.ac.ap_group17.willhero.models.*;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameScreenController {

    @FXML
    private ImageView btnSave;

    @FXML
    private ImageView btnPause;

    @FXML
    private ImageView btnHome;

    @FXML
    private Label lblPause;

    public static TableData currentGame;

    private static ArrayList<Island> islands;
    private ArrayList<CoinSet> coins;
    private static ArrayList<Orc> orcs;
    private static ArrayList<Treasure<Weapon>> treasureWeapons;
    private ArrayList<Treasure<Coin>>  treasureCoin;

    static boolean flag;
    static MenuAnimationController menuAnimationController = new MenuAnimationController();

    public static Hero hero;

    @FXML
    public static AnchorPane gameScreen;

    private FloatingIsland floatingIsland;
    private static FallingPlatform fallingPlatform;
    private ColliderThread thread;

    private Label txtHeroPosition;

    public void initialize() {
        hero = new Hero("/assets/helmet/player.png");
        floatingIsland = new FloatingIsland();
        fallingPlatform = new FallingPlatform();
        txtHeroPosition = new Label("0");
        flag = true;
        islands = new ArrayList<>();
        orcs = new ArrayList<>();
        coins = new ArrayList<>();
        treasureCoin = new ArrayList<>();
        treasureWeapons = new ArrayList<>();
    }


    public void initGameElements(TableData data, AnchorPane scene) {
        currentGame = data;
        HomeApplication.colliderThread.run();
        hero = data.getHero();
        islands = data.getIslands();
        orcs = data.getOrcs();
        coins = data.getCoins();
        treasureWeapons = data.getTreasureWeapons();
        treasureCoin = data.getTreasureCoin();
        fallingPlatform = data.getFallingPlatform();
        gameScreen = scene;
        txtHeroPosition.setText("0");
        gameScreen.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    try {
                        if (!AnimationController.isPaused){
                            moveIslands();
                            moveOrcs();
                            moveTreasures();
                            updatePositionLabel(1);
                            if (hero.getWeapons().size() != 0)  {
                                hero.useWeapon();
                            }
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        txtHeroPosition.setLayoutX(493.0);
        txtHeroPosition.setLayoutY(30);
        txtHeroPosition.setTextFill(Color.WHITE);
        txtHeroPosition.setFont(new Font("Franklin Gothic Book", 50));
        gameScreen.getChildren().add(txtHeroPosition);

    }


    @FXML
    protected  void btnSaveClicked() throws IOException {
        UIAnimationControl.startButtonIllusionAnimation(btnSave, 40, 40);
        //currentGame.update(Game.hero, HomeController.islands, HomeController.coins);
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
            btnPause.setImage(new Image(Objects.requireNonNull(this.getClass().getResource("/assets/controls/b_9.png")).toString()));
            AnimationController.resumeAll();
        }
    }

    public void moveIslands() {
        islands.forEach(Island::move);
        for (Island i: islands) {
            if (i.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                i.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() + 100);
            }
        }

    }

    public void moveOrcs() {
        orcs.forEach(Orc::move);
        for (Orc orc: orcs) {
            if (orc.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                orc.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }
    }

    public void moveTreasures() {
        treasureWeapons.forEach(Treasure::move);
        for (Treasure treasure: treasureWeapons){
            if (treasure.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                treasure.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }
    }

    public static void checkCollisions(){

        boolean[] collisions = new boolean[10];

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
                    orc = o;
                    collisions[3] = true;
                }
            }
        }

        // for orcs
        for(Orc o: orcs) {
            if (hero.onCollisionWith(o)) {
                orc = o;
                collisions[1] = true;
            }

            if (hero.getWeapon() != null) {
                if (hero.getWeapon().onCollisionWith(o)) {
                    o.onCollision(hero.getWeapon());
                }
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
        } else if (hero.onCollisionWith(currentGame.getFallingPlatform())) {
            hero.onCollision(currentGame.getFallingPlatform());
        }

        if (collisions[1]) {
            hero.onCollision(orc);
        }

        if (collisions[2]) {
            hero.onCollision(treasureWeapon);
        }

        if (collisions[3]) {
            assert orc != null;
            orc.onCollision(hero.getWeapon());
        }
    }


    public void loadRedOrc() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Hello Pedro");

                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

    public void loadGreenOrc() {

    }

    public void loadBoss() {

    }

    public void loadTNT() {

    }

    public void loadTreasureChest() {

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

