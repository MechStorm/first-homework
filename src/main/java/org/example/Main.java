package org.example;

public class Main {
    public static void main(String[] args) {
    }
}
class Book {
    private String bookName;
    private int id;

    public Book(String name, int id) {
        this.bookName = name;
        this.id = id;
    }

//    public String toString() {
//        return id + ": " + bookName;
//    }

    public String getBookName() {
        return bookName;
    }

    public int getId() {
        return id;
    }

}