package ru.romanov.modulefive.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/genres"})
public class GenrePagesController {

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showAllGenres(Model model) {
        return "allGenres";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddGenrePage(Model model) {
        return "addGenre";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditGenrePage(Model model, @RequestParam(value = "id") Long id) {
        model.addAttribute("id", id);
        return "editGenre";
    }
}
