package com.wizardjava.dao;

import com.wizardjava.entities.Category;

import java.util.List;

public interface CategoryDao {
    Category getById(Long id);
    void saveCategory(Category category);
    void updateCategory(Category category);
    List<Category> getAllCategories();
    List<Category> getCategoriesWithPagination(int offset, int recordPerPage);
    long getCountCategories();
    void deleteCategoryById(long id);
}
