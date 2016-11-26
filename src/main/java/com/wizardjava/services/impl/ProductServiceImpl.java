package com.wizardjava.services.impl;

import com.wizardjava.dao.ProductDao;
import com.wizardjava.entity.Product;
import com.wizardjava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public Product getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveProduct(Product product) {
        dao.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        dao.updateProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    @Override
    public List<Product> getProductsWithPagination(int offset, int recordPerPage, Long idCategory) {
        if(idCategory == null) {
            return dao.getProductsWithPagination(offset, recordPerPage);
        } else {
            return dao.getProductsWithPaginationByCategoryFilter(offset, recordPerPage, idCategory);
        }
    }

    @Override
    public long getCountProducts(Long idCategory) {
        if (idCategory == null) {
            return dao.getCountProducts();
        } else {
            return dao.getCountProductsByCategoryFilter(idCategory);
        }
    }

    @Override
    public void deleteProductById(Long id) {
        dao.deleteProductById(id);
    }
}
