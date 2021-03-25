package com.tsiupa.dao;

import org.springframework.stereotype.Component;
import com.tsiupa.models.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDao {
    private static int PEOPLE_COUNT;
    private List<Book> books;

    {
        books = new ArrayList<>();

        books.add(new Book(++PEOPLE_COUNT, "Arthur Conan Doyle \"The White Company\" ", "+380971509644"));
        books.add(new Book(++PEOPLE_COUNT, "George Orwell \"1984\" ", "+380971509644"));
        books.add(new Book(++PEOPLE_COUNT, "Erich Maria Remarque \"Three Comrade\" ", "+380971509644"));
        books.add(new Book(++PEOPLE_COUNT, "Mario Puzo \"The Godfather\" ", "+380971509644"));
    }

    public List<Book> index() {
        return books;
    }

    public Book show(int id) {
        return books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }
    
    public void save(Book book) {
    	book.setId(++PEOPLE_COUNT);
    	books.add(book);
    }
    
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = show(id);

        bookToBeUpdated.setName(updatedBook.getName());
    }

    public void delete(int id) {
        books.removeIf(p -> p.getId() == id);
    }
}


