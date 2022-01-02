package iiitd.ac.ap_group17.willhero;

import javafx.scene.*;

import iiitd.ac.ap_group17.willhero.data.Database;
import iiitd.ac.ap_group17.willhero.data.TableData;
import iiitd.ac.ap_group17.willhero.models.Rocket;
import iiitd.ac.ap_group17.willhero.models.Weapon;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;


public class HomeApplication extends Application  implements Serializable {


    private static Database database = new Database();
    static AnchorPane savedGameScreen;
    public static ColliderThread colliderThread;
    public MediaPlayer backgroundsond;
    private boolean On;
    //static MediaPlayer player;

    public static Database getDatabase() {
        return database;
    }

    public void playBackgroundMusic() throws URISyntaxException {
        //String str = "/sounds/game-music-7408.mp3";
        //String str = "C:\\Users\\DEEKSHA SINGH\\OneDrive\\Documents\\AP_PROJECT\\AP_FinalProject\\WillHero\\src\\main\\resources\\sounds\\game-music-7408.mp3";
        Media media = new Media(getClass().getResource("/sounds/game-music-7408.mp3").toString());
        backgroundsond = new MediaPlayer(media);
            //backgroundsond = new MediaPlayer(new Media("C:\\Users\\DEEKSHA SINGH\\OneDrive\\Documents\\AP_PROJECT\\AP_FinalProject\\WillHero\\src\\main\\resources\\sounds\\game-music-7408.mp3").toURI().toString());
            backgroundsond.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundsond.setAutoPlay(true);
    }

    public static void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("database.txt"));
            out.writeObject(getDatabase());
        } finally {
            if (out!=null) {
                out.close();
            }
        }
    }

    public static void  deserialize() throws  IOException {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream("database.txt"));
            database = (Database) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof ClassNotFoundException) {
                serialize();
            }
        }
        finally {
            if (in!= null) {
                in.close();
            }
        }
    }

//    private static final String BACKGROUND_SONG = "sounds/game-music-7408.mp3";
//    Media media = new Media(new File(BACKGROUND_SONG));

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException, URISyntaxException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Win Hero");
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(scene);
        playBackgroundMusic();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



    private void PlaySong() {


    }


    @Override
    public void init() throws Exception {
        savedGameScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("savedgame.fxml")));
        GameScreenController.currentGame = new TableData();
        colliderThread = new ColliderThread();
        deserialize();
        super.init();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }
}