package iiitd.ac.ap_group17.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MenuAnimationController {
    private final Timeline optionMenuTimeline = new Timeline();
    private AnchorPane menu;
    private int flag = 0;

    public MenuAnimationController() {
        optionMenuTimeline.setAutoReverse(false);
        optionMenuTimeline.setCycleCount(1);
    }
    public void setMenu(AnchorPane anchorPane) {
        this.menu = anchorPane;
    }

    public void play() {

        optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), (actionEvent)->{
            menu.setOpacity(0.3);
            menu.setPrefHeight(50);
            menu.setPrefWidth(50);
            menu.setVisible(true);
        }));

        optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), (actionEvent -> {
            menu.setOpacity(0.5);
            menu.setPrefHeight(100);
            menu.setPrefWidth(100);
        })));

        optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), (actionEvent -> {
            menu.setOpacity(0.8);
            menu.setPrefHeight(150);
            menu.setPrefWidth(150);
        })));

        optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(400), (actionEvent -> {
            menu.setOpacity(1);
            menu.setPrefHeight(210);
            menu.setPrefWidth(210);
        })));

        optionMenuTimeline.play();
        flag++;
    }

    public int getFlag() {
        return flag;
    }

    public void reverse() {
            optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), (actionEvent)->{
                menu.setOpacity(1);
                menu.setPrefHeight(210);
                menu.setPrefWidth(210);
            }));

            optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), (actionEvent -> {
                menu.setOpacity(0.8);
                menu.setPrefHeight(150);
                menu.setPrefWidth(150);
            })));

            optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), (actionEvent -> {
                menu.setOpacity(0.5);
                menu.setPrefHeight(100);
                menu.setPrefWidth(100);
            })));

            optionMenuTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(400), (actionEvent -> {
                menu.setOpacity(0);
                menu.setPrefHeight(0);
                menu.setPrefWidth(0);
                menu.setVisible(false);
            })));
            optionMenuTimeline.play();
    }
}
