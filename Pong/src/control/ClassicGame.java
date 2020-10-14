/*
package control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Ball;
import model.PongModel;
import view.FirstPage;
import view.ScoreBoardView;
import javafx.scene.shape.Rectangle;
import view.gameObjects.PlayerView;

import java.io.File;

public class ClassicGame {
    private PongModel player2;
    private PongModel player1;
    private Ball ball;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    //Sound Assets
    private final AudioClip bouncesound = new AudioClip(new File("src/sounds/Shoteffect.mp3").toURI().toString());
    private final AudioClip goal = new AudioClip(new File("src/sounds/Accept.wav").toURI().toString());
    private final AudioClip winner = new AudioClip(new File("src/sounds/Winner.wav").toURI().toString());
    private final Media classicsong = new Media(new File("src/sounds/Uaua.mp3").toURI().toString());
    protected Pane canvas;
    protected double ballx, bally; //Direction of the ball
    protected boolean gameover;
    protected Timeline loop;
    protected MediaPlayer classicbgm = new MediaPlayer(classicsong);
    Rectangle ballRectangle;
    private Scene scene;
    private ScoreBoardView scoreboard1, scoreboard2;
    private Label pause, start, pressb, pressrm, winnerleft, winnerright;
    private boolean running = false, paused = false, keyw, keys, keyp, keyl;
    private double leftlightcurrent, rightlightcurrent; //Stores the current time of the light when it is turned on

    public ClassicGame() {
        //Basic Structure of the Classic game
        setUpBall();
        setUpPlayer();
        PlayerView.createPlayerView(1);
        PlayerView.createPlayerView(2);
        setUpStart();
        setUpPause();
        setUpReplayMenu();
        setUpScoreBoard();
        setUpDisplay();

        loop = gameLoop();
        loop.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
    }

    public PongModel getPlayer1() {
        return player1;
    }

    public PongModel getPlayer2() {
        return player2;
    }

    public Ball getBall() {
        return ball;
    }

    //KeyEvent Handlers
    public int keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                keyw = true;
                break;
            case S:
                keys = true;
                break;
            case P:
                keyp = true;
                break;
            case L:
                keyl = true;
                break;
            case B:
                if (!running) {
                    running = true;
                    loop.play();
                    canvas.getChildren().removeAll(start, pressb);
                    FirstPage.stopMainBGM();
                    playBGM();
                }
                break;
            case SPACE:
                if (running && !paused) {
                    paused = true;
                    loop.pause();
                    pauseBGM();
                    pause.setVisible(true);
                } else if (paused) {
                    paused = false;
                    loop.play();
                    resumeBGM();
                    pause.setVisible(false);
                }
                break;
            case R:
                if (gameover) {
                    scoreboard1.resetScore();
                    scoreboard1.increment();
                    scoreboard2.resetScore();
                    scoreboard2.increment();
                    pressrm.setVisible(false);
                    ball.setVisible(true);
                    canvas.getChildren().removeAll(winnerleft, winnerright);
                    classicbgm.play();
                    loop.play();
                    gameover = false;
                    running = true;
                    return 0;
                }
                break;
            case M:
                if (gameover) {
                    GameLauncher.setScene(GameLauncher.getMainScene());
                    reSetUpGame();
                    FirstPage.playMainBGM();
                    return 0;
                }
                break;
        }
        return 1;
    }



    public Scene getScene() {
        return scene;
    }

    //Methods controls a background music in the game.
    public void playBGM() {
        classicbgm.setCycleCount(Timeline.INDEFINITE);
        classicbgm.setStartTime(Duration.seconds(0.1));
        classicbgm.play();
    }

    public void stopBGM() {
        classicbgm.stop();
    }

    public void pauseBGM() {
        classicbgm.pause();
    }

    public void resumeBGM() {
        classicbgm.play();
    }

    //setUpDisplay method creates the basic structure of control.ClassicGame scene.
    public void setUpDisplay() {
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: CADETBLUE;");
        canvas.getChildren().addAll(PongModel.getLeftLight(), PongModel.getRightLight(), ball.getBall(), ballRectangle, PlayerView.getPlayer(), PlayerView.getPlayer(), scoreboard1.getScoreBoard(),
                scoreboard2.getScoreBoard(), pause, start, pressb, pressrm);
        scene = new Scene(canvas, WIDTH, HEIGHT);
    }

    //SetUpPlayer method creates two players
    public void setUpPlayer() {
        player1 = new PongModel(WIDTH-1235, 240, 1);
        player2 = new PongModel(WIDTH-45, 240, 2);
    }

    //setUpBall method creates a ball
    public void setUpBall() {
        ball = new Ball(640, 360, ballTimeLine);
        ballRectangle = new Rectangle(640, 360, 20, 20);
        ballx = ball.getSpeed();
        bally = 0;
    }

    //setUpScoreBoard method creates two score boards
    public void setUpScoreBoard() {
        scoreboard1 = new ScoreBoardView(200, 50);
        scoreboard2 = new ScoreBoardView(550, 50);
    }

    //setUpPause method creates pause label
    public void setUpPause() {
        pause = new Label("PAUSED");
        pause.setStyle("-fx-font: 100 monospaced; -fx-text-fill: AZURE;");
        pause.setVisible(false);
        pause.relocate(230, 250);
    }

    //setUpReplayMenu method creates replay label
    public void setUpReplayMenu() {
        pressrm = new Label("Press \"R\" Replay"
                + "\nPress \"M\" Menu");
        pressrm.setStyle("-fx-font: 35 monospaced; -fx-text-fill: AZURE;");
        pressrm.relocate(250, 450);
        pressrm.setVisible(false);
    }

    //setUpStart method creates start label
    public void setUpStart() {
        start = new Label("READY");
        start.setStyle("-fx-font: 100 monospaced; -fx-text-fill: AZURE;");
        start.relocate(255, 250);
        pressb = new Label("Press \"B\"");
        pressb.setStyle("-fx-font: 50 monospaced; -fx-text-fill: AZURE;");
        pressb.relocate(270, 340);
    }

    //reSetUpGame method put all the objects in the game to the initial place.
    public void reSetUpGame() {
        gameover = false;
        paused = false;
        running = false;
        scoreboard1.resetScore();
        scoreboard1.increment();
        scoreboard2.resetScore();
        scoreboard2.increment();
        pause.setVisible(false);
        pressrm.setVisible(false);
        ball.setVisible(true);
        PlayerController.lightOff(1);
        PlayerController.lightOff(2);
        canvas.getChildren().addAll(start, pressb);
        canvas.getChildren().removeAll(winnerleft, winnerright);
    }

    public Timeline gameLoop() {
        return new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                lightCheck();
                movePaddle();
                moveBall();
                collisionCheck();
                scoreCheck();
            }
        }));
    }

    //scoreCheck method consistently checks the both players score to find out an winner
    public void scoreCheck() {
        if (scoreboard1.getScore() == 5) {
            winnerFound();
            winnerleft = new Label("<- WINNER");
            winnerleft.setStyle("-fx-font: 100 monospaced; -fx-text-fill: AZURE;");
            winnerleft.relocate(60, 250);
            canvas.getChildren().add(winnerleft);
        } else if (scoreboard2.getScore() == 5) {
            winnerFound();
            winnerright = new Label("WINNER ->");
            winnerright.setStyle("-fx-font: 100 monospaced; -fx-text-fill: AZURE;");
            winnerright.relocate(200, 250);
            canvas.getChildren().add(winnerright);

        }
    }

    //winnerFound method shows options to an user after game is over and set game status.
    public void winnerFound() {
        gameover = true;
        running = false;
        stopBGM();
        loop.stop();
        winner.play();
        player1.relocate(25, 240);
        player2.relocate(755, 240);
        ball.setVisible(false);
        pressrm.setVisible(true);
    }

    //lightCheck method is used to turn off the winner light on certain given time
    public void lightCheck() {
        if (player1.getLight() && leftlightcurrent + 1000 <= System.currentTimeMillis()) {
            PlayerController.lightOff(1);
        }
        if (player2.getLight() && rightlightcurrent + 1000 <= System.currentTimeMillis()) {
            PlayerController.lightOff(2);
        }
    }

    //collisionCheck method checks collisions between ball, 4 walls, and 2 paddles.
    //and do appropriate jobs.
    public int collisionCheck() {
        Bounds player1b = player1.getPlayer().getBoundsInParent();
        Bounds player2b = player2.getPlayer().getBoundsInParent();
        Bounds ballb = ball.getBall().getBoundsInParent();
        Bounds ballRectangleBounds = ballRectangle.getBoundsInParent();
        if (ballRectangle.getBoundsInLocal().intersects(player1b))
            System.out.println("Treffer...");


        if (ballb.intersects(player1b)) {
            if (ball.getLayoutX() - 6 <= player1.getLayoutX() + player1.getWidth()) {
                goal.play();
                leftlightcurrent = PlayerController.lightOn(1);
                scoreboard2.increment();
                ball.relocate(400, 300);
                ballRectangle.setX(400);
                ballRectangle.setY(300);
                ballx = -ballx;
                bally = 0;
            } else {
                bouncesound.stop();
                bouncesound.play();
                ballx = -ballx;
                bally = ball.computeBallYAxisSpeed(this.player1, this.ball);
            }
            return 0;
        } else if (ballRectangle.getBoundsInParent().intersects(player2b)) {
            if (ball.getLayoutX() + 8 >= player2.getLayoutX()) {
                goal.play();
                rightlightcurrent = PlayerController.lightOn(2);
                ball.relocate(400, 300);
                ballRectangle.setX(400);
                ballRectangle.setY(300);
                scoreboard1.increment();
                ballx = -ballx;
                bally = 0;
            } else {
                bouncesound.stop();
                bouncesound.play();
                ballx = -ballx;
                bally = ball.computeBallYAxisSpeed(this.player2, this.ball);
            }
            return 0;
        } else if (ball.getLayoutX() - ball.getRadius() <= 0) {
            goal.play();
            ball.relocate(400, 300);
            ballRectangle.setX(400);
            ballRectangle.setY(300);
            ballx = -ballx;
            leftlightcurrent = PlayerController.lightOn(1);
            scoreboard2.increment();
            return 0;
        } else if (ball.getLayoutX() + ball.getRadius() >= WIDTH) {
            goal.play();
            ball.relocate(400, 300);
            ballx = -ballx;
            ballRectangle.setX(400);
            ballRectangle.setY(300);
            rightlightcurrent = PlayerController.lightOn(2);
            scoreboard1.increment();
            return 0;
        } else if (ball.getLayoutY() - ball.getRadius() <= 0) {
            bouncesound.stop();
            bouncesound.play();
            bally = -bally;
        } else if (ball.getLayoutY() + ball.getRadius() >= HEIGHT) {
            bouncesound.stop();
            bouncesound.play();
            bally = -bally;
        }
        return 1;
    }

    //moveBall method changes the location of the ball object every frame
    public void moveBall() {
        ball.setLayoutX(ball.getLayoutX() + ballx);
        ball.setLayoutY(ball.getLayoutY() + bally);
        ballRectangle.setX(ballRectangle.getX() + ballx);
        ballRectangle.setY(ballRectangle.getY() + bally);
    }

    //movePaddle method allows an user to move around a paddle with given keys.
    public void movePaddle() {
        if (keyw && keyp) {
            if (player1.getLayoutY() >= 0 && player1.getLayoutY() - player1.getSpeed() >= 0)
                player1.setLayoutY(player1.getLayoutY() - player1.getSpeed());
            if (player2.getLayoutY() >= 0 && player2.getLayoutY() - player2.getSpeed() >= 0)
                player2.setLayoutY(player2.getLayoutY() - player2.getSpeed());
        } else if (keyw && keyl) {
            if (player1.getLayoutY() >= 0 && player1.getLayoutY() - player1.getSpeed() >= 0)
                player1.setLayoutY(player1.getLayoutY() - player1.getSpeed());
            if (player2.getLayoutY() <= 450 && player2.getLayoutY() + player2.getSpeed() <= 450)
                player2.setLayoutY((player2.getLayoutY() + player2.getSpeed()));
        } else if (keys && keyp) {
            if (player1.getLayoutY() <= 450 && player1.getLayoutY() + player1.getSpeed() <= 450)
                player1.setLayoutY((player1.getLayoutY() + player1.getSpeed()));
            if (player2.getLayoutY() >= 0 && player2.getLayoutY() - player2.getSpeed() >= 0)
                player2.setLayoutY(player2.getLayoutY() - player2.getSpeed());
        } else if (keys && keyl) {
            if (player1.getLayoutY() < 450 && player1.getLayoutY() + player1.getSpeed() <= 450)
                player1.setLayoutY((player1.getLayoutY() + player1.getSpeed()));
            if (player2.getLayoutY() < 450 && player2.getLayoutY() + player2.getSpeed() <= 450)
                player2.setLayoutY((player2.getLayoutY() + player2.getSpeed()));
        } else if (keyw) {
            if (player1.getLayoutY() > 0 && player1.getLayoutY() - player1.getSpeed() >= 0)
                player1.setLayoutY(player1.getLayoutY() - player1.getSpeed());
        } else if (keys) {
            if (player1.getLayoutY() < 450 && player1.getLayoutY() + player1.getSpeed() <= 450)
                player1.setLayoutY((player1.getLayoutY() + player1.getSpeed()));
        } else if (keyp) {
            if (player2.getLayoutY() > 0 && player2.getLayoutY() - player2.getSpeed() >= 0)
                player2.setLayoutY(player2.getLayoutY() - player2.getSpeed());
        } else if (keyl) {
            if (player2.getLayoutY() < 450 && player2.getLayoutY() + player2.getSpeed() <= 450)
                player2.setLayoutY((player2.getLayoutY() + player2.getSpeed()));
        }
    }
}
*/
