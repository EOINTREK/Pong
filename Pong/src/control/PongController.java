package control;

import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import model.PaddleModel;
import model.PongModel;

public class PongController {

    PaddleModel model;
    public PongController(PaddleModel model) {
        this.model = model;
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                onLeftPaddleUpAction(false);
                break;
            case S:
                onLeftPaddleDownAction(false);
                break;
            case UP:
                onRightPaddleUpAction(false);
                break;
            case DOWN:
                onRightPaddleDownAction(false);
                break;
        }
    }

    public void onLeftPaddleUpAction(boolean b) {
        if (b) model.setLeftPaddleUp(true);
        else model.setLeftPaddleUp(false);
    }

    public void onLeftPaddleDownAction(boolean b) {
        if (b) model.setLeftPaddleDown(true);
        else model.setLeftPaddleDown(false);
    }

    public void onRightPaddleUpAction(boolean b) {
        if (b) model.setRightPaddleUp(true);
        else model.setRightPaddleUp(false);
    }

    public void onRightPaddleDownAction(boolean b) {
        if (b) model.setRightPaddleDown(true);
        else model.setRightPaddleDown(false);
    }

    public void close_action(WindowEvent event) {
        Pong.exit();
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                onLeftPaddleUpAction(true);
                break;
            case S:
                onLeftPaddleDownAction(true);
                break;
            case UP:
                onRightPaddleUpAction(true);
                break;
            case DOWN:
                onRightPaddleDownAction(true);
                break;
        }
    }
}
