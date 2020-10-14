package view;

import control.PlayerController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Ball;

public class BallView extends Pane {

    Circle ball;
    public BallView() {
        ball = new Circle(Ball.getBallSize(), Color.RED);
        ball.radiusProperty().bind(Ball.getBallSizeProperty());
        ball.centerXProperty().bind(Ball.getBallCenterXProperty());
        ball.centerYProperty().bind(Ball.getBallCenterYProperty());
        this.getChildren().add(ball);
    }
}
