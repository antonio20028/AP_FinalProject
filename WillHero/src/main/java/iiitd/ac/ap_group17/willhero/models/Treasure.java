package iiitd.ac.ap_group17.willhero.models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Treasure <T extends Collectable> extends RigidiBody {
    private ArrayList<T> collectables;

    public Treasure(String path) {
        super(path);
    }

    public ArrayList<T> getCollectables() {
        return collectables;
    }

    public int getCollectableQuantity() {
        return this.collectables.size();
    }

    public void openAnimation() {
        ImageView img_1 = new ImageView(new Image(getClass().getResource(getPath()).toString()));
        ImageView img_2 = new ImageView(new Image(getClass().getResource("/assets/chest/2.png").toString()));
        ImageView img_3 = new ImageView(new Image(getClass().getResource("/assets/chest/3.png").toString()));
        ImageView img_4 = new ImageView(new Image(getClass().getResource("/assets/chest/4.png").toString()));
        ImageView img_5 = new ImageView(new Image(getClass().getResource("/assets/chest/5.png").toString()));
        ImageView img_6 = new ImageView(new Image(getClass().getResource("/assets/chest/6.png").toString()));
        
        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);

        this.getPane().getChildren().add(img_1);
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (actionEvent -> {
            this.getPane().getChildren().remove(img_1);
            this.getPane().getChildren().add(img_2);
        })));


        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (actionEvent -> {
            this.getPane().getChildren().remove(img_2);
            this.getPane().getChildren().add(img_3);
        })));


        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (actionEvent -> {
            this.getPane().getChildren().remove(img_3);
            this.getPane().getChildren().add(img_4);
        })));

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(500), (actionEvent -> {
            this.getPane().getChildren().remove(img_4);
            this.getPane().getChildren().add(img_5);
        })));

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(600), (actionEvent -> {
            this.getPane().getChildren().remove(img_5);
            this.getPane().getChildren().add(img_6);
        })));

        timeline.play();
    }
}
