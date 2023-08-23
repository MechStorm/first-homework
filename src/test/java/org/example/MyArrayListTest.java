package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<String> myArrayList;

    @BeforeEach
    void setUp() {
        myArrayList = new MyArrayList<>();
    }

    @Test
    void size() {
        myArrayList.add("one");
        myArrayList.add("two");
        myArrayList.add("three");
        int size = myArrayList.size();
        Assertions.assertEquals(3, size);
    }

    @Test
    void isEmpty() {
        boolean isEmpty = myArrayList.isEmpty();
        Assertions.assertTrue(isEmpty);
    }

    @Test
    void add() {
        boolean addTestVal = myArrayList.add("test");
        Assertions.assertTrue(addTestVal);
        Assertions.assertEquals("test", myArrayList.get(0));
        Assertions.assertEquals(1, myArrayList.size());
    }
//
//    @Test
//    void testAdd() {
//    }
//
//    @Test
//    void remove() {
//    }
//
//    @Test
//    void testRemove() {
//    }
//
//    @Test
//    void clear() {
//    }
//
//    @Test
//    void get() {
//    }
//
//    @Test
//    void sort() {
//    }
//
//    @Test
//    void quickSort() {
//    }
}