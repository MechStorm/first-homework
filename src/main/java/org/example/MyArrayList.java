package org.example;

import java.util.*;

/**
 * Realization of own ArrayList, class MyArrayList
 * */
public class MyArrayList<T> implements MyList<T> {

    /**
     * Unique ID used during serialization
     */
    private static final long serialVersionUID = 1L;
    /**
     * Create initial capacity for array
     */
    private static final int INITIAL_CAPACITY = 16;
    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_INSTANCES = {};
    /**
     * Shared empty array instance used for default sized empty instances.
     * We distinguish this from EMPTY_INSTANCES to know how much to inflate when first element is added.
     */
    private static final Object[] DEFAULT_EMPTY_INSTANCES = {};
    /**
     * The array buffer into which the elements of the ArrayList are stored
     */
    transient Object[] dynamicData;
    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * Constructs an empty list with an initial capacity of sixteen.
     */
    public MyArrayList() {
        this.dynamicData = DEFAULT_EMPTY_INSTANCES;
    }
    /**
     * Constructs an empty list with the specified initial capacity.
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.dynamicData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.dynamicData = EMPTY_INSTANCES;
        } else {
            throw new IllegalArgumentException("Illegal capacity" + initialCapacity);
        }
    }

    /**
     *Constructs a list containing the elements of the specified collection,
     *  in the order they are returned by the collection's iterator.
     * @param t the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
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

    /**
     * Return of number of elements in this list
     * @return number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks this list for null elements
     * @return true or false depending on the verification
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Increases the capacity to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     * @param minCapacity the desired minimum capacity
     * @return new object with increased capacity, and copied data
     * @throws OutOfMemoryError if minCapacity is less than zero
     */
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

    /**
     * Supporting method, that checks if the array is not full and grow it if necessary
     * @param t transmitted element
     * @param dynamicData current array with data
     * @param s size of array
     */
    private void add(T t, Object[] dynamicData, int s) {
        if (s == dynamicData.length)
            dynamicData = grow();
        dynamicData[s] = t;
        size = s + 1;
    }

    /**
     * Check correct transmitted index
     * @param idx
     * @throws IndexOutOfBoundsException if the index more than size of the array, or less than zero
     */
    private void rangeCheckForAdd(int idx) {
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Index: " + idx + ", size: " + size);
        }
    }

    /**
     * Add the element to the array
     * @param t
     * @return true, if method worked correctly
     */
    @Override
    public boolean add(T t) {
        add(t, dynamicData, size);
        return true;
    }

    /**
     * Add element to the array by index
     * @param index index to which the value should be set
     * @param element transmitted value
     */
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

    /**
     * Private remove method that skips bounds checking and does not return the value removed
     * @param es array
     * @param idx index at the array
     */
    private void fastRemove(Object[] es, int idx) {
        final int newSize;
        if ((newSize = size - 1) > idx)
            System.arraycopy(es, idx + 1, es, idx, newSize - idx);
        es[size = newSize] = null;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = dynamicData;
        T oldVal = (T) es[index];
        fastRemove(es, index);
        return oldVal;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list does not contain the element, it is unchanged.
     * More formally, removes the element with the lowest index i such that
     * Objects.equals(o, get(i)) (if such an element exists).
     * Returns true if this list contained the specified element
     * (or equivalently, if this list changed as a result of the call).
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
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

    /**
     * Remove all elements in the array
     */
    @Override
    public void clear() {
        final Object[] elements = dynamicData;
        for (int to = size, from = size = 0; from < to; from++) {
            elements[from] = null;
        }
    }

    /**
     * Return value in the list by the specified index
     * @param idx
     * @return value in the list
     */
    @SuppressWarnings("unchecked")
    T elementData(int idx) {
        return (T) dynamicData[idx];
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    /**
     * implementation of the default sort method, which contains sorting using the Arrays class
     * @param c an object that implements the Comparator interface
     */
    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) dynamicData, 0, size, c);
    }

    /**
     * implementation of the custom sort method, which contains main implementation method quickSortImpl,
     * partition and swap methods as supporting methods
     * @param c an object that implements the Comparator interface
     */
    public void quickSort(Comparator<? super T> c) {
        quickSortImpl(c, 0, size - 1);
    }

    /**
     * if last > first, then it looks for a partition index element in the array and sorts the left and right parts,
     * leaving the partition index element between them
     * @param c an object that implements the Comparator interface
     * @param first first element of the array
     * @param last last element of the array
     */
    private void quickSortImpl(Comparator<? super T> c, int first, int last) {
        if (last > first) {
            int partitionIdx = partition(c, first, last);
            quickSortImpl(c, first, partitionIdx-1);
            quickSortImpl(c, partitionIdx+1, last);
        }
    }

    /**
     *
     * @param c an object that implements the Comparator interface
     * @param first first element of the array
     * @param last last element of the array
     * @return the pivot element
     */
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

    /**
     * Swaps the elements
     * @param x one of the two passed array elements
     * @param y the other element
     */
    private void swap(int x, int y) {
        T tmp = (T)dynamicData[x];
        dynamicData[x] = dynamicData[y];
        dynamicData[y] = tmp;
    }
}