package com.example.w22comp1008gcvideogame.sprites;

import com.example.w22comp1008gcvideogame.GameConfig;
import com.example.w22comp1008gcvideogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien extends Sprite{

    /**
     * This is the constructor for the Sprite class
     *
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top positon of the Sprite
     */
    public Alien(int posX, int posY) {
        super(posX, posY, GameConfig.getAlien_width(), GameConfig.getAlien_height(), GameConfig.getAlien_speed());
        image = new Image(Main.class.getResource("images/alien.png").toExternalForm());
    }

    /**
     * The alien will move from the right side of the game to the left.  When the alien gets to
     * the far left side it will reappear on the right side
     */
    public void moveLeft()
    {
        posX -= speed;

        if (posX < 0)
            posX = GameConfig.getGame_width();
    }

    public void draw(GraphicsContext gc)
    {
        super.draw(gc);
        moveLeft();
    }
}
