package ru.romanov.modulefive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanov.modulefive.page.BookPagesController;
import ru.romanov.modulefive.security.UserDetailServiceImpl;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookPagesController.class)
public class BookPagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailServiceImpl userDetailService;

    @MockBean
    private DataSource dataSource;

    @WithMockUser(username = "petrov", roles = "USER")
    @Test
    public void showAllBooksTest() throws Exception {
        mockMvc.perform(get("/books/getAll"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "ivanov", roles = "ADMIN")
    @Test
    public void showAddBookPageTest() throws Exception {
        mockMvc.perform(get("/books/add"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "ivanov", roles = "ADMIN")
    @Test
    public void showEditBookPageTest() throws Exception {
        mockMvc.perform(get("/books/edit").param("id", "1"))
                .andExpect(status().isOk());
    }
}
