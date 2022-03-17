package com.example.w22comp1008gcvideogame;

public class GameConfig {
    private static int game_width = 1000;
    private static int game_height = 800;
    private static int ship_width = 110;
    private static int ship_height = 60;
    private static int ship_speed = 5;
    private static int missile_width = 40;
    private static int missile_height = 20;
    private static int missile_speed = 7;

    public static int getMissile_width() {
        return missile_width;
    }

    public static void setMissile_width(int missile_width) {
        GameConfig.missile_width = missile_width;
    }

    public static int getMissile_height() {
        return missile_height;
    }

    public static void setMissile_height(int missile_height) {
        GameConfig.missile_height = missile_height;
    }

    public static int getMissile_speed() {
        return missile_speed;
    }

    public static void setMissile_speed(int missile_speed) {
        GameConfig.missile_speed = missile_speed;
    }

    public static int getShip_width() {
        return ship_width;
    }

    public static int getShip_height() {
        return ship_height;
    }

    public static int getShip_speed() {
        return ship_speed;
    }

    public static void setShip_width(int ship_width) {
        if (ship_width >= 40 && ship_width<=200)
            GameConfig.ship_width = ship_width;
        else
            throw new IllegalArgumentException("Ship Width must be in the range of 40-200");
    }

    public static void setShip_height(int ship_height) {
        GameConfig.ship_height = ship_height;
    }

    public static void setShip_speed(int ship_speed) {
        GameConfig.ship_speed = ship_speed;
    }

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
