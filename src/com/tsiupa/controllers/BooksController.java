package com.tsiupa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tsiupa.dao.*;
import com.tsiupa.models.Book;



@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDao bookDAO;

    @Autowired
    public BooksController(BookDao bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }
    
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
    	return "books/new";
    }

    @PostMapping("/new/add")
    public String create(@ModelAttribute("book") Book book) {
    	bookDAO.save(book);
        return "redirect:/books";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
    	bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
    	bookDAO.delete(id);
        return "redirect:/books";
    }
}

