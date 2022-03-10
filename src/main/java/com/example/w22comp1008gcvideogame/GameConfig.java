package com.example.w22comp1008gcvideogame;

public class GameConfig {
    private static int game_width = 1000;
    private static int game_height = 800;

    public static int getGame_width() {
        return game_width;
    }

    public static void setGame_width(int game_width) {
        GameConfig.game_width = game_width;
    }

    public static int getGame_height() {
        return game_height;
    }

    public static void setGame_height(int game_height) {
        GameConfig.game_height = game_height;
    }
}
