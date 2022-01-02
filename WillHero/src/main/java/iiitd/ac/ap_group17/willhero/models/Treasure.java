package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class Treasure <T extends Collectable> extends RigidiBody {
    private ArrayList<T> collectables = new ArrayList<>();
    private String path2;

    public Treasure(String path, String path2) {
        super(path);
        this.path2 = path2;
    }

    public ArrayList<T> getCollectables() {
        return collectables;
    }

    public int getCollectableQuantity() {
        return this.collectables.size();
    }

    public void openAnimation() {

        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(0.3), (actionEvent -> {
            this.getImageView().setImage(new Image(Objects.requireNonNull(getClass().getResource(this.path2)).toString()));

        })));

        AnimationController.timelines.add(timeline);
        timeline.play();
    }

    @Override
    public void move() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), actionEvent -> {
            this.getPane().setLayoutX(this.getPane().getLayoutX() - 100);
        }));
        timeline.play();
        AnimationController.timelines.add(timeline);
    }
}
