package com.wizardjava.controllers.admin;

import com.wizardjava.entities.Category;
import com.wizardjava.entities.Image;
import com.wizardjava.entities.Product;
import com.wizardjava.models.FileBucket;
import com.wizardjava.models.Message;
import com.wizardjava.services.CategoryService;
import com.wizardjava.services.ProductService;
import com.wizardjava.utils.ImagesUtils;
import com.wizardjava.utils.PageNavigation;
import com.wizardjava.validators.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping({"admin/products"})
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ImageValidator imageValidator;

    public final int recordsPerPage = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String listProducts(@RequestParam(required = false) Integer page,
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
        return "products-list";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newProduct(ModelMap model, RedirectAttributes redirectAttributes, Locale locale) {
        List<Category> categories = categoryService.getAllCategories();
        if(categories.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("categories.is_empty", null, locale), Message.Type.DANGER));
            return "redirect:/admin/categories/";
        }

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
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
            return "redirect:/admin/categories/";
        }

        Product product = productService.getById(id);
        if(product == null) {
            redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.not_exist", null, locale), Message.Type.DANGER));
            return "redirect:/admin/products/";
        }
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
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
            return "redirect:/admin/categories/";
        }

        model.addAttribute("categories", categories);
          if (result.hasErrors()) {
            return "product-add";
        }

        productService.updateProduct(product);

        redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.updated", new Object[] {product.getName()}, locale) ));
        return "redirect:/admin/products";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult result,
                              @Valid @ModelAttribute FileBucket fileBucket, BindingResult resultFile,
                              ModelMap model, Locale locale, RedirectAttributes redirectAttributes) throws IOException {

        imageValidator.validate(fileBucket, resultFile);

        if (result.hasErrors() || resultFile.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("fileBucket", fileBucket);
            model.addAttribute("categories", categories);
            return "product-add";
        }

        saveImage(fileBucket, product);

        redirectAttributes.addFlashAttribute("message", new Message(messageSource.getMessage("product.created", new Object[]{product.getName()}, locale)));
        return "redirect:/admin/products/";
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

        return "redirect:/admin/products/";
    }

    private void saveImage(FileBucket fileBucket, Product product) throws IOException {

        Image image = new Image();

        MultipartFile multipartFile = fileBucket.getFile();

        image.setName(multipartFile.getOriginalFilename());
        image.setDescription(fileBucket.getDescriptionFile());
        image.setAlt(fileBucket.getAlt());
        image.setContent(multipartFile.getBytes());
        image.setProduct(product);
        List<Image> images = new ArrayList<Image>();
        images.add(image);
        product.setImages(images);
        productService.saveProduct(product);
    }
}
