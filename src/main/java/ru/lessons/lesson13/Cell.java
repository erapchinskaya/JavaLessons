package ru.lessons.lesson13;

import java.awt.*;


/**
 * Интерфейс ячейки
 */
public interface Cell<T> {

    /**
     * устанавливает значение свойства around
     * @param around присваеваемое значение
     */
    void setAround(int around);

    /**
     * получает значение свойства around
     * @return значение
     */
    int getAround();

    /**
     * узнает бомба или нет
     * @return true если бомба, false если нет
     */
    boolean isBomb();

    /**
     * узнает отмечена ли ячейка как бомба или нет
     * @return true если отмечена, false если нет
     */
    boolean isSuggestBomb();

    /**
     * узнает открыта ли ячейка или нет
     * @return true если открыта, false если нет
     */
    boolean isOpen();

    /**
     * помечает ячейку как бомбу
     */
    void suggestBomb();

    /**
     * открывает ячейку
     */
    void open();

    /**
     * возвращают картинки для ячеек
     */
    Image getImg();
    Image getEmptyImg();
    Image getGameOverImg();
    Image getFinishImg();

}
