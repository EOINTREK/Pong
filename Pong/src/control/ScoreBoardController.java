package control;

import javafx.scene.control.Label;

public class ScoreBoardController {
    private int score;
    private Label scoreboard;

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
