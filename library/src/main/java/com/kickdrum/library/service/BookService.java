package com.kickdrum.library.service;

import com.kickdrum.library.model.Book;
import com.kickdrum.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch(DataIntegrityViolationException e) {
            // DataIntegrityViolationException: indicated database error like not unique, adding null
            if(e.getMessage().contains("Duplicate entry") || e.getMessage().contains("unique constraint")) {
                throw new RuntimeException("Book: "+book.getName()+" already exists");
            }
            throw new RuntimeException("Database error: "+ e.getMessage());
        }
    }
}
