package com.catalog.ecommerce.controller;

import com.catalog.ecommerce.entity.Category;
import com.catalog.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category create(@RequestBody final Category category,
                           @RequestParam(required = false) final Long parentId) {
        return categoryService.create(category, parentId);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable final Long id,
                           @RequestBody final Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        categoryService.delete(id);
    }
}
