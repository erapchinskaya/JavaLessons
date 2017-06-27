package ru.lessons.lesson13;

/**
 * Интерфейс UserAction используется для взаимодействия с пользователем
 */
public interface UserAction {

    /**
     * начинает игру
     */
    void initGame();

    /**
     * вызывается при нажатии на ячейку
     * @param x позиция
     * @param y позиция
     * @param bomb признак того что пользователь отметил ячейку как бомбу
     */
    void select(int x, int y, boolean bomb);
}
