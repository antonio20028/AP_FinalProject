package iiitd.ac.ap_group17.willhero;

import javafx.animation.Animation;
import javafx.animation.Timeline;

import java.util.ArrayList;

public class AnimationController {

    static public boolean isPaused;
    static public ArrayList<Timeline> timelines = new ArrayList<>();

    static void pauseAll() {
        for(Timeline t : timelines) {
            if (t.getStatus() == Animation.Status.RUNNING) {
                t.pause();
                isPaused = true;
            } else {
                System.out.println("Can't pause stopped or running animation");
            }
        }

    }

    static void resumeAll() {
        for(Timeline t : timelines) {
            if (t.getStatus() == Animation.Status.PAUSED) {
                t.play();
                isPaused = false;
            } else {
                System.out.println("Can't pause resume");
            }
        }
    }

}
