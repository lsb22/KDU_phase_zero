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

        Mockito.when(bookRepository.save(bookToAdd)).thenThrow(new RuntimeException("Book Life already exists"));

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,() -> {
            bookService.addBook(bookToAdd);
        });

        Assertions.assertEquals("Book Life already exists",runtimeException.getMessage());
    }
}