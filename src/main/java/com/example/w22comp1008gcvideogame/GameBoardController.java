package com.example.w22comp1008gcvideogame;

import com.example.w22comp1008gcvideogame.sprites.Alien;
import com.example.w22comp1008gcvideogame.sprites.Explosion;
import com.example.w22comp1008gcvideogame.sprites.Missile;
import com.example.w22comp1008gcvideogame.sprites.Ship;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class GameBoardController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    private HashSet<KeyCode> activeKeys;

    private AnimationTimer timer;

    @FXML
    private void startGame(ActionEvent event)
    {
        startButton.setVisible(false);
        activeKeys = new HashSet<>();

        //This is an example of an anonymous inner class
        anchorPane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyPressed) {
                activeKeys.add(keyPressed.getCode());
            }
        });

        anchorPane.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyReleased) {
                activeKeys.remove(keyReleased.getCode());
            }
        });

        //a canvas can be used to "draw" on.  The GraphicsContext is the tool used for the drawing
        Canvas canvas = new Canvas(GameConfig.getGame_width(), GameConfig.getGame_height());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //we need to load some images to draw the background and the ship
        Image background = new Image(getClass().getResource("images/space.png").toExternalForm());

        //create the Ship sprite
        Ship ship = new Ship(100,100);

        //create a few aliens
        SecureRandom rng = new SecureRandom();
        ArrayList<Alien> aliens = new ArrayList<>();
        for (int i=1 ; i<= 5; i++)
        {
            Alien newAlien =new Alien(rng.nextInt(500, GameConfig.getGame_width()),
                                rng.nextInt(0, GameConfig.getGame_height()-GameConfig.getAlien_height()-80));

            for (Alien alien : aliens)
            {
                if (alien.collidesWith(newAlien))
                    alien.moveLeft();
            }
            aliens.add(newAlien);
        }

        //create a collection to hold all of the explosions
        ArrayList<Explosion> explosions = new ArrayList<>();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0,0, GameConfig.getGame_width(), GameConfig.getGame_height());

                //update the ship position and draw it
                updateShip(ship);
                ship.draw(gc);

                //remove any destroyed aliens
                removeDeceasedAliens(aliens);

                //update the stats
                updateStats(gc, aliens);

                //check to see if the game should end
                if (aliens.size()==0)
                {
                    finalMessage(gc, "Congratulations - you saved the universe!!", Color.WHITE);
                    if(explosions.size()==0)
                        timer.stop();
                }

                //draw the aliens
                for (Alien alien : aliens)
                {
                    alien.draw(gc);

                    for (Missile missile : ship.getActiveMissiles())
                    {
                        if (missile.collidesWith(alien))
                        {
                            explosions.add(new Explosion(alien.getPosX(), alien.getPosY(),50,50));
                            missile.setAlive(false);
                            alien.setAlive(false);
                        }
                    }

                    if (alien.collidesWith(ship))
                    {
                        explosions.add(new Explosion(alien.getPosX(), alien.getPosY(),100,100));
                        ship.setAlive(false);
                        alien.setAlive(false);

                    }
                    if(explosions.size()==0 && !ship.isAlive())
                    {
                        finalMessage(gc, "The Aliens Got YOU!!", Color.RED);
                        timer.stop();
                    }
                }

                explosions.removeIf(explosion -> !explosion.isAlive());

                for (Explosion explosion : explosions)
                    explosion.draw(gc);

            }
        };
        timer.start();

        //attach the canvas to the anchorpane
        anchorPane.getChildren().add(canvas);
    }

    /**
     * This method will display the final message to the person playing the game
     */
    private void finalMessage(GraphicsContext gc, String message, Color color)
    {
        Font font = Font.font("Arial", FontWeight.NORMAL, 32);
        gc.setFont(font);
        gc.setFill(color);
        gc.fillText(message, 250, 350);
    }

    private void removeDeceasedAliens(ArrayList<Alien> aliens) {
        //this will create a concurrency exception, we need to use an iterator instead
//        for (Alien alien : aliens)
//        {
//            if (!alien.isAlive())
//                aliens.remove(alien);
//        }

        aliens.removeIf(alien -> !alien.isAlive());
    }

    /**
     * This method will update the location of the ship based on the keys pressed
     */
    private void updateShip(Ship ship)
    {
        if (activeKeys.contains(KeyCode.DOWN))
            ship.moveDown();
        if (activeKeys.contains(KeyCode.UP) || activeKeys.contains("W"))
            ship.moveUp();
        if (activeKeys.contains(KeyCode.RIGHT))
            ship.moveRight();
        if (activeKeys.contains(KeyCode.LEFT))
            ship.moveLeft();
        if (activeKeys.contains(KeyCode.SPACE))
            ship.shootMissile();
    }

    /**
     * This method will update the scoreboard at the bottom of the canvas
     */
    private void updateStats(GraphicsContext gc, ArrayList<Alien> aliens)
    {
        //draw a black rectangle across the bottom of the canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, GameConfig.getGame_height()-80, GameConfig.getGame_width(), 80);

        //write how many aliens are left
        Font font = Font.font("Arial",FontWeight.NORMAL, 32);
        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillText("Missiles remaining: "+aliens.size(), 600, 750);
    }
}
