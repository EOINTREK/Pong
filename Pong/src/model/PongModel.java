package model;

import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import resources.Constants;

import java.io.File;

public class PongModel {

    private static final int WIDTH = Constants.DEFAULT_PLAY_FIELD_WIDTH;
    private static final int HEIGHT = Constants.DEFAULT_PLAY_FIELD_HEIGHT;
    static Player playerOne;
    static Player playerTwo;
    static DoubleProperty playFieldWIDTH = new SimpleDoubleProperty(Constants.DEFAULT_PLAY_FIELD_WIDTH);
    DoubleProperty playFieldHEIGHT = new SimpleDoubleProperty(Constants.DEFAULT_PLAY_FIELD_HEIGHT);
    private static boolean light;
    private static CollisionBox player;
    private static Image basicImage = new Image(new File("src/images/Background.png").toURI().toString());
    //Images for lights effect
    //c0f0cf green color. f0c0cc pink color
    private static Image rightPaddleLightImage = new Image(new File("src/images/RightPaddleLight.png").toURI().toString());
    private static ImageView rightPaddleLight = new ImageView(rightPaddleLightImage);
    private static Image leftPaddleLightImage = new Image(new File("src/images/LeftPaddleLight.png").toURI().toString());
    private static ImageView leftPaddleLight = new ImageView(leftPaddleLightImage);
    private static Pane rightLight = new Pane(rightPaddleLight);
    private static Pane leftLight = new Pane(leftPaddleLight);
    private static Timeline paddleTimeline = new Timeline();
    private static Player playerScored;
    private static Timeline ballTimeLine = new Timeline();
    CollisionBox[] collisionBoxes = new CollisionBox[3];
    Ball ball;
    PaddleModel paddleModel;
    private double speed;
    private ImageView basic;

    public PongModel() {
        playerOne = new Player("one");
        playerTwo = new Player("two");

        this.paddleModel = new PaddleModel(playFieldWIDTH, playFieldHEIGHT, paddleTimeline);
        this.ball = new Ball(playFieldWIDTH, playFieldHEIGHT, ballTimeLine, paddleModel);

        startGame();
    }

    public static Timeline getPaddleTimeline() {
        return paddleTimeline;
    }

    public static void setPaddleTimeline(Timeline tl) {
        paddleTimeline = tl;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static Pane getRightLight() {
        return rightLight;
    }

    public static Pane getLeftLight() {
        return leftLight;
    }

    public static boolean getLight() {
        return light;
    }

    public static void setLight(boolean bool) {
        light = bool;
    }

    public static void relocate(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    public static void updateBallAndPaddleTimeLine() {
        ballTimeLine.setRate(ballTimeLine.getRate() * Constants.BALL_ACCELERATION);
        paddleTimeline.setRate(paddleTimeline.getRate() * Constants.BALL_ACCELERATION);
    }

    public static Player getPlayerOne() {
        return playerOne;
    }

    public static Player getPlayerTwo() {
        return playerTwo;
    }

    public static double getPlayFieldWIDTH() {
        return playFieldWIDTH.get();
    }

    public DoubleProperty playFieldWIDTHProperty() {
        return playFieldWIDTH;
    }

    public double getPlayFieldHEIGHT() {
        return playFieldHEIGHT.get();
    }

    public DoubleProperty playFieldHEIGHTProperty() {
        return playFieldHEIGHT;
    }


    public Image getBasicImage() {
        return basicImage;
    }

    public static void setBasicImage(Image basicImage) {
        PongModel.basicImage = basicImage;
    }

    public void startGame() {
        playerOne.points.set(0);
        playerTwo.points.set(0);

        ball.startBall();
        ballTimeLine.play();
    }

    public PaddleModel getPaddleModel() {
        return paddleModel;
    }

    public Timeline getBallTimeLine() {
        return ballTimeLine;
    }

    public static void setBallTimeLine(Timeline btl) {
        ballTimeLine = btl;
    }

    public CollisionBox getPlayer() {
        return player;
    }

    public int getWidth() {
        return WIDTH;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getLayoutX() {
        return player.getLayoutX();
    }

    public static void setLayoutX(double x) {
        player.setLayoutX(x);
    }

    public double getLayoutY() {
        return player.getLayoutY();
    }

    public static void setLayoutY(double y) {
        player.setLayoutY(y);
    }

}
