package ru.lessons.lesson13.gui;

import javax.swing.*;
import ru.lessons.lesson13.*;

import java.awt.*;

/**
 * Реализация игровой доски
 */
public class GuiBoard extends JPanel implements Board {


    /** Объект для хранения информации о ходе игры */
    private Environment env;

    /** Массив ячеек */
    public Cell<Graphics>[][] cells;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.cells != null){
            for (int x = 0; x != this.cells.length; x++){
                for (int y = 0; y != cells[0].length; y++){
                    g.drawImage(this.getCellImage(x, y), x*GuiCell.PADDING, y*GuiCell.PADDING, this);
                }
            }
        }
    }

    /**
     * возвращает нужную картинку для ячейки в зависимости от ситуациии игры
     * @param x координата
     * @param y координата
     * @return картинка для ячейки
     */
    private Image getCellImage(int x, int y){
        Image img = cells[x][y].getImg();
        if (env.isGameOver()) {
            img = cells[x][y].getGameOverImg();
        } else if (env.isFinish()) {
            img = cells[x][y].getFinishImg();
        }
        return img;
    }

    /**
     * Инициирует Board параметрами и отрисовывает
     * @param e экземпляр для хранения переменных о состоянии игры
     * @param cells массив ячеек
     */
    @Override
    public void drawBoard(Environment e, Cell[][] cells) {
        this.env = e;
        this.cells = cells;
        this.repaint();
    }

    /**
     * Получение номера клетки по координате экрана
     * @param x позиция
     * @return номер элемента
     */
    @Override
    public int getCellOnX(int x) {
        return (int) x/GuiCell.PADDING;
    }

    /**
     * Получение номера клетки по координате экрана
     * @param y позиция
     * @return номер элемента
     */
    @Override
    public int getCellOnY(int y) {
        return (int) y/GuiCell.PADDING;
    }

}
