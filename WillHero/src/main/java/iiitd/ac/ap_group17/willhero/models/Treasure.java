package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.AnimationController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

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
        ImageView img_1 = new ImageView(new Image(getClass().getResource(getPath()).toString()));
        ImageView img_2 = new ImageView(new Image(getClass().getResource(this.path2).toString()));

        Timeline timeline = new Timeline();
        timeline.setAutoReverse(false);

        this.getPane().getChildren().add(img_1);
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (actionEvent -> {
            this.getPane().getChildren().remove(img_1);
            this.getPane().getChildren().add(img_2);
        })));

        AnimationController.timelines.add(timeline);
        timeline.play();
    }
}
