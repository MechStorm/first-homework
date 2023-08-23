package org.example;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Book> myArrayList = new MyArrayList<>();

        Book b1 = new Book("Spring in action", 1);
        Book b2 = new Book("Actions and reactions", 2);

        myArrayList.add(b1);
        myArrayList.add(b2);
        Comparator<Book> byName = Comparator.comparing(Book::getBookName);
//        myArrayList.sort(byName);
        myArrayList.quickSort(byName);


        System.out.println(myArrayList.get(0));
    }
}

class Book implements Comparable<Book>{
    String bookName;
    int id;

    public Book(String name, int id) {
        this.bookName = name;
        this.id = id;
    }

    public String toString() {
        return id + ": " + bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public int compareTo(Book o) {
        return this.bookName.compareTo(o.bookName);
    }
}