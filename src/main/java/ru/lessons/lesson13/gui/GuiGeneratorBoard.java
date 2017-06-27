package ru.lessons.lesson13.gui;

import ru.lessons.lesson13.Cell;
import ru.lessons.lesson13.GeneratorBoard;

import java.util.Random;

/**
 * Created by User on 13.06.2017.
 */
public class GuiGeneratorBoard implements GeneratorBoard{

    /** число бомб на поле */
    private int numBombs;

    /** массив набор ячеек */
    private Cell[][] cells;

    @Override
    public int getNumBombs() {
        return numBombs;
    }

    /**
     * Генерирует массив ячеек для поля игры
     * Заполняет данные о количестве бомб
     * Назначает ячейчам свойства around
     * @return массив ячеек для игры
     */

    /**
     *
     * @param resX количество ячеек по оси
     * @param resY количество ячеек по оси
     * @param numBombs число бомб на поле
     * @return
     */
    @Override
    public Cell[][] generate(int resX, int resY, int numBombs) {

        this.numBombs = numBombs; //задаем число бомб
        this.cells = new Cell[resX][resY]; //определяем число ячеек

        boolean[][] bombs = new boolean[resX][resY]; //массив распределения бомб

        //распределяем бомбы по полю в случайном порядке
        if (numBombs < resX*resY) {
            int numGeneratedBombs = 0;
            Random rand = new Random();
            while (numGeneratedBombs < this.numBombs) {
                int x = rand.nextInt(resX);
                int y = rand.nextInt(resY);
                if (bombs[x][y] != true) {
                    bombs[x][y] = true;
                    numGeneratedBombs++;
                }
            }
        }

        //в соответствии с распределением бомб заполняем массив ячеек
        for (int x = 0; x != cells.length; x++){
            for (int y = 0; y != cells[0].length; y++){
                cells[x][y] = new GuiCell(bombs[x][y]);
            }
        }

        this.setCellsAround(); //устанавливаем свойство around для ячеек

        return this.cells;
    }

    /**
     * назначает всем ячейкам Cell[][] свойство around
     */
    private void setCellsAround(){
        for (int x = 0; x != cells.length; x++){
            for (int y = 0; y != cells[0].length; y++){
                cells[x][y].setAround(countCellAround(x, y));
            }
        }
    }

    /**
     * подсчитывает для ячейки число бомб вокруг нее на поле
     * @param x координата
     * @param y координата
     * @return число бомб вокруг ячейки
     */
    private int countCellAround(int x, int y){
        int toReturn = 0;
        for (int inX = x-1; inX <= x+1; inX++){
            for(int inY = y-1; inY <= y+1; inY++){
                if(inX < 0 || inY < 0 || inX >= cells.length || inY >= cells[0].length){
                    continue;
                } else if (cells[inX][inY].isBomb()) {
                    toReturn++;}
            }
        }
        return toReturn;
    }

}
