package com.example.w22comp1008gcvideogame;

import javafx.scene.image.Image;

public class Ship extends Sprite{
    /**
     * This is the constructor for the Sprite class
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top positon of the Sprite
     */
    public Ship(int posX, int posY) {
        super(posX, posY, GameConfig.getShip_width(), GameConfig.getShip_height(), GameConfig.getShip_speed());
        image = new Image(Main.class.getResource("images/ship.png").toExternalForm());
    }
}
