package com.software.swastik.project.swastik.connect.controllers;

import com.software.swastik.project.swastik.connect.payloads.ApiResponse;
import com.software.swastik.project.swastik.connect.payloads.CategoryDto;
import com.software.swastik.project.swastik.connect.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    //Create a new category through POST Call
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createNewCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    //Update a Category through PUT Call
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateExistingCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Long categoryId)
    {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //Delete a Category through DELETE call
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity(new ApiResponse("Category Deleted Successfully", true, "Ok"), HttpStatus.OK);
    }

    //Get a Single Category through GET Call
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    //Get All categories through GET Call
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId") Long categoryId)
    {
        return new ResponseEntity<>(this.categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }
}