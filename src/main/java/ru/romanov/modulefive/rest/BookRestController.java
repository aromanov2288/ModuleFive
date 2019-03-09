package ru.romanov.modulefive.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.modulefive.domain.Book;
import ru.romanov.modulefive.domain.Genre;
import ru.romanov.modulefive.form.NewBook;
import ru.romanov.modulefive.form.UpdatedBook;
import ru.romanov.modulefive.repository.BookRepository;
import ru.romanov.modulefive.repository.GenreRepository;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/books"})
public class BookRestController {

    private BookRepository bookRepository;

    private GenreRepository genreRepository;

    @Autowired
    public BookRestController(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody NewBook newBook) {
        String author = newBook.getAuthor();
        String name = newBook.getName();
        Long genreId = newBook.getGenreId();
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", genreId)));
        return bookRepository.save(new Book(author, name, genre));
    }

    @GetMapping(value = "{id}")
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Book with id %d not found", id)));
    }

    @PutMapping
    public Book updateBook(@RequestBody UpdatedBook updatedBook) {
        Long id = updatedBook.getId();
        String author = updatedBook.getAuthor();
        String name = updatedBook.getName();
        Long genreId = updatedBook.getGenreId();
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", genreId)));
        return bookRepository.save(new Book(id, author, name, genre));
    }

    @DeleteMapping(value = "{id}")
    public void deleteBookById(@PathVariable(value = "id") Long id) {
        bookRepository.deleteById(id);
    }
}
