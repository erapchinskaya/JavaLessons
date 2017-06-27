package ru.lessons.lesson12;

import java.util.LinkedList;

/**
 * Created by User on 09.06.2017.
 */
public class BlockQueue<T> {
    public final LinkedList<T> queue = new LinkedList<>();

    public void push(final T t){
        synchronized (this.queue) {

            // здесь начинаются действия
            this.queue.add(t);
            this.queue.notifyAll();
        }
    }

    public T poll(){
        synchronized (this.queue) {

            // здесь начинаются действия

            while (this.queue.isEmpty()){
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {e.printStackTrace();}
            }
            return this.queue.remove();

        }
    }
}
