package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class MediumOrc extends Orc implements Jumpable{
        public MediumOrc() {
            super("GREEN","/assets/orcs/OrcBoss");
            this.setWidth(70);
            this.setHeight(80);
        }

    public MediumOrc(String color, String path) {
        super(color, path);
    }

    @Override
        public void move() {
            super.move();
        }

    @Override
    public void jump() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.setAutoReverse(true);
        time.getKeyFrames().add(new KeyFrame(Duration.seconds(0.7),
                new KeyValue(this.getPane().translateYProperty(),
                        -(this.getHeight()+10))));
        AnimationController.timelines.add(time);
        time.play();
    }
}
