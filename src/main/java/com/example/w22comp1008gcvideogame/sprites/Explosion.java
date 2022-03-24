package com.example.w22comp1008gcvideogame.sprites;

import com.example.w22comp1008gcvideogame.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Sprite{
    private final int REFRESH_RATE = 5;
    private int currentCount;
    private int explosionPosition;
    private int[] spriteStartX;


    /**
     * This is the constructor for the Explosion class
     *
     * @param posX        - the left most position of the Sprite
     * @param posY        - the top positon of the Sprite
     * @param imageWidth  - the width of the image when drawn
     * @param imageHeight - the height of the image when drawn
     */
    public Explosion(int posX, int posY, int imageWidth, int imageHeight) {
        super(posX, posY, imageWidth, imageHeight, 0);
        image = new Image(Main.class.getResource("images/fullExplosion2.png").toExternalForm());
        explosionPosition = 0;
        spriteStartX = new int[]{0, 170, 330, 520, 710};
        currentCount = REFRESH_RATE;
    }

    /**
     * This method will draw the explosion in the same position
     * every 5th time the method is called, it will advance to the next explosion image
     * until the final image is shown.  It will then set the "isAlive" status to false
     * @param gc
     */
    @Override
    public void draw(GraphicsContext gc)
    {
        //every 5th time the explosion is drawn, increase the explosion position to select a bigger
        //explosion
        if (--currentCount < 0)
        {
            explosionPosition++;
            currentCount = REFRESH_RATE;
        }

        //if we get to explosion position 5, the explosion is complete and we set the isAlive to false
        if (explosionPosition>=spriteStartX.length)
        {
            setAlive(false);
        }
        else
        {
            if (isAlive()){
                //image, sx, sy, sw, sh, posX, posY, imageWidth, imageHeight
                gc.drawImage(image,spriteStartX[explosionPosition],0,184,368,
                        posX, posY, imageWidth, imageHeight);
            }
        }
    }
}
