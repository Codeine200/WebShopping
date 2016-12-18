package com.wizardjava.controllers.admin;

import com.wizardjava.entities.Product;
import com.wizardjava.models.Message;
import com.wizardjava.services.ProductService;
import com.wizardjava.utils.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    public final int recordsPerPage = 10;

    @Autowired
    ProductService productService;

    @RequestMapping(value={"/", ""}, method = RequestMethod.GET)
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

}
