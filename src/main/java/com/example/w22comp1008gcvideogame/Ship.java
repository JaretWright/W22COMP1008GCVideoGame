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

    /**
     * This method will decrease the Y coordinate based on the ship speed until it gets to 0
     */
    public void moveUp()
    {
        posY -= speed;

        if (posY<0)
            posY = 0;
    }

    /**
     * This method will increase the Y coordinate based on the ship speed until it reaches the bottom of the scene
     */
    public void moveDown()
    {
        int furthestDown = GameConfig.getGame_height()-GameConfig.getShip_height();
        posY += speed;

        if (posY> furthestDown)
            posY = furthestDown;
    }

    /**
     * This method will move the ship to the left until it reaches the left most edge of the screen
     */
    public void moveLeft()
    {
        posX -= speed;

        if (posX < 0)
            posX=0;
    }

    /**
     * This method will move the ship to the right until it reaches the far right side of the screen
     */
    public void moveRight()
    {
        int furthestRight = GameConfig.getGame_width()-GameConfig.getShip_width();
        posX += speed;

        if (posX > furthestRight)
            posX = furthestRight;
    }
}
