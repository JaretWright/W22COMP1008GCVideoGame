package com.example.w22comp1008gcvideogame.sprites;

import com.example.w22comp1008gcvideogame.GameConfig;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
    protected Image image;
    protected int posX, posY, imageWidth, imageHeight, speed;
    private boolean alive;

    /**
     * This is the constructor for the Sprite class
     * @param posX - the left most position of the Sprite
     * @param posY - the top positon of the Sprite
     * @param imageWidth - the width of the image when drawn
     * @param imageHeight - the height of the image when drawn
     * @param speed - how many pixels the Sprite can move hj
     */
    public Sprite(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        setPosX(posX);
        setPosY(posY);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSpeed(speed);
        alive = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        int furthestRight = GameConfig.getGame_width()-imageWidth;
        if (posX>=0 && posX <= furthestRight)
            this.posX = posX;
        else
            throw new IllegalArgumentException("posX must be in the range of 0-"+furthestRight);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        int furthestDown = GameConfig.getGame_height()-imageHeight;
        if (posY>=0 && posY <= furthestDown)
            this.posY = posY;
        else
            throw new IllegalArgumentException(String.format("posY must be in the range of 0-%d",furthestDown));
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void draw(GraphicsContext gc)
    {
        if (alive)
            gc.drawImage(image, posX, posY, imageWidth, imageHeight);
    }

    public boolean collidesWith(Sprite sprite)
    {
        return ((posX + imageWidth/2 > sprite.posX) && (posX < sprite.posX + sprite.imageWidth/2) &&
                (posY + imageHeight/2 > sprite.posY) && (posY < sprite.posY + sprite.imageHeight/2));
    }
}
