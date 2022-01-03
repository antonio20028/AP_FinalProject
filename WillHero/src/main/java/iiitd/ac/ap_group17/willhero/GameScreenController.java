package iiitd.ac.ap_group17.willhero;

import iiitd.ac.ap_group17.willhero.data.TableData;
import iiitd.ac.ap_group17.willhero.models.*;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import javax.management.timer.Timer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.TimerTask;

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
    private static ArrayList<CoinSet> coins;
    private static ArrayList<Orc> orcs;
    private static ArrayList<Treasure<Weapon>> treasureWeapons;
    private static ArrayList<Treasure<Coin>>  treasureCoins;
    private static ArrayList<Obstacle> obstacles;

    public static Hero hero;

    @FXML
    public static AnchorPane gameScreen;


    private static FallingPlatform fallingPlatform;

    private Label txtHeroPosition;

    public void initialize() {
        hero = new Hero("/assets/helmet/player.png");
        fallingPlatform = new FallingPlatform();
        txtHeroPosition = new Label(String.valueOf(hero.getPosition()));
        islands = new ArrayList<>();
        orcs = new ArrayList<>();
        coins = new ArrayList<>();
        treasureCoins = new ArrayList<>();
        treasureWeapons = new ArrayList<>();
    }


    public void initGameElements(TableData data, AnchorPane scene) {
        currentGame = data;
        HomeApplication.colliderThread.run();
        loadGreenOrc();
        loadRedOrc();
        loadTNT();
        loadTreasureChest();
        hero = data.getHero();
        islands = data.getIslands();
        orcs = data.getOrcs();
        coins = data.getCoins();
        treasureWeapons = data.getTreasureWeapons();
        treasureCoins = data.getTreasureCoin();
        fallingPlatform = data.getFallingPlatform();
        obstacles = data.getObstacles();
        gameScreen = scene;
        gameScreen.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    try {
                        if (!AnimationController.isPaused){
                            moveIslands();
                            moveOrcs();
                            moveTreasures();
                            moveObstacles();
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
        currentGame.update(hero, islands, coins, fallingPlatform, treasureWeapons, treasureCoins, orcs, obstacles);
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
        treasureCoins.forEach(Treasure::move);

        for (Treasure treasure: treasureWeapons){
            if (treasure.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                treasure.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }

        for (Treasure treasure: treasureCoins){
            if (treasure.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                treasure.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }
    }


    public void moveObstacles() {
        obstacles.forEach(Obstacle::move);
        for (Obstacle ob: obstacles){
            if (ob.getPane().getLayoutX() == gameScreen.getBoundsInParent().getMinX()) {
                ob.getPane().setLayoutX(gameScreen.getBoundsInParent().getMaxX() - 100);
            }
        }

    }

    public static void checkCollisions(){

        boolean[] collisions = new boolean[10];

        Island tmp = null;
        Orc orc = null;
        TreasureWeapon treasureWeapon = null;
        TreasureCoin treasureCoin = null;
        Obstacle obstacle = null;
        

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
        for(Treasure<Weapon> treasure: treasureWeapons){
            if (hero.onCollisionWith(treasure)) {
                treasureWeapon = (TreasureWeapon) treasure;
                collisions[2] = true;
            }
        }

        for(Treasure<Coin> treasure: treasureCoins){
            if (hero.onCollisionWith(treasure)) {
                treasureCoin = (TreasureCoin) treasure;
                collisions[4] = true;
            }
        }

        for(Obstacle obs: obstacles) {
            if (hero.onCollisionWith(obs)) {
                collisions[5] = true;
                obstacle = obs;
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

        if (collisions[4]) {
            hero.onCollision(treasureCoin);
        }

        if(collisions[5]) {
            hero.onCollision(obstacle);
        }
    }


    public void loadRedOrc() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Timeline timeline = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(9000), actionEvent -> {
                    RedOrc redOrc = new RedOrc();
                    redOrc.setWidth(80);
                    redOrc.getCoordinates().setY(hero.getCoordinates().getY());
                    redOrc.getCoordinates().setX(islands.get(1 + new Random().nextInt(islands.size())%2).getPane().getLayoutX());
                    orcs.add(redOrc);
                    redOrc.mountImage();
                    redOrc.jump();
                    gameScreen.getChildren().add(redOrc.getPane());
                });
                timeline.getKeyFrames().add(frame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        };
        thread.start();
    }

    public void loadGreenOrc() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Timeline timeline = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(9000), actionEvent -> {
                    GreenOrc redOrc = new GreenOrc();
                    redOrc.setWidth(80);
                    redOrc.getCoordinates().setY(hero.getCoordinates().getY());
                    redOrc.getCoordinates().setX(islands.get(new Random().nextInt(islands.size())).getPane().getLayoutX());
                    orcs.add(redOrc);
                    redOrc.mountImage();
                    redOrc.jump();
                    gameScreen.getChildren().add(redOrc.getPane());
                });
                timeline.getKeyFrames().add(frame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        };
        thread.start();
    }

    public void loadBoss() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Timeline timeline = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(9000), actionEvent -> {
                    RedOrc redOrc = new RedOrc();
                    redOrc.setWidth(80);
                    redOrc.getCoordinates().setY(hero.getCoordinates().getY());
                    redOrc.getCoordinates().setX(islands.get(1 + new Random().nextInt(islands.size())%2).getPane().getLayoutX());
                    orcs.add(redOrc);
                    redOrc.mountImage();
                    redOrc.jump();
                    gameScreen.getChildren().add(redOrc.getPane());
                });
                timeline.getKeyFrames().add(frame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        };
        thread.start();
    }

    public void loadTNT() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Timeline timeline = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(10000), actionEvent -> {
                    TNT tnt = new TNT();
                    tnt.setWidth(80);
                    tnt.setHeight(60);
                    tnt.getCoordinates().setY(335);
                    tnt.getCoordinates().setX(islands.get(new Random().nextInt(islands.size())).getPane().getLayoutX());
                    obstacles.add(tnt);
                    tnt.mountImage();
                    gameScreen.getChildren().add(tnt.getPane());
                });
                timeline.getKeyFrames().add(frame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        };
        thread.start();
    }

    public void loadTreasureChest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Timeline timeline = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(10000), actionEvent -> {
                    TreasureWeapon treas = new TreasureWeapon();
                    treas.setHeight(60);
                    treas.setWidth(100);
                    treas.getCoordinates().setY(335);
                    treas.getCoordinates().setX(islands.get(new Random().nextInt(islands.size())).getPane().getLayoutX());
                    treasureWeapons.add(treas);
                    treas.mountImage();
                    gameScreen.getChildren().add(treas.getPane());
                });
                timeline.getKeyFrames().add(frame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        };
        thread.start();
    }

    private void loadCoins() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                Timeline timeline = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(9000), actionEvent -> {
                    RedOrc redOrc = new RedOrc();
                    redOrc.setWidth(80);
                    redOrc.getCoordinates().setY(hero.getCoordinates().getY());
                    redOrc.getCoordinates().setX(islands.get(1 + new Random().nextInt(islands.size())%2).getPane().getLayoutY());
                    orcs.add(redOrc);
                    redOrc.mountImage();
                    redOrc.jump();
                    gameScreen.getChildren().add(redOrc.getPane());
                });
                timeline.getKeyFrames().add(frame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        };
        thread.start();

    }


    public void updatePositionLabel(int v) {
        hero.increasePosition(v);
        txtHeroPosition.setText(String.valueOf(hero.getPosition()));
    }


}

