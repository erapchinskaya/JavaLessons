package ru.lessons.lesson12;

/**
 * Created by User on 09.06.2017.
 */
public class Counter {
    private int amount;
    public synchronized int increase(){
        amount++;
        return amount;
    }
}
