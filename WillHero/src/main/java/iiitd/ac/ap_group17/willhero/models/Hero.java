package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import iiitd.ac.ap_group17.willhero.HomeApplication;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;

import static iiitd.ac.ap_group17.willhero.HomeController.gameScreen;
import static iiitd.ac.ap_group17.willhero.HomeController.hero;

public class Hero extends Character implements Jumpable{
    private int position;


    ArrayList<Weapon> weapons  = new ArrayList<>();
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

    public void useWeapon(Weapon weapon) {
        if (weapon instanceof Rocket rocket) {
            rocket.getPane().setVisible(true);
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(rocket.getPane().translateXProperty(), 500)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(rocket.getPane().visibleProperty(), false)));
            AnimationController.timelines.add(timeline);
            timeline.play();
        }
    }

    @Override
    public void onCollision(RigidiBody other) {
        if ((other instanceof Island island)) {
            System.out.println("");
        } else if (other instanceof  Orc orc) {
            //upperbound
            if (this.getPane().getBoundsInParent().getMaxY() > orc.getPane().getBoundsInParent().getMaxY()) {
               if(this.getPane().getBoundsInParent().getMaxY() - orc.getPane().getBoundsInParent().getMinY() >= this.getHeight()) {
                   //if(orc.getLife()==0){
                       this.fall();
                       gameOver();
                   //}
               }
            }
            System.out.println("Orc:" + orc.getPane().getBoundsInParent());
            System.out.println("Hero:" + this.getPane().getBoundsInParent());


            //lateral bound
        }else  if(other instanceof Obstacle obstacle){
            fall();
            gameOver();
        } else if (other instanceof Treasure<?> treasure) {
            treasure.openAnimation();
            for (Collectable weapon: treasure.getCollectables()) {
                weapons.add((Weapon) weapon);
            }
//            for(Collectable coin : treasure.getCollectables()){
//                coins.add((Coin) coin);
//            }
        }
    }

    public void gameOver(){
        Text t = new Text("GAME OVER");
        t.setX(200);
        t.setY(200);
        t.setFill(Color.BEIGE);
        AnimationController.stopAll();
        gameScreen.getChildren().add(t);
    }
}
