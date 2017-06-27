package ru.lessons.lesson13.gui;

/**
 * Класс для хранения информации о ходе игры
 */
public class Environment {
    /** Признак разгаданной игры */
    private boolean finish;

    /** Признак проигранной игры */
    private boolean gameOver;

    /** Число бомб на поле */
    private int numBombs;

    /**
     * Конструктор
     * @param numBombs число бомб на поле
     */
    public Environment(int numBombs) {
        this.numBombs = numBombs;
        this.finish = false;
        this.gameOver = false;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
