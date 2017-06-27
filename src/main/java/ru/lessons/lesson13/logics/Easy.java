package ru.lessons.lesson13.logics;

import ru.lessons.lesson13.Cell;
import ru.lessons.lesson13.GameLogic;
import ru.lessons.lesson13.gui.Environment;

/**
 * Класс логики приложения
 */
public class Easy implements GameLogic {

    /** Ячейки */
    private Cell[][] cells;

    /** Экземпляр для хранения переменных о состоянии игры */
    private Environment env;

    /**
     * Подготовка логики для работы, заполнение cells и env
     * @param e экземпляр для хранения переменных о состоянии игры
     * @param cells массив ячеек
     */
    @Override
    public void loadBoard(Environment e, Cell[][] cells) {
        this.env = e;
        this.cells = cells;
    }

    /**
     * проверяет должны ли мы взорваться на ячейке
     * взрываемся в счучае если: это бомба и мы ее открыли
     * перед возвратом устанавливаем соответствующее значение для свойства GameOver объекта env
     * @param x позиция ячейки
     * @param y позиция ячейки
     * @return true если взорвались и false если нет
     */
    @Override
    public boolean shouldBang(int x, int y) {
        final Cell selected = this.cells[x][y];
        boolean toReturn = false;
        if(selected.isBomb() && selected.isOpen()){
            toReturn = true;
        }
        this.env.setGameOver(toReturn);
        return toReturn;
    }


    /**
     * Проверяет условия успешного решения игры
     * Игра считается завершенной в случаях:
     *      - все не открытые ячейки - бомбы (даже если они не помечены как бомбы)
     * Иначе проверяются все ячейки на следующие условия, означающие что игра не завершена
     *      - какая-либо ячейка с бомбой не отмечена как бомба
     *      - ячейка не открыта и при этом бомбы в ней нет
     *      - ячейка отмечена бомбой по ошибке
     * Перед возвратом устанавливаем соответствующее значение для свойства Finish объекта env
     * @return true если выполнены условия финиша false если нет
     */
    @Override
    public boolean finish() {
        boolean toReturn = true;
        if(!this.minEnclosedCells()) {
            for (Cell[] row: cells){
                for (Cell cell: row){
                    if (!cell.isSuggestBomb() && cell.isBomb()) toReturn = false;
                    if (!cell.isOpen() && !cell.isBomb()) toReturn = false;
                    if (cell.isSuggestBomb() && !cell.isBomb()) toReturn = false;
                }
            }
        }
        this.env.setFinish(toReturn);
        return toReturn;
    }

    /**
     * Помечает ячейку как бомбу либо открывает ее.
     * В случае если открываемая ячейка не окружена бомбами,
     * запускается самооткрытия всех граничащих с ней ячеек
     * @param x позиция ячейки
     * @param y позиция ячейки
     * @param bomb признак бомбы
     */
    @Override
    public void suggest(int x, int y, boolean bomb) {
        if (bomb){
            this.cells[x][y].suggestBomb();
        } else if (cells[x][y].getAround()==0 && !cells[x][y].isSuggestBomb()) {
            this.selfOpen(x, y);
        } else {
            this.cells[x][y].open();
        }
    }

    /**
     * Функция самооткрытия для ячейки
     * открывает себя и все граничащие ячейки
     * вызывает саму себя для граничащей ячейки если вокруг нее также нет бомб
     * @param x позиция ячейки
     * @param y позиция ячейки
     */
    @Override
    public void selfOpen(int x, int y){
        this.cells[x][y].open();
        for (int inX = x - 1; inX <= x + 1; inX++) {
            for (int inY = y - 1; inY <= y + 1; inY++) {
                if (
                        inX < 0 || inY < 0 ||
                                inX == x && inY == y ||
                                inX >= cells.length || inY >= cells[0].length
                        ) {
                    continue;
                } else if (cells[inX][inY].getAround() == 0 && !cells[inX][inY].isOpen()) {
                    this.selfOpen(inX, inY);
                } else {
                    cells[inX][inY].open();
                }
            }
        }
    }

    /**
     * Возвращает признак финала игры, основывась на данных из объекта env
     * @return true если финал, false если нет
     */
    @Override
    public boolean finalized(){
        return (env.isFinish() || env.isGameOver());
    }


    /**
     * Проверяет равно ли число НЕоткрытых ячеек общему числу бомб
     * @return true в случае если равно, false если нет
     */
    private boolean minEnclosedCells() {
        int numEnclosed = 0;
        for (int x = 0; x != cells.length; x++){
            for (int y = 0; y != cells[0].length; y++){
                if(!cells[x][y].isOpen()) numEnclosed++;
            }
        }
        boolean toReturn = false;
        if(numEnclosed == env.getNumBombs()){
            toReturn = true;
            this.selfSuggest();
        }
        return toReturn;
    }

    /**
     * Помечает неотмеченные ячейки с бомбами
     * Использовано для наглядности внешнего вида
     */
    private void selfSuggest(){
        for (Cell[] row: cells){
            for (Cell cell: row){
                if(cell.isBomb() && !cell.isSuggestBomb()){
                    cell.suggestBomb();
                }
            }
        }
    }
}
