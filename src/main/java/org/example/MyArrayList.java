package org.example;

import java.util.*;

/**
 * realisation of own ArrayList, class MyArrayList
 * */
public class MyArrayList<T> implements List<T> {
    private static final long serialVersionUID = 1L;
    /**
     * Create initial capacity for array
     */
    private static final int INITIAL_CAPACITY = 16;
    private static final Object[] EMPTY_INSTANCES = {};
    private static final Object[] DEFAULT_EMPTY_INSTANCES = {};
    transient Object[] dynamicData;
    private int size;

    public MyArrayList() {
        this.dynamicData = DEFAULT_EMPTY_INSTANCES;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.dynamicData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.dynamicData = EMPTY_INSTANCES;
        } else {
            throw new IllegalArgumentException("Illegal capacity" + initialCapacity);
        }
    }

    public MyArrayList(Collection<? extends T> t) {
        Object[] x = t.toArray();
        if ((size = x.length) != 0) {
            if (t.getClass() == ArrayList.class) {
                dynamicData = x;
            } else {
                dynamicData = Arrays.copyOf(x, size, Object[].class);
            }
        } else {
            dynamicData = EMPTY_INSTANCES;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = dynamicData.length;
        if (oldCapacity > 0 || dynamicData != DEFAULT_EMPTY_INSTANCES) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            return dynamicData = Arrays.copyOf(dynamicData, newCapacity);
        } else {
            return dynamicData = new Object[Math.max(INITIAL_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    private void add(T t, Object[] dynamicData, int s) {
        if (s == dynamicData.length)
            dynamicData = grow();
        dynamicData[s] = t;
        size = s + 1;
    }

    private void rangeCheckForAdd(int idx) {
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Index: " + idx + ", size: " + size);
        }
    }

    @Override
    public boolean add(T t) {
        add(t, dynamicData, size);
        return true;
    }

    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        final int s;
        Object[] dynamicData;
        if ((s = size) == (dynamicData = this.dynamicData).length)
            dynamicData = grow();
        System.arraycopy(dynamicData, index, dynamicData, index + 1, size - index);
        dynamicData[index] = element;
        size = s + 1;
    }

    private void fastRemove(Object[] es, int idx) {
        final int newSize;
        if ((newSize = size - 1) > idx)
            System.arraycopy(es, idx + 1, es, idx, newSize - idx);
        es[size = newSize] = null;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = dynamicData;
        T oldVal = (T) es[index];
        fastRemove(es, index);
        return oldVal;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] es = dynamicData;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null) {
                        break found;
                    }
            } else {
                for (; i < size; i++) {
                    if (o.equals(es[i])) {
                        break found;
                    }
                }
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] x = c.toArray();
        int numNew = x.length;
        if (numNew == 0)
            return false;
        Object[] dynamicData;
        final int s;
        if (numNew > (dynamicData = this.dynamicData).length - (s = size))
            dynamicData = grow(s + numNew);
        System.arraycopy(x, 0, dynamicData, s, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        rangeCheckForAdd(index);
        Object[] x = c.toArray();
        int numNew = x.length;
        if (numNew == 0)
            return false;
        Object[] dynamicData;
        final int s;
        if (numNew > (dynamicData = this.dynamicData).length - (s = size))
            dynamicData = grow(s + numNew);
        int numMoved = s - index;
        if (numMoved > 0)
            System.arraycopy(dynamicData, index, dynamicData, index + numNew, numMoved);
        System.arraycopy(x, 0, dynamicData, index, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Implementation of clear method.
     */
    @Override
    public void clear() {
        final Object[] elements = dynamicData;
        for (int to = size, from = size = 0; from < to; from++) {
            elements[from] = null;
        }
    }

    @SuppressWarnings("unchecked")
    T elementData(int idx) {
        return (T) dynamicData[idx];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) dynamicData, 0, size, c);
    }

    public void quickSort(Comparator<? super T> c) {
        quickSortImpl(c, 0, size - 1);
    }

    private void quickSortImpl(Comparator<? super T> c, int first, int last) {
        if (last > first) {
            int pivot = partition(c, first, last);
            quickSortImpl(c, first, pivot-1);
            quickSortImpl(c, pivot+1, last);
        }
    }

    private int partition(Comparator<? super T> c, int first, int last) {
        int middle = first + (last - first) / 2;
        T pivot = (T) dynamicData[middle];

        swap(middle, last);

        int x = first;
        for (int y = first; y < last; y++) {
            if (c.compare((T)dynamicData[y], pivot) < 0) {
                swap(x, y);
                x++;
            }
        }

        swap(x, last);

        return x;
    }

    private void swap(int x, int y) {
        T tmp = (T)dynamicData[x];
        dynamicData[x] = dynamicData[y];
        dynamicData[y] = tmp;
    }
}