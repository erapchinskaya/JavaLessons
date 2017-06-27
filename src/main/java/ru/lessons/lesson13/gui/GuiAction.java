package ru.lessons.lesson13.gui;

import ru.lessons.lesson13.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Реализация взаимодействия с пользователем
 * Новая игра инициируется в методе initGame()
 *
 */
public class GuiAction implements UserAction, ActionListener, MouseListener{

    /** Генератор ячеек */
    private final GeneratorBoard generator;

    /** Доска */
    private final GuiBoard board;

    /** Логика приложения */
    private final GameLogic logic;

    /** Размер поля по оси*/
    private final int RESOLUTION_X = 6;

    /** Размер поля по оси*/
    private final int RESOLUTION_Y = 6;

    /** Число бомб*/
    private final int NUM_BOMBS = 9;

    /**
     * Кончтруктор
     * @param generator генератор ячеек
     * @param board доска
     * @param logic логика приложения
     */
    public GuiAction(GeneratorBoard generator, GuiBoard board, GameLogic logic) {
        this.generator = generator;
        this.board = board;
        this.logic = logic;
        this.board.addMouseListener(this);

    }

    /**
     * Начинает новую игру.
     * В методе создается объект 'ячеек поля' и объект 'Enviroment' хранящий данные о ходе игры.
     * Далее эти экземпляры передаются в объекты игровой доски (board) и логики(logic)
     * В результате внутри (board) и (logic) мы оперируем общими объектами
     * что позволяет исключить необходимость передачи параметров между (board) и (logic)
     */
    @Override
    public void initGame() {
        final Cell[][] cells = generator.generate(RESOLUTION_X,RESOLUTION_Y,NUM_BOMBS); //создаем объект ячеек
        final Environment e = new Environment(generator.getNumBombs()); //создаем переменную для хранения информации о ходе игры
        this.board.drawBoard(e, cells); //передаем объекты в board и рисуем доску
        this.logic.loadBoard(e, cells);  //передаем объекты в logic
    }

    /**
     * Вызывается при нажатии на ячейку и обрабатывает действие игрока
     * @param x позиция
     * @param y позиция
     * @param bomb признак того что пользователь отметил ячейку как бомбу
     */
    @Override
    public void select(int x, int y, boolean bomb) {
        if(!this.logic.finalized()) {
            this.logic.suggest(x, y, bomb); //отметить либо открыть ячейку
            this.logic.shouldBang(x, y); //проверить на взрыв
            this.logic.finish(); //проверить на финиш
            this.board.repaint(); //отрисовать доску заново
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.initGame();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean bomb = (e.getButton() != 1);
        this.select(board.getCellOnX(e.getX()),board.getCellOnY(e.getY()), bomb);
        board.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
