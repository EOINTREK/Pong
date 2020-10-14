package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import resources.Constants;

public class Ball {
    private static double ballYAxisSpeed;
    private static DoubleProperty ballSize = new SimpleDoubleProperty();
    private final int radius = 10;
    DoubleProperty playFieldWIDTH;
    DoubleProperty playFieldHEIGHT;
    PaddleModel model;
    Timeline ballTimeLine;
    private Circle ball;
    private Rectangle Ballpunkt;
    private double ballspeed;
    private static DoubleProperty ballCenterX = new SimpleDoubleProperty();
    private static DoubleProperty ballCenterY = new SimpleDoubleProperty();
    private DoubleProperty speedX = new SimpleDoubleProperty();
    private DoubleProperty speedY = new SimpleDoubleProperty();

    double xMin;
    double xMax;
    double yMin;
    double yMax;

    public Ball(DoubleProperty WIDTH, DoubleProperty HEIGHT, Timeline ballTimeLine, PaddleModel model) {
        this.playFieldWIDTH = WIDTH;
        this.playFieldHEIGHT = HEIGHT;
        this.model = model;
        this.ballTimeLine = ballTimeLine;
        ballCenterX.setValue(Constants.DEFAULT_PLAY_FIELD_WIDTH/2);
        ballCenterY.setValue(Constants.DEFAULT_PLAY_FIELD_HEIGHT/2);
        ballSize.set(Constants.DEFAULT_BALL_SIZE);
        this.ballTimeLine.setCycleCount(Timeline.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(1/90.0), event -> moveBall());
        this.ballTimeLine.getKeyFrames().add(moveBall);
    }

    public static double getBallSpeedY() {
        return ballYAxisSpeed;
    }

    public static double getBallSize() {
        return ballSize.get();
    }

    public static DoubleProperty getBallSizeProperty() {
        return ballSize;
    }

    public double getBallCenterX() {
        return ballCenterX.get();
    }

    public static DoubleProperty getBallCenterXProperty() {
        return ballCenterX;
    }

    public double getBallCenterY() {
        return ballCenterY.get();
    }

    public static DoubleProperty getBallCenterYProperty() {
        return ballCenterY;
    }

    public void startBall() {
        if (Math.random() < 0.5) {
            ballCenterX.setValue(0.0 + ballSize.get());
            speedX.set(Constants.DEFAULT_BALL_MOVEMENT_SPEED);
        } else {
            ballCenterX.setValue(playFieldWIDTH.get() - ballSize.get());
            speedX.set(-Constants.DEFAULT_BALL_MOVEMENT_SPEED);
        }

        // random height (y) to start from
        ballCenterY.setValue(Math.random() * playFieldHEIGHT.get());

        // random direction to shoot the ball at the start
        speedY.set(Constants.DEFAULT_BALL_MOVEMENT_SPEED * (Math.random() < 0.5 ? 1 : -1));
    }

    public void moveBall() {
        ballCenterX.setValue(ballCenterX.get() + speedX.get());
        ballCenterY.setValue(ballCenterY.get() + speedY.get());
        checkCollision();
    }

    private void checkCollision() {
        xMin = ballCenterX.get() - ballSize.get();
        xMax = ballCenterX.get() + ballSize.get();
        yMin = ballCenterY.get() - ballSize.get();
        yMax = ballCenterY.get() + ballSize.get();

        // hit top or bottom wall
        if (yMin < 0 || yMax > playFieldHEIGHT.get()) {
            speedY.set(speedY.get() * -1);
        }

        // hit left or right wall
        if (xMax < 0 || xMin > playFieldWIDTH.get()) {
            goal(xMin < 0 ? PongModel.playerOne : PongModel.playerTwo);
        }

        // hit on a paddle - left
        if ((speedX.get() < 0 // moving left
                && (xMin) <= (model.leftPaddleX.get() + Constants.DEFAULT_PADDLE_WIDTH)
                && (yMax > model.leftPaddleY.get())
                && (yMin < model.leftPaddleY.get() + model.paddleLength.get()))
            ||
            // hit on a paddle - right
                    (speedX.get() > 0 // moving right
                && (xMax) >= (model.rightPaddleX.get())
                && (yMax > model.rightPaddleY.get())
                && (yMin < model.rightPaddleY.get() + model.paddleLength.get()))) {
            speedX.set(speedX.get() * -1);
            computeBallYAxisSpeed(speedX.get() < 0 ? model.getLeftPaddleYProperty() : model.getRightPaddleYProperty());
            PongModel.updateBallAndPaddleTimeLine();
        }
        //speedY.setValue(computeBallYAxisSpeed
    }

    private void goal(Player playerScored) {
        // hide ball
        ballTimeLine.pause();

        // reset speed
        ballTimeLine.setRate(1.0);
        PongModel.getPaddleTimeline().setRate(1.0);

        // start from either side of the board
        if (playerScored.equals(PongModel.playerOne)) {
            ballCenterX.setValue(0.0 + ballSize.get());
            speedX.set(Constants.DEFAULT_BALL_MOVEMENT_SPEED);
            PongModel.playerOne.points.set(PongModel.playerOne.points.get() + 1);
        } else {
            ballCenterX.setValue(PongModel.getWIDTH() - ballSize.get());
            speedX.set(-Constants.DEFAULT_BALL_MOVEMENT_SPEED);
            PongModel.playerTwo.points.set(PongModel.playerTwo.points.get() + 1);
        }

        // random y
        ballCenterY.setValue(Math.random() * PongModel.getHEIGHT());

        // random direction
        speedY.set(Constants.DEFAULT_BALL_MOVEMENT_SPEED * (Math.random() < 0.5 ? 1 : -1));

        // short break
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // move again
        ballTimeLine.play();
    }

    public Rectangle getBallpunkt() {
        return Ballpunkt;
    }

    public Circle getBall() {
        return ball;
    }

    public double getCenterX() {
        return ball.getCenterX();
    }

    public double getCenterY() {
        return ball.getCenterY();
    }

    public void computeBallYAxisSpeed(DoubleProperty player) {
        double relativeIntersectY = (player.get()  + model.getPaddleLength() - getBallCenterY());
        double normalizedRelativeIntersectionY = (relativeIntersectY / (model.getPaddleLength() / 2.0));
        double bounceAngle = normalizedRelativeIntersectionY * 5 * Math.PI / 12;

        speedY.setValue(speedY.get() * -Math.cos(bounceAngle));
    }

    public double getSpeed() {
        return ballspeed;
    }

    public void setSpeed(double speed) {
        this.ballspeed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public double getLayoutX() {
        return ball.getLayoutX();
    }

    public void setLayoutX(double x) {
        ball.setLayoutX(x);
    }

    public double getLayoutY() {
        return ball.getLayoutY();
    }

    public void setLayoutY(double y) {
        ball.setLayoutY(y);
    }

    public void setColor(Paint color) {
        ball.setFill(color);
    }

    public void setVisible(boolean choice) {
        ball.setVisible(choice);
    }

    public void relocate(double x, double y) {
        ball.relocate(x, y);
    }
}
