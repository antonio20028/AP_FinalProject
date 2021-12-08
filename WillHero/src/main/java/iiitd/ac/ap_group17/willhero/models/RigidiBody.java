package iiitd.ac.ap_group17.willhero.models;

abstract class RigidiBody {
    private Vector2D coordinates;
    private double height;
    private double width;
    private double velocity;
    private String path;

    public RigidiBody(String path) {
        this.path = path;
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
