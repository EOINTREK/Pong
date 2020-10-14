package view;

import control.PongController;
import javafx.scene.layout.Pane;
import model.PongModel;

public class PlayfieldView extends Pane {
    private final PongModel model;
    private final PongController controller;
    private final PongView view;
   // private final PaddleView paddleView;
    private final BallView ballView;

    public PlayfieldView(PongModel model, PongController controller, PongView view) {
        super();

        this.model = model;
        this.controller = controller;
        this.view = view;

        this.prefWidthProperty().bind(model.playFieldWIDTHProperty());
        this.prefHeightProperty().bind(model.playFieldHEIGHTProperty());

        ScoreBoardView scoreBoardView = new ScoreBoardView(this);
        //this.getChildren().add(scoreBoardView);

        PaddleView paddleView = new PaddleView(model.getPaddleModel(), this);
        this.getChildren().add(paddleView);

        ballView = new BallView();
        this.getChildren().add(ballView);

    }
}
