package com.example.demo.services.category;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryById(long id);
    List<Category> getAllCategory();
   Category updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
