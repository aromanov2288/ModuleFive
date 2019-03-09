package ru.romanov.modulefive.form;

import java.util.Objects;

public class NewBook {

    private String author;

    private String name;

    private Long genreId;

    public NewBook() {
    }

    public NewBook(String author, String name, Long genreId) {
        this.author = author;
        this.name = name;
        this.genreId = genreId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewBook newBook = (NewBook) o;
        return Objects.equals(author, newBook.author) &&
                Objects.equals(name, newBook.name) &&
                Objects.equals(genreId, newBook.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, genreId);
    }
}
