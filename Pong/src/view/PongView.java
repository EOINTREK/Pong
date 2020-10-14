package view;

import control.PongController;
import javafx.scene.layout.*;
import model.PongModel;

public class PongView extends Pane{
    PongModel model;
    PongController controller;
    BorderPane view;

    public PongView(PongModel model, PongController controller) {
        this.model = model;
        this.controller = controller;

        view = new BorderPane();
/*
        view.setBackground(new Background(new BackgroundImage(model.getBasicImage(),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1280, 720, false, false, true, false))));*/

        view.setStyle("-fx-background-color: CADETBLUE;");
        PlayfieldView playfieldView = new PlayfieldView(this.model, this.controller, this);
        view.setCenter(playfieldView);

    }

    public void createInputHandler() {
        view.getScene().setOnKeyPressed(event -> controller.keyPressed(event));
        view.getScene().setOnKeyReleased(event -> controller.keyReleased(event));
    }

    public BorderPane getView() {
        return view;
    }
}
