package control;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Ball;
import model.PongModel;
import view.PongView;

public class Pong  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PongModel pongmodel = new PongModel();
        PongController controller = new PongController(pongmodel.getPaddleModel());
        PongView view = new PongView(pongmodel, controller);

        Scene scene = new Scene(view.getView());

        view.createInputHandler();

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event ->
        {
            controller.close_action(event);
            event.consume();
        });

        primaryStage.show();
    }

    public static void exit() { System.exit(0); }
}
