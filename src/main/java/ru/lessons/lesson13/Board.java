package ru.lessons.lesson13;

import ru.lessons.lesson13.gui.Environment;

/**
 * Интерфейс Board
 */
public interface Board {

    /**
     * Инициирует Board параметрами и отрисовывает
     * @param e экземпляр для хранения переменных о состоянии игры
     * @param cells массив ячеек
     */
    void drawBoard(Environment e, Cell[][] cells);

    /**
     * Получение номера клетки по координате экрана
     * @param x позиция
     * @return номер элемента
     */
    int getCellOnX(int x);

    /**
     * Получение номера клетки по координате экрана
     * @param y позиция
     * @return номер элемента
     */
    int getCellOnY(int y);
}
