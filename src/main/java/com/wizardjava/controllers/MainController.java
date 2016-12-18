package com.wizardjava.controllers;

import com.wizardjava.entities.Image;
import com.wizardjava.entities.Product;
import com.wizardjava.models.Message;
import com.wizardjava.services.ProductService;
import com.wizardjava.utils.ImagesUtils;
import com.wizardjava.utils.PageNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    public final int recordsPerPage = 10;

    @Autowired
    ProductService productService;

    @RequestMapping(value={"/", ""}, method = RequestMethod.GET)
    public @ResponseBody
    List<Product> listProducts(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer recordsPerPage,
                               @RequestParam(required = false) Long idCategoryFilter,
                               @ModelAttribute("message") Message message, ModelMap model) throws UnsupportedEncodingException {
        long countProducts = productService.getCountProducts(idCategoryFilter);
        if(recordsPerPage == null || recordsPerPage < 1 || recordsPerPage > countProducts) recordsPerPage = this.recordsPerPage;
        int countPage = PageNavigation.getPageCount(countProducts, recordsPerPage);
        if(page == null || page < 1 || page > countPage) page = 1;
        int offset = (page > 1) ? recordsPerPage*(page - 1) : 0;

        List<Product> products = productService.getProductsWithPagination(offset, recordsPerPage, idCategoryFilter);
        List<String> images = new ArrayList<String>();
        Map<Long, List<String>> imagesProduct = new HashMap<Long, List<String>>();
        if(products != null && !products.isEmpty()) {
            for(Product product : products) {
                if(!product.getImages().isEmpty()) {
                    images.add(ImagesUtils.base64Encoded(product.getImages().get(0).getContent()));
                    imagesProduct.put(product.getId(), images);
                }
            }
        }
        model.addAttribute("images", imagesProduct);
        model.addAttribute("products", products);
        model.addAttribute("message", message);
        model.addAttribute("page", page);
        model.addAttribute("countPage", countPage);

        return products;
    }

}
