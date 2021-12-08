package iiitd.ac.ap_group17.willhero.models;

public class Character extends RigidiBody {
    private int life;

    public Character(String path) {
        super(path);
        this.life = 0;
    }

    public int getLife() {
        return life;
    }

    public void reduceLife(int value){

    }

    public void die(){

    }
}
