package com.wizardjava.services.impl;

import com.wizardjava.dao.CategoryDao;
import com.wizardjava.entity.Category;
import com.wizardjava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao dao;

    @Override
    public Category getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveCategory(Category category) {
        dao.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        dao.updateCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }

    @Override
    public List<Category> getCategoriesWithPagination(int offset, int recordPerPage) {
        return dao.getCategoriesWithPagination(offset, recordPerPage);
    }

    @Override
    public long getCountCategories() {
        return dao.getCountCategories();
    }

    @Override
    public void deleteCategoryById(long id) {
        dao.deleteCategoryById(id);
    }

}
