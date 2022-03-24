package com.example.w22comp1008gcvideogame.sprites;

import com.example.w22comp1008gcvideogame.GameConfig;
import com.example.w22comp1008gcvideogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Ship extends Sprite {
    private final int REFRESH_RATE = 5;
    private int currentMissilePause;

    private ArrayList<Missile> activeMissiles;

    /**
     * This is the constructor for the Sprite class
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top positon of the Sprite
     */
    public Ship(int posX, int posY) {
        super(posX, posY, GameConfig.getShip_width(), GameConfig.getShip_height(), GameConfig.getShip_speed());
        image = new Image(Main.class.getResource("images/ship.png").toExternalForm());
        activeMissiles = new ArrayList<>();
        currentMissilePause = REFRESH_RATE;
    }

    public ArrayList<Missile> getActiveMissiles() {
        return activeMissiles;
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
        int furthestDown = GameConfig.getGame_height()-GameConfig.getShip_height()-80;
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

    /**
     * This method will shoot a missile from the middle of the ship
     */
    public void shootMissile()
    {
        if (currentMissilePause<0)
        {
            Missile newMissile = new Missile(posX+imageWidth,posY+imageHeight/2-GameConfig.getMissile_height()/2);
            activeMissiles.add(newMissile);
            currentMissilePause = REFRESH_RATE;
        }

    }

    /**
     * This method will draw the ship and then loop over all active missiles to draw them
     * @param gc
     */
    public void draw(GraphicsContext gc)
    {
        currentMissilePause--;

        super.draw(gc);

        //-> is a lambda expression
        //it is calling the Predicate method test for every object in the collection (which in this
        //case is a missile)
        activeMissiles.removeIf(missile -> !missile.isAlive());

        //This is the same thing using an annonymous inner class
//        activeMissiles.removeIf(new Predicate<Missile>() {
//            @Override
//            public boolean test(Missile missile) {
//                return !missile.isAlive();
//            }
//        });

        for (Missile missile : activeMissiles)
            missile.draw(gc);
    }
}
