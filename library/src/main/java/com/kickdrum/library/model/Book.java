package com.kickdrum.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

// @Entity: Creates a table for this class
@Entity
public class Book {
    // @ID: Specifies Primary key
    // @GeneratedValue: Value generated automatically
    // GenerationType.AUTO :  Value is incremented sequentially
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    // these fields will be mapped to columns in the table
    // @NotBlank: performs validation at controller or service layer
    // , and it doesn't allow null, empty values, even string with just
    // spaces will be rejected
    // @Column(nullable = false) acts at database layer, provide extra security,
    // it rejects null values. Even if invalid data passes service/controller
    // layer, this acts as second line of defense

    @Column(nullable = false, unique = true)
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "author is required")
    private String author;

    // @ColumnDefault() to provide default values at database level
    // also initialize so it immediately gets the default value
    @ColumnDefault("'Custom'")
    private String genre = "Custom";

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be greater than equal to 0")
    private double price;

    @ColumnDefault("0.0")
    @Min(value = 0, message = "rating must be greater than equal to 0")
    private double rating = 0.0;

    @NotBlank(message = "language is required")
    private String language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Book() {}

    public Book(String title, String author, double price, String language) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.language = language;
    }
}
