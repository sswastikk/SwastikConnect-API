package com.software.swastik.project.swastik.connect.services;


import com.software.swastik.project.swastik.connect.payloads.CategoryDto;
import java.util.List;

public interface CategoryService
{
    //Create a new Category
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update an existing category
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    //Delete Category
    void deleteCategory(Long categoryId);

    //Get Category Details By Id
    CategoryDto getCategoryById(Long categoryId);

    //Get All Categories
    List<CategoryDto> getAllCategories();
}
