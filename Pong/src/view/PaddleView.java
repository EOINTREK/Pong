package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.PaddleModel;
import resources.Constants;

import java.io.File;

public class PaddleView extends Pane {

    private ImageView leftPaddle;
    private Rectangle rightPaddle;

    public PaddleView(PaddleModel model, PlayfieldView view) {
        leftPaddle = new ImageView(new Image(new File("src/images/RightPaddle.png").toURI().toString()));
        leftPaddle.setFitHeight(Constants.DEFAULT_PADDLE_LENGTH);
        leftPaddle.setFitWidth(Constants.DEFAULT_PADDLE_WIDTH);
        leftPaddle.translateYProperty().bind(model.getPaddleLengthProperty());
        leftPaddle.translateXProperty().bind(model.getPaddleWidthProperty());
        leftPaddle.translateXProperty().bind(model.getLeftPaddleXProperty());
        leftPaddle.translateYProperty().bind(model.getLeftPaddleYProperty());
        this.getChildren().add(leftPaddle);

        /*leftPaddle = new ImageView(model.getPaddleWidth(), model.getPaddleLengthProperty().get(), Color.BLACK);
        leftPaddle.fitHeightProperty().bind(model.getPaddleLengthProperty());
        leftPaddle.xProperty().bind(model.getLeftPaddleXProperty());
        leftPaddle.yProperty().bind(model.getLeftPaddleYProperty());
        this.getChildren().add(leftPaddle);*/

        rightPaddle = new Rectangle(model.getPaddleWidth(), model.getPaddleLength(), Color.BLACK);
        rightPaddle.heightProperty().bind(model.getPaddleLengthProperty());
        rightPaddle.xProperty().bind(model.getRightPaddleXProperty());
        rightPaddle.yProperty().bind(model.getRightPaddleYProperty());
        this.getChildren().add(rightPaddle);
    }
}
