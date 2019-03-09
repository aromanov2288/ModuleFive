package ru.romanov.modulefive.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/books"})
public class BookPagesController {

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        return "allBooks";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddBookPage(Model model) {
        return "addBook";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditBookPage(Model model, @RequestParam(value = "id") Long id) {
        model.addAttribute("id", id);
        return "editBook";
    }
}
