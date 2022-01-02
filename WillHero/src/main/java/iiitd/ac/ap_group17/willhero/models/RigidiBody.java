package iiitd.ac.ap_group17.willhero.models;

import iiitd.ac.ap_group17.willhero.GameScreenController;
import iiitd.ac.ap_group17.willhero.HomeController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public abstract class RigidiBody implements Serializable {
    private Vector2D coordinates = new Vector2D(0, 0);
    private double height;
    private double width;
    private double velocity;
    private String path;
    private final transient Pane pane;
    private transient ImageView imageView;

    public RigidiBody(String path) {
        this.path = path;
        this.pane = new Pane();
    }

    public void mountImage() {
        this.imageView = new ImageView( new Image(Objects.requireNonNull(getClass().getResource(path)).toString()));
        imageView.setFitWidth(this.width);
        imageView.setFitHeight(this.height);
        pane.setLayoutX(coordinates.getX());
        pane.setLayoutY(coordinates.getY());
        this.pane.getChildren().add(imageView);
    }


    public ImageView getImageView() {
        return imageView;
    }


    public Pane getPane() {
        return pane;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public Vector2D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector2D coordinates) {
        this.coordinates = coordinates;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

//    public void checkCollision(){
//        if(h.getCoordinates().intersects(o)){
//
//        }
//    }
    public void onCollision(RigidiBody other){

    }

    public boolean onCollisionWith(RigidiBody other) {
        return this.getPane().getBoundsInParent().intersects(other.getPane().getBoundsInParent());
    }

    public void destroy(){
        this.getPane().setVisible(false);
        this.getPane().setLayoutX(1000);
        this.getPane().setLayoutY(-100);
        //GameScreenController.gameScreen.getChildren().removeIf(next -> next.getId().equals(this.getPane().getId()));

    }

    public void move(){

    }

}
