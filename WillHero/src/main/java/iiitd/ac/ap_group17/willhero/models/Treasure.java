package iiitd.ac.ap_group17.willhero.models;

import java.util.ArrayList;

public class Treasure extends RigidiBody implements Cloneable{
    private ArrayList<Collectable> collectables;

    public Treasure(ArrayList<Collectable> collectables,String path) {

        super(path);
        this.collectables = collectables;
    }

    public ArrayList<Collectable> getCollectables() {
        return collectables;
    }
}
