package com.kickdrum.library.service;

import com.kickdrum.library.model.Book;
import com.kickdrum.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch(DataIntegrityViolationException e) {
            // DataIntegrityViolationException: indicates database error like not unique, adding null
            throw new RuntimeException("Database error: "+ e.getMessage());
        }
    }

    public Book findBookById(int id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id: "+id+" not found"));
    }

    public Book findBookByTitle(String title) {
        return bookRepository
                .findBookByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book with title: "+title+" not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBookById(int id) {
        if(!bookRepository.existsById(id)) {
            throw new RuntimeException("Book with id: "+id+" doesn't exist. So it can't be deleted.");
        }

        bookRepository.deleteById(id);
    }
}
