package iiitd.ac.ap_group17.willhero.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {

    @Serial
    private static final long serialVersionUID = 40L;

    private final ArrayList<TableData> savedGames;

    public Database(){
        savedGames = new ArrayList<>();
    }

    public ArrayList<TableData> getSavedGames() {
        return savedGames;
    }


}
