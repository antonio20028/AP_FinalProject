package iiitd.ac.ap_group17.willhero.data;

import iiitd.ac.ap_group17.willhero.GameScreenController;
import iiitd.ac.ap_group17.willhero.HomeApplication;
import iiitd.ac.ap_group17.willhero.MenuAnimationController;
import iiitd.ac.ap_group17.willhero.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TableData implements Serializable {

    @Serial
    private static final long serialVersionUID = 40L;

    private ArrayList<Island> islands;
    private ArrayList<CoinSet> coins;
    private ArrayList<Orc> orcs;
    private ArrayList<Treasure<Weapon>> treasureWeapons;
    private ArrayList<Treasure<Coin>>  treasureCoin;

    static boolean flag;
    static MenuAnimationController menuAnimationController = new MenuAnimationController();
    private Hero hero;
    private FallingPlatform fallingPlatform;

    private String name;
    private String date;

    public TableData() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm");
        date = dateTime.format(formatter);
        name = "Game saved on " + " " + date;
    }

    {
        hero = new Hero("/assets/helmet/player.png");
        fallingPlatform = new FallingPlatform();
        flag = true;
        islands = new ArrayList<>();
        orcs = new ArrayList<>();
        coins = new ArrayList<>();
        treasureCoin = new ArrayList<>();
        treasureWeapons = new ArrayList<>();
    }

    public TableData(AnchorPane screen) {

        Island islandStart = new Island();
        Island island = new Island();
        Island island1 = new Island();
        Island island2 = new Island();
        Island island3 = new Island();
        FloatingIsland floatingIsland = new FloatingIsland();

        RedOrc orc = new RedOrc();
        TreasureWeapon treasureWeapon = new TreasureWeapon();

        fallingPlatform.getCoordinates().setX(50);
        fallingPlatform.getCoordinates().setY(390);
        fallingPlatform.setWidth(screen.getWidth());
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

        screen.getChildren().add(hero.getPane());
        islands.forEach(is -> screen.getChildren().add(is.getPane()));
        orcs.forEach(or -> screen.getChildren().add(or.getPane()));
        screen.getChildren().add(fallingPlatform.getPane());
        treasureWeapons.forEach(tr -> screen.getChildren().add(tr.getPane()));
    }

    //this method is to load Game
    public void read() throws IOException, ClassNotFoundException {
        HomeApplication.deserialize();
    }


    //this method is to save game.
    public void write() throws IOException {
        HomeApplication.getDatabase().getSavedGames().add(this);
        HomeApplication.serialize();
    }

    public void update(Hero hero, ArrayList<Island> islands, ArrayList<CoinSet> coins,
    FallingPlatform fallingPlatform, ArrayList<Treasure<Weapon>> treasureWeapons, ArrayList<Treasure<Coin>> treasureCoin, ArrayList<Orc> orcs) {
        this.islands = islands;
        this.coins = coins;
        this.hero = hero;
        this.fallingPlatform = fallingPlatform;
        this.treasureCoin = treasureCoin;
        this.treasureWeapons = treasureWeapons;
        this.orcs = orcs;

    }

    public ArrayList<CoinSet> getCoins() {
        return coins;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public Hero getHero() {
        return hero;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Orc> getOrcs() {
        return orcs;
    }

    public ArrayList<Treasure<Weapon>> getTreasureWeapons() {
        return treasureWeapons;
    }

    public ArrayList<Treasure<Coin>> getTreasureCoin() {
        return treasureCoin;
    }

    public static boolean isFlag() {
        return flag;
    }

    public static MenuAnimationController getMenuAnimationController() {
        return menuAnimationController;
    }

    public FallingPlatform getFallingPlatform() {
        return fallingPlatform;
    }

    public String getDate() {
        return date;
    }
}
