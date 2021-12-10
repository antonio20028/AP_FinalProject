package iiitd.ac.ap_group17.willhero.models;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class RedOrc extends Orc implements Jumpable{

    public RedOrc() {
        super("RED","/assets/orcs/RedOrc1.png");
        this.setWidth(70);
        this.setHeight(72);
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
                        -(this.getHeight()+35))));
        time.play();
    }

}
