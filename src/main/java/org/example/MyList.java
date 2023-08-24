package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;

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

//    default void sort(Comparator<? super T> c) {
//        Object[] a = this.toArray();
//        Arrays.sort(a, (Comparator) c);
//        ListIterator<T> i = this.listIterator();
//        for (Object e : a) {
//            i.next();
//            i.set((T) e);
//        }
//    }
}
