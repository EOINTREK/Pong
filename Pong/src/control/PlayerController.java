package control;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.PongModel;
import view.gameObjects.PlayerView;

import java.io.File;

public class PlayerController {
    static PongModel player;

    public PlayerController(PongModel player) {
        player = player;
    }

    //setUpPlayer creates model.Player object and set it's properties
    public static void setUpPlayer(double x, double y, int num) {
        player = new PongModel();
        player.getPlayer().relocate(x, y);
        PlayerView.setBasic(new ImageView(PlayerView.getBasicImage()));
        PlayerView.getBasic().setFitWidth(PongModel.getWIDTH());
        PlayerView.getBasic().setFitHeight(PongModel.getHEIGHT());
    }

    //setPlayerImage adds a correct image according to the side of the player
    public static void setPlayerImage(int num) {
        if (num == 1)
            player.setBasicImage(new Image(new File("src/images/LeftPaddle.png").toURI().toString()));
        else
            player.setBasicImage(new Image(new File("src/images/RightPaddle.png").toURI().toString()));
    }

    //setUpLight adjust the location of the light object;
    public static void setUpLight() {
        PongModel.getRightLight().relocate(322, 0);
        PongModel.getRightLight().setVisible(false);
        PongModel.getLeftLight().setVisible(false);
    }

    public static double lightOn(int player) {
        PongModel.setLight(true);
        if (player == 1)
            PongModel.getLeftLight().setVisible(true);
        else
            PongModel.getRightLight().setVisible(true);
        return System.currentTimeMillis();
    }

    //lightOff turns off the light
    public static void lightOff(int player) {
        PongModel.setLight(false);
        if (player == 1)
            PongModel.getLeftLight().setVisible(false);
        else
            PongModel.getRightLight().setVisible(false);
    }


}
