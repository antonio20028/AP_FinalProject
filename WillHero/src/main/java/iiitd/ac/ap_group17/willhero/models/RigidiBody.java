package iiitd.ac.ap_group17.willhero.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public abstract class RigidiBody implements Serializable {
    private Vector2D coordinates = new Vector2D(0, 0);
    private double height;
    private double width;
    private double velocity;
    private String path;
    private final transient Pane pane;

    public RigidiBody(String path) {
        this.path = path;
        this.pane = new Pane();
    }

    public void mountImage() {
        ImageView imageView = new ImageView( new Image(getClass().getResource(path).toString()));
        imageView.setFitWidth(this.width);
        imageView.setFitHeight(this.height);
        imageView.setLayoutX(coordinates.getX());
        imageView.setLayoutY(coordinates.getY());
        this.pane.getChildren().add(imageView);
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

    public void onCollision(RigidiBody other){

    }

    public void destroy(){

    }

    public void move(){

    }

}
