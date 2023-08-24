package org.example;

import java.util.Comparator;

public interface MyList<T> {
    int size();
    boolean isEmpty();
    boolean add(T e);
    boolean remove(Object o);
    void clear();
    T get(int index);
    void add(int index, T element);
    T remove(int index);

    void sort(Comparator<? super T> c);
}
