package com.example.w22comp1008gcvideogame;

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

import java.util.HashSet;

public class GameBoardController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    private HashSet<KeyCode> activeKeys;

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
        Missile missile = new Missile(100,100);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0,0, GameConfig.getGame_width(), GameConfig.getGame_height());

                missile.draw(gc);
                updateShipLocation(ship);
                ship.draw(gc);
            }
        };
        timer.start();

        //attach the canvas to the anchorpane
        anchorPane.getChildren().add(canvas);
    }

    /**
     * This method will update the location of the ship based on the keys pressed
     */
    private void updateShipLocation(Ship ship)
    {
        if (activeKeys.contains(KeyCode.DOWN))
            ship.moveDown();
        if (activeKeys.contains(KeyCode.UP) || activeKeys.contains("W"))
            ship.moveUp();
        if (activeKeys.contains(KeyCode.RIGHT))
            ship.moveRight();
        if (activeKeys.contains(KeyCode.LEFT))
            ship.moveLeft();
    }
}
