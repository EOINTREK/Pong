package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.PongModel;

public class ScoreBoardView extends Pane {
    private int score;
    private Label scoreboard;
    Text leftScore = new Text();
    Text rightScore = new Text();

    public ScoreBoardView(PlayfieldView playfieldView) {
        /*scoreboard = new Label(Integer.toString(score));
        scoreboard.setTextFill(Color.AZURE);
        scoreboard.setStyle("-fx-font: 100 monospaced;");
        relocate(100, 480);*/
        playfieldView.getChildren().add(leftScore);
        playfieldView.getChildren().add(rightScore);
        // positioning helpers
        final double middle = PongModel.getPlayFieldWIDTH() / 2;
        final int offsetFromMiddle = 150;

        // layout helpers
        final Font font = Font.font("OCR A Std", FontWeight.BOLD, FontPosture.REGULAR, 40.0);
        final int locationY = 50;
        final Color color = Color.WHITE;

        // left
        leftScore.setFont(font);
        leftScore.setY(locationY);
        leftScore.setFill(color);
        // right
        rightScore.setFont(font);
        rightScore.setY(locationY);
        rightScore.setFill(color);

        // position score text
        leftScore.setX(middle - offsetFromMiddle - 15);
        rightScore.setX(middle + offsetFromMiddle);

        leftScore.textProperty().bind(PongModel.getPlayerOne().points.asString());
        rightScore.textProperty().bind(PongModel.getPlayerTwo().points.asString());
    }

    public Label getScoreBoard() {
        return scoreboard;
    }

    public int getScore() {
        return score;
    }

    public void relocate(double x, double y) {
        scoreboard.relocate(x, y);
    }

    //increment method increments a player's score board when he scores
    public void increment() {
        score++;
        scoreboard.setText(Integer.toString(score));
    }

    //resetScore resets a score board
    public void resetScore() {
        score = -1;
    }
}
