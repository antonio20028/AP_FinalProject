package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import iiitd.ac.ap_group17.willhero.GameScreenController;
import iiitd.ac.ap_group17.willhero.HomeApplication;
import iiitd.ac.ap_group17.willhero.HomeController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;

import static iiitd.ac.ap_group17.willhero.HomeController.gameScreen;

public class Hero extends Character implements Jumpable, Shooter{
    private int position;

    private final ArrayList<Weapon> weapons  = new ArrayList<>();
    private Weapon weapon;
    ArrayList<Coin> coins = new ArrayList<>();

    public Hero(String path) {
        super(path);
        this.position = 0;

    }

    public int getPosition() {
        return position;
    }

    public void increasePosition(int v) {
        this.position = this.position + v;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    private void startJump() {
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(this.getPane().translateYProperty(), -(this.getHeight() + 15))));
        AnimationController.timelines.add(timeline);
        timeline.play();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void jump() {
        startJump();
    }

    private int i = 0;
    @Override
    public void useWeapon() {

       try {
           GameScreenController.gameScreen.getChildren().add(this.getWeapon().getPane());
           if (this.getWeapon() instanceof Rocket rocket) {
               rocket.getPane().setVisible(true);
               Timeline timeline = new Timeline();
               timeline.setCycleCount(1);
               timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(rocket.getPane().translateXProperty(), 500)));
               timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), actionEvent -> {
                   rocket.destroy();
               }));
               AnimationController.timelines.add(timeline);
               timeline.play();
           }
       } catch (IllegalArgumentException e) {
           System.out.println("");
       } finally {
           i++;
       }
    }

    @Override
    public void upgradeWeapon(ArrayList<Weapon> newweapon) {
        this.getWeapons().clear();
        this.getWeapons().addAll(newweapon);
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void set() {
        Weapon currentWeapon = this.getWeapons().get(i);
        currentWeapon.setHeight(50);
        currentWeapon.setWidth(50);
        currentWeapon.getCoordinates().setY(this.getCoordinates().getY());
        currentWeapon.getCoordinates().setX(this.getCoordinates().getX());
        currentWeapon.getPane().setVisible(false);
        currentWeapon.mountImage();
        this.weapon = currentWeapon;
    }

    @Override
    public void onCollision(RigidiBody other) {
        if ((other instanceof Island island)) {
            System.out.println("");
        } else if (other instanceof  Orc orc) {
            //upperbound
            if (this.getPane().getBoundsInParent().getMaxY() > orc.getPane().getBoundsInParent().getMaxY()) {
               if(this.getPane().getBoundsInParent().getMaxY() - orc.getPane().getBoundsInParent().getMinY() >= this.getHeight()) {
                   this.fall();
               }
            }

            //lateral bound
        }else  if(other instanceof Obstacle obstacle){
            fall();
        } else if (other instanceof TreasureWeapon treasure) {
            treasure.openAnimation();
            if (this.getWeapons().size() != 0) {
                this.upgradeWeapon(treasure.getCollectables());
                this.set();
            } else {
                weapons.addAll(treasure.getCollectables());
                this.set();
            }

        }
    }
}
