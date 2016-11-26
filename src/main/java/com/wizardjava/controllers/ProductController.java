package com.wizardjava.controllers;

import com.wizardjava.entity.Category;
import com.wizardjava.entity.Product;
import com.wizardjava.models.Message;
import com.wizardjava.services.CategoryService;
import com.wizardjava.services.ProductService;
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
@RequestMapping({"admin/products"})
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;

    public final int recordsPerPage = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String listProducts(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer recordsPerPage,
                               @RequestParam(required = false) Long idCategoryFilter,
                               @ModelAttribute("message") Message message, ModelMap model) {
        long countProducts = productService.getCountProducts(idCategoryFilter);
        if(recordsPerPage == null || recordsPerPage < 1 || recordsPerPage > countProducts) recordsPerPage = this.recordsPerPage;
        int countPage = PageNavigation.getPageCount(countProducts, recordsPerPage);
        if(page == null || page < 1 || page > countPage) page = 1;
        int offset = (page > 1) ? recordsPerPage*(page - 1) : 0;

        List<Product> products = productService.getProductsWithPagination(offset, recordsPerPage, idCategoryFilter);
        model.addAttribute("products", products);
        model.addAttribute("message", message);
        model.addAttribute("page", page);
        model.addAttribute("countPage", countPage);
        return "products-list";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newProduct(ModelMap model, RedirectAttributes redirectAttributes, Locale locale) {
        List<Category> categories = categoryService.getAllCategories();
        if(categories.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.is_empty", null, locale), Message.Type.DANGER));
            return "redirect:/categories/";
        }

        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        model.addAttribute("edit", false);
        return "product-add";
    }

    @RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
    public String editProduct(@PathVariable Long id, ModelMap model, RedirectAttributes redirectAttributes, Locale locale) {
        List<Category> categories = categoryService.getAllCategories();
        if(categories.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.is_empty", null, locale), Message.Type.DANGER));
            return "redirect:/categories/";
        }

        Product product = productService.getById(id);
        if(product == null) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.not_exist", null, locale), Message.Type.DANGER));
            return "redirect:/products/";
        }

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("edit", true);
        return "product-add";
    }

    @RequestMapping(value = { "/edit/{idProduct}" }, method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Locale locale, ModelMap model, RedirectAttributes redirectAttributes) {
        List<Category> categories = categoryService.getAllCategories();
        if(categories.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.is_empty", null, locale), Message.Type.DANGER));
            return "redirect:/categories/";
        }

        model.addAttribute("categories", categories);
          if (result.hasErrors()) {
            return "product-add";
        }

        productService.updateProduct(product);

        redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.updated", new Object[] {product.getName()}, locale) ));
        return "redirect:/products";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, ModelMap model, Locale locale, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "product-add";
        }

        productService.saveProduct(product);

        redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.created", new Object[]{product.getName()}, locale)));
        return "redirect:/products/list";
    }

    @RequestMapping(value = { "/delete/{idProduct}" }, method = RequestMethod.GET)
    public String deleteProduct(Locale locale, @PathVariable Long idProduct, RedirectAttributes redirectAttributes) {
        Product product = productService.getById(idProduct);
        if(product != null) {
            productService.deleteProductById(idProduct);
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.deleted", new Object[] {product.getName()}, locale)));
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.not_exist", new Object[]{idProduct}, locale), Message.Type.DANGER));
        }

        return "redirect:/products/list";
    }
}
