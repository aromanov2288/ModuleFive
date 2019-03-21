package ru.romanov.modulefive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanov.modulefive.domain.Book;
import ru.romanov.modulefive.domain.Genre;
import ru.romanov.modulefive.form.NewBook;
import ru.romanov.modulefive.repository.BookRepository;
import ru.romanov.modulefive.repository.GenreRepository;
import ru.romanov.modulefive.rest.BookRestController;
import ru.romanov.modulefive.security.UserDetailServiceImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private UserDetailServiceImpl userDetailService;

    @MockBean
    private DataSource dataSource;

    @WithMockUser(username = "petrov", roles = "USER")
    @Test
    public void getAllTest() throws Exception {
        Genre genre = new Genre(1L, "Genre1");
        Book book1 = new Book(1L, "Pushkin", "Name1", genre);
        Book book2 = new Book(2L, "Lermontov", "Name2", genre);
        List<Book> booksList = new ArrayList<>(Arrays.asList(book1, book2));
        when(bookRepository.findAll()).thenReturn(booksList);
        String jsonResponse = "[{\"id\":1,\"author\":\"Pushkin\",\"name\":\"Name1\",\"genre\":{\"id\":1,\"name\":\"Genre1\"}},"
                + "{\"id\":2,\"author\":\"Lermontov\",\"name\":\"Name2\",\"genre\":{\"id\":1,\"name\":\"Genre1\"}}]";

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(jsonResponse));
    }

    @WithMockUser(username = "ivanov", roles = "ADMIN")
    @Test
    public void addBookTest() throws Exception {
        Long genreId = 1L;
        NewBook newBook = new NewBook("Pushkin", "BookName", genreId);
        Genre genre = new Genre(genreId, "Genre");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));
        when(bookRepository.save(new Book("Pushkin", "BookName", genre)))
                .thenReturn(new Book(1L, "Pushkin", "BookName", genre));
        String jsonRequest = "{\"author\":\"Pushkin\",\"name\":\"BookName\",\"genreId\":1}";
        String jsonResponse = "{\"id\":1,\"author\":\"Pushkin\",\"name\":\"BookName\",\"genre\":{\"id\":1,\"name\":\"Genre\"}}";

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(jsonResponse));
    }

    @WithMockUser(username = "ivanov", roles = "ADMIN")
    @Test
    public void getByIdTest() throws Exception {
        Genre genre = new Genre(1L, "Genre1");
        Book book = new Book(1L, "Pushkin", "Name1", genre);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        String jsonResponse = "{\"id\":1,\"author\":\"Pushkin\",\"name\":\"Name1\",\"genre\":{\"id\":1,\"name\":\"Genre1\"}}";

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(jsonResponse));
    }

    @WithMockUser(username = "ivanov", roles = "ADMIN")
    @Test
    public void UpdateBookTest() throws Exception {
        Long genreId = 1L;
        Genre genre = new Genre(genreId, "Genre");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));
        when(bookRepository.save(new Book(genreId, "Pushkin", "BookName", genre)))
                .thenReturn(new Book(genreId, "Pushkin", "BookName", genre));
        String jsonRequest = "{\"id\":1,\"author\":\"Pushkin\",\"name\":\"BookName\",\"genreId\":1}";
        String jsonResponse = "{\"id\":1,\"author\":\"Pushkin\",\"name\":\"BookName\",\"genre\":{\"id\":1,\"name\":\"Genre\"}}";

        mockMvc.perform(put("/api/books").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(jsonResponse));
    }

    @WithMockUser(username = "ivanov", roles = "ADMIN")
    @Test
    public void deleteBookById() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
        verify(bookRepository).deleteById(1L);
    }
}
