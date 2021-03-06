package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Character extends RigidiBody {

    private int life;

    public Character(String path) {
        super(path);
        this.life = 0;
    }

    public int getLife() {
        return life;
    }

    public void reduceLife(int value){
        this.life = this.life - value;
    }

    public void die(){

//        if(life==0){
//            fall();
//        }

    }

    public void fall() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3.5), new KeyValue(this.getPane().translateYProperty(), 1000)));
        timeline.play();
        AnimationController.timelines.add(timeline);
    }
}
