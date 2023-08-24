package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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
        myArrayList.add("four");
        myArrayList.add("five");
        myArrayList.add("six");
        myArrayList.add("seven");
        myArrayList.add("eight");
        myArrayList.add("nine");
        myArrayList.add("ten");
        myArrayList.add("eleven");
        myArrayList.add("twelve");
        myArrayList.add("thirteen");
        myArrayList.add("fourteen");
        myArrayList.add("fifteen");
        myArrayList.add("sixteen");
        myArrayList.add("seventeen");

        int size = myArrayList.size();
        Assertions.assertEquals(17, size);
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

    @Test
    void testAdd() {
        myArrayList.add("Max payne");
        myArrayList.add("GTA");
        myArrayList.add("Witcher");
        myArrayList.add("Divinity");
        myArrayList.add("Baldur's Gate");
        myArrayList.add("Bloodrayne");
        myArrayList.add("Star Wars");
        myArrayList.add("Alien: isolation");
        myArrayList.add("Dead Space");
        myArrayList.add("Kingdom Come");
        myArrayList.add("Resident Evil");
        myArrayList.add("Honkai: Star Rail");
        myArrayList.add("Genshin Impact");
        myArrayList.add("OSU");
        myArrayList.add("Darkest Dungeon");
        myArrayList.add("Mortal Kombat");
        Assertions.assertEquals("Star Wars", myArrayList.get(6));
        myArrayList.add(0, "Red dead Redemption");

        int size = myArrayList.size();
        Assertions.assertEquals(17, size);
        Assertions.assertEquals("Star Wars", myArrayList.get(7));
    }

    @Test
    void remove() {
        myArrayList.add("Tesla");
        myArrayList.add("BMW");
        myArrayList.add("Falcon-9");

        String oldValue = myArrayList.remove(2);
        Assertions.assertEquals("Falcon-9", oldValue);
    }

    @Test
    void testRemove() {
        MyArrayList<Book> myArrayList = new MyArrayList<>();
        Book b1 = new Book("Spring in action", 1);
        Book b2 = new Book("Unity in action", 2);
        Book b3 = new Book("Effective Java", 3);
        Book b4 = null;
        myArrayList.add(b1);
        myArrayList.add(b2);
        myArrayList.add(b3);
        myArrayList.add(b4);

        boolean isRemove = myArrayList.remove(b2);
        Assertions.assertTrue(isRemove);

        isRemove = myArrayList.remove(b4);
        Assertions.assertTrue(isRemove);

        isRemove = myArrayList.remove(b4);
        Assertions.assertFalse(isRemove);
        Assertions.assertEquals(2, myArrayList.size());
    }

    @Test
    void clear() {
        myArrayList.add("one");
        myArrayList.add("two");
        myArrayList.add("three");

        myArrayList.clear();

        Assertions.assertEquals(0, myArrayList.size());
    }

    @Test
    void get() {
        myArrayList.add("one");
        myArrayList.add("two");
        myArrayList.add("three");

        Assertions.assertEquals("one", myArrayList.get(0));
    }

    @Test
    void sort() {
        MyArrayList<Book> myArrayList = new MyArrayList<>();
        Book b1 = new Book("Spring in action", 1);
        Book b2 = new Book("Algorithms", 2);

        myArrayList.add(b1);
        myArrayList.add(b2);
        Comparator<Book> byName = Comparator.comparing(Book::getBookName);
        Assertions.assertEquals(1, myArrayList.get(0).getId());
        myArrayList.sort(byName);

        Assertions.assertEquals(2, myArrayList.get(0).getId());
    }

    @Test
    void quickSort() {
        MyArrayList<Book> myArrayList = new MyArrayList<>();
        Book b1 = new Book("Spring in action", 1);
        Book b2 = new Book("Algorithms", 2);

        myArrayList.add(b2);
        myArrayList.add(b1);
        Comparator<Book> byName = Comparator.comparing(Book::getId);
        Assertions.assertEquals(2, myArrayList.get(0).getId());
        myArrayList.quickSort(byName);
        Assertions.assertEquals(1, myArrayList.get(0).getId());
    }

    @Test
    void myArrayListWithCustomCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-5));
    }

    @Test
    void myArrayListWithCollection() {
        Set<Integer> arr = new HashSet<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        MyArrayList<Integer> arrData = new MyArrayList<>(arr);

        Assertions.assertEquals(1, arrData.get(0));
        Assertions.assertEquals(3, arrData.size());
    }
    @Test
    void testMain() {
        Main.main(new String[]{"1, 2, 3"});
    }
}