package com.wizardjava.services;

import com.wizardjava.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);
    void saveCategory(Category category);
    void updateCategory(Category category);
    List<Category> getAllCategories();
    List<Category> getCategoriesWithPagination(int offset, int recordPerPage);
    long getCountCategories();
    void deleteCategoryById(long id);
}
