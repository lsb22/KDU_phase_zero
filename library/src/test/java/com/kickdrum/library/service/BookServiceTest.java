package com.kickdrum.library.service;

import com.kickdrum.library.model.Book;
import com.kickdrum.library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void addBookShouldAddBookSuccessfully() {
        Book bookToAdd = new Book("Book1","Ling",1000.000,"Kannada");
        Book expectedBook = new Book("Book1","Ling",1000.000,"Kannada");

        Mockito.when(bookRepository.save(bookToAdd)).thenReturn(expectedBook);
        Book actualBook = bookService.addBook(bookToAdd);

        Assertions.assertEquals(expectedBook.getTitle(),actualBook.getTitle());
        Mockito.verify(bookRepository,Mockito.times(1)).save(bookToAdd);
    }

    @Test
    void addBookShouldThrowRuntimeExceptionForDuplicateEntry() {
        Book bookToAdd = new Book("Life","Ming",100.0,"Hindi");

        Mockito.when(bookRepository.save(bookToAdd)).thenThrow(new DataIntegrityViolationException("Book 'Life' is a duplicate entry"));

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,() -> {
            bookService.addBook(bookToAdd);
        });

        Assertions.assertEquals("Database error: Book 'Life' is a duplicate entry",runtimeException.getMessage());
    }

    @Test
    void findBookByIdShouldSuccessfullyReturnBookWithGivenId() {
        Book expectedBook = new Book("Book1","Ling",1000.000,"Kannada");
        expectedBook.setId(5);

        Mockito.when(bookRepository.findById(5)).thenReturn(Optional.of(expectedBook));
        Book actualBook = bookService.findBookById(5);

        Assertions.assertEquals(expectedBook.getId(),actualBook.getId());
        Assertions.assertEquals(expectedBook.getTitle(),actualBook.getTitle());
        Mockito.verify(bookRepository,Mockito.times(1)).findById(5);
    }

    @Test
    void findBookByIdShouldThrowRuntimeExceptionForInvalidIds() {
        int bookId = 5;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // I expect this error to happen when executing this particular code,
        // so catch it and return the exception object so that I can inspect it
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> {
            bookService.findBookById(bookId);
        });

        Assertions.assertEquals("Book with id: "+bookId+" not found",runtimeException.getMessage());
    }
}