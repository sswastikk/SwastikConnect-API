package com.software.swastik.project.swastik.connect.services.implementations;

import com.software.swastik.project.swastik.connect.entities.Category;
import com.software.swastik.project.swastik.connect.exceptions.ResourceNotFoundException;
import com.software.swastik.project.swastik.connect.payloads.CategoryDto;
import com.software.swastik.project.swastik.connect.repositories.CategoryRepository;
import com.software.swastik.project.swastik.connect.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
        Category category = this.dtoToCategory(categoryDto);
        Date createdOn = new Date();
        category.setCreatedOn(createdOn);
        category.setModifiedOn(createdOn);
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        this.categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    //Conversion Method CategoryDto to Category
    public Category dtoToCategory(CategoryDto categoryDto)
    {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }

    //Conversion Method Category to CategoryDto
    public CategoryDto categoryToDto(Category category)
    {
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return  categoryDto;
    }
}
