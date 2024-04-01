package com.group.libraryapp.domain.book;

public class Book {

    String name;

    public Book(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
