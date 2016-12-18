package com.wizardjava.services;

import com.wizardjava.entities.Product;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    void saveProduct(Product product);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductsWithPagination(int offset, int recordPerPage, Long idCategory);
    long getCountProducts(Long idCategory);
    void deleteProductById(Long id);
}
