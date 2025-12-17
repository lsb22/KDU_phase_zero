package com.kickdrum.library.controller;

import com.kickdrum.library.model.Book;
import com.kickdrum.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        if(createdBook != null) {
            return ResponseEntity.ok(createdBook);
        } else return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable int bookId) {
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> findBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findBookByTitle(title));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<String,String>();
        errors.put("timestamp",LocalDateTime.now().toString());
        errors.put("error","Validation failed");
        // HttpStatus.BAD_REQUEST.value(): returns code for Bad Request : 400
        // Bad Request indicates client error
        errors.put("status", Integer.toString(HttpStatus.BAD_REQUEST.value()));

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleRuntimeExceptions(Exception ex){
        Map<String,String> errors = new HashMap<String,String>();
        errors.put("message",ex.getMessage());
        errors.put("timestamp", LocalDateTime.now().toString());

        if(ex.getMessage().contains("already exists")) {
            // HttpStatus.CONFLICT.value(): returns code for Conflict : 409
            // Conflict indicates conflict with the current state of the target resource.
            errors.put("status", Integer.toString(HttpStatus.CONFLICT.value()));
            errors.put("error","Duplicate Entry");
            return ResponseEntity.badRequest().body(errors);
        } else if(ex.getMessage().contains("not found")) {
            errors.put("status",Integer.toString(HttpStatus.NOT_FOUND.value()));
            errors.put("error","Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        } else {
            // HttpStatus.INTERNAL_SERVER_ERROR.value(): returns code for Server Error : 500
            errors.put("status", Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            errors.put("error","Internal Server Error");
        }
        return ResponseEntity.internalServerError().body(errors);
    }
}
