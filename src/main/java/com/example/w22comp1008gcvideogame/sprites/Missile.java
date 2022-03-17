package com.example.w22comp1008gcvideogame.sprites;

import com.example.w22comp1008gcvideogame.GameConfig;
import com.example.w22comp1008gcvideogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile extends Sprite{

    /**
     * This is the constructor for the Sprite class
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top positon of the Sprite
     */
    public Missile(int posX, int posY) {
        super(posX, posY, GameConfig.getMissile_width(), GameConfig.getMissile_height(), GameConfig.getMissile_speed());
        image = new Image(Main.class.getResource("images/missile.png").toExternalForm());
    }

    private void moveRight()
    {
        posX += speed;

        if (posX > GameConfig.getGame_width())
            setAlive(false);
    }

    public void draw(GraphicsContext gc)
    {
        super.draw(gc);
        moveRight();
    }
}
