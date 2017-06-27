package ru.lessons.lesson13;

/**
 * Интерфейс GeneratorBoard
 */
public interface GeneratorBoard {

    /**
     * Сгенерировать массив ячеек для поля игры
     * @param resX количество ячеек по оси
     * @param resY количество ячеек по оси
     * @param numBombs число бомб на поле
     * @return массив ячеек для игры
     */
    Cell[][] generate(int resX, int resY, int numBombs);

    /**
     * Получить число бомб на поле
     * @return число бомб
     */
    int getNumBombs();
}
