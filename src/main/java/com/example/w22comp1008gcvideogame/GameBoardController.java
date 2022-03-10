package com.example.w22comp1008gcvideogame;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class GameBoardController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    @FXML
    private void startGame(ActionEvent event)
    {
        startButton.setVisible(false);

        //a canvas can be used to "draw" on.  The GraphicsContext is the tool used for the drawing
        Canvas canvas = new Canvas(GameConfig.getGame_width(), GameConfig.getGame_height());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //we need to load some images to draw the background and the ship
        Image background = new Image(getClass().getResource("images/space.png").toExternalForm());
        Image shipImage = new Image(getClass().getResource("images/ship.png").toExternalForm());

        //create the Ship sprite
        Sprite ship = new Sprite(shipImage, 400,100, 100, 70, 3);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0,0, GameConfig.getGame_width(), GameConfig.getGame_height());
                ship.draw(gc);
                ship.moveRight();
            }
        };

        timer.start();


        //attach the canvas to the anchorpane
        anchorPane.getChildren().add(canvas);
    }
}
