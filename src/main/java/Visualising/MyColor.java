package Visualising;

import java.awt.*;

public enum MyColor {
    WALL,
    POINT,
    NOTHING,
    PACMAN,
    GHOST,
    WEAKGHOST,
    CHERRY;


    public Color toColor() {
        return switch (this) {
            case WALL -> new Color(0,0,128); // granatowy
            case POINT -> new Color(255,204,153); // brzoskwiniowy
            case NOTHING -> new Color(0,0,0); // czarny
            case PACMAN -> new Color(255,125,0); // zloty
            case GHOST -> new Color(255,0,0); // czerwony
            case WEAKGHOST -> new Color(124,252,0); // trawiasty
            case CHERRY -> new Color(128,0,128); // purpurowy
        };
    }




}
