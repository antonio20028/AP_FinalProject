package iiitd.ac.ap_group17.willhero.models;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Boss extends Orc implements Jumpable{

    public Boss() {
        super("GREEN","/assets/orcs/boss.png");
        this.setWidth(100);
        this.setHeight(100);
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
        time.getKeyFrames().add(new KeyFrame(Duration.seconds(0.4),
                new KeyValue(this.getPane().translateYProperty(),
                        -(this.getHeight()+20))));
        time.play();
    }

}
