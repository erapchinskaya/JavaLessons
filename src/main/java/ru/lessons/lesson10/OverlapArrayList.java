package ru.lessons.lesson10;

import java.text.MessageFormat;
import java.util.AbstractList;
import java.util.List;

/**
 * ЗАДАНИЕ: Реализовать совю собственную коллекцию ArrayList
 * из описания AbstractList:
         To implement an unmodifiable list, the programmer needs only to extend
         this class and provide implementations for the {@link #get(int)} and
         {@link List#size() size()} methods.

         To implement a modifiable list, the programmer must additionally
         override the {@link #set(int, Object) set(int, E)} method (which otherwise
         throws an {@code UnsupportedOperationException}).  If the list is
         variable-size the programmer must additionally override the
         {@link #add(int, Object) add(int, E)} and {@link #remove(int)} methods.
 * @param <E> Тип-параметр
 */
public class OverlapArrayList<E> extends AbstractList<E> implements List<E> {

    private final String INDEX_OUT_OF_BOUNDS_EXC_FORMAT = "Index \"{0}\" but size \"{1}\"";
    private final static int START_CAPACITY = 10;
    private int size;
    private E[] keeping;

    public OverlapArrayList() {
        this.keeping = (E[]) new Object[START_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public E get(int index) {
        rangeCheck(index);
        return keeping[index];
    }

    @Override
    public void add(int index, E newElement) {
        rangeCheck(index);
        if(needToIncSize()) incSize();
        System.arraycopy(this.keeping, index, this.keeping, index + 1, this.size - index);
        this.keeping[index] = newElement;
        this.size++;
    }

    @Override
    public E set(int index, E newElement) {
        E toReturn = this.get(index);
        this.keeping[index] = newElement;
        return toReturn;
    }

    @Override
    public E remove(int index) {
        E toReturn = this.get(index);
        System.arraycopy(this.keeping, index+1, this.keeping, index, this.size - index - 1);
        this.size--;
        this.keeping[this.size] = null;
        return toReturn;
    }

    private boolean needToIncSize() {
        return this.size == this.keeping.length;
    }

    private void incSize() {
        E[] tmpArray = (E[]) new Object[this.size + START_CAPACITY];
        System.arraycopy(this.keeping, 0, tmpArray, 0, this.size);
        this.keeping = tmpArray;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(MessageFormat.format(INDEX_OUT_OF_BOUNDS_EXC_FORMAT, index, this.size));
    }

}