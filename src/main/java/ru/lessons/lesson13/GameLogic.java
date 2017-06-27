package ru.lessons.lesson13;

import ru.lessons.lesson13.gui.Environment;

/**
 * Интерфейс GameLogic
 */
public interface GameLogic {

    /**
     * Функция самооткрытия для ячейки
     * вызывается в случае если пользователь открыл ячейку вокруг которой нет бомб
     * @param x позиция
     * @param y позиция
     */
    void selfOpen(int x, int y);

    /**
     * Подготовка логики для работы, заполнение cells и env
     * @param e экземпляр для хранения переменных о состоянии игры
     * @param cells массив ячеек
     */
    void loadBoard(Environment e, Cell[][] cells);

    /**
     * Проверяет должны ли мы взорваться на ячейке
     * @param x позиция ячейки
     * @param y позиция ячейки
     * @return true если взорвались и false если нет
     */
    boolean shouldBang(int x, int y);

    /**
     * Проверяет условия успешного решения игры
     * @return true если выполнены условия финиша false если нет
     */
    boolean finish();

    /**
     * Возвращает признак финала игры
     * @return true если финал, false если нет
     */
    boolean finalized();

    /**
     * Помечает ячейку как бомбу либо открывает ее.
     * @param x позиция ячейки
     * @param y позиция ячейки
     * @param bomb признак бомбы
     */
    void suggest(int x, int y, boolean bomb);
}
