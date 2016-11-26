package com.wizardjava.dao;


import com.wizardjava.entity.Product;

import java.util.List;

public interface ProductDao {
    Product getById(Long id);
    void saveProduct(Product product);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getProductsWithPagination(int offset, int recordPerPage);
    List<Product> getProductsWithPaginationByCategoryFilter(int offset, int recordPerPage, Long idCategory);
    long getCountProducts();
    long getCountProductsByCategoryFilter(Long idCategory);
    void deleteProductById(Long id);
}
