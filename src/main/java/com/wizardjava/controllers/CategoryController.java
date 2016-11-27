package com.wizardjava.controllers;

import com.wizardjava.entity.Category;
import com.wizardjava.models.Message;
import com.wizardjava.services.CategoryService;
import com.wizardjava.utils.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping({"admin/categories"})
public class CategoryController {

    public final int recordsPerPage = 3;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String listCategories(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer recordsPerPage, @ModelAttribute("message") Message message, ModelMap model) {
        long countProducts = categoryService.getCountCategories();
        if(recordsPerPage == null || recordsPerPage < 1 || recordsPerPage > countProducts) recordsPerPage = this.recordsPerPage;
        int countPage = PageNavigation.getPageCount(countProducts, recordsPerPage);
        if(page == null || page < 1 || page > countPage) page = 1;
        int offset = (page > 1) ? recordsPerPage*(page - 1) : 0;

        List<Category> categories = categoryService.getCategoriesWithPagination(offset, recordsPerPage);
        model.addAttribute("categories", categories);
        model.addAttribute("message", message);
        model.addAttribute("page", page);
        model.addAttribute("countPage", countPage);
        return "categories-list";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newCategory(ModelMap model) {
        model.addAttribute("category", new Category());
        model.addAttribute("edit", false);
        return "category-add";
    }

    @RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
    public String editCategory(@PathVariable Long id, ModelMap model, RedirectAttributes redirectAttributes, Locale locale) {
        Category category = categoryService.getById(id);
        if(category == null) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.not_exist", null, locale), Message.Type.DANGER));
            return "redirect:/admin/categories/";
        }


        model.addAttribute("category", category);
        model.addAttribute("edit", true);
        return "category-add";
    }

    @RequestMapping(value = { "/edit/{idCategory}" }, method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute("category") @Valid Category category, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {

        if (result.hasErrors()) {
            return "category-add";
        }

        categoryService.updateCategory(category);

        redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.updated", new Object[]{category.getName()}, locale)));
        return "redirect:/admin/categories/";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("category") @Valid Category category, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {

        if (result.hasErrors()) {
            return "category-add";
        }

        categoryService.saveCategory(category);

        redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.created", new Object[]{category.getName()}, locale)));
        return "redirect:/admin/categories/";
    }

    @RequestMapping(value = { "/delete/{idCategory}" }, method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Long idCategory, RedirectAttributes redirectAttributes, Locale locale) {
        Category category = categoryService.getById(idCategory);
        if(category != null) {
            categoryService.deleteCategoryById(idCategory);
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.deleted", new Object[]{category.getName()}, locale)));
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.not_exist", new Object[]{idCategory}, locale), Message.Type.DANGER));
        }

        return "redirect:/admin/categories/";
    }
}
