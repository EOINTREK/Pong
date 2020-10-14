package model;

import javafx.scene.shape.Rectangle;

public class CollisionBox extends Rectangle {
    public CollisionBox(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public CollisionBox(double x, double y) {
        super(x, y);
    }
}
