package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;
import resources.Constants;

public class PaddleModel {

    DoubleProperty leftPaddleX = new SimpleDoubleProperty();
    DoubleProperty rightPaddleX = new SimpleDoubleProperty();
    DoubleProperty leftPaddleY = new SimpleDoubleProperty();
    DoubleProperty rightPaddleY = new SimpleDoubleProperty();
    BooleanProperty leftPaddleUp = new SimpleBooleanProperty();
    BooleanProperty leftPaddleDown = new SimpleBooleanProperty();
    BooleanProperty rightPaddleUp = new SimpleBooleanProperty();
    BooleanProperty rightPaddleDown = new SimpleBooleanProperty();

    DoubleProperty paddleLength = new SimpleDoubleProperty();
    DoubleProperty paddleWidth = new SimpleDoubleProperty(Constants.DEFAULT_PADDLE_WIDTH);

    public PaddleModel(DoubleProperty WIDTH, DoubleProperty HEIGHT, Timeline paddleTimeline) {
        leftPaddleX.set(Constants.DEFAULT_X_LOCATION_LEFT_PADDLE);
        rightPaddleX.bind(WIDTH.subtract(Constants.DEFAULT_X_LOCATION_LEFT_PADDLE).subtract(paddleWidth));
        paddleLength.set(Constants.DEFAULT_PADDLE_LENGTH);
        leftPaddleY.set(WIDTH.get() / 2 - paddleLength.get() / 2);
        rightPaddleY.set(HEIGHT.get() / 2 - paddleLength.get() / 2);

        paddleTimeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame movePaddle = new KeyFrame(Duration.seconds((1 / 90.0)), event -> movePaddle());
        paddleTimeline.getKeyFrames().add(movePaddle);
        paddleTimeline.play();
    }

    public double getPaddleWidth() {
        return paddleWidth.get();
    }

    public DoubleProperty getPaddleWidthProperty() {
        return paddleWidth;
    }

    public void movePaddle() {
        if (leftPaddleUp.get() && leftPaddleY.get() > 0.0) {
            leftPaddleY.setValue(leftPaddleY.getValue() - Constants.DEFAULT_BALL_MOVEMENT_SPEED);
        }
        if (leftPaddleDown.get() && leftPaddleY.get() + paddleLength.get() < PongModel.getHEIGHT()) {
            leftPaddleY.set(leftPaddleY.get() + Constants.DEFAULT_BALL_MOVEMENT_SPEED);
        }
        if (rightPaddleUp.get() && rightPaddleY.get() > 0.0) {
            rightPaddleY.setValue(rightPaddleY.getValue() - Constants.DEFAULT_BALL_MOVEMENT_SPEED);
        }
        if (rightPaddleDown.get() && rightPaddleY.get() + paddleLength.get() < PongModel.getHEIGHT()) {
            rightPaddleY.set(rightPaddleY.get() + Constants.DEFAULT_BALL_MOVEMENT_SPEED);
        }
    }

    public void setLeftPaddleUp(boolean leftPaddleUp) {
        this.leftPaddleUp.set(leftPaddleUp);
    }

    public void setLeftPaddleDown(boolean leftPaddleDown) {
        this.leftPaddleDown.set(leftPaddleDown);
    }

    public void setRightPaddleUp(boolean rightPaddleUp) {
        this.rightPaddleUp.set(rightPaddleUp);
    }

    public void setRightPaddleDown(boolean rightPaddleDown) {
        this.rightPaddleDown.set(rightPaddleDown);
    }

    public DoubleProperty getPaddleLengthProperty() {
        return paddleLength;
    }

    public double getPaddleLength() {
        return paddleLength.get();
    }

    public double getLeftPaddleX() {
        return leftPaddleX.get();
    }

    public DoubleProperty getLeftPaddleXProperty() {
        return leftPaddleX;
    }

    public double getRightPaddleX() {
        return rightPaddleX.get();
    }

    public DoubleProperty getRightPaddleXProperty() {
        return rightPaddleX;
    }

    public double getLeftPaddleY() {
        return leftPaddleY.get();
    }

    public DoubleProperty getLeftPaddleYProperty() {
        return leftPaddleY;
    }

    public DoubleProperty getRightPaddleYProperty() {
        return rightPaddleY;
    }

}
