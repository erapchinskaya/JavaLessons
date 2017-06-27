package ru.lessons.lesson13.gui;

import ru.lessons.lesson13.Cell;

import java.awt.*;

/**
 * Реализация ячейки
 */
public class GuiCell implements Cell<Graphics> {

    /** размер ячейки */
    public static final int PADDING = 82;

    /** Путь к файлам изображений */
    private final String PATH = "c:\\jProject\\java-collections\\src\\main\\java\\ru\\lessons\\lesson13\\icons\\";

    /** Признак бомбы */
    private boolean bomb;

    /** Число бомб вокруг ячейки */
    private int around;

    /** Признак того что ячейка отмечена как бомба */
    private boolean suggestBomb = false;

    /** Признак того что ячейка открыта */
    private boolean open = false;

    /**
     * Конструктор
     * @param bomb признак бомбы
     */
    public GuiCell(boolean bomb) {
        this.bomb = bomb;
    }

    @Override
    public void setAround(int around) {
        this.around = around;
    }

    @Override
    public int getAround() {
        return around;
    }

    @Override
    public boolean isBomb() {
        return this.bomb;
    }

    @Override
    public boolean isSuggestBomb() {
        return this.suggestBomb;
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }

    @Override
    public void suggestBomb() {
        if (!this.open) this.suggestBomb = !this.suggestBomb;
    }

    @Override
    public void open() {
        if (!this.suggestBomb) this.open = true;
    }

    @Override
    public Image getImg(){
        Image img = Toolkit.getDefaultToolkit().getImage(this.PATH+"unverified.png");
        if(this.suggestBomb){
            img = Toolkit.getDefaultToolkit().getImage(this.PATH+"suggestBomb.png");
        }else if (this.open){
            img = this.getEmptyImg();
        }
        return img;
    }

    @Override
    public Image getEmptyImg() {
        Image img = Toolkit.getDefaultToolkit().getImage(this.PATH+"empty.png");
        switch (this.around){
            case 0: break;
            case 1: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"1.png"); break;
            case 2: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"2.png"); break;
            case 3: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"3.png"); break;
            case 4: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"4.png"); break;
            case 5: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"5.png"); break;
            case 6: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"6.png"); break;
            case 7: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"7.png"); break;
            case 8: img = Toolkit.getDefaultToolkit().getImage(this.PATH+"8.png"); break;
        }
        return img;
    }

    @Override
    public Image getGameOverImg() {
        Image img = this.getEmptyImg();
        if (this.isBomb() && this.isOpen()){
            img = Toolkit.getDefaultToolkit().getImage(this.PATH+"gameOverBomb.png");
        } else if(this.isBomb() && this.isSuggestBomb()){
            img = Toolkit.getDefaultToolkit().getImage(this.PATH+"suggestBomb.png");
        } else if(this.isBomb() && !this.isSuggestBomb()){
            img = Toolkit.getDefaultToolkit().getImage(this.PATH+"finishBomb.png");
        } else if(!this.isBomb() && this.isSuggestBomb()){
            img = Toolkit.getDefaultToolkit().getImage(this.PATH+"falseSuggestBomb.png");
        }
        return img;
    }

    @Override
    public Image getFinishImg() {
        Image img = this.getEmptyImg();
        if (this.isBomb() && this.isSuggestBomb()){
            img = Toolkit.getDefaultToolkit().getImage(this.PATH+"suggestBomb.png");
        }
        return img;
    }
}