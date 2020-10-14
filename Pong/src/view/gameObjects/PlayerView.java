package view.gameObjects;

import control.PlayerController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.PongModel;

import java.io.File;

public class PlayerView {

    PongModel model;
    PlayerController controller;

    private final BorderPane view;

    private final int WIDTH = 30;
    private final int HEIGHT = 300;
    private double speed;
    private static Pane playerOne;
    private static Pane playerTwo;
    private static Image basicImage;
    private static ImageView basic;
    private boolean light;

    //Images for lights effect
    //c0f0cf green color. f0c0cc pink color
    private Image rightPaddleLightImage = new Image(new File("src/images/RightPaddleLight.png").toURI().toString());
    private ImageView rightPaddleLight = new ImageView(rightPaddleLightImage);
    private Pane rightLight = new Pane(rightPaddleLight);
    private Image leftPaddleLightImage = new Image(new File("src/images/LeftPaddleLight.png").toURI().toString());
    private ImageView leftPaddleLight = new ImageView(leftPaddleLightImage);
    private Pane leftLight = new Pane(leftPaddleLight);

    public PlayerView(PongModel model, PlayerController controller) {

        this.model = model;
        this.controller = controller;

        view = new BorderPane();
        view.setStyle("-fx-background-color: CADETBLUE;");


    }

    public static void createPlayerView() {
        PlayerController.setPlayerImage(1);
        PlayerController.setPlayerImage(2);
        PlayerController.setUpLight();

    }

    public static Image getBasicImage() {
        return basicImage;
    }

    public static void setBasic(ImageView imageView) {
        basic = imageView;
    }


    public static ImageView getBasic() {
        return basic;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }



    public Pane getrightLight() {
        return rightLight;
    }

    public Pane getleftLight() {
        return leftLight;
    }



    //setUpLight adjust the location of the light object;
    public void setUpLight() {
        rightLight.relocate(322, 0);
        rightLight.setVisible(false);
        leftLight.setVisible(false);
    }


    public boolean light() {
        return light;
    }

    //lightOn turn on the light when a player scores
    public double lightOn(int player) {
        light = true;
        if (player == 1)
            leftLight.setVisible(true);
        else
            rightLight.setVisible(true);
        return System.currentTimeMillis();
    }

    //lightOff turns off the light
    public void lightOff(int player) {
        light = false;
        if (player == 1)
            leftLight.setVisible(false);
        else
            rightLight.setVisible(false);
    }
}
