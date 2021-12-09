package iiitd.ac.ap_group17.willhero.models;

public class Orc extends Character implements Cloneable{
    private String color;

    public Orc(String color,String path) {

        super(path);
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void move() {
        super.move();
    }

}
