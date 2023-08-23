package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;

public interface MyList<T> {
    int size();
    boolean isEmpty();
    Object[] toArray();
    boolean add(T e);
    boolean remove(Object o);
    boolean addAll(Collection<? extends T> c);
    boolean addAll(int index, Collection<? extends T> c);
    void clear();
    T get(int index);
    void add(int index, T element);
    T remove(int index);
    ListIterator<T> listIterator();


    default void sort(Comparator<? super T> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<T> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((T) e);
        }
    }
}
