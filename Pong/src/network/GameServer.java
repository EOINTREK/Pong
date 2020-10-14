package network;

import model.Ball;
import model.PongModel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer {
    PongModel playerOne;
    PongModel playerTwo;
    Ball ball;
    private DatagramSocket socket;
    private DatagramPacket packetPlayerTwo;

    public GameServer(PongModel playerOne, PongModel playerTwo, Ball ball) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.ball = ball;
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                extract(packet);
            } catch (IOException ex) {
                Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
    }

    public void extract(DatagramPacket p) {
        String pack = new String(p.getData());
        pack = pack.trim();
        String[] data = pack.split(" ");
    }

    public void sendParameters() {
        String params = "";
        params = params + String.valueOf(" ");    //player 1 details
        params = params + String.valueOf(playerOne.getLayoutY()) + " ";
        params = params + String.valueOf(playerTwo.getLayoutY()) + " ";
        params = params + String.valueOf(ball.getCenterX()) + " ";
        params = params + String.valueOf(ball.getCenterY()) + " ";
        params = params + String.valueOf(Ball.getBallSpeedY()) + " ";
    }
}
