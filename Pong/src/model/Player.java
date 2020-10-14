package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.adapter.JavaBeanDoubleProperty;

public class Player {
    public final String playerName;
    public DoubleProperty points = new SimpleDoubleProperty();

    public Player(String playerName) {
        this.playerName = playerName;
        points.setValue(0);
    }
}
