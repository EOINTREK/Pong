/*
package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//import view.FirstPage;

public class GameLauncher extends Application {
    private static Scene mainscene;
    private static Stage mainstage;
    private int WIDTH = 800;//Display size
    private int HEIGHT = 600;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Scene getMainScene() {
        return mainscene;
    }

    //setRoot is used to shift the menu pages in the game.
    public static void setRoot(Pane root) {
        mainscene.setRoot(root);
    }

    public static void setScene(Scene scene) {
        mainstage.setScene(scene);
    }

    public void start(Stage stage) {
        mainstage = stage;
        mainstage.show();

        mainstage.setX(stage.getX() + stage.getWidth() / 2 - mainstage.getWidth() / 2); //mainstage.getWidth() = not NaN
        mainstage.setY(stage.getY() + stage.getHeight() / 2 - mainstage.getHeight() / 2); //mainstage.getHeight() = not NaN
        //Creating a view.FirstPage object
        new FirstPage();
        mainscene = new Scene(FirstPage.getPane(), WIDTH, HEIGHT);

        //Creating basic display structure
        mainstage.setTitle("Pong");
        mainstage.setScene(mainscene);
        mainstage.show();

    }
}
*/
